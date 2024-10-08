/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectobases;

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
public class Factura {
    
    private int id;
    private String metodoPago;
    private String numeroFactura;
    private Date fechaEmision;
    private String estadoFactura;

    public Factura() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

 

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }
    public void insertarFactura(JTextField paramEstado,JTextField paramFecha,JTextField paramMetodo){
        
        setEstadoFactura(paramEstado.getText());
        setFechaEmision(Date.valueOf(paramFecha.getText()));
        setMetodoPago(paramMetodo.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "Insert into factura(estado_factura,fecha_emision,metodo_pago) values (?,?,?);";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getEstadoFactura());
            cs.setDate(2, getFechaEmision());
            cs.setString(3, getMetodoPago());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se añadio correctamente la Factura");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se añadió correctamente la Factura, error: " + e.toString());
        }
    }
    
    public void mostrarFacturas(JTable paramTablaTotalFacturas){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> ordernarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalFacturas.setRowSorter(ordernarTabla);
        
        String sql = "";
        
        modelo.addColumn("idFactura");
        modelo.addColumn("estado_factura");
        modelo.addColumn("fecha_emision");
        modelo.addColumn("metodo_pago");
        
        paramTablaTotalFacturas.setModel(modelo);
        
        sql = "select * from factura;";
        
        String[] datos =  new String[4];
        
        Statement st;
        
        try{
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalFacturas.setModel(modelo);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
        
    }
    
    public void seleccionarFacturas(JTable paramTablaFacturas, JTextField paramId, JTextField paramEstado,JTextField paramFecha,JTextField paramMetodo){
        
        try{
            int fila = paramTablaFacturas.getSelectedRow();
            
            if(fila >= 0){
                paramId.setText(paramTablaFacturas.getValueAt(fila, 0).toString());
                paramEstado.setText(paramTablaFacturas.getValueAt(fila, 1).toString());
                paramFecha.setText(paramTablaFacturas.getValueAt(fila, 2).toString());
                paramMetodo.setText(paramTablaFacturas.getValueAt(fila, 3).toString());
                
            }else{
               JOptionPane.showMessageDialog(null, "Fila no seleccionaada " ); 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Seleccion, error: " + e.toString());
        }
    }
    public void modificarFacturas(JTextField paramId, JTextField paramEstado,JTextField paramFecha,JTextField paramMetodo){
        
        setId(Integer.parseInt(paramId.getText()));
        setEstadoFactura(paramEstado.getText());
        setFechaEmision(Date.valueOf(paramFecha.getText()));
        setMetodoPago(paramMetodo.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE factura SET factura.estado_factura = ?, factura.fecha_emision = ?, factura.metodo_pago = ? WHERE factura.idFactura = ?;";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getEstadoFactura());
            cs.setDate(2, getFechaEmision());
            cs.setString(3, getMetodoPago());
            cs.setInt(4, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
        }
    }
    public void eliminarFacturas(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM factura WHERE factura.idFactura = ?;";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente la Factura " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    }
    
}
