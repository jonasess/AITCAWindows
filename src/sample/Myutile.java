package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Myutile {
    public static JSONObject makejsonobj(String ...params){
        Map<String, Object> param = new HashMap<>();
        param.put("email",params[0]);
        param.put("password",params[1]);
        JSONObject objj = new JSONObject(param);
        return objj;
    }
    public static boolean emptyfield(String ...fileds){
        for(int i=0;i<fileds.length;i++)
            if(fileds[0].isEmpty()) return false;
        return true;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static JSONObject getResault(HttpsURLConnection conn,JSONObject json){
        try {
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            //writeStream(out);
            out.write(json.toString().getBytes("UTF-8"));
            out.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result1 = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            JSONObject jsonObject = new JSONObject(result1);
            System.out.println(jsonObject.toString());
            in.close();
            conn.disconnect();
            return jsonObject;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
