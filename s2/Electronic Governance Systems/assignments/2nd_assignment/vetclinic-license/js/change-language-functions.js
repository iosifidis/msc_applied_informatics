var languageContent = {
  greek: {
      languageBtn: "EL",
      mainTitle: "Άδεια Ίδρυσης και Λειτουργίας Ιδιωτικών Κλινικών Ζώων",
      pageTitle: "Άδεια Ίδρυσης και Λειτουργίας Ιδιωτικών Κλινικών Ζώων",
      infoTitle: "Έκδοση Άδειας Ίδρυσης και Λειτουργίας Ιδιωτικών Κλινικών Ζώων",
      subTitle1: "Αυτό το ερωτηματολόγιο μπορεί να σας βοηθήσει να βρείτε αν δικαιούστε την Άδεια Ίδρυσης και Λειτουργίας Ιδιωτικής Κλινικής Ζώων",
      subTitle2: "H συμπλήρωση του ερωτηματολογίου δεν απαιτεί παραπάνω από 10 λεπτά.",
      subTitle3: "Δεν θα αποθηκεύσουμε ούτε θα μοιραστούμε τις απαντήσεις σας.",
      backButton: "Πίσω",
      nextQuestion: "Επόμενη ερώτηση",
      biggerCursor: "Μεγαλύτερος Δρομέας",
      bigFontSize: "Μεγάλο Κείμενο",
      readAloud: "Ανάγνωση",
      changeContrast: "Αντίθεση",
      readingMask: "Μάσκα Ανάγνωσης",
      footerText: "Αυτό το έργο δημιουργήθηκε για τις ανάγκες της 2ης εργασίας στο μάθημα Συστήματα Ηλεκτρονικής Διακυβέρνησης του μεταπτυχιακού στο Πανεπιστήμιο Μακεδονίας από τον φοιτητή",
      //and: "και",
      student1: "Ευστάθιος Ιωσηφίδης",
      //student2: "",
      startBtn: "Ας ξεκινήσουμε",
      chooseAnswer: "Επιλέξτε την απάντησή σας",
      oneAnswer: "Μπορείτε να επιλέξετε μόνο μία επιλογή.",
      errorAn: "Λάθος:",
      choose: "Πρέπει να επιλέξετε μια απάντηση",
  },
  english: {
      languageBtn: "EN",
      mainTitle: "License for Establishment and Operation of Private Animal Clinics",
      pageTitle: "License for Establishment and Operation of Private Animal Clinics",
      infoTitle: "Issuance of License for Establishment and Operation of Private Animal Clinics",
      subTitle1: "This questionnaire can help you determine if you are eligible for the License for Establishment and Operation of a Private Animal Clinic",
      subTitle2: "Completing the questionnaire should not take more than 10 minutes.",
      subTitle3: "We will neither store nor share your answers.",
      backButton: "Back",
      nextQuestion: "Next Question",
      biggerCursor: "Bigger Cursor",
      bigFontSize: "Big Font Size",
      readAloud: "Read Aloud",
      changeContrast: "Change Contrast",
      readingMask: "Reading Mask",
      footerText: "This project was created for the needs of the 2nd assignment in the course of E-Government Systems of the Master's program at the University of Macedonia by the student",
      //and: "and",
      student1: "Efstathios Iosifidis",
      //student2: "",
      startBtn: "Let's start",
      chooseAnswer: "Choose your answer",
      oneAnswer: "You can choose only one option.",
      errorAn: "Error:",
      choose: "You must choose one option",
  }
};

// Retrieve the selected language from localStorage or set default to "greek"
var currentLanguage = localStorage.getItem("preferredLanguage") || "greek";

function toggleLanguage() {
  currentLanguage = currentLanguage === "greek" ? "english" : "greek";
  localStorage.setItem("preferredLanguage", currentLanguage);
  updateContent();
}

function updateContent() {
  var components = document.querySelectorAll(".language-component");
   
  components.forEach(function (component) {
      var componentName = component.dataset.component;
      component.textContent = languageContent[currentLanguage][componentName];
  });
}

// Initialize the content based on the selected language
updateContent();
