package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "root";

        try{
            //Clase de conexion específica para MySql
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);
        }
        catch(Exception e){
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return conexion;
    }
}
