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
public class Distribuidor {
    
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String cedula;
    private Date fechaNacimiento;

    public Distribuidor() {
        
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



   public void insertarDistribuidor(JTextField paramNombre, JTextField paramApellido, JTextField paramDireccion, JTextField paramFecha, JTextField paramTelefono, JTextField paramCedula) {
    
    setNombre(paramNombre.getText());
    setApellido(paramApellido.getText());
    setDireccion(paramDireccion.getText());
    setTelefono(paramTelefono.getText());
    setFechaNacimiento(Date.valueOf(paramFecha.getText()));
    setCedula(paramCedula.getText());

    // Validación de que la cédula tiene 10 dígitos
    if (paramCedula.getText().length() != 10) {
        JOptionPane.showMessageDialog(null, "La cédula debe tener 10 dígitos.");
        return;
    }

    // Validación de que el teléfono tiene 10 dígitos
    if (paramTelefono.getText().length() != 9) {
        JOptionPane.showMessageDialog(null, "El teléfono debe tener 9 dígitos.");
        return;
    }
    
    CConexion objetoConexion = new CConexion();
    
    String consulta = "{call InsertarDistribuidor(?, ?, ?, ?, ?, ?)}";
    
    try {
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        
        cs.setString(1, getNombre());
        cs.setString(2, getApellido());
        cs.setString(3, getDireccion());
        cs.setDate(4, getFechaNacimiento());
        cs.setString(5, getTelefono());
        cs.setString(6, getCedula());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Se añadió correctamente el Distribuidor");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se añadió correctamente el Distribuidor, error: " + e.toString());
    }
}
    
    public void mostrarDistribuidor(JTable paramTablaTotalDistribuidor){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> ordernarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalDistribuidor.setRowSorter(ordernarTabla);
        
        String sql = "";
        
        modelo.addColumn("idDistribuidor");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido");
        modelo.addColumn("direccion");
        modelo.addColumn("fecha_nacimiento");
        modelo.addColumn("telefono");
        modelo.addColumn("cedula");
        
        paramTablaTotalDistribuidor.setModel(modelo);
        
        sql = "select * from tablaDistribuidor;";
        
        String[] datos =  new String[7];
        
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
                datos[6] = rs.getString(7);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalDistribuidor.setModel(modelo);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
        
    }
    
    public void seleccionarDistribuidor(JTable paramTablaDistribuidor,JTextField paramId ,JTextField paramNombre,JTextField paramApellido,JTextField paramDireccion, JTextField paramfecha, JTextField paramTelefono,JTextField paramCedula){
        
        try{
            int fila = paramTablaDistribuidor.getSelectedRow();
            
            if(fila >= 0){
                paramId.setText(paramTablaDistribuidor.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaDistribuidor.getValueAt(fila, 1).toString());
                paramApellido.setText(paramTablaDistribuidor.getValueAt(fila, 2).toString());
                paramDireccion.setText(paramTablaDistribuidor.getValueAt(fila, 3).toString());
                paramfecha.setText(paramTablaDistribuidor.getValueAt(fila, 4).toString());
                paramTelefono.setText(paramTablaDistribuidor.getValueAt(fila, 5).toString());                
                paramCedula.setText(paramTablaDistribuidor.getValueAt(fila, 6).toString());
                
            }else{
               JOptionPane.showMessageDialog(null, "Fila no seleccionaada " ); 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Seleccion, error: " + e.toString());
        }
    }
    public void modificarDistribuidor(JTextField paramId, JTextField paramNombre,JTextField paramApellido,JTextField paramDireccion, JTextField paramfecha, JTextField paramTelefono,JTextField paramCedula){
        
        setId(Integer.parseInt(paramId.getText()));
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setDireccion(paramDireccion.getText());
        setTelefono(paramTelefono.getText());
        setFechaNacimiento(Date.valueOf(paramfecha.getText()));
        setCedula(paramCedula.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call ActualizarDistribuidor(?, ?, ?, ?, ?, ?, ?)}";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            cs.setString(2, getNombre());
            cs.setString(3, getApellido());
            cs.setString(4, getDireccion());
            cs.setDate(5, getFechaNacimiento());
            cs.setString(6, getTelefono());
            cs.setString(7, getCedula());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
        }
    }
    public void eliminarDistribuidor(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call eliminar_distribuidor(?)}";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el distribuidor " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    }
    
    
}
