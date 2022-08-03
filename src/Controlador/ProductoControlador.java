/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ProductoModelo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrator
 */
public class ProductoControlador {
    
    JTextField descripcion;

    public ProductoControlador(){}
    
    public ProductoControlador(JTextField descripcion){
        this.descripcion = descripcion;
    }
    
    public ArrayList<ProductoModelo> ListarProductos(){
        ProductoModelo modelo = new ProductoModelo();
        return modelo.ListarProductos();
    }
    
    public Boolean Guardar(){
        ProductoModelo modelo = new ProductoModelo();
        modelo.setDescripcion(descripcion.getText());
        return modelo.AgregarProducto();
    }
    
    public Boolean Eliminar(){
        ProductoModelo modelo = new ProductoModelo();
        //modelo.setDescripcion(descripcion.getText()); jtable Que contenga el ID del Producto a eliminar
        return modelo.EliminarProducto();
    }
}
