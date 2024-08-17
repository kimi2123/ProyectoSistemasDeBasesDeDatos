
package com.sebaescu.proyectofinalbasededatos;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precio;
    private int cantidadStock;
    
    public Producto() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    public void insertarProducto(JTextField paramNombre,JTextField paramDescripcion, JTextField paramStock, JTextField paramPrecio,JTextField paramCategoria){
        
        setNombre(paramNombre.getText());
        setDescripcion(paramDescripcion.getText());
        setCantidadStock(Integer.parseInt(paramStock.getText()));
        setPrecio(Double.parseDouble(paramPrecio.getText()));
        setCategoria(paramCategoria.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call InsertarProducto(?, ?, ?, ?, ?)}";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getDescripcion());
            cs.setInt(3, getCantidadStock());
            cs.setDouble(4, getPrecio());
            cs.setString(5, getCategoria());
                    
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se añadio correctamente el Producto");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se añadió correctamente el Producto, error: " + e.toString());
        }
    }
    
    public void mostrarProductos(JTable paramTablaTotalProductos){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> ordernarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalProductos.setRowSorter(ordernarTabla);
        
        String sql = "";
        
        modelo.addColumn("idProducto");
        modelo.addColumn("nombre");
        modelo.addColumn("descripcion");
        modelo.addColumn("Cantidad_stock");
        modelo.addColumn("Precio");
        modelo.addColumn("categoria");
        
        paramTablaTotalProductos.setModel(modelo);
        
        sql = "select * from tablaProductos;";
        
        String[] datos =  new String[6];
        
        Statement st;
        
        try{
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalProductos.setModel(modelo);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
        
    }
    
    public void seleccionarProducto(JTable paramTablaTotalProductos, JTextField paramId, JTextField paramNombre, JTextField paramDescripcion, JTextField paramStock, JTextField paramPrecio,JTextField paramCategoria){
        
        try{
            int fila = paramTablaTotalProductos.getSelectedRow();
            
            if(fila >= 0){
                paramId.setText(paramTablaTotalProductos.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaTotalProductos.getValueAt(fila, 1).toString());
                paramDescripcion.setText(paramTablaTotalProductos.getValueAt(fila, 2).toString());
                paramStock.setText(paramTablaTotalProductos.getValueAt(fila, 3).toString());
                paramPrecio.setText(paramTablaTotalProductos.getValueAt(fila, 4).toString());
                paramCategoria.setText(paramTablaTotalProductos.getValueAt(fila, 5).toString());
                
            }else{
               JOptionPane.showMessageDialog(null, "Fila no seleccionaada " ); 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Seleccion, error: " + e.toString());
        }
    }
    public void modificarProducto(JTextField paramId, JTextField paramNombre, JTextField paramDescripcion, JTextField paramStock, JTextField paramPrecio,JTextField paramCategoria){
        
        setId(Integer.parseInt(paramId.getText()));
        setNombre(paramNombre.getText());
        setDescripcion(paramDescripcion.getText());
        setCantidadStock(Integer.parseInt(paramStock.getText()));
        setPrecio(Double.parseDouble(paramPrecio.getText()));
        setCategoria(paramCategoria.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call actualizar_producto(?, ?, ?, ?, ?, ?)}";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            cs.setString(2, getNombre());
            cs.setString(3, getDescripcion());
            cs.setInt(4, getCantidadStock());
            cs.setDouble(5, getPrecio());
            cs.setString(6, getCategoria());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
        }
    }
    public void eliminarProducto(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call eliminar_producto(?)}";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Producto " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    }
    
}
