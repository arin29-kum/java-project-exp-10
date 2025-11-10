package dao;
import java.sql.*;
import java.util.*;
import db.DBConnection;
import model.Student;

public class StudentDAO {
    private Connection conn = DBConnection.getConnection();

    // Insert new student
    public void addStudent(Student s) {
        String sql = "INSERT INTO students (name, email, course, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());
            ps.setInt(4, s.getAge());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("age")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching students: " + e.getMessage());
        }
        return list;
    }

    // Update student
    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, email=?, course=?, age=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getCourse());
            ps.setInt(4, s.getAge());
            ps.setInt(5, s.getId());
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student updated successfully!");
            else
                System.out.println("⚠️ Student not found!");
        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }

    // Delete student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student deleted successfully!");
            else
                System.out.println("⚠️ Student not found!");
        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }
}
