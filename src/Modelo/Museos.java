package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Museos {

    private String codigo;
    private String nombre;
    private String direccion;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
  
    public String toString() {
        return this.nombre;
    }
    
    public Vector<Museos> mostrarMuseos(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionDB conn = new ConexionDB();
        Connection con = conn.getConnection();
        
        Vector<Museos> datos = new Vector<Museos>();
        
        Museos dat = null;
        
        try {
            
            String sql = "SELECT * FROM museos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            dat = new Museos();
            
            dat.setCodigo("0");
            dat.setNombre("Seleccione un Museo...");
            dat.setDireccion("0");
            datos.add(dat);            
            
            while(rs.next()){
                
                dat = new Museos();
            
                dat.setCodigo(rs.getString("codigo"));
                dat.setNombre(rs.getString("nombre"));
                dat.setDireccion(rs.getString("direccion"));
                datos.add(dat);                
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
}