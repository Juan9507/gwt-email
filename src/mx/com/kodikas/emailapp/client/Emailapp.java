package mx.com.kodikas.emailapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import mx.com.kodikas.emailapp.modelos.Email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Emailapp implements EntryPoint {

    CellTable<Email> tablaEmail;
    List<Email> emails = new ArrayList<Email>();
    TextArea msgArea = new TextArea();


    public void onModuleLoad() {
        DockLayoutPanel dock = new DockLayoutPanel(Style.Unit.EM);

        dock.addNorth(new Label("Bienvenido Juan"), 5);

        SplitLayoutPanel split1 = new SplitLayoutPanel();
        SplitLayoutPanel split2 = new SplitLayoutPanel();

        dock.add(split1);

        split1.addWest(buildBarraLateral(), 250);
        split1.add(split2);

        split2.addNorth(listaEmails(),200);
        msgArea.setWidth("1000");
        split2.add(msgArea);

        RootLayoutPanel.get().add(dock);

    }

    private Widget headers(String html, String text){
        FlowPanel panel = new FlowPanel();
        panel.add(new HTML(html));
        panel.add(new Label(text));
        return panel;
    }

    public Widget buildBarraLateral() {
        StackLayoutPanel stackLateral = new StackLayoutPanel(Style.Unit.EM);
        stackLateral.add(opcionesEmail(), headers("<i class='fas fa-box-open'></i>", "Mailbox"), 5);
        stackLateral.add(opcionesTareas(), headers("<i class='fas fa-tasks'></i>", "Tareas"), 5);
        return  stackLateral;
    }

    public Widget opcionesEmail(){
        TreeItem home = new TreeItem(headers("<i class='fas fa-home'></i>", "Home"));
        TreeItem inbox = new TreeItem(headers("<i class='fas fa-inbox'></i>", "Inbox"));
        TreeItem drafts = new TreeItem(headers("<i class='fas fa-pencil-ruler'></i>", "Drafts"));
        TreeItem templates = new TreeItem(headers("<i class='fas fa-gopuram'></i>", "Template"));
        TreeItem sent = new TreeItem(headers("<i class='fas fa-paper-plane'></i>", "Sent"));
        TreeItem trash = new TreeItem(headers("<i class='fas fa-trash'></i>", "Trash"));
        home.addItem(inbox);
        home.addItem(drafts);
        home.addItem(templates);
        home.addItem(sent);
        home.addItem(trash);

        Tree opcionesEmail = new Tree();

        opcionesEmail.addSelectionHandler(new SelectionHandler<TreeItem>() {
            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                if(event.getSelectedItem().getText().equals("Inbox")){
                    cargarEmails();
                }
            }
        });

        opcionesEmail.addItem(home);
        return opcionesEmail;
    }

    public Widget opcionesTareas(){
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(10);
        panel.add(new CheckBox("Ir por suministros"));
        panel.add(new CheckBox("Pasear al perro"));
        panel.add(new CheckBox("Iniciar una compania web 2.0"));
        panel.add(new CheckBox("Escribir una aplicacion cool en gwt"));
        panel.add(new CheckBox("Ganar dinero"));
        panel.add(new CheckBox("Tomar vacaciones"));

        return panel;
    }

    public Widget listaEmails(){
        tablaEmail = new CellTable<Email>();
        tablaEmail.addStyleName("Me-a");
        TextColumn<Email> colEmail = new TextColumn<Email>() {
            @Override
            public String getValue(Email object) {
                return object.getEmail();
            }
        };
        tablaEmail.addColumn(colEmail, "Email");

        TextColumn<Email> colAsunto = new TextColumn<Email>() {
            @Override
            public String getValue(Email object) {
                return object.getSubject();
            }
        };

        tablaEmail.addColumn(colAsunto, "Asunto");

        SingleSelectionModel<Email> modeloSeleccion = new SingleSelectionModel<Email>();
        tablaEmail.setSelectionModel(modeloSeleccion);
        modeloSeleccion.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                Email seleccionado = modeloSeleccion.getSelectedObject();
                if(seleccionado != null){
                    msgArea.setText(seleccionado.getMessage());
                }
            }
        });

        tablaEmail.setRowCount(emails.size());
        tablaEmail.setRowData(0, emails);

        return tablaEmail;

    }

    private EmailServiceAsync seviceEmail = GWT.create(EmailService.class);

    public void cargarEmails(){
        if(seviceEmail == null){
            seviceEmail = GWT.create(EmailService.class);
        }

        seviceEmail.getAllEmails(new AsyncCallback<Email[]>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(Email[] emailsArray) {
                emails = Arrays.asList(emailsArray);

            }
        });

        tablaEmail.setRowCount(emails.size());
        tablaEmail.setRowData(0, emails);
    }

}
