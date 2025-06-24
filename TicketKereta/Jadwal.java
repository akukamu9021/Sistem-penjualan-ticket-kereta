package TicketKereta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Jadwal extends JDialog {

    private JTable tabelJadwal;
    private DefaultTableModel tableModel;

    public Jadwal(Frame owner) {
        super(owner, "Daftar Semua Jadwal Keberangkatan", true);
        setSize(800, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Setup tabel
        String[] kolom = {"Kode", "Asal", "Tujuan", "Waktu Berangkat", "Waktu Tiba", "Harga", "Sisa Kursi"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelJadwal = new JTable(tableModel);
        tabelJadwal.setDefaultEditor(Object.class, null); // Membuat tabel tidak bisa diedit

        add(new JScrollPane(tabelJadwal), BorderLayout.CENTER);
        
        // Tombol tutup
        JButton tombolTutup = new JButton("Tutup");
        tombolTutup.addActionListener(e -> dispose());
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBawah.add(tombolTutup);
        add(panelBawah, BorderLayout.SOUTH);

        // Muat data jadwal ke tabel
        loadSemuaJadwalKeTabel();
    }

    private void loadSemuaJadwalKeTabel() {
        JadwalPerjalananDAO dao = new JadwalPerjalananDAO();
        List<JadwalPerjalanan> daftarJadwal = dao.getAllJadwal();

        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        if (daftarJadwal.isEmpty()) {
            // Menampilkan pesan jika tidak ada jadwal sama sekali di database
            tableModel.addRow(new Object[]{"Tidak ada jadwal tersedia saat ini.", "", "", "", "", "", ""});
        } else {
            for (JadwalPerjalanan jadwal : daftarJadwal) {
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