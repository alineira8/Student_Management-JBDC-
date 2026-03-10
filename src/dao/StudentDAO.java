package dao;

import config.DBConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    Connection conn = DBConnection.getConnection();

    public void addStudent(Student s){

        try{

            String sql="INSERT INTO students(first_name,last_name,email,date_of_birth) VALUES(?,?,?,?)";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,s.getFirstName());
            ps.setString(2,s.getLastName());
            ps.setString(3,s.getEmail());
            ps.setDate(4,s.getDob());

            ps.executeUpdate();

            System.out.println("Student Added");

        }catch(Exception e){e.printStackTrace();}
    }

    public List<Student> getAllStudents(){

        List<Student> list=new ArrayList<>();

        try{

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM students");

            while(rs.next()){

                Student s=new Student();

                s.setId(rs.getInt("id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));
                s.setDob(rs.getDate("date_of_birth"));

                list.add(s);
            }

        }catch(Exception e){e.printStackTrace();}

        return list;
    }

    public void deleteStudent(int id){

        try{

            PreparedStatement ps=conn.prepareStatement("DELETE FROM students WHERE id=?");

            ps.setInt(1,id);
            ps.executeUpdate();

            System.out.println("Student Deleted");

        }catch(Exception e){e.printStackTrace();}
    }
}