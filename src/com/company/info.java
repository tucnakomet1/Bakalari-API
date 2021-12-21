package com.company;

public class info {
    public static String ApiVersion = "";
    public static String AppVersion = "";
    public static String UserId = "";
    public static String access_token = "";
    public static String token_type = "";
    public static String expires_in = "";
    public static String scope = "";
    public static String refresh_token = "";

    public static String getApiVersion() {
        return ApiVersion;
    }

    public static void setApiVersion(String apiVersion) {
        ApiVersion = apiVersion;
    }

    public static String getAppVersion() {
        return AppVersion;
    }

    public static void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public static String getUserId() {
        return UserId;
    }

    public static void setUserId(String userId) {
        UserId = userId;
    }

    public static String getAccess_token() {
        return access_token;
    }

    public static void setAccess_token(String access_token) {
        info.access_token = access_token;
    }

    public static String getToken_type() {
        return token_type;
    }

    public static void setToken_type(String token_type) {
        info.token_type = token_type;
    }

    public static String getExpires_in() {
        return expires_in;
    }

    public static void setExpires_in(String expires_in) {
        info.expires_in = expires_in;
    }

    public static String getScope() {
        return scope;
    }

    public static void setScope(String scope) {
        info.scope = scope;
    }

    public static String getRefresh_token() {
        return refresh_token;
    }

    public static void setRefresh_token(String refresh_token) {
        info.refresh_token = refresh_token;
    }
}
