package TicketKereta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Riwayat extends JDialog {

    private Pelanggan pelanggan;
    private JTable tabelRiwayat;
    private DefaultTableModel tableModel;

    public Riwayat(Frame owner, Pelanggan pelanggan) {
        super(owner, "Riwayat Pemesanan Anda", true);
        this.pelanggan = pelanggan;

        setSize(800, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Setup tabel
        String[] kolom = {"Kode Booking", "Rute", "Jadwal Berangkat", "Jumlah Tiket", "Total Bayar", "Status"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelRiwayat = new JTable(tableModel);
        tabelRiwayat.setDefaultEditor(Object.class, null);

        add(new JScrollPane(tabelRiwayat), BorderLayout.CENTER);

        // Muat data riwayat
        loadRiwayat();
    }

    private void loadRiwayat() {
        PemesananDAO pemesananDAO = new PemesananDAO();
        JadwalPerjalananDAO jadwalDAO = new JadwalPerjalananDAO();

        // 1. Ambil semua pesanan milik pelanggan ini
        List<Pemesanan> daftarPemesanan = pemesananDAO.getPemesananByPelangganId(pelanggan.getIdPelanggan());

        // Kosongkan tabel
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // 2. Untuk setiap pesanan, ambil detail jadwalnya dan tampilkan di tabel
        for (Pemesanan p : daftarPemesanan) {
            JadwalPerjalanan jadwal = jadwalDAO.getJadwalById(p.getIdJadwal());
            
            String rute = "N/A";
            String waktuBerangkat = "N/A";

            if (jadwal != null) {
                rute = jadwal.getStasiunKeberangkatan() + " -> " + jadwal.getStasiunTujuan();
                waktuBerangkat = jadwal.getWaktuKeberangkatan().format(formatter);
            }

            Object[] rowData = {
                p.getKodeBooking(),
                rute,
                waktuBerangkat,
                p.getJumlahTiket(),
                p.getTotalBayar(),
                p.getStatusPembayaran()
            };
            tableModel.addRow(rowData);
        }
    }
}