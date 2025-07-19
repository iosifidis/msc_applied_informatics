#!/usr/bin/env python3

def calculate_final_grade():
    """
    Υπολογίζει τον τελικό βαθμό του Μεταπτυχιακού Προγράμματος Σπουδών
    στην Εφαρμοσμένη Πληροφορική του Πανεπιστημίου Μακεδονίας.
    """

    print("--- Υπολογιστής Βαθμολογίας Πτυχίου ---")
    print("Παρακαλώ, εισάγετε τους βαθμούς σας (από 1 έως 10).")
    print("Αν δεν έχετε βαθμολογηθεί ακόμα σε ένα μάθημα ή στη διπλωματική, δώστε 0.")

    # Λίστα για να αποθηκεύσουμε τους μέσους όρους των συνιστωσών (2 εξάμηνα + διπλωματική)
    component_averages = []

    # --- Α' Εξάμηνο ---
    grades_sem_a = get_grades_from_user("Α' εξαμήνου", 4)
    if grades_sem_a:  # Αν υπάρχουν βαθμοί (δεν ήταν όλοι 0)
        avg_a = sum(grades_sem_a) / len(grades_sem_a)
        component_averages.append(avg_a)
        print(f"-> Μέσος όρος Α' Εξαμήνου: {avg_a:.2f}\n")
    else:
        print("-> Δεν δόθηκαν βαθμοί για το Α' Εξάμηνο.\n")


    # --- Β' Εξάμηνο ---
    grades_sem_b = get_grades_from_user("Β' εξαμήνου", 4)
    if grades_sem_b: # Αν υπάρχουν βαθμοί (δεν ήταν όλοι 0)
        avg_b = sum(grades_sem_b) / len(grades_sem_b)
        component_averages.append(avg_b)
        print(f"-> Μέσος όρος Β' Εξαμήνου: {avg_b:.2f}\n")
    else:
        print("-> Δεν δόθηκαν βαθμοί για το Β' Εξάμηνο.\n")


    # --- Διπλωματική Εργασία ---
    diploma_grade = get_single_grade("Διπλωματικής Εργασίας")
    if diploma_grade > 0:
        component_averages.append(diploma_grade)
        print(f"-> Βαθμός Διπλωματικής: {diploma_grade:.2f}\n")
    else:
         print("-> Δεν δόθηκε βαθμός για τη Διπλωματική Εργασία.\n")

    # --- Υπολογισμός Τελικού Βαθμού ---
    if not component_averages:
        print("Δεν έχετε εισάγει κανέναν βαθμό. Ο υπολογισμός δεν είναι δυνατός.")
    else:
        # Ο τελικός βαθμός είναι ο μέσος όρος των διαθέσιμων συνιστωσών
        final_grade = sum(component_averages) / len(component_averages)
        print("========================================")
        print(f"Η τελική σας βαθμολογία είναι: {final_grade:.2f}")
        print("========================================")


def get_grades_from_user(semester_name, num_courses):
    """
    Λαμβάνει και επικυρώνει τους βαθμούς για ένα συγκεκριμένο εξάμηνο από τον χρήστη.
    Αγνοεί τους βαθμούς που είναι 0.
    """
    grades = []
    print(f"--- Εισαγωγή Βαθμών για το {semester_name} ---")
    for i in range(num_courses):
        while True:
            try:
                grade_str = input(f"Δώστε βαθμό για το μάθημα {i + 1}: ")
                grade = float(grade_str)
                if 0 <= grade <= 10:
                    if grade > 0: # Προσθέτουμε τον βαθμό μόνο αν δεν είναι 0
                        grades.append(grade)
                    break
                else:
                    print("Παρακαλώ δώστε έναν έγκυρο βαθμό (0-10).")
            except ValueError:
                print("Παρακαλώ εισάγετε έναν αριθμό.")
    return grades

def get_single_grade(item_name):
    """
    Λαμβάνει και επικυρώνει έναν μεμονωμένο βαθμό (π.χ. διπλωματικής).
    """
    print(f"--- Εισαγωγή Βαθμού {item_name} ---")
    while True:
        try:
            grade_str = input(f"Δώστε τον βαθμό: ")
            grade = float(grade_str)
            if 0 <= grade <= 10:
                 return grade
            else:
                print("Παρακαλώ δώστε έναν έγκυρο βαθμό (0-10).")
        except ValueError:
            print("Παρακαλώ εισάγετε έναν αριθμό.")


# Εκτέλεση του προγράμματος
if __name__ == "__main__":
    calculate_final_grade()
