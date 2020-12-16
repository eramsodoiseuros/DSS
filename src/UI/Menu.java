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

    //
    public void printMenu(){
        System.out.println("1. Iniciar Sessão");
        System.out.println("2. Criar conta");
        System.out.println("0. Sair");
    }

    //
    public void printMenuGestor(){
        System.out.println("1. Consultar Inventario");
        System.out.println("2. Verificar robots disponiveis");
        System.out.println("3. Verificar Requisições");
        System.out.println("4. Verificar Entregas");
        System.out.println("0. Terminar Sessão");
    }

    @Override
    public void notifica(String texto) {
        System.out.println(texto);
    }
}
