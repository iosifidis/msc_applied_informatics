# Σημειώσεις HTML

## Responsive Design για κινητά
```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

## Παράδειγμα πίνακα
```html
<table>
  <caption>Βαθμοί μαθητών</caption>
  <thead>
    <tr>
      <th>Όνομα</th>
      <th>Μάθημα</th>
      <th>Βαθμός</th>
    </tr>
  </thead>
  <tfoot>
    <tr>
      <td colspan="2">Μέσος όρος</td>
      <td>87.5</td>
    </tr>
  </tfoot>
  <tbody>
    <tr>
      <td>Μαρία</td>
      <td>Μαθηματικά</td>
      <td>90</td>
    </tr>
    <tr>
      <td>Γιάννης</td>
      <td>Φυσική</td>
      <td>85</td>
    </tr>
  </tbody>
</table>
```

## Φόρμες

### Βασική φόρμα
```html
<form action="/submit" method="post">
  <label for="name">Όνομα:</label>
  <input type="text" id="name" name="name">
  <button type="submit">Υποβολή</button>
</form>
```

### Fieldset with Legend
```html
<fieldset>
  <legend>Προσωπικά Στοιχεία</legend>
  <label for="email">Email:</label>
  <input type="email" id="email" name="email">
</fieldset>
```

## Labels και Inputs

### Label και Input Αριθμού
```html
<label for="age">Ηλικία:</label>
<input type="number" id="age" name="age">
```

### Placeholder Examples
```html
<input type="text" placeholder="Όνομα">
<input type="password" placeholder="Κωδικός">
```

## Datalist
```html
<input list="cities" name="city">
<datalist id="cities">
  <option value="Αθήνα">
  <option value="Θεσσαλονίκη">
</datalist>
```

## Description List
```html
<dl>
  <dt>Beast of Bodmin</dt>
  <dd>A large feline inhabiting Bodmin Moor.</dd>

  <dt>Morgawr</dt>
  <dd>A sea serpent.</dd>

  <dt>Owlman</dt>
  <dd>A giant owl-like creature.</dd>
</dl>
```

## Buttons
```html
<button type="submit">Υποβολή</button>
<button type="reset">Επαναφορά</button>
```

## Select, Optgroup, and Option
```html
<select name="category">
  <optgroup label="Φρούτα">
    <option value="apple">Μήλο</option>
    <option value="banana">Μπανάνα</option>
  </optgroup>
</select>
```

## Textarea
```html
<textarea rows="4" cols="50" placeholder="Γράψτε κάτι..."></textarea>
```

## Progress and Meter

### Progress
```html
<progress value="70" max="100"></progress>
```

### Meter
```html
<meter value="0.7" min="0" max="1">70%</meter>
```

## Semantic HTML

### Article
```html
<article>
  Τμήμα του εγγράφου ή της εφαρμογής που θα μπορούσε να αποσπαστεί από το περιβάλλον του
</article>
```

### Section
```html
<section>
  Ενότητα περιεχομένου
</section>
```

### Aside
```html
<aside>
  Υλικό περιφερειακά σχετιζόμενο με το περιβάλλον sectioning element
</aside>
```

### Navigation
```html
<nav>
  Σύνολο από links σε άλλες σελίδες ή άλλα sections
</nav>
```

### Header and Footer
```html
<header>
  Υλικό επικεφαλίδας
</header>

<footer>
  Υλικό υποσέλιδου
</footer>

