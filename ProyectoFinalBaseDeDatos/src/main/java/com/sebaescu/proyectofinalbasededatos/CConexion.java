/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectofinalbasededatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Sebastian
 */
public class CConexion {
    Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "Kimikony123@";
    String bd = "gestion_ventas_final";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
            //JOptionPane.showMessageDialog(null, "Conexion se ha realizado con exito");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos " + e.toString());
        }
        return conectar;
    } 
    public boolean isOpen(){
        try {
            return conectar != null && !conectar.isClosed();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el estado de la conexión: " + e.toString());
            return false;
        }
        
    }
    public void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                conectar = null; // Opcional: Asegúrate de que la referencia se establezca en null después de cerrar la conexión.
                //JOptionPane.showMessageDialog(null, "Conexión cerrada con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }
    
}
