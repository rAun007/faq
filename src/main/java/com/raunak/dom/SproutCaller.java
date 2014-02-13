package com.raunak.dom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

public class SproutCaller {

    public static void main(String[] args) throws HttpException, IOException {

        HttpClient client = new HttpClient();
        
        client.getParams().setAuthenticationPreemptive(true);
        client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("metadataapiuser@inmobi.com", "abc123"));
        GetMethod getMethod = new GetMethod("http://studio.inmobi.com/service/api/creative/source/GXHtBUuxd3nrMS28");

        int status = client.executeMethod(getMethod);

        InflaterInputStream inflaterInputStream = new InflaterInputStream(getMethod.getResponseBodyAsStream());

        ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
        int b;
        while ((b = inflaterInputStream.read()) != -1) {
            bout.write(b);
        }
        inflaterInputStream.close();
        bout.close();
        String s = new String(bout.toByteArray());
        System.out.println("-------------------------");
        System.out.print(s);
    }
}
