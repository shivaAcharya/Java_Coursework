
public class Student {

    private String name;
    private int id;
    private double grade;
        
    private static int numberOfStudents = 0;
        
    public Student(String name, double grade) {
        this.name = name;
        this.id = ++numberOfStudents;
        this.grade = grade;
    }
        
    public Student(String name) {
        this(name, 100.0);
    }
        
    public String getName() {
        return name;
    }
        
    public void setName(String newName) {
        if(newName.length() > 0) {
            name = newName;
        } else {
            System.err.println("Attempt to change name to empty string");
        }
    }
        
    public int getNameLength() {
        return name.length();
    }
        
    public int getID() {
        return id;
    }
        
    public double getGrade() {
        return grade;
    }
        
    public void setGrade(double newGrade) {
        if(newGrade < 0 || newGrade > 100) {
            System.err.println("Invalid grade: " + newGrade);
        } else {
            grade = newGrade;
        }
    }
        
    public String toString() {
        return "Student:" + id;
    }
        
    public String toVerboseString() {
        return toString() + " name=" + name + " grade=" + grade;
    }
        
    public static void main(String[] args) {

        Student s = new Student("Bob");

        System.out.println(s.toString() + " has name " + s.getName());
                
        System.out.println(s.toVerboseString());
                
        Student s2 = new Student("Jane");

        System.out.println(s2 + " has name " + s2.getName());
                
        System.out.println(s + " equals " + s2 + "? " + s.equals(s2));
                
        Student[] students = new Student[10];
        students[0] = s;
        students[1] = s2;
        students[2] = new Student("Howard");
                        
        for(int i = 0; i < students.length; i++) {
            Student student = students[i];
            if(student != null) {
                System.out.println(student + " has name " + student.getName());
            }
        }
    }
}