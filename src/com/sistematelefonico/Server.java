package com.sistematelefonico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Server {

    private static final int PUERTO = 9999;

    public static void main(String[] args) {

        System.out.println("SERVIDOR DEL SISTEMA TELEFÓNICO");
        System.out.println("Iniciando servidor...");

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {

            System.out.println("Servidor iniciado correctamente.");
            System.out.println("Esperando conexión del cliente en el puerto " + PUERTO + "...");

            while (true) {

                try (Socket cliente = servidor.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(
                             cliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: "
                            + cliente.getInetAddress());

                    String telefono = entrada.readLine();

                    System.out.println("Número recibido: " + telefono);

                    String respuesta = buscarPersona(telefono);

                    salida.println(respuesta);

                    System.out.println("Respuesta enviada al cliente.");
                }

            }

        } catch (Exception e) {

            System.out.println("ERROR EN EL SERVIDOR");
            e.printStackTrace();
        }
    }

    private static String buscarPersona(String telefono) {

        String sql = """
                SELECT p.dir_tel,
                       p.dir_tipo_tel,
                       p.dir_nombre,
                       p.dir_direccion,
                       c.ciud_nombre
                FROM personas p
                JOIN ciudades c
                    ON p.dir_ciud_id = c.ciud_id
                WHERE p.dir_tel = ?
                """;

        try (Connection conexion = DatabaseConnection.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, telefono);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    return """
                            PERSONA ENCONTRADA
                            Teléfono: %s
                            Tipo: %s
                            Nombre: %s
                            Dirección: %s
                            Ciudad: %s
                            """.formatted(
                            resultado.getString("dir_tel"),
                            resultado.getString("dir_tipo_tel"),
                            resultado.getString("dir_nombre"),
                            resultado.getString("dir_direccion"),
                            resultado.getString("ciud_nombre")
                    );

                } else {

                    return "Persona dueña de ese número telefónico no existe.";
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            return "Error al consultar la base de datos.";
        }
    }
}