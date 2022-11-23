package mx.com.kodikas.emailapp.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatosMysql implements Configuracion {

    private static Connection con = null; //VARIABLE PARA LA CONEXION
    //CONECTAR DIRECTAMENTE

    static {
        try {
            Class.forName(DRIVER); // LE PASAMOS EL NOMBRE DE ESPACIO QUE TRAE EL CONECTOR PARA MYSQL (SE CARGA MEDIANTE EL METODO forName() DE LA CLASE  java.lang.Class)
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);// SINTAXIS PARA REALIZAR LA CONEXION
            if (con != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error en conexión : " + e);
        }
    }

    public Connection getConnection() {//METODO DE TIPO CONNECTION PARA RETONAR LA CONEXION
        return con;
    }

    public void desconectarBD() {
        try {
            con.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println("Error en cerrar : " + e);
        }

    }

}
