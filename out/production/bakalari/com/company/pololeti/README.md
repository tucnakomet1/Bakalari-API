# Pololetní vysvědčení

Třída `Certificate` pro získání předmětů a jejich známek.

*Nejprve je třeba se přihlásit - viz. [LogIn](../core/README.md#prihlaseni)*

| Argument | Typ | Popis |
| --- | --- | --- |
| `get_json()` | `String` | Vrátí originální JSON. |
| `get_semester()` | `ArrayList<String>` | Vrátí seznam všech absolvovaných semestrů. |
| `get_achievement()` | `ArrayList<String>` | Vrátí seznam s hodnotou prošel/ neprošel v závislosti na semestru. |
| `get_year()` | `ArrayList<String>` | Vrátí seznam ročníků daného semestru. |
| `get_date()` | `ArrayList<String>` | Vrátí seznam dat předaných vysvědčení. |
| `get_not_excused()` | `ArrayList<Integer>` | Vrátí seznam neomluvených hodin daného pololetí. |
| `get_grade()` | `ArrayList<Integer>` | Vrátí seznam ročníku, ve kterém se student nacházel v daném pololetí.|
| `get_absent()` | `ArrayList<Integer>` | Vrátí seznam hodin, na kterých studen nebyl přítomen.|
| `get_repeated()` | `ArrayList<Boolean>` | Vrátí v seznamu: `true` pokud student pololetí opakoval; `false` pokud neopakoval.|
| `get_closed()` | `ArrayList<Boolean>` | Vrátí v seznamu: `true` pokud má student uzavřené pololetí; `false` pokud nemá.|
| `get_subjects(String shorten)` | `ArrayList<ArrayList<String>>` | Vrátí seznam předmětů daného polloletí.|
| `get_marks()` | `ArrayList<ArrayList<String>>` | Vrátí seznam známek daného pololetí.|

***Počet prvků uvnitř všech seznamů (`get_semester()` až `get_marks()`) se rovná.*** <br>
***Počet prvků uvnitř všech podseznamů (`get_closed().get(i)` až `get_marks().get(i)`) se taktéž rovná.***


## Příklad

### Vstup

```java
// [...]

ArrayList<String> login = LogIn.getData("username", "password", school);

new Certificate(school, login.get(0)).getInstance();

for (int i = 0; i < Certificate.get_semester().size(); i++) {
    System.out.println("\nV " + Certificate.get_semester().get(i)
        + ". pololetí pro rok " + Certificate.get_year().get(i)
        + " student " + Certificate.get_achievement().get(i));

    System.out.println("Počet omluvených hodin: "
        + Certificate.get_absent().get(i));

    for (int j = 0; j < Certificate.get_marks().get(i).size(); j++) {
        System.out.println("Z Předmětu: '"
        + Certificate.get_subjects(false).get(i).get(j)
        + "' známka: " +Certificate.get_marks().get(i).get(j));
    }
}

// [...]
```

### Výstup

```
V 1. pololetí pro rok 2018/19 student prospěl
Počet omluvených hodin: 13
Z Předmětu: 'Chování' známka: 1
Z Předmětu: 'Český jazyk a literatura' známka: 2
Z Předmětu: 'Anglický jazyk' známka: 2
Z Předmětu: 'Německý jazyk' známka: 1

[...]
```