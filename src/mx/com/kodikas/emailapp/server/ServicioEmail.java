package mx.com.kodikas.emailapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import mx.com.kodikas.emailapp.client.EmailService;
import mx.com.kodikas.emailapp.dao.EmailDAO;
import mx.com.kodikas.emailapp.modelos.Email;

public class ServicioEmail extends RemoteServiceServlet implements EmailService {

    @Override
    public Email[] getAllEmails() {
        EmailDAO daoEmail = new EmailDAO();

        return daoEmail.getEmails();
    }
}
