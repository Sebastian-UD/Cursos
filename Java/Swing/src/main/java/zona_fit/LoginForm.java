package zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginForm extends JFrame{
    private JPanel panelPrincipal;
    private JTextField usuarioTexto;
    private JPasswordField passwordTexto;
    private JButton enviarButton;

    public LoginForm(){
        inicializarForma();
        enviarButton.addActionListener(e -> validar());
    }

    private void inicializarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);//centrar la ventana
    }

    private void validar(){
        // Leer los valores
        String usuario = this.usuarioTexto.getText();
        String password = new String(this.passwordTexto.getPassword());
        if("root".equals(usuario) && "admin".equals(password))
            mostrarMensaje("Datos correstos, bienvenidos");
        else if("root".equals(usuario))
            mostrarMensaje("Password incorrecto, corregirlo");
        else
            mostrarMensaje("Usuario incorrecto, corregirlo");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();//cambiar look and feel a dark
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
