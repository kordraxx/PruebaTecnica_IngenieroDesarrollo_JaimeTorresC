package Modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.ir.TryNode;

public class ConexionDB {

    /*Parámetros de conexión con la BD*/
    
    private static String DRIVER="com.mysql.jdbc.Driver";
    private static String USER="root";
    private static String PWD="toor";
    private static String URL="jdbc:mysql://localhost:3306/mc_pruebatecnica";
    private Connection con = null;
    
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error en el Driver: " + e);
        }
    }
    
    /*Método para realizar la conexión a la BD*/
    
    public Connection getConnection(){
        
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error en la conexión." + e);
        }
        return con;
    }

    /*Método para reutilización de código*/
    
    public ResultSet consulta(String sql){
        
        ResultSet res = null;
        
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error en la consulta: "+ e.getMessage());
        }
        return res;
    }
    
}
