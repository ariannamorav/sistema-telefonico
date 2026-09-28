package com.sistematelefonico;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try (Connection conexion = DatabaseConnection.conectar()) {

            System.out.println("CONEXION EXITOSA A POSTGRESQL");
            System.out.println("Base de datos: sistema_telefonico");

        } catch (Exception e) {

            System.out.println("ERROR DE CONEXION");
            e.printStackTrace();
        }
    }
}