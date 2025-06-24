package TicketKereta;

// Pastikan semua import ini ada di bagian atas file
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // <- Kemungkinan ini yang hilang
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Kelas DAO untuk menangani semua operasi database
 * yang berhubungan dengan tabel 'pemesanan'.
 */
public class PemesananDAO {

    /**
     * Metode untuk menyimpan objek Pemesanan ke dalam tabel 'pemesanan'.
     * @param pemesanan Objek Pemesanan yang akan disimpan.
     * @return true jika penyimpanan berhasil, false jika gagal.
     */
    public boolean simpan(Pemesanan pemesanan) {
        Connection conn = DatabaseConnetion.getConnection();
        String sql = "INSERT INTO pemesanan (kode_booking, id_pelanggan, id_jadwal, jumlah_tiket, total_bayar, status_pembayaran, waktu_pemesanan, waktu_batas_bayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pemesanan.getKodeBooking());
            ps.setInt(2, pemesanan.getIdPelanggan());
            ps.setInt(3, pemesanan.getIdJadwal());
            ps.setInt(4, pemesanan.getJumlahTiket());
            ps.setBigDecimal(5, pemesanan.getTotalBayar());
            ps.setString(6, pemesanan.getStatusPembayaran());
            ps.setTimestamp(7, Timestamp.valueOf(pemesanan.getWaktuPemesanan()));
            
            if (pemesanan.getWaktuBatasBayar() != null) {
                ps.setTimestamp(8, Timestamp.valueOf(pemesanan.getWaktuBatasBayar()));
            } else {
                ps.setNull(8, java.sql.Types.TIMESTAMP);
            }
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan pemesanan: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metode untuk mengambil semua riwayat pemesanan milik satu pelanggan.
     * @param idPelanggan ID dari pelanggan yang riwayatnya akan dicari.
     * @return List berisi objek Pemesanan.
     */
    public List<Pemesanan> getPemesananByPelangganId(int idPelanggan) {
        List<Pemesanan> daftarPemesanan = new ArrayList<>();
        Connection conn = DatabaseConnetion.getConnection();
        String sql = "SELECT * FROM pemesanan WHERE id_pelanggan = ? ORDER BY waktu_pemesanan DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPelanggan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pemesanan pesanan = new Pemesanan();
                    pesanan.setIdPemesanan(rs.getInt("id_pemesanan"));
                    pesanan.setKodeBooking(rs.getString("kode_booking"));
                    pesanan.setIdPelanggan(rs.getInt("id_pelanggan"));
                    pesanan.setIdJadwal(rs.getInt("id_jadwal"));
                    pesanan.setJumlahTiket(rs.getInt("jumlah_tiket"));
                    pesanan.setTotalBayar(rs.getBigDecimal("total_bayar"));
                    pesanan.setStatusPembayaran(rs.getString("status_pembayaran"));
                    pesanan.setWaktuPemesanan(rs.getTimestamp("waktu_pemesanan").toLocalDateTime());
                    
                    daftarPemesanan.add(pesanan);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil riwayat pemesanan: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return daftarPemesanan;
    }
}