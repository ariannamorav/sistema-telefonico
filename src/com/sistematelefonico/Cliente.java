package com.sistematelefonico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 9999;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(HOST, PUERTO);
                BufferedReader teclado = new BufferedReader(
                        new InputStreamReader(System.in));
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(
                        socket.getOutputStream(), true)
        ) {

            System.out.println("CLIENTE DEL SISTEMA TELEFÓNICO");

            System.out.print("Ingrese el número telefónico: ");

            String telefono = teclado.readLine();

            salida.println(telefono);

            System.out.println();
            System.out.println("Respuesta del servidor:");

            String linea;

            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("--------------------------------------");

        } catch (Exception e) {

            System.out.println("ERROR AL CONECTARSE CON EL SERVIDOR");
            e.printStackTrace();
        }
    }
}