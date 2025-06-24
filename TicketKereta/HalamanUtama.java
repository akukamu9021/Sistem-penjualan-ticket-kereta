package TicketKereta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanUtama extends JFrame {

    private Pelanggan pelanggan;

    // Komponen GUI
    private JLabel labelSelamatDatang = new JLabel();
    private JButton tombolPesanTiket = new JButton("Pesan Tiket Kereta");
    private JButton tombolRiwayat = new JButton("Riwayat Pemesanan");
    private JButton tombolLihatJadwal = new JButton("Lihat Semua Jadwal");
    private JButton tombolLogout = new JButton("Logout");

    public HalamanUtama(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;

        setTitle("Menu Utama - Aplikasi Tiket Kereta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat JPanel utama yang memiliki background
        JPanel panelUtama = new JPanel(new BorderLayout(10, 10)) {
            private Image backgroundImage;
            {
                try {
                    // PASTIKAN PATH DAN NAMA FILE INI BENAR
                    backgroundImage = new ImageIcon(getClass().getResource("/resources/62776a168c685.jpg")).getImage();
                } catch (Exception e) {
                    System.err.println("Gagal memuat gambar background untuk Halaman Utama: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        
        // Menjadikan panel utama sebagai content pane dari JFrame
        setContentPane(panelUtama);

        // --- Panel Atas (Header) ---
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHeader.setOpaque(false); // Buat panel transparan agar background terlihat
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelSelamatDatang.setText("Selamat Datang, " + this.pelanggan.getNamaLengkap() + "!");
        labelSelamatDatang.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelSelamatDatang.setForeground(Color.BLACK); // Ganti warna teks agar kontras
        panelHeader.add(labelSelamatDatang);
        
        panelUtama.add(panelHeader, BorderLayout.NORTH);

        // --- Panel Tengah (Menu Tombol) ---
        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));
        panelMenu.setOpaque(false); // Buat panel transparan
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        
        Font fontTombol = new Font("Segoe UI", Font.PLAIN, 18);
        tombolPesanTiket.setFont(fontTombol);
        tombolRiwayat.setFont(fontTombol);
        tombolLihatJadwal.setFont(fontTombol);
        tombolLogout.setFont(fontTombol);
        tombolLogout.setBackground(new Color(217, 83, 79));
        tombolLogout.setForeground(Color.WHITE);

        panelMenu.add(tombolPesanTiket);
        panelMenu.add(tombolRiwayat);
        panelMenu.add(tombolLihatJadwal);
        panelMenu.add(tombolLogout);

        panelUtama.add(panelMenu, BorderLayout.CENTER);

        // --- Menambahkan Aksi pada Tombol ---
        tombolLogout.addActionListener(e -> {
            int pilihan = JOptionPane.showConfirmDialog(HalamanUtama.this, "Apakah Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                dispose();
                new Main().setVisible(true);
            }
        });
        
        tombolPesanTiket.addActionListener(e -> {
            new PesanTicket(HalamanUtama.this, pelanggan).setVisible(true);
        });
        
        tombolRiwayat.addActionListener(e -> {
            new Riwayat(HalamanUtama.this, pelanggan).setVisible(true);
        });
        
        tombolLihatJadwal.addActionListener(e -> {
            new Jadwal(HalamanUtama.this).setVisible(true);
        });
    }
}