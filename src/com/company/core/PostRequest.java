package com.company.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest {
    public static String output = null;

    public static String get_request(URL school_url, String data, String token, boolean post) throws IOException {
        output = null;
        // making HttpURLConnection
        HttpURLConnection httpURLConnection = (HttpURLConnection)school_url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("charset", "UTF-8");

        // if token isn't null, make request for authorization
        if(token!=null) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        }

        // send POST/ GET request
        if (post) {
            httpURLConnection.setRequestMethod("POST");
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        httpURLConnection.setUseCaches(false);
        if(data!=null){
            httpURLConnection.setRequestProperty("Content-Length", "" + data.length());
            try(OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream())) {
                out.write(data);
            }
        }

        // read input stream
        try(BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))){
            String currentLine;
            while((currentLine = in.readLine()) != null){
                output += currentLine;
            }
        }

        output = output.substring(4);

        return output;
    }
}
