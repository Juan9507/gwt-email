package mx.com.kodikas.emailapp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import mx.com.kodikas.emailapp.modelos.Email;

public interface EmailServiceAsync {
    void getAllEmails(AsyncCallback<Email[]> async);
}
