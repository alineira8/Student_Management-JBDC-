package dao;

import config.DBConnection;

import java.sql.*;

public class MarkDAO {

    Connection conn = DBConnection.getConnection();

    public void addMark(int studentId,int courseId,float marks){

        try{

            String sql="INSERT INTO marks(student_id,course_id,marks) VALUES(?,?,?)";

            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setInt(1,studentId);
            ps.setInt(2,courseId);
            ps.setFloat(3,marks);

            ps.executeUpdate();

            System.out.println("Mark Added");

        }catch(Exception e){e.printStackTrace();}
    }

    public void viewStudentMarks(int studentId){

        try{

            PreparedStatement ps=conn.prepareStatement(
                    "SELECT * FROM marks WHERE student_id=?"
            );

            ps.setInt(1,studentId);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println(
                        "Course ID: "+rs.getInt("course_id")+
                                " Marks: "+rs.getFloat("marks")
                );
            }

        }catch(Exception e){e.printStackTrace();}
    }
}