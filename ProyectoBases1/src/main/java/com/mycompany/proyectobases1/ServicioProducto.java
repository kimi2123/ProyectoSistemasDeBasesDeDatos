/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobases1;

import java.util.ArrayList;
import java.util.List;

public class ServicioProducto {
    
    private List<Producto> productos = new ArrayList<>();
    private Long contadorId = 1L;

    public  void addProducto(Producto producto) {
        producto.setId(contadorId++);
        productos.add(producto);
    }

    public void updateProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.getId().equals(producto.getId())) {
                p.setCodigoUnico(producto.getCodigoUnico());
                p.setNombre(producto.getNombre());
                p.setDescripcion(producto.getDescripcion());
                p.setCategoria(producto.getCategoria());
                p.setPrecio(producto.getPrecio());
                p.setCantidadStock(producto.getCantidadStock());
            }
        }
    }

    public void deleteProducto(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public List<Producto> findAll() {
        return productos;
    }
    
}