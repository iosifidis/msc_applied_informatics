// Efstathios Iosifidis - mai25017

// Κλάση για την καταγραφή και ενημέρωση των στοιχείων επικοινωνίας των δανειστών
public class Contact {

    // Ιδιότητες
    private String address;
    private String town;
    private int zipCode;
    private String phoneNumber;

	// Κατασκευαστής
    public Contact(String address, String town, int zipCode, String phoneNumber) {
        this.address = address;
        this.town = town;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    // Μέθοδο πρόσβασης επιστροφής τηλεφώνου
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Μεθόδοι μετάλλαξης για την ενημέρωση καθενός από τις 4 ιδιότητες.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    // Μέθοδος μετάλλαξης
    public void setFullAddress(String address, String town, int zipCode) {
        this.address = address;
        this.town = town;
        this.zipCode = zipCode;
    }

    // Μέθοδος πρόσβασης getFullAddress με έλεγχο και παραμετροποιημένη επιστροφόμενη μορφή
    public String getFullAddress() {
        if (address != null && town != null && zipCode != 0) {
            return address + ", " + town + ", " + zipCode;
        } else {
            String result = "";
            if (address == null) {
                result += "Missing address.\n";
            }
            if (town == null) {
                result += "Missing town.\n";
            }
            if (zipCode == 0) {
                result += "Missing zip code.\n";
            }
            return result;
        }
    }
}