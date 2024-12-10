package gr.uom.market_week7;

//Η κλάση Product αναπαριστά ένα προϊόν με χαρακτηριστικά όπως κωδικός, όνομα και τιμή
public class Product {
	
	// Κωδικός του προϊόντος (χρησιμοποιείται Integer για να υποστηρίζει nullable τιμές)
	private Integer code;  // Αλλαγή από int σε Integer
	
    private String name;   // Όνομα του προϊόντος
    
    // Τιμή του προϊόντος (χρησιμοποιείται Integer για να υποστηρίζει nullable τιμές)
    private Integer price;  // Αλλαγή από int σε Integer
	
    // Κατασκευαστής για την αρχικοποίηση των χαρακτηριστικών του προϊόντος
    public Product(Integer c, String n, Integer t) {
        code = c;  // Ανάθεση τιμής στον κωδικό
        name = n;  // Ανάθεση τιμής στο όνομα
        price = t; // Ανάθεση τιμής στην τιμή
    }

    // Getter για τον κωδικό του προϊόντος
    public Integer getCode() {
        return code;
    }

    // Getter για το όνομα του προϊόντος
    public String getName() {
        return name;
    }

    // Getter για την τιμή του προϊόντος
    public Integer getPrice() {
        return price;
    }

    // Μέθοδος toString (σχολιασμένη προς το παρόν) που επιστρέφει αναπαράσταση του προϊόντος ως συμβολοσειρά
    // Μπορεί να ενεργοποιηθεί για ευκολότερη εκτύπωση ή debugging
//    @Override
//    public String toString() {
//        return "Product{name='" + name + "'}";
//    }
}