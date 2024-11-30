// Efstathios Iosifidis mai25017

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Δημιουργία αντικειμένου της κλάσης FilmProgram (ερώτημα a)
        FilmProgram filmProgram = new FilmProgram();
        
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        // Ερώτημα b: Ανάγνωση δεδομένων για όλες τις ταινίες
        System.out.println("***** QUESTION b: read data for all the films *****");
        while (continueInput) {
            System.out.print("Give title? ");
            String title = scanner.nextLine();

            System.out.print("Give director? ");
            String director = scanner.nextLine();

            System.out.print("Give playing time? ");
            int playingTime = scanner.nextInt();
            scanner.nextLine(); // Άδειασμα buffer

            System.out.print("The film is candidate for Oscar? (true/false) ");
            boolean candidacy = scanner.nextBoolean();
            scanner.nextLine(); // Άδειασμα buffer

            System.out.print("Give show date? ");
            String showDate = scanner.nextLine();

            // Δημιουργία αντικειμένου και προσθήκη στην λίστα
            Film film = new Film(title, director, playingTime, showDate); // ερώτημα b-ii
            film.setCandidacy(candidacy); // ερώτημα b-iii
            filmProgram.storeFilm(film); // ερώτημα b-iv

            // Ερώτημα να συνεχίσει ή όχι (ερώτημα b-v)
            System.out.print("Continue? (y/n) ");
            String input = scanner.nextLine();
            continueInput = input.equalsIgnoreCase("y");
        }

        // Ερώτημα c: Εμφάνιση λίστας όλων των ταινιών
        System.out.println("\n***** QUESTION c: show the list of all the films *****");
        filmProgram.showFilms(filmProgram.getList());

        // Ερώημα d: Εμφάνιση ταινιών υποψήφιων για OSCAR
        System.out.println("\n***** QUESTION d: show the list of films that are candidate for OSCAR *****");
        filmProgram.showFilms(filmProgram.candidateFilms());

        // Ερώτημα e: Εμφάνιση λίστας ταινιών για ένα συγκεκριμένο μήνα
        System.out.println("\n***** QUESTION e: show the list of films shown on November *****");
        System.out.print("Give the month to see the program of films? ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Άδεισμα buffer
        filmProgram.showFilms(filmProgram.monthFilms(month));

        // Ερώτημα f: Υπολογισμός μέσου όρου χρόνου όλων των ταινιών
        System.out.println("\n***** QUESTION f: mean time of all films *****");
        System.out.println("The mean playing time of all the films in the collection is " + filmProgram.filmsMeanTime());

        // Ερώτημα g: Αναζήτηση ταινίας
        System.out.println("\n***** QUESTION g: search for films *****");

        for(int i=0;i<2;i++) {
          System.out.print("Give the title of the film for searching? ");
          String searchTitle = scanner.nextLine();
          filmProgram.findFilm(searchTitle);
        }
        
        scanner.close();
    }

}
