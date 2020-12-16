package GUI;

import BL.Requisicao;
import PL.Controlador;
import PL.ControladorSessoes;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class View implements GUI {

    private Controlador c;
    private TextField usertxt, txt, txt2, txt3, txt4;
    private PasswordField passwordtxt;
    private ListView<String> listView;

    public View(){
        listView = new ListView<>();
        listView.getItems().addAll("a");
        c = new ControladorSessoes();
    }

    public static void alert(String titulo, String mensagem){
        Stage w = new Stage();
        w.initModality(Modality.APPLICATION_MODAL);
        w.setTitle(titulo);
        w.setMinWidth(300);

        Label label = new Label();
        label.setText(mensagem);
        Button closeButton = new Button("Fechar.");
        closeButton.setOnAction(e -> w.close());

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        w.setScene(scene);
        w.showAndWait();
    }

    public Scene menu() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        listView = new ListView<>();
        listView.getItems().addAll(
                "Registar Gestor", "Login de Gestor", "Painel de Robots",
                "Entregas Ativas", "Requisições Ativas", "Requisições Feitas",
                "Entregas Feitas"
        );
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Button b1 = new Button("Escolher.");
        b1.setOnAction(e -> escolher_menu());

        Button b2 = new Button("Sair.");
        b2.setOnAction(e -> {
            c.save();
            Platform.exit();
        });

        Button b3 = new Button("Guardar.");
        b3.setOnAction(e -> {
            c.save();
            c.end_scene(e);
            make_window("Menu Principal", menu());
        });
        layout.getChildren().addAll(listView,b1,b2,b3);
        return new Scene(layout, 400, 400);
    }

    public void make_window(String title, Scene s){
        Stage w = new Stage();
        w.setTitle(title);
        w.setScene(s);
        w.show();
    }

    private void escolher_menu(){
        String s = String.valueOf(listView.getSelectionModel().getSelectedItems());
        if(s.equals("[Registar Gestor]")){
            Stage w = new Stage();
            w.setTitle("Resgistar Gestor");
            w.setScene(registar_gestor());
            w.show();
        }

        if(s.equals("[Login Gestor]")){
            Stage w = new Stage();
            w.setTitle("Login de Gestor");
            w.setScene(login_gestor());
            w.show();
        }

        if(s.equals("[Painel de Robots]")){
            Stage w = new Stage();
            w.setTitle("Painel de Robots");
            w.setScene(painel_robot());
            w.show();
        }

        if(s.equals("[Entregas Ativas]")){
            Stage w = new Stage();
            w.setTitle("Painel das Entregas Ativas");
            w.setScene(painel_pedido(c.lista_EA()));
            w.show();
        }

        if(s.equals("[Requisições Ativas]")){
            Stage w = new Stage();
            w.setTitle("Painel das Requisições Ativas");
            w.setScene(painel_pedido(c.lista_RA()));
            w.show();
        }

        if(s.equals("[Entregas Feitas]")){
            Stage w = new Stage();
            w.setTitle("Painel das Entregas Feitas");
            w.setScene(painel_pedido(c.lista_EF()));
            w.show();
        }

        if(s.equals("[Requisições Feitas]")){
            Stage w = new Stage();
            w.setTitle("Painel das Requisições Feitas");
            w.setScene(painel_pedido(c.lista_RF()));
            w.show();
        }
    }

    @Override
    public Scene login_gestor() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        usertxt = new TextField();

        Label lblUser = new Label("Código ID de Gestor");

        passwordtxt = new PasswordField();
        Label lblPassword = new Label("Password");

        Button b = new Button("Login.");
        b.setOnAction(e -> {
            String user = usertxt.getText();
            String pwd = passwordtxt.getText();
            c.logInGestor(user, pwd);
            c.end_scene(e);
        });

        layout.getChildren().addAll(lblUser, usertxt, lblPassword, passwordtxt, b);
        return new Scene(layout, 400, 300);
    }

    @Override
    public Scene registar_gestor() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        txt = new TextField();
        Label lblNome = new Label("Nome Completo");

        usertxt = new TextField();
        Label lblUser = new Label("Username");

        passwordtxt = new PasswordField();
        Label lblPassword = new Label("Password");

        Button b = new Button("Registar.");
        b.setOnAction(e -> {
            String user = usertxt.getText();
            String pwd = passwordtxt.getText();
            String nome = txt.getText();

            if(user.equals("")) alert("Email NULL", "Precisa de inserir um email para se registar.");
            if(pwd.equals("")) alert("Password NULL", "Precisa de inserir uma palavra-passe para se registar.");
            if(nome.equals("")) alert("Nome NULL", "Precisa de inserir um nome para se registar.");
            else {
                c.validaRegisto(user, pwd, nome);
                c.end_scene(e);
            }
        });

        layout.getChildren().addAll(lblNome, txt, lblUser, usertxt, lblPassword, passwordtxt, b);
        return new Scene(layout, 500, 400);
    }

    @Override
    public Scene painel_pedido(List<String> lista) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        listView= new ListView<>();
        listView.getItems().addAll(lista);

        layout.getChildren().addAll(listView);
        return new Scene(layout, 600, 500);
    }

    @Override
    public Scene painel_robot() {
        return null;
    }
}
