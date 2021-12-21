package com.company.get_school;

import com.company.info;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetUserInfo {

    public GetUserInfo(String username, String password, String school_url) {
        try {
            URL new_school_url = new URL(school_url + "/api/login");

            System.out.println(new_school_url);

            String data = "client_id=ANDR&grant_type=password&username=" + username + "&password=" + password;
            //data = "client_id=ANDR&grant_type=refresh_token&refresh_token="+refreshToken;

            // login
            String got = PostRequest.get_request(new_school_url, data, null, true);
            System.out.println(got);

            // get separated values and "save" them
            JSONObject obj = new JSONObject(got);
            info.setApiVersion(obj.getString("bak:ApiVersion"));
            info.setAppVersion(obj.getString("bak:AppVersion"));
            info.setUserId(obj.getString("bak:UserId"));
            info.setAccess_token(obj.getString("access_token"));
            info.setToken_type(obj.getString("token_type"));
            info.setExpires_in(obj.getString("expires_in"));
            info.setScope(obj.getString("scope"));
            info.setRefresh_token(obj.getString("refresh_token"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Wrong login or no internet!");
            System.exit(0);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}