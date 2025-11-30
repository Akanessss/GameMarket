import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://192.168.0.102:5433/db_for_test";
        String user = "dev";
        String pass = "devdev";

        String sql = "SELECT now() AS ts, current_database() AS db";

        try {
            // Explicitly load the PostgreSQL driver (optional for modern Java)
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection to PostgreSQL established successfully!");

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Timestamp ts = rs.getTimestamp("ts");
                String db = rs.getString("db");
                System.out.println("Connected OK. now()=" + ts + ", db=" + db);
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }


}