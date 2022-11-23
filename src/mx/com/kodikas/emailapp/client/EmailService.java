package mx.com.kodikas.emailapp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import mx.com.kodikas.emailapp.modelos.Email;

@RemoteServiceRelativePath("servicioEmail")
public interface EmailService extends RemoteService {

    Email[] getAllEmails();
}
