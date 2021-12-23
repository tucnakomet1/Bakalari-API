# Absence

Třída `Absence` pro získání informací o absenci.

*Nejprve je třeba se přihlásit - viz. [LogIn](../core/README.md#prihlaseni)*

| Argument | Typ | Popis |
| --- | --- | --- |
| `get_json()` | `String` | Vrátí originální JSON. |
| `get_average_absence()` | `Double` | Vrátí průměrnou absenci všech předmětů. |
| `get_subjects()` | `ArrayList<String>` | Vrátí seznam všech předmětů, kterých se absence týká. |
| `get_lessons()` | `ArrayList<Integer>` | Vrátí počet hodin všech daných předmětů. |
| `get_late()` | `ArrayList<Integer>` | Vrátí seznam všech pozdních příchodů. |
| `get_absence()` | `ArrayList<Integer>` | Vrátí seznam všech omluvených hodin. |
| `get_school_events()` | `ArrayList<Integer>` | Vrátí seznam všech školou omluvených hodin.|
| `get_percent(boolean)` | `ArrayList<Float>` | Vrátí seznam všech (školou → `true`) omluvených hodin v procentech. |

***Počet prvků uvnitř všech seznamů (`get_subjects()` až `get_percente()`) se rovná.***

## Příklad

### Vstup

```java
// [...]
ArrayList<String> login = LogIn.getData("username", "password", school);

new Absence(school, login.get(0)).getInstance();

for (int i = 0; i<Absence.get_subjects().size(); i++) {
    System.out.println(Absence.get_subjects().get(i) + " ("
        + Absence.get_absence().get(i) + " / "
        + Absence.get_lessons().get(i) + ")");
    
    System.out.println("Pošet pozdních příchodů: " 
        + Absence.get_late().get(i));
    
    System.out.println("Celková absence: " 
        + Absence.get_percente(false).get(i) + "%\n");
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