package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

public class Pinturas {
    
    private String codigo;
    private int precio;
    private String fecha;
    private String codigo_artista;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo_artista() {
        return codigo_artista;
    }

    public void setCodigo_artista(String codigo_artista) {
        this.codigo_artista = codigo_artista;
    }
    
    public String toString(){
        return this.codigo;
    }

    public Vector<Pinturas> mostrarPinturas(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionDB conn = new ConexionDB();
        Connection con = conn.getConnection();
        
        Vector<Pinturas> datos = new Vector<Pinturas>();
        
        Pinturas dat = null;
        
        try {
            
            String sql = "SELECT * FROM pinturas";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            dat = new Pinturas();
            dat.setCodigo("Seleccione una pintura...");
            dat.setPrecio(0);
            dat.setFecha("1-1-1");
            dat.setCodigo_artista("0");
            
            datos.add(dat);       
            
            while(rs.next()){
                
                dat = new Pinturas();
            
                dat.setCodigo(rs.getString("codigo"));
                dat.setPrecio(rs.getInt("precio"));
                dat.setFecha(rs.getString("fecha"));
                dat.setCodigo_artista(rs.getString("codigo_artista"));
                datos.add(dat);                
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
}