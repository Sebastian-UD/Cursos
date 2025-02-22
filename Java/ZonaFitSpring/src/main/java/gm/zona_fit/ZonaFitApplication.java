package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		// Levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion finalizada!");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		boolean salir = false;
		Scanner consola = new Scanner(System.in);

		while(!salir){
			int opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}

	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
				\n*** Aplicacion Zona Fit (GYM) ***
				1. Listar Clientes
				2. Buscar Cliente por Id
				3. Agregar Cliente
				4. Modicicar Cliente
				5. Eliminar Cliente
				6. Salir
				Elige una opcion:\s""");

		return Integer.parseInt(consola.next());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		boolean salir = false;
		switch (opcion){
			case 1:
				logger.info("--- Listado de Clientes ---" + nl);
				List<Cliente> clientes= this.clienteServicio.listaCliente();
				clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
			break;
			case 2:
				logger.info(nl + "--- Buscar Cliente por Id ---" + nl);
				logger.info("Id Cliente a buscar: ");
				int idCliente = Integer.parseInt(consola.next());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null)
					logger.info("Cliente encontrado: " + cliente + nl);
				else
					logger.info("Cliente no encontrado: " + cliente + nl);
			break;
			case 3:
				logger.info("--- Agregar Cliente ---");
				logger.info("Nombre : ");
				String nombre = consola.next();
				logger.info("Apellido : ");
				String apellido = consola.next();
				logger.info("Membresia: ");
				int membresia = Integer.parseInt(consola.next());
				Cliente nuevoCliente = new Cliente();
				nuevoCliente.setNombre(nombre);
				nuevoCliente.setApellido(apellido);
				nuevoCliente.setMembresia(membresia);
				clienteServicio.guardarCliente(nuevoCliente);
				logger.info("Cliente agregado: " + nuevoCliente + nl);
			break;
			case 4:
				logger.info("--- Modificar Cliente ---");
				logger.info("Id Cliente: ");
				int idCliente2 = Integer.parseInt(consola.next());
				Cliente cliente2 = clienteServicio.buscarClientePorId(idCliente2);
				if(cliente2 != null){
					logger.info("Nombre : ");
					String nombre2 = consola.next();
					logger.info("Apellido : ");
					String apellido2 = consola.next();
					logger.info("Membresia: ");
					int membresia2 = Integer.parseInt(consola.next());
					cliente2.setNombre(nombre2);
					cliente2.setApellido(apellido2);
					cliente2.setMembresia(membresia2);
					clienteServicio.guardarCliente(cliente2);
					logger.info("Cliente modificado: " + cliente2 + nl);
				}
				else
					logger.info("El Cliente no existe");
			break;
			case 5:
				logger.info("--- Eliminar Cliente ---");
				logger.info("Id Cliente: ");
				int idCliente3 = Integer.parseInt(consola.next());
				Cliente cliente3 = clienteServicio.buscarClientePorId(idCliente3);
				if(cliente3 != null){
					clienteServicio.eliminarCliente(cliente3);
					logger.info("Cliente eliminado: " + cliente3 + nl);
				}
				else
					logger.info("Cliente no encontrado: " + cliente3 + nl);
			break;
			case 6:
				logger.info("Hasta pronto!" + nl + nl);
				salir = true;
			break;
			default:
				logger.info("Opcion no reconocida: " + opcion + nl);
			break;
		}
		return salir;
	}
}
