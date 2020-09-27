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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO users VALUES('" + user.getUserName() + "')");
            
            st.execute();
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            DbConnection.closeConnection(conn, st);
        }
    }
    
    public static List<UserModel> getAllUsers() {
        Connection conn = DbConnection.createConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<UserModel> allUsers = new ArrayList<>();
        
        try {
            st = conn.prepareStatement("SELECT * FROM users");
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
}
