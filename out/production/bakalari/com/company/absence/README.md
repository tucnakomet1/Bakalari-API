# Absence

Třída `GetAbsence` pro získání informací o absenci.

*Nejprve je třeba se přihlásit - viz. [LogIn](../core/README.md#prihlaseni)*

| Argument | Typ | Popis |
| --- | --- | --- |
| `get_json()` | `String` | Vrátí originální JSON. |
| `get_subjects()` | `ArrayList<String>` | Vrátí seznam všech předmětů, kterých se absence týká. |
| `get_lessons()` | `ArrayList<Integer>` | Vrátí počet hodin všech daných předmětů. |
| `get_late()` | `ArrayList<Integer>` | Vrátí seznam všech pozdních příchodů. |
| `get_absence()` | `ArrayList<Integer>` | Vrátí seznam všech omluvených hodin. |
| `get_school_events()` | `ArrayList<Integer>` | Vrátí seznam všech školou omluvených hodin.|
| `get_percente(boolean)` | `ArrayList<Float>` | Vrátí seznam všech (školou → `true`) omluvených hodin v procentech. |

***Počet prvků uvnitř všech seznamů (`get_subjects()` až `get_percente()`) se rovná.***

## Příklad

### Vstup

```java
// [...]
ArrayList<String> userInfo = LogIn.getData("username", "password", school);

new GetAbsence(school, userInfo.get(0)).getInstance();

for (int i = 0; i<GetAbsence.get_subjects().size(); i++) {
    System.out.println(GetAbsence.get_subjects().get(i) + " ("
        + GetAbsence.get_absence().get(i) + " / "
        + GetAbsence.get_lessons().get(i) + ")");
    
    System.out.println("Pošet pozdních příchodů: " 
        + GetAbsence.get_late().get(i));
    
    System.out.println("Celková absence: " 
        + GetAbsence.get_percente(false).get(i) + "%\n");
}
// [...]
```

### Výstup

```
[...]
Biologie (0 / 32)
Pošet pozdních příchodů: 0
Celková absence: 0.0%

Tělesná výchova (0 / 28)
Pošet pozdních příchodů: 0
Celková absence: 0.0%

Informatika odborná (2 / 34)
Pošet pozdních příchodů: 0
Celková absence: 5.88%
[...]
```