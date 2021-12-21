package com.company.get_school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest {
    public static String output;

    public static String get_request(URL school_url, String data, String token, boolean post) throws IOException {
        output=null;

        HttpURLConnection conn = (HttpURLConnection)school_url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "UTF-8");
        if(token!=null) {
            conn.setRequestProperty("Authorization", "Bearer " + token);
        }
        if (post) {
            conn.setRequestMethod("POST");
            System.out.println("POST:" + conn);
        } else {
            conn.setRequestMethod("GET");
            System.out.println("GET:" + conn);
        }
        conn.setUseCaches(false);
        if(data!=null){
            conn.setRequestProperty("Content-Length", "" + data.length());
            try(OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
                out.write(data);
            }
        }

        // Read input stream
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
            String currentLine;
            while((currentLine = in.readLine()) != null){
                output += currentLine;
            }
        }

        output = output.substring(4);

        return output;
    }
}
