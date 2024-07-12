/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebaescu.proyectobases;

import java.util.ArrayList;
import java.util.List;

public class ServicioVeterinaria {
    
    private List<Veterinaria> veterinarias = new ArrayList<>();
    private Long contadorId = 1L;

    public void addVeterinaria(Veterinaria veterinaria) {
        veterinaria.setId(contadorId++);
        veterinarias.add(veterinaria);
    }

    public void updateVeterinaria(Veterinaria veterinaria) {
        for (Veterinaria v : veterinarias) {
            if (v.getId().equals(veterinaria.getId())) {
                v.setNombre(veterinaria.getNombre());
                v.setDireccion(veterinaria.getDireccion());
                v.setTelefono(veterinaria.getTelefono());
                v.setCorreoElectronico(veterinaria.getCorreoElectronico());
                v.setCantidadPedidosMes(veterinaria.getCantidadPedidosMes());
            }
        }
    }

    public void deleteVeterinaria(Long id) {
        veterinarias.removeIf(v -> v.getId().equals(id));
    }

    public List<Veterinaria> findAll() {
        return veterinarias;
    }
}
