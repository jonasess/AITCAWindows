package sample;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

public class Estaplish {
    Class clazz;
    URL url;
    public Estaplish(Class c, URL u) {
        this.clazz=c;
        this.url=u;
    }
    public HttpsURLConnection conn(){
        try{
        HttpsURLConnection conn = (HttpsURLConnection) this.url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestMethod("POST");
        conn.setSSLSocketFactory(getssl().getSocketFactory());
        conn.setDoOutput(true);
        return conn;
        }catch(Exception e){
            System.out.println(e.toString()+"https");
            return null;
        }
    }
    public SSLContext getssl(){
        SSLContext context=null;
        try {
            // GeSystem.out.println(e.toString()t an instance of the Bouncy Castle KeyStore format
            KeyStore trusted = KeyStore.getInstance("BKS");
            // Get the raw resource, which contains the keystore with
            // your trusted certificates (root and any intermediate certs)
            InputStream in1 = clazz.getResourceAsStream("../resources/mystore.bks");
            System.out.println(in1.toString());

            try {
                // Initialize the keystore with the provided trusted certificates
                // Provide the password of the keystore
                trusted.load(in1, "ez24get".toCharArray());
            } finally {
                in1.close();
            }

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(trusted);

            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

        }catch (Exception e){
            System.out.println(e.toString()+"getssl");
        }

        return context;
    }
}
