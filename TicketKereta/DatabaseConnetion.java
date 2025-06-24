/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketKereta;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author USER90
 */
public class DatabaseConnetion {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ticket_kereta";
            String user = "root";       // ubah sesuai user MySQL kamu
            String pass = "";           // ubah jika pakai password

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil!");
        } catch (Exception e) {
            System.out.println("Gagal konek ke database: " + e.getMessage());
        }
        return conn;
    }
}
