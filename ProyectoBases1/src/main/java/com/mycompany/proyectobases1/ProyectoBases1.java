
package com.mycompany.proyectobases1;


import java.util.Scanner;

public class ProyectoBases1 {
    
    private static ServicioProducto servicioProducto = new ServicioProducto();
    private static ServicioVeterinaria servicioVeterinaria = new ServicioVeterinaria();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clínicas Veterinarias");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    gestionarProductos();
                    break;
                case 2:
                    gestionarVeterinarias();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private static void gestionarProductos() {
        int opcion;
        do {
            System.out.println("1. Crear Producto");
            System.out.println("2. Actualizar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    createProducto();
                    break;
                case 2:
                    updateProducto();
                    break;
                case 3:
                    deleteProducto();
                    break;
                case 4:
                    findAllProductos();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (true);
    }

    private static void createProducto() {
        Producto producto = new Producto();
        System.out.print("Código Único: ");
        producto.setCodigoUnico(scanner.nextLine());
        System.out.print("Nombre: ");
        producto.setNombre(scanner.nextLine());
        System.out.print("Descripción: ");
        producto.setDescripcion(scanner.nextLine());
        System.out.print("Categoría: ");
        producto.setCategoria(scanner.nextLine());
        System.out.print("Precio: ");
        producto.setPrecio(scanner.nextDouble());
        System.out.print("Cantidad en Stock: ");
        producto.setCantidadStock(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        servicioProducto.addProducto(producto);
        System.out.println("Producto creado exitosamente.");
    }

    private static void updateProducto() {
        Producto producto = new Producto();
        System.out.print("ID del Producto a actualizar: ");
        producto.setId(scanner.nextLong());
        scanner.nextLine(); // Consume newline
        System.out.print("Código Único: ");
        producto.setCodigoUnico(scanner.nextLine());
        System.out.print("Nombre: ");
        producto.setNombre(scanner.nextLine());
        System.out.print("Descripción: ");
        producto.setDescripcion(scanner.nextLine());
        System.out.print("Categoría: ");
        producto.setCategoria(scanner.nextLine());
        System.out.print("Precio: ");
        producto.setPrecio(scanner.nextDouble());
        System.out.print("Cantidad en Stock: ");
        producto.setCantidadStock(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        servicioProducto.updateProducto(producto);
        System.out.println("Producto actualizado exitosamente.");
    }

    private static void deleteProducto() {
        System.out.print("ID del Producto a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        servicioProducto.deleteProducto(id);
        System.out.println("Producto eliminado exitosamente.");
    }

    private static void findAllProductos() {
        System.out.println("Lista de Productos:");
        for (Producto producto : servicioProducto.findAll()) {
            System.out.println(producto);
        }
    }

    private static void gestionarVeterinarias() {
        int opcion;
        do {
            System.out.println("1. Crear Clínica Veterinaria");
            System.out.println("2. Actualizar Clínica Veterinaria");
            System.out.println("3. Eliminar Clínica Veterinaria");
            System.out.println("4. Listar Clínicas Veterinarias");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    createVeterinaria();
                    break;
                case 2:
                    updateVeterinaria();
                    break;
                case 3:
                    deleteVeterinaria();
                    break;
                case 4:
                    findAllVeterinarias();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (true);
    }

    private static void createVeterinaria() {
        Veterinaria veterinaria = new Veterinaria();
        System.out.print("Nombre: ");
        veterinaria.setNombre(scanner.nextLine());
        System.out.print("Dirección: ");
        veterinaria.setDireccion(scanner.nextLine());
        System.out.print("Teléfono: ");
        veterinaria.setTelefono(scanner.nextLine());
        System.out.print("Correo Electrónico: ");
        veterinaria.setCorreoElectronico(scanner.nextLine());
        System.out.print("Cantidad de Pedidos por Mes: ");
        veterinaria.setCantidadPedidosMes(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        servicioVeterinaria.addVeterinaria(veterinaria);
        System.out.println("Clínica Veterinaria creada exitosamente.");
    }

    private static void updateVeterinaria() {
        Veterinaria veterinaria = new Veterinaria();
        System.out.print("ID de la Clínica Veterinaria a actualizar: ");
        veterinaria.setId(scanner.nextLong());
        scanner.nextLine(); // Consume newline
        System.out.print("Nombre: ");
        veterinaria.setNombre(scanner.nextLine());
        System.out.print("Dirección: ");
        veterinaria.setDireccion(scanner.nextLine());
        System.out.print("Teléfono: ");
        veterinaria.setTelefono(scanner.nextLine());
        System.out.print("Correo Electrónico: ");
        veterinaria.setCorreoElectronico(scanner.nextLine());
        System.out.print("Cantidad de Pedidos por Mes: ");
        veterinaria.setCantidadPedidosMes(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        servicioVeterinaria.updateVeterinaria(veterinaria);
        System.out.println("Clínica Veterinaria actualizada exitosamente.");
    }

    private static void deleteVeterinaria() {
        System.out.print("ID de la Clínica Veterinaria a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        servicioVeterinaria.deleteVeterinaria(id);
        System.out.println("Clínica Veterinaria eliminada exitosamente.");
    }

    private static void findAllVeterinarias() {
        System.out.println("Lista de Clínicas Veterinarias:");
        for (Veterinaria veterinaria : servicioVeterinaria.findAll()) {
            System.out.println(veterinaria);
        }
    }
}
