package user;

import db.connection.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UserDao {
    public static void insertUser(UserModel user) {
        Connection conn = DbConnection.createConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO USERS VALUES('" + user.getUserName() + "')");
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbConnection.closeConnection(conn, ps);
        }
    }
    
    public static List<UserModel> getAllUsers() {
        Connection conn = DbConnection.createConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<UserModel> allUsers = new ArrayList<>();
        
        try {
            st = conn.prepareStatement("SELECT * FROM USERS");
            rs = st.executeQuery();
            
            while(rs.next()) {
                UserModel userToList = new UserModel();
                
                userToList.setUserName(rs.getString("NOME_USUARIO"));
                
                allUsers.add(userToList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbConnection.closeConnection(conn, st, rs);
        }
        return allUsers;
    }
    public static void updateUser(String newName, String oldName) {
        Connection conn = DbConnection.createConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE USERS SET NOME_USUARIO = '" + newName + "' WHERE NOME_USUARIO = '" + oldName + "'");
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbConnection.closeConnection(conn, ps);
        }
    }
    public static void deleteUser(UserModel user) {
        Connection conn = DbConnection.createConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM USERS WHERE NOME_USUARIO = '" + user.getUserName() + "'");
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbConnection.closeConnection(conn, ps);
        }
    }
}
