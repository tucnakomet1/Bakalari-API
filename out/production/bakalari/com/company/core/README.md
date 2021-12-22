
- [Města](#mesta)
- [Školy](#skoly)
- [Přihlášení](#prihlaseni)

# Města

Třída `City` pro výčet všech měst, které podporují Bakaláře.

| Argument | Typ | Popis |
| --- | --- | --- |
| `getCities()` | `ArrayList<String>` | Vrátí seznam všech měst. |

## Příklad

### Vstup

```java
// [...]

ArrayList<String> cities = City.getCities();

for (String city: cities) {
    System.out.println(city);
}

// [...]
```

### Výstup

```
Albrechtice
Albrechtice v Jizerských horách
Aš

[...]

Žirovnice
Žiželice
Žulová
```

# Školy

Třída `School` pro výčet škol daného města.

| Argument | Typ | Popis |
| --- | --- | --- |
| `getSchools(String city)` | `ArrayList<ArrayList<String>>` | Vrátí seznam názvů a URL adres všech škol daného města. |

## Příklad

### Vstup

```java
// [...]

ArrayList<ArrayList<String>> schools = School.getSchools("Albrechtice");

for (int i = 0; i < schools.get(0).size(); i++) {
    System.out.println("Název školy: "
        + schools.get(0).get(i));
    
    System.out.println("Adresa URL školy: "
        schools.get(1).get(i) + "\n");
}

// [...]
```

### Výstup

```
Název školy: Základní škola a mateřská škola, Albrechtice v Jizerských horách, příspěvková organizace
Adresa URL školy: https://zsalbrechtice.bakalari.cz

Název školy: ZŠ a MŠ s polským jazykem vyučovací,Albrechtice,Školní 11
Adresa URL školy: https://zsamsalbrechtice.bakalari.cz
```

# Přihlášení

Nejdůležitější třída (`LogIn`), slouží k přihlášení uživatele do systému Bakaláři.

| Argument | Typ | Popis |
| --- | --- | --- |
| `getData(String username, String password, String url)` | `ArrayList<String>` | Vrátí seznam všech potřebných získaných dat. Seznam obsahuje 8 prvků. |

**Výčet prvků seznamu `getData().get(i)` pro index `i`:**
- `i=0`: `access_token` - ***nedjůležitější argument***, token, díky kterému jsme dál schopni pracovat, typ `at+jwt`; 
- `i=1`: `refresh_token` - typ `oi_reft+jwt`
- `i=2`: `expires_in` - udává v sekundách délku platnosti tokenu
- `i=3`: `token_type` - `"Bearer"`
- `i=4`: `ApiVersion`
- `i=5`: `AppVersion`
- `i=6`: `UserId`
- `i=7`: `scope`

## Příklad

### Vstup

```java
// [...]

String school_url = "https://gymjbc.bakalari.cz/";
ArrayList<String> login = LogIn.getData("username", "password", school_url);
String token = login.get(0);

System.out.println(token);

// [...]
```

### Výstup

> eyJhbGciOiJSUzI1NiIsImtpZCI6InVzc19kUTFCNmViZ1ZrY2h1NFFmSmciLCJ0eXAiOiJhdCtqd3QifQ.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IlpHU1pYMiIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiI0LkEsIFZlbGnEjWthIEthcmVsIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvdXBuIjoiVmVsaWMyMjE5MCIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2dlbmRlciI6Ik1hbGUiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9kYXRlb2ZiaXJ0aCI6IjIwMDMtMDEtMDRUMDA6MDA6MDAiLCJCYWthbGFyaS5Vc2VyVHlwZSI6IloiLCJCYWthbGFyaS5TZXNzaW9uSWQiOiI3Yzg3NDBjNjY4ZGQ0OGJiOTcyNTZhZWRkMGE5YTE4ZiIsInN1YiI6IlpHU1pYMiIsIm5hbWUiOiI0LkEsIFZlbGnEjWthIEthcmVsIiwiYXVkIjoiYmFrYWxhcmlfYXBpIiwib2lfcHJzdCI6IkFORFIiLCJvaV9hdV9pZCI6ImY5NTBmYjA4YjA1NjQ5Y2M5Y2E2NzQ0ZTdkYzQ3OTBjIiwiY2xpZW50X2lkIjoiQU5EUiIsIm9pX3Rrbl9pZCI6ImQ1ZmE0Yzk5Njg3ODQyMmM5NWYzZTFmYWVhYjA4OTNlIiwic2NvcGUiOiJvZmZsaW5lX2FjY2VzcyBiYWthbGFyaV9hcGkiLCJleHAiOjE2NDAyMDk0NzksImlzcyI6Imh0dHBzOi8vZ3ltamJjLmJha2FsYXJpLmN6LyIsImlhdCI6MTY0MDIwNTg3OX0.I0XkWqjSxJKZmHn5X90b52VPK6Wlnpf-887SEGFNA7wZp7I5LPg2xc3AbqcvYKAHm4eu9-GNbEGlpRgJdKjVfqMSpnGveCBtrXbKzJaLaMrfbWj5dYaRF6-XoMvpAostqsa3mTPhvwVbnuQW1-eHUGDdi1Ab8dRt8HPrqY2Pn526BT_Vp8AUQOjuh0PtqbOyc0uNHS2FmZwt3PDZMIrJSaVW07N757QoCtTDvxGhnzcS8C4LlmQ14nqo9D-Dd-WZnNx3OCAZwqnMRgPLsSFQlO8lO3AWA4vHv0UJapWikKr8bdHNAuCVhAg8FNlKN9mlA__ltGQcByrqp6-us_qI3g