/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bacilio.components.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cbaciliod La declaración de prueba con recursos
 */
public class Database {

    private Connection connection;

    //aplicacion ----driver---- base de datos
    private Connection getConnection() {
        try {
            final String URL_DATABASE = "jdbc:ucanaccess://D://data//database.accdb";
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(URL_DATABASE);
            if (connection != null) {
                System.out.println("CONECTADO");
            } else {
                System.out.println("NO CONECTADO");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void main(String[] args) {
        Database database = new Database();
        database.selectRows();
    }

    private void insertRow() {
        Database database = new Database();
        final String QUERY_INSERT = "INSERT INTO PERSONA(name) VALUES ('JOSE')";

        try (
                //1.-OBTENER LA CONEXION A BASE DE DATOS
                Connection conn = database.getConnection(); //2.-PREPARAR LA CONSULTA
                 PreparedStatement ps = conn.prepareStatement(QUERY_INSERT);) {
            int row = ps.executeUpdate();
            System.out.println("ROWS " + row);
        } catch (Exception e) {
        }
    }

    private void selectRows() {
        Database database = new Database();
        final String QUERY_SELECT = "SELECT * FROM PERSONA";
        try (
                //1.-OBTENER LA CONEXION A BASE DE DATOS
                Connection conn = database.getConnection(); //2.-PREPARAR LA CONSULTA
                 PreparedStatement ps = conn.prepareStatement(QUERY_SELECT); //3.-EJECUTAR LA CONSULTA
                 ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                System.out.println("PERSONAS " + rs.getString("name"));
            }
        } catch (Exception e) {
        }
    }

    private void updateRow() {
        Database database = new Database();
        final String QUERY_UPDATE = "UPDATE PERSONA SET name = ? WHERE Id = ?";
        try (
                //1.-OBTENER LA CONEXION A BASE DE DATOS
                Connection conn = database.getConnection(); //2.-PREPARAR LA CONSULTA
                 PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE);) {
            System.out.println("QUERY_UPDATE " + QUERY_UPDATE);
            ps.setString(1, "ROBERT");
            ps.setInt(2, 1);
            int row = ps.executeUpdate();
            System.out.println("ROWS " + row);
        } catch (Exception e) {
        }
    }

    private void deleteRow() {
        Database database = new Database();
        final String QUERY_DELETE = "DELETE FROM PERSONA WHERE Id = ?";
        try (
                //1.-OBTENER LA CONEXION A BASE DE DATOS
                Connection conn = database.getConnection(); //2.-PREPARAR LA CONSULTA
                 PreparedStatement ps = conn.prepareStatement(QUERY_DELETE);) {
            ps.setInt(1, 2);
            int row = ps.executeUpdate();
            System.out.println("ROWS " + row);
        } catch (Exception e) {
        }
    }

}
