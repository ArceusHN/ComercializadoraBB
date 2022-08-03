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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ProductoModelo {
    private Conexion cnx = new Conexion();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private int Id;
    private String Descripcion;
    private int StockGlobal;
    private char TipoProducto;
    private int CategoriaId;
    private Date FechaAgrega;
    private Boolean EsActivo;
    private Date FechaElimina;
    
    private String Categoria;
    private String TipoProductoDescripcion;

    public ProductoModelo(){
    }

    public ProductoModelo(String Descripcion, char TipoProducto, int CategoriaId, Boolean EsActivo) {
        this.Descripcion = Descripcion;
        this.TipoProducto = TipoProducto;
        this.CategoriaId = CategoriaId;
        this.EsActivo = EsActivo;
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

    public int getStockGlobal() {
        return StockGlobal;
    }

    public void setStockGlobal(int StockGlobal) {
        this.StockGlobal = StockGlobal;
    }

    public char getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(char TipoProducto) {
        this.TipoProducto = TipoProducto;
    }

    public int getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(int CategoriaId) {
        this.CategoriaId = CategoriaId;
    }
    
    public Date getFechaAgrega() {
        return FechaAgrega;
    }

    public void setFechaAgrega(Date FechaAgrega) {
        this.FechaAgrega = FechaAgrega;
    }

    public Boolean getEsActivo() {
        return EsActivo;
    }

    public void setEsActivo(Boolean EsActivo) {
        this.EsActivo = EsActivo;
    }

    public Date getFechaElimina() {
        return FechaElimina;
    }

    public void setFechaElimina(Date FechaElimina) {
        this.FechaElimina = FechaElimina;
    }
     
    public String getCategoria(){
        return Categoria;
    }
    
    public void setCategoria(String Categoria){
        this.Categoria = Categoria;
    }

    public String getTipoProductoDescripcion() {
        return TipoProductoDescripcion;
    }

    public void setTipoProductoDescripcion(String TipoProductoDescripcion) {
        this.TipoProductoDescripcion = TipoProductoDescripcion;
    }
    
    public ArrayList<ProductoModelo> ListarProductos(){
        ArrayList<ProductoModelo> listado = new ArrayList<>();     
        try{
            String query = "SELECT prod.Id, prod.Descripcion, StockGlobal, categ.Nombre, CASE WHEN TipoProducto = 'I' THEN 'Insumo' ELSE 'Cosecha' END TipoProducto, prod.FechaAgrega FROM `productos` AS prod INNER JOIN categorias AS categ ON categ.Id = prod.Id;";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                ProductoModelo producto = new ProductoModelo();
                producto.setId(rs.getInt("Id"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setStockGlobal(rs.getInt("StockGlobal"));
                producto.setCategoria(rs.getString("Nombre"));
                producto.setTipoProductoDescripcion("TipoProducto");
                producto.setFechaAgrega(rs.getDate("FechaAgrega"));
                listado.add(producto);
            }
        }catch(SQLException ex){
            return new ArrayList<>();
        }
        return listado;
    }
      
    public boolean AgregarProducto(){
        try{
            String query = "INSERT INTO Productos(Descripcion, StockGlobal,FechaAgrega) VALUES(?,?,CURRENT_DATE())";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, Descripcion);
            ps.setInt(2, StockGlobal);
            
            ps.executeUpdate();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Boolean ActualizarProducto(){
        try{
            String query = "Update Productos set Descripcion = ?, InventarioGlobal = ? where Id = ?";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, Descripcion);
            ps.setInt(2, StockGlobal);
            ps.setInt(3, Id);
            
            ps.executeUpdate();
        }
        catch(SQLException ex){
            return false;
        }
        return true;
    }
    
    public Boolean EliminarProducto(){
        try{
            String query = "Update Productos SET esActivo = 0 WHERE Id = ?";
            con = cnx.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, Id);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            return false;
        }
        return true;
    }
     
}
