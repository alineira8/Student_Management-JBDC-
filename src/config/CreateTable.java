package config;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {

    public static void createTables() {

        try {

            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();

            String students = "CREATE TABLE IF NOT EXISTS students (" +
                    "id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(50)," +
                    "last_name VARCHAR(50)," +
                    "email VARCHAR(100) UNIQUE," +
                    "date_of_birth DATE)";

            String courses = "CREATE TABLE IF NOT EXISTS courses (" +
                    "id SERIAL PRIMARY KEY," +
                    "course_name VARCHAR(100) UNIQUE," +
                    "course_description TEXT)";

            String marks = "CREATE TABLE IF NOT EXISTS marks (" +
                    "student_id INT," +
                    "course_id INT," +
                    "marks FLOAT," +
                    "PRIMARY KEY(student_id,course_id)," +
                    "FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE," +
                    "FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE CASCADE)";

            st.executeUpdate(students);
            st.executeUpdate(courses);
            st.executeUpdate(marks);

            System.out.println("Tables ready!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}