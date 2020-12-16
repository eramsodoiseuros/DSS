package UI;

import BL.Servidor;
import PL.Admin;
import PL.ControladorSessoes;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.in;

public class Menu implements UI {
    private ControladorSessoes cs;

    public Menu(){
        cs = new ControladorSessoes();
    }

    public void printMenu(){
        System.out.println("1. Iniciar Sessão");
        System.out.println("2. Criar conta");
        System.out.println("0. Sair");
    }

    public void printMenuGestor(){
        System.out.println("1. Consultar Inventario");
        System.out.println("2. Verificar robots disponiveis");
        System.out.println("3. Verificar Requisições");
        System.out.println("4. Verificar Entregas");
        System.out.println("0. Terminar Sessão");
    }

    public void printMenuAdmin(){
        System.out.println("1. Adicionar Gestor");
        System.out.println("2. Remover Gestor");
        System.out.println("0. Sair");
    }

    public int lerInt() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        int i = 0;
        while(!ok) {
            try {
                i = input.nextInt();
                ok = true;
            }
            catch(InputMismatchException e){
                System.out.println("Inteiro Invalido");
                System.out.println("Novo valor: ");
                input.nextLine();
            }
        }

        return i;
    }

    public String lerString() {
        Scanner input = new Scanner(in);
        boolean ok = false;
        String txt = "";
        while(!ok) {
            try {
                txt = input.nextLine();
                ok = true;
            }
            catch(InputMismatchException e) {
                System.out.println("Texto Invalido");
                System.out.println("Novo valor: ");
                input.nextLine();
            }
        }
        //input.close();
        return txt;
    }

    public void run() {
        printMenu();
        int i = lerInt();
        boolean dentro0 = true;
        String output;
        while (dentro0){
            switch (i) {
                case 1:
                    if(trataInicioSessao()){
                        printMenuGestor();
                        boolean dentro1 = true;
                        int g = lerInt();
                        while (dentro1) {
                            switch (g) {
                                case 1:
                                    output = cs.consultarListaPaletes().toString();
                                    System.out.println(output);
                                    dentro1 = false;
                                    break;
                                case 2:
                                    output = cs.getRobotsDisponiveis();
                                    System.out.println(output);
                                    dentro1 = false;
                                    break;
                                case 3:
                                    output = cs.getGestor_Pedidos();
                                    System.out.println(output);
                                    dentro1 = false;
                                    break;
                                case 4:
                                    output = cs.getGestor_Pedidos();
                                    System.out.println(output);
                                    dentro1 = false;
                                    break;
                                case 0:
                                    trataFimSessao();
                            }
                        }
                    }
                    else {
                        System.out.println("Credenciais Erradas");
                        run();
                    }
                    break;
                case 2:
                    printMenuAdmin();
                    boolean dentro2 = true;
                    int a = lerInt();
                    while (dentro2){
                        switch (a){
                            case 1:
                                trataAddGestor();
                                dentro2 = false;
                                break;
                            case 2:
                                trataRemGestor();
                                dentro2 = false;
                                break;
                        }
                    }
                    run();
                    break;
            }
            if(i == 0) dentro0 = false;
        }
        System.out.println("Até breve!...");
    }

    private void trataFimSessao() {
       // isto
    }


    public boolean trataInicioSessao (){
       System.out.println("CodID:");
       String c = lerString();
       System.out.println("Password:");
       String p = lerString();
       boolean r = false;
       try{
           r = cs.iniciaSessao(c,p);
       } catch (NullPointerException e){
           System.out.println("Não existem Gestores neste Armazém.");
       }
       return r;
   }

   public void trataAddGestor (){
       System.out.println("CodID:");
       String c = lerString();
       System.out.println("Nome:");
       String n = lerString();
       cs.addUser(c,n);
   }

    public void trataRemGestor (){
        System.out.println("CodID:");
        String c = lerString();
        cs.deleteUser(c);
    }

    @Override
    public void notifica(String texto) {

    }
}
