import config.CreateTable;
import dao.CourseDAO;
import dao.MarkDAO;
import dao.StudentDAO;
import model.Course;
import model.Student;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CreateTable.createTables();

        Scanner sc=new Scanner(System.in);

        StudentDAO studentDAO=new StudentDAO();
        CourseDAO courseDAO=new CourseDAO();
        MarkDAO markDAO=new MarkDAO();

        while(true){

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println("a. Add Student");
            System.out.println("b. View Students");
            System.out.println("c. Delete Student");
            System.out.println("d. Add Course");
            System.out.println("e. Get All Courses");
            System.out.println("f. Add Mark");
            System.out.println("g. View Student Marks");
            System.out.println("n. Exit");

            String choice=sc.next();

            switch(choice){

                case "a":

                    sc.nextLine();

                    System.out.println("First Name:");
                    String fn=sc.nextLine();

                    System.out.println("Last Name:");
                    String ln=sc.nextLine();

                    System.out.println("Email:");
                    String email=sc.nextLine();

                    System.out.println("DOB (yyyy-mm-dd):");
                    String dob=sc.nextLine();

                    studentDAO.addStudent(
                            new Student(fn,ln,email,Date.valueOf(dob))
                    );

                    break;

                case "b":

                    studentDAO.getAllStudents().forEach(s->
                            System.out.println(
                                    s.getId()+" "+s.getFirstName()+" "+s.getLastName()
                            )
                    );

                    break;

                case "c":

                    System.out.println("Student ID:");
                    int id=sc.nextInt();

                    studentDAO.deleteStudent(id);

                    break;

                case "d":

                    sc.nextLine();

                    System.out.println("Course Name:");
                    String cn=sc.nextLine();

                    System.out.println("Description:");
                    String cd=sc.nextLine();

                    courseDAO.addCourse(new Course(cn,cd));

                    break;

                case "e":

                    courseDAO.viewCourses();
                    break;

                case "f":

                    System.out.println("Student ID:");
                    int sid=sc.nextInt();

                    System.out.println("Course ID:");
                    int cid=sc.nextInt();

                    System.out.println("Marks:");
                    float m=sc.nextFloat();

                    markDAO.addMark(sid,cid,m);

                    break;

                case "g":

                    System.out.println("Student ID:");
                    int s=sc.nextInt();

                    markDAO.viewStudentMarks(s);

                    break;

                case "n":

                    System.exit(0);
            }
        }
    }
}