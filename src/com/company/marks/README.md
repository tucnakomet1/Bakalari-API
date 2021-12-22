# Známky

Třída `GetMarks` pro získání předmětů a jejich známek.

*Nejprve je třeba se přihlásit - viz. [LogIn](../core/README.md#prihlaseni)*

| Argument | Typ | Popis |
| --- | --- | --- |
| `get_json()` | `String` | Vrátí originální JSON. |
| `get_mark_date()` | `ArrayList<ArrayList<String>>` | Vrátí seznam všech dat přidání všech známek odpovídajících danému předmětu. |
| `get_edit_date()` | `ArrayList<ArrayList<String>>` | Vrátí seznam všech dat úprav všech známek odpovídajících danému předmětu. |
| `get_caption()` | `ArrayList<ArrayList<String>>` | Vrátí seznam všech nadpisů známek odpovídajících danému předmětu |
| `get_marks()` | `ArrayList<ArrayList<Float>>` | Vrátí seznam všech známek odpovídajících danému předmětu. |
| `get_weight()` | `ArrayList<ArrayList<Integer>>` | Vrátí seznam všech vah známek odpovídajících danému předmětu.|
| `get_points()` | `ArrayList<Boolean>` | Vrátí seznam závislý na předmětech → `true` = bodování; `false` = známkování.|
| `get_subject(boolean)` | `ArrayList<String>` | Vrátí seznam všech předmětů (`true` = název předmětu; `false` = zkratka předmětu |
| `get_average()` | `ArrayList<Float>` | Vrátí seznam všech průměrů známek daných předmětů. |

***Počet prvků uvnitř všech seznamů (`get_mark_date()` až `get_average()`) se rovná.*** <br>
***Počet prvků uvnitř všech podseznamů (`get_mark_date().get(i)` až `get_weight().get(i)`) se taktéž rovná.***


## Příklad

### Vstup

```java
// [...]

ArrayList<String> userInfo = LogIn.getData("username", "password", school);

new GetMarks(school, userInfo.get(0)).getInstance();

for (int i = 0; i < GetMarks.get_mark_date().size(); i++) {
    String prumer = "\n---\n\nCelkový průměr známek z předmětu: "
        + GetMarks.get_subject(true).get(i) + " ("
        + GetMarks.get_subject(false).get(i) + ") je "
        + GetMarks.get_average().get(i);
    
    if (!GetMarks.get_points().get(i)) {
        System.out.println(prumer);
    } else {
        System.out.println(prumer + " %");
    }

    for (int j = 0; j < GetMarks.get_mark_date().get(i).size(); j++) {
        System.out.println("\nZnámka: "
            + GetMarks.get_marks().get(i).get(j) + " (váha: "
            + GetMarks.get_weight().get(i).get(j) + ")" );
        
        System.out.println("Téma: " + GetMarks.get_caption().get(i).get(j));
        System.out.println("Datum přidání známky: " + GetMarks.get_mark_date().get(i).get(j));
        System.out.println("Datum poslední úpravy: " + GetMarks.get_edit_date().get(i).get(j));
    }
}

// [...]
```

### Výstup

```
[...]

Celkový průměr známek z předmětu: Fyzika (F) je 76.81 %

Známka: 13.0 (váha: 17)
Téma: Optické zobrazování
Datum přidání známky: 2021-12-17T00:00:00+01:00
Datum poslední úpravy: 2021-12-17T06:56:00+01:00

[...]

Celkový průměr známek z předmětu: Konverzace v anglickém jazyce (Ak2) je 1.36

Známka: 1.0 (váha: 2)
Téma: education
Datum přidání známky: 2021-10-11T00:00:00+02:00
Datum poslední úpravy: 2021-11-09T12:19:00+01:00

[...]
```