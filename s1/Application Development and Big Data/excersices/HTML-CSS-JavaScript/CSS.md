# CSS Notes

### Link Stylesheet
```html
<link rel="stylesheet" href="styles.css">
```

### Selectors
```css
.X { /* Class selector */ }
#X { /* ID selector */ }
X > Y { /* Child selector */ }
X Y { /* Descendant selector */ }
X + Y { /* Adjacent sibling selector */ }
X ~ Y { /* General sibling selector */ }
```

### Text and Background Styling
```css
/* Text and background colors */
color: red;
background-color: yellow;
background-image: url('image.png');
background-repeat: no-repeat;
background-attachment: fixed;
background-position: center;
background: #fff url('image.png') no-repeat center;

/* Fonts */
font-family: Arial, sans-serif;
font-style: italic;
font-variant: small-caps;
font-weight: bold;
font-size: 16px;
font: italic small-caps bold 16px/30px Arial, sans-serif;

/* Text styling */
text-indent: 20px;
text-align: center;
text-decoration: underline;
letter-spacing: 2px;
word-spacing: 5px;
text-transform: uppercase;
white-space: nowrap;
line-height: 1.5;
vertical-align: middle;

/* Lists */
list-style-type: disc;
list-style-image: url('bullet.png');
list-style-position: inside;
list-style: circle url('bullet.png') inside;
```

### Pseudo-classes
```css
a:hover { color: blue; }
a:visited { color: purple; }
section:not(#references) { border: 1px solid black; }
p:lang(el) { font-style: italic; }
input[type="checkbox"]:checked { background-color: green; }
```

## Box Model Example
```css
p {
  background-color: orange;
  margin: 10px;
  margin-bottom: 0;
  border: 3px solid red;
  padding-top: 10px;
  padding-right: 25px;
  padding-bottom: 30px;
  padding-left: 35px;
  width: 400px;
  box-sizing: content-box; /* or border-box */
  border-radius: 15px;
  box-shadow: 10px 10px 25px red;
}
```

---

# Επεξήγηση των CSS Display, Flex, Grid

## Display
Η ιδιότητα **`display`** ορίζει πώς ένα στοιχείο εμφανίζεται στο έγγραφο. Είναι θεμελιώδης για τη διάταξη μιας σελίδας. Οι πιο συνηθισμένες τιμές περιλαμβάνουν:

- **`block`**: Το στοιχείο καταλαμβάνει όλο το πλάτος του γονικού στοιχείου και ξεκινά σε νέα γραμμή.
- **`inline`**: Το στοιχείο καταλαμβάνει μόνο το απαραίτητο πλάτος και βρίσκεται στην ίδια γραμμή με τα υπόλοιπα inline στοιχεία.
- **`inline-block`**: Συνδυάζει τα χαρακτηριστικά του `inline` και του `block`. Επιτρέπει αλλαγές διαστάσεων χωρίς να "σπάει" η γραμμή.
- **`none`**: Το στοιχείο δεν εμφανίζεται καθόλου.

Παράδειγμα:
```css
#inline li {
  display: inline;
}
#inlineBlock li {
  display: inline-block;
}
```

---

## Flexbox (Flexible Box Layout)
Το **Flexbox** είναι ένα σύστημα διάταξης που προορίζεται για την οργάνωση στοιχείων σε μία διάσταση (οριζόντια ή κάθετα). Επιτρέπει εύκολη στοίχιση, διανομή χώρου και ευελιξία στα στοιχεία.

### Ιδιότητες του γονικού στοιχείου (container):
- **`display: flex;`**: Ενεργοποιεί το flexbox για το container.
- **`flex-direction`**: Καθορίζει την κατεύθυνση των στοιχείων (row, column).
- **`justify-content`**: Στοίχιση στοιχείων κατά μήκος του κύριου άξονα.
- **`align-items`**: Στοίχιση στοιχείων κατά μήκος του κάθετου άξονα.
- **`flex-wrap`**: Επιτρέπει τα στοιχεία να "σπάνε" σε νέες γραμμές.

### Παράδειγμα Flexbox:
```css
.navigation {
  display: flex;
  justify-content: flex-end;
  background: deepskyblue;
}

.wrapper {
  display: flex;
  flex-wrap: wrap;
  text-align: center;
}
.wrapper > * {
  flex: 1 100%;
}
```

---

## CSS Grid Layout
Το **Grid** είναι ένα ισχυρό εργαλείο για τη δημιουργία πολύπλοκων διατάξεων σε δύο διαστάσεις (γραμμές και στήλες). Παρέχει ακριβή έλεγχο της τοποθέτησης των στοιχείων.

### Βασικές έννοιες:
- **Grid Container**: Το στοιχείο που ορίζεται με `display: grid;`.
- **Grid Items**: Τα άμεσα παιδιά του container.
- **Grid Template Areas**: Ορίζει περιοχές του grid που μπορούν να αντιστοιχιστούν σε στοιχεία.

### Ιδιότητες του Grid:
- **`grid-template-columns` / `grid-template-rows`**: Ορίζει τις στήλες/γραμμές.
- **`grid-gap`**: Ορίζει την απόσταση μεταξύ των γραμμών και των στηλών.
- **`grid-area`**: Καθορίζει πού τοποθετείται κάθε στοιχείο.

### Παράδειγμα Grid:
```css
body {
  display: grid;
  grid-gap: 5px;
  grid-template-areas:
    "head"
    "navi"
    "main"
    "foot";
}

@media all and (min-width: 600px) {
  body {
    grid-template-columns: 1fr 2fr;
    grid-template-areas:
      "head head"
      "navi main"
      "foot foot";
  }
}
```

### Ολοκληρωμένο παράδειγμα Grid

```
/* Colorize and assign grid names */
header  { background-color: yellow;         grid-area: head; }
nav     { background-color: yellowgreen;    grid-area: navi; }
main    { background-color: orange;         grid-area: main; text-align: left; }
article { background-color: skyblue;        grid-area: news; }
aside   { background-color: violet;         grid-area: info; }
footer  { background-color: lightgray;      grid-area: foot; }

/* Grid container - small screens */
body {
    display: grid;
    grid-gap: 5px;
    grid-template-columns: 1fr;
    grid-template-areas:
        "head"
        "navi"
        "main"
        "news"
        "info"
        "foot";
}

/* Medium screens */
@media all and (min-width: 600px) {
    body {
        grid-template-columns: 1fr 2fr;
        grid-template-areas:
            "head head"
            "navi navi"
            "news main"
            "info foot";
    }
}

/* Large screens */
@media all and (min-width: 800px) {
    nav {
        justify-content: flex-start;
        flex-direction: column;
    }
    a { flex: 0; }
    
    body {
        grid-template-columns: 1fr 4fr 1fr;
        grid-template-areas:
            "head head head"
            "navi main news"
            "navi main info"
            "foot foot foot";
    }
}
```

---

### NAV MENU TOP-RIGHT
```
ul {
                position: fixed; /* Fixed in upper right */
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                list-style-type: none;
                background: deepskyblue;
                font-family: sans-serif;
            }
            li {
                position: relative; /* Prepare for sub-menu */
                display: inline-block;
                cursor: pointer;
                padding: 10px;
            }
            li:hover {
                background: white;
            }
            li > ul {
                display: none; /* Hide submenu */
                position: absolute; /* Submenu beneath menu item */
                top: 100%;
                left: 0;
            }
            li:hover > ul { /* Show submenu */
                display: block;
            }
            li li {
                display: block;
            }
            div {
                height: 600px;
                border: 1px solid black;
                background-color: bisque;
            }

##### HTML

 <ul>
            <li>Αρχική</li>
            <li>Πληροφορίες
                <ul>
                    <li>Προσωπικό</li>
                    <li>Γραμματεία</li>
                    <li>Ιστορικό</li>
                </ul>
            </li>
            <li>Προϊόντα</li>
            <li>Επικοινωνία</li>
        </ul>
```
