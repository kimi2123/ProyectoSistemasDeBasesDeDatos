/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectofinalbasededatos;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author erick
 */
public class Pedido {
    
    private int id,idVeterinaria,idDistribuidor;
    private String nombreProducto;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private String estado;

    public Pedido() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(int idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }

    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
public void insertarPedido(JTextField paramFechaEntrega,JTextField paramFechaCreacion,JTextField paramNombreProducto, JTextField paramEstado,JTextField paramidVeterinaria,JTextField paramidDistribuidor ){
        
        setFechaEntrega(Date.valueOf(paramFechaEntrega.getText()));
        setFechaCreacion(Date.valueOf(paramFechaCreacion.getText()));
        setNombreProducto(paramNombreProducto.getText());
        setEstado(paramEstado.getText());
        setIdVeterinaria(Integer.valueOf(paramidVeterinaria.getText()));
        setIdDistribuidor(Integer.valueOf(paramidDistribuidor.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call InsertarPedido(?, ?, ?, ?, ?, ?)}";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setDate(1, getFechaEntrega());
            cs.setDate(2, getFechaCreacion());
            cs.setString(3, getNombreProducto());
            cs.setString(4, getEstado());
            cs.setInt(5, getIdVeterinaria());
            cs.setInt(6, getIdDistribuidor());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se añadio correctamente el Pedido");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se añadió correctamente la veterinaria, error: " + e.toString());
        }
    }
    
   public void mostrarPedidos(JTable paramTablaTotalPedidos){
        if (paramTablaTotalPedidos == null) {
            JOptionPane.showMessageDialog(null, "La tabla de pedidos no está inicializada.");
            return;
        }

        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordernarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalPedidos.setRowSorter(ordernarTabla);

        modelo.addColumn("idPedido");
        modelo.addColumn("fecha_entrega");
        modelo.addColumn("fecha_creacion");
        modelo.addColumn("nombre_producto");
        modelo.addColumn("estado");
        modelo.addColumn("idVeterinaria");
        modelo.addColumn("idDistribuidor");

        paramTablaTotalPedidos.setModel(modelo);

        String sql = "select * from pedido;";
        String[] datos = new String[7];
        Statement st;

        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                modelo.addRow(datos);
            }

            paramTablaTotalPedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
    }    
    public void seleccionarPedido(JTable paramTablaVeterinarias, JTextField paramId, JTextField paramFechaEntrega,JTextField paramFechaCreacion,JTextField paramNombreProducto, JTextField paramEstado,JTextField paramidVeterinaria,JTextField paramidDistribuidor  ){
        
        try{
            int fila = paramTablaVeterinarias.getSelectedRow();
            
            if(fila >= 0){
                paramId.setText(paramTablaVeterinarias.getValueAt(fila, 0).toString());
                paramFechaEntrega.setText(paramTablaVeterinarias.getValueAt(fila, 1).toString());
                paramFechaCreacion.setText(paramTablaVeterinarias.getValueAt(fila, 2).toString());
                paramNombreProducto.setText(paramTablaVeterinarias.getValueAt(fila, 3).toString());
                paramEstado.setText(paramTablaVeterinarias.getValueAt(fila, 4).toString());
                paramidVeterinaria.setText(paramTablaVeterinarias.getValueAt(fila, 5).toString());
                paramidDistribuidor.setText(paramTablaVeterinarias.getValueAt(fila, 6).toString());

                
            }else{
               JOptionPane.showMessageDialog(null, "Fila no seleccionaada " ); 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Seleccion, error: " + e.toString());
        }
    }
    public void modificarPedido(JTextField paramId, JTextField paramFechaEntrega,JTextField paramFechaCreacion,JTextField paramNombreProducto, JTextField paramEstado ){
        
        setId(Integer.parseInt(paramId.getText()));
        setFechaEntrega(Date.valueOf(paramFechaEntrega.getText()));
        setFechaCreacion(Date.valueOf(paramFechaCreacion.getText()));
        setNombreProducto(paramNombreProducto.getText());
        setEstado(paramEstado.getText());

        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call ActualizarPedido(?, ?, ?, ?, ?)}";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            cs.setDate(2, getFechaEntrega());
            cs.setDate(3, getFechaCreacion());
            cs.setString(4, getNombreProducto());
            cs.setString(5, getEstado());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
        }
    }
    public void eliminarPedido(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call eliminar_pedido(?)}";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Pedido " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    }
}
