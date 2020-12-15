package UI;

import BL.Servidor;
import PL.Admin;
import PL.ControladorSessoes;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.in;

public class Menu {
    private ControladorSessoes CS;
    private Admin admin;
    private Servidor servidor;

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
        do {
            switch (i) {
                case 1:
                    if(trataInicioSessao()){
                        printMenuGestor();
                        int g = lerInt();
                        do{
                        switch (g){
                            case 1:
                                admin.consultarListaPaletes().toString();
                                break;
                            case 2:
                                servidor.getRobotsDisponiveis().toString();
                                break;
                            case 3:
                                servidor.getGestor_Pedidos().toString();
                                break;
                            case 4:
                                servidor.getGestor_Pedidos().toString();
                                break;

                        }

                    } while (g!=0);
                    }
                    else {
                        System.out.println("Credenciais Erradas");
                        run();
                    }
                    break;
                case 2:
                    printMenuAdmin();
                    int a = lerInt();
                    do{
                        switch (a){
                            case 1:
                                trataAddGestor();
                                break;
                            case 2:
                                trataRemGestor();
                                break;
                        }
                    } while (a!=0);
                    break;
            }
        } while (i!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
    }


   public boolean trataInicioSessao (){
       System.out.println("CodID:");
       String c = lerString();
       System.out.println("Password:");
       String p = lerString();
       return CS.iniciaSessao(p,c);

   }

   public void trataAddGestor (){
       System.out.println("CodID:");
       String c = lerString();
       System.out.println("Nome:");
       String n = lerString();
       admin.addUser(c,n);
   }

    public void trataRemGestor (){
        System.out.println("CodID:");
        String c = lerString();
        admin.deleteUser(c);
    }
}
