package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        ZonaFitApp();
    }

    private static void ZonaFitApp(){
        boolean salir = false;
        Scanner consola = new Scanner(System.in);

        IClienteDAO clienteDao = new ClienteDAO();

        while(!salir){
            try{
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            }
            catch(Exception e){
                System.out.println("Error al ejectuar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Zona Fit (GYM)
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elije una opcion: \n""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        boolean salir = false;
        switch(opcion){
            case 1: //Listar clientes
                System.out.println("--- Listado de Clientes ---");
                List<Cliente> clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            break;
            case 2:
                System.out.print("Ingrese el id del cliente:");
                int id = consola.nextInt();
                Cliente cliente = new Cliente(id);
                boolean encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no encontrado: " + cliente.getId());
            break;
            case 3:
                System.out.println("--- Ingresar cliente ---");
                System.out.print("Nombre: ");
                String nombre = consola.next();
                System.out.print("Apellido: ");
                String apellido = consola.next();
                System.out.print("Membresia: ");
                int membresia = consola.nextInt();
                Cliente nuevoCliente = new Cliente(nombre, apellido, membresia);
                boolean agregado = clienteDAO.agregarCliente(nuevoCliente);
                if(agregado)
                    System.out.println("Cliente agregado: " + nuevoCliente);
                else
                    System.out.println("No se agrego el cliente: " + nuevoCliente);
            break;
            case 4:
                System.out.println("--- Modificar cliente ---");
                System.out.println("Id Cliente: ");
                int idCliente = consola.nextInt();
                System.out.print("Nombre: ");
                String nombre2 = consola.next();
                System.out.print("Apellido: ");
                String apellido2 = consola.next();
                System.out.print("Membresia: ");
                int membresia2 = consola.nextInt();
                Cliente cliente2 = new Cliente(idCliente, nombre2, apellido2, membresia2);
                boolean modificado = clienteDAO.modificarCliente(cliente2);
                if(modificado)
                    System.out.println("Cliente modificado: " + cliente2);
                else
                    System.out.println("No se modifico cliente: " + cliente2);
            break;
            case 5:
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Id cliente: ");
                int idCliente2 = consola.nextInt();
                Cliente cliente3 = new Cliente(idCliente2);
                boolean eliminado = clienteDAO.eliminarCliente(cliente3);
                if(eliminado)
                    System.out.println("Cliente eliminado: " + cliente3);
                else
                    System.out.println("No se elimino el cliente: " + cliente3);
            break;
            case 6:
                System.out.println("Hasta pronto!");
                salir = true;
            break;
            default:
                System.out.println("Opcion no reconocida: " + opcion);
            break;
        }
        return salir;
    }
}
