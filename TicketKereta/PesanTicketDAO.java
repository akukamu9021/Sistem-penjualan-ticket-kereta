package TicketKereta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 * Kelas DAO (Data Access Object) untuk menangani semua operasi database
 * yang berhubungan dengan tabel 'pemesanan'.
 */
public class PesanTicketDAO {

    /**
     * Metode untuk menyimpan objek Ticket (data pemesanan) ke dalam tabel 'pemesanan'.
     * @param ticket Objek Ticket yang akan disimpan.
     * @return true jika penyimpanan berhasil, false jika gagal.
     */
    public boolean simpan(Pemesanan ticket) {
        // Mengambil koneksi dari kelas DatabaseConnection Anda
        Connection conn = DatabaseConnetion.getConnection();
        
        // Query SQL untuk memasukkan data baru ke tabel 'pemesanan'
        String sql = "INSERT INTO pemesanan (kode_booking, id_pelanggan, id_jadwal, jumlah_tiket, total_bayar, status_pembayaran, waktu_pembelian, waktu_batas_bayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Menggunakan try-with-resources untuk memastikan PreparedStatement ditutup otomatis
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Mengisi parameter '?' dengan data dari objek Ticket
            ps.setString(1, ticket.getKodeTicket());
            ps.setInt(2, ticket.getIdPelanggan());
            ps.setInt(3, ticket.getIdJadwal());
            ps.setInt(4, ticket.getJumlahTiket());
            ps.setBigDecimal(5, ticket.getTotalBayar());
            ps.setString(6, ticket.getStatusPembayaran());
            ps.setTimestamp(7, Timestamp.valueOf(ticket.getWaktuPembelian()));
            
            // Menangani jika waktu_batas_bayar bisa null
            if (ticket.getWaktuBatasBayar() != null) {
                ps.setTimestamp(8, Timestamp.valueOf(ticket.getWaktuBatasBayar()));
            } else {
                ps.setNull(8, java.sql.Types.TIMESTAMP);
            }
            
            // Menjalankan query untuk menyimpan data
            int rowsAffected = ps.executeUpdate();
            
            // Mengembalikan true jika ada baris yang berhasil ditambahkan (rowsAffected > 0)
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Menampilkan pesan error yang jelas jika terjadi masalah dengan database
            JOptionPane.showMessageDialog(null, "Gagal menyimpan tiket: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Mencetak detail error di konsol untuk debugging
            return false;
        }
    }
    
    // Nanti bisa ditambahkan metode lain di sini, misalnya untuk mengambil riwayat pemesanan
}