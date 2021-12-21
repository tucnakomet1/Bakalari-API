# BAKALÁŘI - JAVA API

```java
(ArrayList<String>) GetList.getCities(); // seznam všech měst v databázi Bakalářů
(ArrayList<ArrayList<String>>) GetSpecificCity.getSchools(city); // seznam všech škol v konkrétním městě (včetně URL)
(ArrayList<String>) LogIn.getData(username, password, school_url); // přihlásí uživatele a získá všechna potřebná data
// (access_token, refresh_token, expires_in, token_type, ApiVersion, AppVersion, UserId, scope)
```