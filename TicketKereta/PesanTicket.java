package TicketKereta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class PesanTicket extends JDialog {

    private Pelanggan pelanggan;
    private List<JadwalPerjalanan> hasilPencarian; // Untuk menyimpan hasil pencarian

    // Komponen GUI
    private JTable tabelJadwal;
    private DefaultTableModel tableModel;
    private JScrollPane panelTabel = new JScrollPane();
    private JTextField fieldJumlahTiket = new JTextField("1", 5);
    private JButton tombolPesan = new JButton("Pesan Tiket Terpilih");

    public PesanTicket(Frame owner, Pelanggan pelanggan) { 
        super(owner, "Pilih Jadwal Keberangkatan", true);
        this.pelanggan = pelanggan;

        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        // --- Panel Tengah (Tabel Jadwal) ---
        String[] kolom = {"Kode", "Asal", "Tujuan", "Waktu Berangkat", "Waktu Tiba", "Harga", "Sisa Kursi"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelJadwal = new JTable(tableModel);
        tabelJadwal.setDefaultEditor(Object.class, null);
        tabelJadwal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelTabel.setViewportView(tabelJadwal);
        add(panelTabel, BorderLayout.CENTER);
        
        // --- Panel Bawah (Aksi Pemesanan) ---
        JPanel panelPesan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelPesan.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panelPesan.add(new JLabel("Jumlah Tiket:"));
        panelPesan.add(fieldJumlahTiket);
        panelPesan.add(tombolPesan);
        add(panelPesan, BorderLayout.SOUTH);

        loadSemuaJadwalKeTabel();

        // Di dalam file PesanTicket.java

        tombolPesan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barisPilihan = tabelJadwal.getSelectedRow();
                if (barisPilihan == -1) {
                    JOptionPane.showMessageDialog(PesanTicket.this, "Harap pilih salah satu jadwal dari tabel!", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JadwalPerjalanan jadwalTerpilih = hasilPencarian.get(barisPilihan);

                int jumlahTiket;
                try {
                    jumlahTiket = Integer.parseInt(fieldJumlahTiket.getText());
                    if (jumlahTiket <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PesanTicket.this, "Jumlah tiket harus berupa angka positif!", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (jumlahTiket > jadwalTerpilih.getSisaKursi()) {
                    JOptionPane.showMessageDialog(PesanTicket.this, "Jumlah tiket yang dipesan melebihi sisa kursi!", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // === KODE YANG DIPERBAIKI DI SINI ===
                // Membuat objek dari kelas Pemesanan
                Pemesanan pesananBaru = new Pemesanan();

                // Menggunakan nama metode setter yang benar dari kelas Pemesanan
                pesananBaru.setKodeBooking("KAI-" + System.currentTimeMillis());      // <-- DIPERBAIKI dari setKodeTicket
                pesananBaru.setIdPelanggan(pelanggan.getIdPelanggan());
                pesananBaru.setIdJadwal(jadwalTerpilih.getIdJadwal());
                pesananBaru.setJumlahTiket(jumlahTiket);
                pesananBaru.setTotalBayar(jadwalTerpilih.getHargaTiket().multiply(new BigDecimal(jumlahTiket)));
                pesananBaru.setStatusPembayaran("LUNAS");
                pesananBaru.setWaktuPemesanan(LocalDateTime.now());                    // <-- DIPERBAIKI dari setWaktuPembelian

                PemesananDAO pemesananDAO = new PemesananDAO();
                boolean simpanBerhasil = pemesananDAO.simpan(pesananBaru);

                if (simpanBerhasil) {
                    JadwalPerjalananDAO jadwalDAO = new JadwalPerjalananDAO();
                    jadwalDAO.updateSisaKursi(jadwalTerpilih.getIdJadwal(), jumlahTiket);
                    JOptionPane.showMessageDialog(PesanTicket.this, "Pemesanan berhasil! Kode Booking Anda: " + pesananBaru.getKodeBooking(), "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadSemuaJadwalKeTabel();
                }
            }
        });
    }
    
    private void loadSemuaJadwalKeTabel() {
        JadwalPerjalananDAO dao = new JadwalPerjalananDAO();
        this.hasilPencarian = dao.getAllJadwal();

        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        if (hasilPencarian != null && !hasilPencarian.isEmpty()) {
            for (JadwalPerjalanan jadwal : hasilPencarian) {
                Object[] rowData = {
                    jadwal.getKodeJadwal(),
                    jadwal.getStasiunKeberangkatan(),
                    jadwal.getStasiunTujuan(),
                    jadwal.getWaktuKeberangkatan().format(formatter),
                    jadwal.getEstimasiWaktuTiba().format(formatter),
                    jadwal.getHargaTiket(),
                    jadwal.getSisaKursi()
                };
                tableModel.addRow(rowData);
            }
        }
    }
}