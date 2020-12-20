package UI;

import PL.ControladorSessoes;

import java.util.ArrayList;
import java.util.List;

public class UI {

    public static void notifica(String texto) {
        System.out.println(texto);
    }
    public static void print_mapa(Integer[][] mapa, int l, int c){
        List<String> linha = new ArrayList<>();
        for (int i = 0; i < l; i++){
            for(int j = 0; j < c; j++){
                linha.add(mapa[i][j]+"");
            }
            System.out.println(linha);
            linha.removeAll(linha);
        }
    }
    
}
