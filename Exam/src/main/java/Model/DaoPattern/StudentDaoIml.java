//package Model.DaoPattern;
//
//import Model.SingletongDesign;
//import Model.Student;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StudentDaoIml implements StudentDao {
//    public List<Student> getAllStudent() {
//
//        SingletongDesign singletongDesign = SingletongDesign.getInstance();
//        Connection connection = singletongDesign.getConnection();
//        List<Student> studentList = null;
//
//        try {
//            Statement statement = connection.createStatement();
//            String query = "select * from Student";
//            ResultSet resultSet = statement.executeQuery(query);
//            studentList = new ArrayList<>();
//
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                double cgpa = resultSet.getDouble("cgpa");
//
//                Student student = new Student(id, name, cgpa);
//                studentList.add(student);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return studentList;
//    }
//
//
//    public Student getStudent(String id) {
//            SingletongDesign singletongDesign = SingletongDesign.getInstance();
//            Connection connection = singletongDesign.getConnection();
//            double cgpa = 0.0;
//            Student student = null;
//
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//            String query = "select * from Student where id = '"+id+"'";
//            ResultSet resultSet = statement.executeQuery(query);
//            resultSet.next();
//
//            String sId = resultSet.getString("id");
//            String name = resultSet.getString("name");
//            cgpa = resultSet.getDouble("cgpa");
//            student = new Student(sId, name, cgpa);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return student;
//    }
//
//    public double getCgpa(String id) {
//        SingletongDesign singletongDesign = SingletongDesign.getInstance();
//        Connection connection = singletongDesign.getConnection();
//
//        double cgpa = 0.0;
//        try {
//            Statement statement = connection.createStatement();
//
//            String query = "select cgpa from Student where id = '"+id+"'";
//            ResultSet resultSet = statement.executeQuery(query);
//            resultSet.next();
//
//            cgpa = resultSet.getDouble("cgpa");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return cgpa;
//    }
//
//    public void setStudent(Student student) {
//        SingletongDesign singletongDesign = SingletongDesign.getInstance();
//        Connection connection = singletongDesign.getConnection();
//        try {
//
//            Statement statement = connection.createStatement();
//            String query = "insert into Student values('"+student.getStudentId()+"', '"+student.getStudentName()+"'," +
//                    " '"+student.getCgpa()+"')";
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public void deleteStudent(String id) {
//        SingletongDesign singletongDesign = SingletongDesign.getInstance();
//        Connection connection = singletongDesign.getConnection();
//        try {
//
//            Statement statement = connection.createStatement();
//            String query = "delete from Student where id = '"+id+"'";
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void updateStudent(Student student) {
//        SingletongDesign singletongDesign = SingletongDesign.getInstance();
//        Connection connection = singletongDesign.getConnection();
//        try {
//
//            Statement statement = connection.createStatement();
//            String query = "update  Student set id='"+student.getStudentId()+"', name = '"+student.getStudentName()+"',  cgpa='"+student.getCgpa()+"' where id='"+student.getStudentId()+"'";
//            statement.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
