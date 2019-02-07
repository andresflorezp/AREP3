package edu.eci.arsw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeerFichero {
    
    public static String muestraContenido(String archivo) throws IOException {
        String linea;
        String resultado="";
        //Path file = Paths.get("Resources"+archivo);
        BufferedReader in = new BufferedReader(new FileReader(archivo));
        while((linea=in.readLine())!=null) {
           resultado+=linea;
        }
        in.close();
        return resultado;
    }
    /*
    public static void main(String args[]) throws IOException {


        System.out.println(LeerFichero.muestraContenido("/index.html"));



    }
    */

}