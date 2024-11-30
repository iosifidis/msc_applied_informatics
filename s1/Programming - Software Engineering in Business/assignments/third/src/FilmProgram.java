// Efstathios Iosifidis mai25017

import java.util.ArrayList;

public class FilmProgram {

	// Πεδίο τύπου ArrayList
    private ArrayList<Film> list;

    // Κατασκευαστής. ερώτημα 7
    public FilmProgram() {
        this.list = new ArrayList<>();
    }

    // μέθοδο με όνομα storeFilm. ερώτημα 8
    public void storeFilm(Film film) {
        list.add(film);
    }

    // μέθοδο πρόσβασης, έστω getList. ερώτημα 9
    public ArrayList<Film> getList() {
        return list;
    }

    // Μέθοδος candidateFilms. ερώτημα 10
    public ArrayList<Film> candidateFilms() {
        ArrayList<Film> candidateList = new ArrayList<>();
        for (Film film : list) {
            if (film.getCandidacy()) {
                candidateList.add(film);
            }
        }
        return candidateList;
    }

    // Μέθοδος monthFilms. ερώτημα 11
    public ArrayList<Film> monthFilms(int month) {
        ArrayList<Film> monthList = new ArrayList<>();
        for (Film film : list) {
            String[] dateParts = film.getShowDate().split("/");
            int filmMonth = Integer.parseInt(dateParts[1]);
            if (filmMonth == month) {
                monthList.add(film);
            }
        }
        return monthList;
    }

    // Μέθοδος filmsMeanTime. ερώτημα 12
    public double filmsMeanTime() {
        if (list.isEmpty()) {
            return 0;
        }
        int totalPlayingTime = 0;
        for (Film film : list) {
            totalPlayingTime += film.getPlayingTime();
        }
        return (double) totalPlayingTime / list.size();
    }

    // Μέθοδος findFilm. ερώτημα 13
    public void findFilm(String title) {
        boolean found = false;
        for (Film film : list) {
            if (film.getTitle().equalsIgnoreCase(title)) {
                film.print();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("The film " + title + " does not belong to the collection.");
        }
    }

    // Μέθοδος showFilms. ερώτημα 14
    public void showFilms(ArrayList<Film> alist) {
        for (Film film : alist) {
            film.print();
        }
    }

}
