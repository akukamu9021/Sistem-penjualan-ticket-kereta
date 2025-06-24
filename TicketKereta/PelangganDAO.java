/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketKereta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class PelangganDAO {

    public void simpan(Pelanggan pelanggan) {
        // Mengambil koneksi dari kelas DatabaseConnetion Anda
        Connection conn = DatabaseConnetion.getConnection(); 
        
        // Query SQL untuk memasukkan data baru
        String sql = "INSERT INTO pelanggan (nama_lengkap, username, email, password_hash, preferensi_kelas, no_telepon) VALUES (?, ?, ?, ?, ?, ?)";

        // Menggunakan try-with-resources untuk memastikan PreparedStatement ditutup otomatis
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Mengisi parameter '?' dengan data dari objek pelanggan
            ps.setString(1, pelanggan.getNamaLengkap());
            ps.setString(2, pelanggan.getUsername());
            ps.setString(3, pelanggan.getEmail());
            ps.setString(4, pelanggan.getPasswordHash());
            ps.setString(5, pelanggan.getPreferensiKelas()); // Pastikan ini bisa NULL jika tidak diisi
            ps.setString(6, pelanggan.getNoTelepon());       // Pastikan ini bisa NULL jika tidak diisi

            // Menjalankan query untuk menyimpan data
            ps.executeUpdate();
            
        } catch (SQLException e) {
            // Menampilkan pesan error yang jelas jika terjadi masalah dengan database
            JOptionPane.showMessageDialog(null, 
                "DATABASE ERROR saat menyimpan pelanggan: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Mencetak detail error di konsol untuk debugging
        }
    }

    /**
     * Metode untuk mencari seorang pelanggan berdasarkan username-nya (untuk proses login).
     * @param username Username yang akan dicari.
     * @return Objek Pelanggan jika ditemukan, atau null jika tidak ditemukan.
     */
    public Pelanggan getByUsername(String username) {
        Connection conn = DatabaseConnetion.getConnection(); 
        String sql = "SELECT * FROM pelanggan WHERE username = ?";
        Pelanggan pelanggan = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                // Jika data ditemukan, buat objek Pelanggan
                if (rs.next()) {
                    pelanggan = new Pelanggan();
                    pelanggan.setIdPelanggan(rs.getInt("id_pelanggan"));
                    pelanggan.setNamaLengkap(rs.getString("nama_lengkap"));
                    pelanggan.setUsername(rs.getString("username"));
                    pelanggan.setEmail(rs.getString("email"));
                    pelanggan.setPasswordHash(rs.getString("password_hash"));
                    pelanggan.setPreferensiKelas(rs.getString("preferensi_kelas"));
                    pelanggan.setNoTelepon(rs.getString("no_telepon"));
                    pelanggan.setTanggalRegistrasi(rs.getTimestamp("tanggal_registrasi").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, 
                "DATABASE ERROR saat mengambil data pelanggan: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return pelanggan; // Akan mengembalikan null jika tidak ditemukan
    }
}