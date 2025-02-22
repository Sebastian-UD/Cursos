package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM cliente ORDER BY id";

        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        }
        catch (Exception e){
            System.out.println("Error al listar clientes " + e.getMessage());
        }
        finally{
            try{
                conexion.close();
            }
            catch(Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            //Consulta usando parametros
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }
        finally{
            try{
                conexion.close();
            }
            catch(Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO cliente(nombre,apellido,membresia) VALUES(?,?,?)";
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());

            ps.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                conexion.close();
            }
            catch(Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());

            ps.execute();
            return true;
        }
        catch(Exception e){
            System.out.println("Error en actualizar un cliente: " + e.getMessage());
        }
        finally{
            try{
                conexion.close();
            }
            catch(Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());

            ps.execute();
            return true;
        }
        catch(Exception e){
            System.out.println("No se pudo eliminar cliente: " + e.getMessage());
        }
        finally{
            try{
                conexion.close();
            }
            catch(Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }


}
