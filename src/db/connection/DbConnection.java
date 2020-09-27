package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static final String DBSTRING ="jdbc:derby://localhost:1527/crud_java";
    
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
    public static void closeConnection(Connection connection, PreparedStatement pstm) {
        try {
            closeConnection(connection);
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement pstm, ResultSet rs) {
        try {
            closeConnection(connection, pstm);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
