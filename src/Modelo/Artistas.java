package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

public class Artistas {
    
    private String codigo;
    private String nombre;
    private String apellido;
    private String codigo_museo;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo_museo() {
        return codigo_museo;
    }

    public void setCodigo_museo(String codigo_museo) {
        this.codigo_museo = codigo_museo;
    }
    
    public String toString(){
        return this.nombre;
    }
    
    public Vector<Artistas> mostrarArtistas(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionDB conn = new ConexionDB();
        Connection con = conn.getConnection();
        
        Vector<Artistas> datos = new Vector<Artistas>();
        
        Artistas dat = null;
        
        try {
            
            String sql = "SELECT * FROM artistas";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            dat = new Artistas();
            
            dat.setCodigo("0");
            dat.setNombre("Seleccione un Artista...");
            datos.add(dat);            
            
            while(rs.next()){
                
                dat = new Artistas();
            
                dat.setCodigo(rs.getString("codigo"));
                dat.setNombre(rs.getString("nombre") +" "+ rs.getString("apellido"));
                dat.setApellido(rs.getString("apellido"));
                dat.setCodigo_museo(rs.getString("codigo_museo"));
                datos.add(dat);                
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
}
