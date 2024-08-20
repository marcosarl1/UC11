package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    private static Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/uc11";
    private static final String DB_USER = "dev";
    private static final String DB_PASSWORD = "123456";

    public Connection connectDB() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
        public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            } finally {
                conn = null;
            }
        }
    }
    
    public static void closeSt(PreparedStatement st) { 
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

}
