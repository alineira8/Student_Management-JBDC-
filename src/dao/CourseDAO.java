package dao;

import config.DBConnection;
import model.Course;

import java.sql.*;

public class CourseDAO {

    Connection conn = DBConnection.getConnection();

    public void addCourse(Course c){

        try{

            String sql="INSERT INTO courses(course_name,course_description) VALUES(?,?)";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setString(1,c.getCourseName());
            ps.setString(2,c.getDescription());

            ps.executeUpdate();

            System.out.println("Course Added");

        }catch(Exception e){e.printStackTrace();}
    }

    public void viewCourses(){

        try{

            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM courses");

            while(rs.next()){

                System.out.println(
                        rs.getInt("id")+" "+
                                rs.getString("course_name")+" "+
                                rs.getString("course_description")
                );
            }

        }catch(Exception e){e.printStackTrace();}
    }
}