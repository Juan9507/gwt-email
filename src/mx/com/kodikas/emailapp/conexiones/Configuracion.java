package mx.com.kodikas.emailapp.conexiones;

public interface Configuracion {
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String DATA_BASE = "emails";
    String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
    String USERNAME = "root";
    String PASSWORD = "0709";
}
