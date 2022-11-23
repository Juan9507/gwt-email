package mx.com.kodikas.emailapp.dao;

import mx.com.kodikas.emailapp.conexiones.BaseDatosMysql;
import mx.com.kodikas.emailapp.modelos.Email;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO {

    public Email[] getEmails(){
        List<Email> emails = new ArrayList<Email>();
        Email email = null;

        try {
            BaseDatosMysql baseDatosMysql = new BaseDatosMysql();
            String sql = "select * from emails_recibidos";
            PreparedStatement preparedStatement = baseDatosMysql.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String email_string = resultSet.getString("email");
                String subject = resultSet.getString("subject");
                String message = resultSet.getString("message");

                email = new Email();
                email.setEmail(email_string);
                email.setSubject(subject);
                email.setMessage(message);
                email.setId(id);
                emails.add(email);
            }
           // baseDatosMysql.desconectarBD();
            Email[] emailArray = new Email[emails.size()];
            return emails.toArray(emailArray);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
