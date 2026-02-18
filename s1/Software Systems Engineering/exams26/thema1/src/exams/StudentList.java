package exams;

import java.util.ArrayList;

public class StudentList {
    
    private ArrayList<Student> list;

    public StudentList() {
        list = new ArrayList<Student>();
        
        // Default data moved here
        this.add(new Student("Maria", "Papadopoulou", 6.78));
        this.add(new Student("Giorgos", "Nikolaou", 8.41));
        this.add(new Student("Eleni", "Nikolaidou", 8.88));
        this.add(new Student("Petros", "Asteriou", 7.34));
    }

    public void add(Student s) {
        list.add(s);
    }

    public ArrayList<Student> getList() {
        return list;
    }
    
    // Helper to clear list (useful when re-populating after sort)
    public void clear() {
        list.clear();
    }
}