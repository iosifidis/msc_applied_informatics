#!/usr/bin/env python3

import sys

def get_grade_input(prompt_text):
    """
    Ζητά από τον χρήστη να εισάγει έναν βαθμό, ελέγχει αν είναι έγκυρος (αριθμός από 0-10)
    και τον επιστρέφει ως float. Υποστηρίζει τελεία και κόμμα.
    """
    while True:
        try:
            # Ζητάμε την είσοδο από τον χρήστη
            grade_str = input(prompt_text).replace(',', '.')
            grade_float = float(grade_str)
            
            # Ελέγχουμε αν ο βαθμός είναι στο αποδεκτό εύρος
            if 0 <= grade_float <= 10:
                return grade_float
            else:
                print(">> Λάθος: Ο βαθμός πρέπει να είναι μεταξύ 0 και 10. Προσπαθήστε ξανά.")
        except ValueError:
            print(">> Λάθος: Παρακαλώ εισάγετε έναν έγκυρο αριθμό. Προσπαθήστε ξανά.")
        except KeyboardInterrupt:
            print("\n>> Ο υπολογισμός ακυρώθηκε από τον χρήστη.")
            sys.exit()

def calculate_grades():
    """
    Κύρια συνάρτηση που εκτελεί όλη τη λογική του προγράμματος.
    """
    print("--- Υπολογιστής Τελικού Βαθμού για το μάθημα 'Συστήματα ΗΔ' ---")
    print("Παρακαλώ εισάγετε τους παρακάτω βαθμούς (0-10):\n")

    # --- Βήμα 1: Λήψη βαθμολογιών από τον χρήστη ---
    
    # Βαθμολογίες Εξετάσεων
    pollaplon_grade = get_grade_input("Βαθμός Εξέτασης Πολλαπλών: ")
    bpmn_grade = get_grade_input("Βαθμός Εξέτασης BPMN: ")

    # Βαθμολογίες Εργασιών
    assignment_grades = []
    for i in range(1, 6):
        grade = get_grade_input(f"Βαθμός Εργασίας {i} (E{i}): ")
        assignment_grades.append(grade)

    # --- Βήμα 2: Υπολογισμοί με βάση τους κανόνες ---

    # Ο βαθμός στις Εργασίες προκύπτει από 20% βαρύτητα σε κάθε εργασία (E1-E5),
    # που ισοδυναμεί με τον απλό μέσο όρο τους.
    total_assignments_grade = sum(assignment_grades) / len(assignment_grades)

    # Η Εξέταση προκύπτει από 70% Πολλαπλών + 30% ΒΠΜΝ.
    total_exam_grade = (0.70 * pollaplon_grade) + (0.30 * bpmn_grade)

    # Ο ΤΕΛΙΚΟΣ προκύπτει από 50% Εξέταση + 50% Εργασίες.
    final_grade = (0.50 * total_exam_grade) + (0.50 * total_assignments_grade)

    # --- Βήμα 3: Εμφάνιση αποτελεσμάτων ---
    
    print("\n---------------- ΑΠΟΤΕΛΕΣΜΑΤΑ ----------------")
    print(f"Συνολικός Βαθμός Εργασιών (Μ.Ο. των 5): {total_assignments_grade:.2f}")
    print(f"Συνολικός Βαθμός Εξέτασης (70% Πολλαπλών + 30% ΒΠΜΝ): {total_exam_grade:.2f}")
    print("---------------------------------------------")
    print(f"  ΤΕΛΙΚΟΣ ΒΑΘΜΟΣ (50% Εξέταση + 50% Εργασίες): {final_grade:.2f}")
    print("---------------------------------------------")


# Κύριο σημείο εκκίνησης του προγράμματος
if __name__ == "__main__":
    calculate_grades()
