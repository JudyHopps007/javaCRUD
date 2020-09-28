package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static final String DBSTRING ="jdbc:derby://localhost:1527/crud_java"; //String de conexão
    
    public static Connection createConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(DBSTRING);
            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error: ", ex);
        }
    }
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Error: ", ex);
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement ps) {
        try {
            closeConnection(connection);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {
            closeConnection(connection, ps);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
