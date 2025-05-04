package students_package;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager_class {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INTEGER PRIMARY KEY,
                        firstName TEXT,
                        lastName TEXT,
                        birthYear INTEGER,
                        groupType TEXT,
                        grades TEXT
                    );
                    """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveAll(ArrayList<Student_class> students) {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM students");

            String sql = "INSERT INTO students (id, firstName, lastName, birthYear, groupType, grades) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Student_class s : students) {
                    pstmt.setInt(1, s.getId());
                    pstmt.setString(2, s.firstName);
                    pstmt.setString(3, s.lastName);
                    pstmt.setInt(4, s.getBirthYear());
                    pstmt.setString(5, s instanceof TelecomStudent_class ? "telecom" : "cyber");
                    pstmt.setString(6, s.getGrades().toString());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student_class> loadAll() {
        ArrayList<Student_class> list = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String fn = rs.getString("firstName");
                String ln = rs.getString("lastName");
                int year = rs.getInt("birthYear");
                String group = rs.getString("groupType");
                String gradesText = rs.getString("grades");

                Student_class s = group.equals("telecom")
                        ? new TelecomStudent_class(id, fn, ln, year)
                        : new CyberStudent_class(id, fn, ln, year);

                gradesText = gradesText.replaceAll("[\\[\\]\\s]", "");
                if (!gradesText.isEmpty()) {
                    for (String g : gradesText.split(",")) {
                        s.addGrade(Integer.parseInt(g));
                    }
                }

                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
