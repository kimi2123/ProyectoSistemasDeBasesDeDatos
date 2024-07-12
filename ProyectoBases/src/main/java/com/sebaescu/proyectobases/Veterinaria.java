/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectobases;

/**
 *
 * @author erick
 */
public class Veterinaria {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private int cantidadPedidosMes;

    public Veterinaria() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getCantidadPedidosMes() {
        return cantidadPedidosMes;
    }

    public void setCantidadPedidosMes(int cantidadPedidosMes) {
        this.cantidadPedidosMes = cantidadPedidosMes;
    }
    
    
}
