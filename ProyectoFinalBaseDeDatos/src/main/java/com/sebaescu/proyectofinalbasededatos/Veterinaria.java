/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author erick
 */
public class Veterinaria {
    private int id;
    private String nombreVeterinaria;
    private String direccionVeterinaria;
    private String telefonoVeterinaria;
    private String correoVeterinaria;


    public Veterinaria() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getDireccionVeterinaria() {
        return direccionVeterinaria;
    }

    public void setDireccionVeterinaria(String direccionVeterinaria) {
        this.direccionVeterinaria = direccionVeterinaria;
    }

    public String getTelefonoVeterinaria() {
        return telefonoVeterinaria;
    }

    public void setTelefonoVeterinaria(String telefonoVeterinaria) {
        this.telefonoVeterinaria = telefonoVeterinaria;
    }

    public String getCorreoVeterinaria() {
        return correoVeterinaria;
    }

    public void setCorreoVeterinaria(String correoVeterinaria) {
        this.correoVeterinaria = correoVeterinaria;
    }

    public void insertarVeterinaria(JTextField paramNombre,JTextField paramDireccion,JTextField paramTelefono, JTextField paramCorreo ){
        
        setNombreVeterinaria(paramNombre.getText());
        setDireccionVeterinaria(paramDireccion.getText());
        setTelefonoVeterinaria(paramTelefono.getText());
        setCorreoVeterinaria(paramCorreo.getText());

        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call InsertarVeterinaria(?, ?, ?, ?)}";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreVeterinaria());
            cs.setString(2, getDireccionVeterinaria());
            cs.setString(3, getTelefonoVeterinaria());
            cs.setString(4, getCorreoVeterinaria()); 
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se añadio correctamente la veterinaria");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se añadió correctamente la veterinaria, error: " + e.toString());
        }
    }
    
   public void mostrarVeterinarias(JTable paramTablaTotalVeterinarias){
    if (paramTablaTotalVeterinarias == null) {
        JOptionPane.showMessageDialog(null, "La tabla de veterinarias no está inicializada.");
        return;
    }

    CConexion objetoConexion = new CConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> ordernarTabla = new TableRowSorter<TableModel>(modelo);
    paramTablaTotalVeterinarias.setRowSorter(ordernarTabla);

    modelo.addColumn("idVeterinaria");
    modelo.addColumn("nombre");
    modelo.addColumn("direccion");
    modelo.addColumn("telefono");
    modelo.addColumn("correo_electronico");

    paramTablaTotalVeterinarias.setModel(modelo);

    String sql = "select * from veterinaria;";
    String[] datos = new String[5];
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
            modelo.addRow(datos);
        }

        paramTablaTotalVeterinarias.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}    
    public void seleccionarVeterinaria(JTable paramTablaVeterinarias, JTextField paramId, JTextField paramNombre, JTextField paramDireccion,JTextField paramTelefono, JTextField paramCorreo ){
        
        try{
            int fila = paramTablaVeterinarias.getSelectedRow();
            
            if(fila >= 0){
                paramId.setText(paramTablaVeterinarias.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaVeterinarias.getValueAt(fila, 1).toString());
                paramDireccion.setText(paramTablaVeterinarias.getValueAt(fila, 2).toString());
                paramTelefono.setText(paramTablaVeterinarias.getValueAt(fila, 3).toString());
                paramCorreo.setText(paramTablaVeterinarias.getValueAt(fila, 4).toString());

                
            }else{
               JOptionPane.showMessageDialog(null, "Fila no seleccionaada " ); 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Seleccion, error: " + e.toString());
        }
    }
    public void modificarVeterinaria(JTextField paramId, JTextField paramNombre, JTextField paramDireccion,JTextField paramTelefono, JTextField paramCorreo){
        
        setId(Integer.parseInt(paramId.getText()));
        setNombreVeterinaria(paramNombre.getText());
        setDireccionVeterinaria(paramDireccion.getText());
        setTelefonoVeterinaria(paramTelefono.getText());
        setCorreoVeterinaria(paramCorreo.getText());

        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call ActualizarVeterinaria(?, ?, ?, ?, ?)}";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            cs.setString(2, getNombreVeterinaria());
            cs.setString(3, getDireccionVeterinaria());
            cs.setString(4, getTelefonoVeterinaria());
            cs.setString(5, getCorreoVeterinaria());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
        }
    }
    public void eliminarVeterinaria(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "{call eliminar_veterinaria(?)}";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente la Veterinaria " ); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    }
}
