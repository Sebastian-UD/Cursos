package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JLabel tituloApp;
    private JTable tablaClientes;
    private JPanel panelTabla;
    private JPanel panelFormulario;
    private JPanel panelBotones;
    private JScrollPane barraScroll;
    private JLabel labelNombre;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JLabel labelApellido;
    private JLabel labelMembresia;
    private JButton botonGuardar;
    private JButton botonEliminar;
    private JButton botonLimpiar;
    private final IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloClientes;
    private Integer idCliente;

    //Inyeccion de dependencias de Spring pero en un constructor
    public ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
        botonGuardar.addActionListener(e -> guardarCliente());
        tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        botonEliminar.addActionListener(e -> eliminarCliente());
        botonLimpiar.addActionListener(e -> limpiarFormulario());
    }

    private void iniciarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);//centra ventana
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //this.tablaModeloClientes = new DefaultTableModel(0, 4);
        //Evitar editar los campos de la tabla
        this.tablaModeloClientes = new DefaultTableModel(0, 4){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] cabeceros = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloClientes.setColumnIdentifiers(cabeceros);
        this.tablaClientes = new JTable(tablaModeloClientes);
        // Restringir la seleccion a un solo registro
        this.tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Cargar listado de clientes
        listarClientes();
    }

    private void listarClientes(){
        this.tablaModeloClientes.setRowCount(0);
        List<Cliente> clientes = clienteServicio.listaCliente();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tablaModeloClientes.addRow(renglonCliente);
        });
    }

    private void guardarCliente(){
        if(nombreTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(membresiaTexto.getText().isEmpty()){
            mostrarMensaje("Proporciona una membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }
        // Recuperar los valores del formulario
        String nombre = nombreTexto.getText();
        String apellido = apellidoTexto.getText();
        int membresia = Integer.parseInt(membresiaTexto.getText());
        Cliente cliente = new Cliente(this.idCliente, nombre, apellido, membresia);
        this.clienteServicio.guardarCliente(cliente);// inserta/modifica cliente
        if(this.idCliente == null)
            mostrarMensaje("Se agrego el nuevo cliente");
        else
            mostrarMensaje("Se actualizo el cliente");
        limpiarFormulario();
        listarClientes();
    }

    private void cargarClienteSeleccionado(){
        int renglon = this.tablaClientes.getSelectedRow();
        if(renglon != -1){ // -1 significa que no se selecciono ningun registro
            String id = tablaClientes.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);
            String nombre = tablaClientes.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);
            String apellido = tablaClientes.getModel().getValueAt(renglon, 2).toString();
            this.apellidoTexto.setText(apellido);
            String membresia = tablaClientes.getModel().getValueAt(renglon, 3).toString();
            this.membresiaTexto.setText(membresia);
        }
    }

    private void eliminarCliente(){
        if(this.idCliente != null){
            Cliente cliente = new Cliente();
            cliente.setId(this.idCliente);
            clienteServicio.eliminarCliente(cliente);
            mostrarMensaje("Cliente eliminado");
            limpiarFormulario();
            listarClientes();

        }
        else{
            mostrarMensaje("Seleccione un cliente");
        }
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void limpiarFormulario(){
        this.nombreTexto.setText("");
        this.apellidoTexto.setText("");
        this.membresiaTexto.setText("");
        // Limpiar el id cliente seleccionado
        this.idCliente = null;
        // Deseleccionar el registro seleccionado de la tabla
        this.tablaClientes.getSelectionModel().clearSelection();
    }
}
