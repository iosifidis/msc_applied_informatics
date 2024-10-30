package gr.uom.init;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Δηλώνουμε ότι αυτή η κλάση είναι REST Controller
@RequestMapping("/goodnight") // Τα endpoints θα ξεκινούν με "/goodnight"
public class GoodNight {
 
    @GetMapping() // Endpoint που επιστρέφει το μήνυμα "Good Night"
    public String goodnight() {
        return "Good Night";
    }
}
// URL για το endpoint: http://localhost:8081/goodnight

