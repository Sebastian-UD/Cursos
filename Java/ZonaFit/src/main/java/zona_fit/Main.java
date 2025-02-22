package zona_fit;

import zona_fit.conexion.Conexion;
import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*Connection conexion = Conexion.getConexion();
        if(conexion != null)
            System.out.println("Conexion exitosa " + conexion);
        else
            System.out.println("Error al conectar");*/

        IClienteDAO cd = new ClienteDAO();

        //Buscar cliente por id
        /*System.out.println("\n***Buscar cliente por id:***");
        Cliente cliente1 = new Cliente(1);
        IClienteDAO cd = new ClienteDAO();
        System.out.println("Cliente antes de la busqueda: " + cliente1);
        boolean encontrado = cd.buscarClientePorId(cliente1);
        if(encontrado)
            System.out.println("Cliente encontrado: " + cliente1);
        else
            System.out.println("No se encontro cliente: " + cliente1.getId());*/

        //Agregar cliente
        /*Cliente nuevoCliente = new Cliente("Daniel", "Ortiz", 300);
        boolean agregado = cd.agregarCliente(nuevoCliente);
        if(agregado)
            System.out.println("Cliente agregado: " + nuevoCliente);
        else
            System.out.println("No se agrego el cliente: " + nuevoCliente);*/

        //Modificar cliente
        /*Cliente cliente = new Cliente(3, "Carlos Daniel", "Ortiz", 300);
        boolean modificado = cd.modificarCliente(cliente);
        if(modificado)
            System.out.println("Cliente modificado: " + cliente);
        else
            System.out.println("No se modifico cliente: " + cliente);*/

        //Elimar cliente
        Cliente cliente = new Cliente(3);
        boolean eliminado = cd.eliminarCliente(cliente);
        if(eliminado)
            System.out.println("Cliente eliminado: " + cliente);
        else
            System.out.println("No se elimino el cliente: " + cliente);

        //Listar clientes
        System.out.println("***Listar clientes:***");
        List<Cliente> clientes = cd.listarClientes();
        clientes.forEach(System.out::println);
    }
}