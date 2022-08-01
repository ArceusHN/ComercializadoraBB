/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Producto {
    private Conexion cnx = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private int Id;
    private String Descripcion;
    private int InventarioGlobal;
    private String FechaAgrega;

    public Producto() {
    }

    public Producto(int Id, String Descripcion, int InventarioGlobal, String FechaAgrega) {
        this.Id = Id;
        this.Descripcion = Descripcion;
        this.InventarioGlobal = InventarioGlobal;
        this.FechaAgrega = FechaAgrega;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getInventarioGlobal() {
        return InventarioGlobal;
    }

    public void setInventarioGlobal(int InventarioGlobal) {
        this.InventarioGlobal = InventarioGlobal;
    }

    public String getFechaAgrega() {
        return FechaAgrega;
    }

    public void setFechaAgrega(String FechaAgrega) {
        this.FechaAgrega = FechaAgrega;
    }
    
      public ArrayList<Producto> ListarProductos(){
        ArrayList<Producto> listado = new ArrayList<>();
        
        try{
            String query = "SELECT * FROM Productos";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt("Id"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setInventarioGlobal(rs.getInt("InventarioGlobal"));
                producto.setFechaAgrega(rs.getString("FechaAgrega"));

                
                listado.add(producto);
            }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return listado;
    }
    public void AgregarProducto(){
        try{
            String query = "INSERT INTO Productos(Descripcion, InventarioGlobal,FechaAgrega) VALUES(?,?,CURRENT_DATE())";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, Descripcion);
            ps.setInt(2, InventarioGlobal);
            
            ps.executeUpdate();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public void ActualizarProducto(){
        try{
            String query = "Update Productos set Descripcion = ?, InventarioGlobal = ? where Id = ?";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, Descripcion);
            ps.setInt(2, InventarioGlobal);
            ps.setInt(3, Id);
            
            ps.executeUpdate();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
