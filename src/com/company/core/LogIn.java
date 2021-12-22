package com.company.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LogIn {
    public static String username, password, school_url;

    public LogIn() {
        ArrayList<String> userInfo = LogIn.getData(username, password, school_url);
        for (String user : userInfo) {
            System.out.println(user);
        }
    }

    public static ArrayList<String> getData(String username, String password, String school_url) {
        ArrayList<String> user_data = new ArrayList<>();

        try {
            URL new_school_url = new URL(school_url + "api/login");
            String data = "client_id=ANDR&grant_type=password&username=" + username + "&password=" + password;

            // login
            String req = PostRequest.get_request(new_school_url, data, null, true);

            // get JSON values
            JSONObject obj = new JSONObject(req);

            String ApiVersion = obj.getString("bak:ApiVersion");
            String AppVersion = obj.getString("bak:AppVersion");
            String UserId = obj.getString("bak:UserId");
            String access_token = obj.getString("access_token");
            String token_type = obj.getString("token_type");
            String expires_in = obj.getString("expires_in");
            String scope = obj.getString("scope");
            String refresh_token = obj.getString("refresh_token");

            user_data.add(access_token);
            user_data.add(refresh_token);
            user_data.add(expires_in);
            user_data.add(token_type);
            user_data.add(ApiVersion);
            user_data.add(AppVersion);
            user_data.add(UserId);
            user_data.add(scope);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return user_data;
    }
}