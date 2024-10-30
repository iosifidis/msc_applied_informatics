package gr.uom.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Δηλώνουμε ότι αυτή η κλάση είναι REST Controller
@RequestMapping("/hello") // Όλα τα endpoints θα ξεκινούν με "/hello"
public class Controller {
     // Εδώ βάζουμε τα endpoint που θα χρησιμοποιήσουμε από τους clients
    // Βασικό endpoint που επιστρέφει ένα μήνυμα
    @GetMapping
    public String hello() {
        return "Hello World";
    }
 
    // Endpoint που λαμβάνει το όνομα του χρήστη από το path
   // (path="hello/{name}" ή απλά "") και το εμφανίζει
    @GetMapping(path="/{name}")
    public String helloName(@PathVariable(value="name") String name) {
         // Στο PathVariable δηλώνω στο value την τιμή που θέλουμε να πάρει.
 // Και το όρισμα λέω ότι έρχεται από το path
return "Hello " + name;
    }
    // URL για το endpoint: http://localhost:8081/hello/Stathis
 
    // Endpoint που λαμβάνει το όνομα και την ηλικία του χρήστη
    @GetMapping(path="/helloandage/{name}")
    public String helloNameAndAge(
        @PathVariable(value="name") String name,
        @RequestParam(value="age") int age) {
         // Στο PathVariable δηλώνω το value ποιο είναι αυτό που θέλουμε να πάρει.
// Και το όρισμα λέω ότι έρχεται από το path
return "Hello " + name + " " + age;
    }
    // URL για το endpoint: http://localhost:8081/helloandage/Stathis?age=40
 
    // Endpoint που λαμβάνει το όνομα, ηλικία και τοποθεσία του χρήστη
    @GetMapping(path="/helloandlocation/{name}")
    public String helloNameAndLocation(
        @PathVariable(value="name") String name,
        @RequestParam(value="age") int age,
        @RequestParam(value="location") String location) {
 
        
//  // Στο PathVariable δηλώνω το value ποιο είναι αυτό που θέλουμε να πάρει.
//  return "Hello " + name + " " + age + " from " + location;
  
// Αντί για το παραπάνω, δημιουργούμε αντικείμενο της κλάσης Person και επιστρέφουμε τα δεδομένα του
        Person person = new Person(name, age, location);
// Επιστρέφω την εκτύπωση των δεδομένων του Person.
        return person.getData();
    }
    // URL για το endpoint: http://localhost:8081/helloandlocation/Stathis?age=40&amp;location=Thessaloniki
 
    // Endpoint που επιστρέφει λίστα με μηνύματα σε μορφή JSON
    @GetMapping(path="/list")
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Hola");
        list.add("Me llamo Estathis");
        list.add("Tengo 40 años");
        return list;
    }
}

