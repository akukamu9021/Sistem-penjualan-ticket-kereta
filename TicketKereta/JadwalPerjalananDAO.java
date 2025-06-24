package TicketKereta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JadwalPerjalananDAO {

    /**
     * Mengambil SEMUA jadwal perjalanan dari database yang akan datang.
     * Digunakan oleh PesanTicket.java untuk mengisi tabel.
     * @return sebuah List dari objek JadwalPerjalanan.
     */
    public List<JadwalPerjalanan> getAllJadwal() {
        List<JadwalPerjalanan> daftarJadwal = new ArrayList<>();
        Connection conn = DatabaseConnetion.getConnection();
        String sql = "SELECT * FROM jadwal_perjalanan WHERE waktu_keberangkatan > NOW() ORDER BY waktu_keberangkatan ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                JadwalPerjalanan jadwal = new JadwalPerjalanan();
                jadwal.setIdJadwal(rs.getInt("id_jadwal"));
                jadwal.setKodeJadwal(rs.getString("kode_jadwal"));
                jadwal.setIdKereta(rs.getInt("id_kereta"));
                jadwal.setStasiunKeberangkatan(rs.getString("stasiun_keberangkatan"));
                jadwal.setStasiunTujuan(rs.getString("stasiun_tujuan"));
                jadwal.setWaktuKeberangkatan(rs.getTimestamp("waktu_keberangkatan").toLocalDateTime());
                jadwal.setEstimasiWaktuTiba(rs.getTimestamp("estimasi_waktu_tiba").toLocalDateTime());
                jadwal.setHargaTiket(rs.getBigDecimal("harga_tiket"));
                jadwal.setSisaKursi(rs.getInt("sisa_kursi"));
                
                daftarJadwal.add(jadwal);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil semua jadwal: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return daftarJadwal;
    }

    /**
     * Mengambil detail satu jadwal perjalanan berdasarkan ID-nya.
     * Digunakan oleh RiwayatView.java.
     * @param idJadwal ID dari jadwal yang akan dicari.
     * @return Objek JadwalPerjalanan jika ditemukan, atau null.
     */
    public JadwalPerjalanan getJadwalById(int idJadwal) {
        Connection conn = DatabaseConnetion.getConnection();
        String sql = "SELECT * FROM jadwal_perjalanan WHERE id_jadwal = ?";
        JadwalPerjalanan jadwal = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idJadwal);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jadwal = new JadwalPerjalanan();
                    jadwal.setIdJadwal(rs.getInt("id_jadwal"));
                    jadwal.setKodeJadwal(rs.getString("kode_jadwal"));
                    jadwal.setIdKereta(rs.getInt("id_kereta"));
                    jadwal.setStasiunKeberangkatan(rs.getString("stasiun_keberangkatan"));
                    jadwal.setStasiunTujuan(rs.getString("stasiun_tujuan"));
                    jadwal.setWaktuKeberangkatan(rs.getTimestamp("waktu_keberangkatan").toLocalDateTime());
                    jadwal.setEstimasiWaktuTiba(rs.getTimestamp("estimasi_waktu_tiba").toLocalDateTime());
                    jadwal.setHargaTiket(rs.getBigDecimal("harga_tiket"));
                    jadwal.setSisaKursi(rs.getInt("sisa_kursi"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil detail jadwal: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return jadwal;
    }

    /**
     * Metode untuk mengurangi sisa kursi setelah pemesanan berhasil.
     * Digunakan oleh PesanTicket.java.
     * @param idJadwal ID dari jadwal yang akan diupdate.
     * @param jumlahDipesan Jumlah tiket yang dipesan.
     */
    public void updateSisaKursi(int idJadwal, int jumlahDipesan) {
        Connection conn = DatabaseConnetion.getConnection();
        String sql = "UPDATE jadwal_perjalanan SET sisa_kursi = sisa_kursi - ? WHERE id_jadwal = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jumlahDipesan);
            ps.setInt(2, idJadwal);
            ps.executeUpdate();
            System.out.println("Sisa kursi untuk jadwal ID " + idJadwal + " berhasil diupdate.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal update sisa kursi: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}