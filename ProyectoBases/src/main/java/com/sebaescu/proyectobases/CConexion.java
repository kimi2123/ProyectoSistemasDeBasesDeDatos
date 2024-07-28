/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectobases;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author Sebastian
 */
public class CConexion {
    Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "root";
    String bd = "gestion_ventas";
    String ip = "localhost";
    String puerto = "3307";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
            JOptionPane.showMessageDialog(null, "Conexion se ha realizado con exito");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos" + e.toString());
        }
        return conectar;
    } 
    
}
