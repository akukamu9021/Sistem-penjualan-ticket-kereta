/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketKereta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author USER90
 */
public class Register extends JDialog {

    // Deklarasi semua komponen GUI
    private final JLabel labelJudul = new JLabel("Formulir Pendaftaran Akun Baru");
    private final JTextField fieldNama = new JTextField(20);
    private final JTextField fieldUsername = new JTextField(20);
    private final JTextField fieldEmail = new JTextField(20);
    private final JPasswordField fieldPassword = new JPasswordField(20);
    private final JButton tombolDaftar = new JButton("Daftar");
    private final JButton tombolBatal = new JButton("Batal");

    public Register() {
        // --- Pengaturan Dasar JDialog ---
        setTitle("Pendaftaran Pelanggan Baru");
        setSize(450, 400);
        setLocationRelativeTo(null); // Muncul di tengah jendela utama
        setModal(true); // PENTING! Membuat dialog ini harus ditutup sebelum bisa kembali ke jendela lain.
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelJudul.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // --- Menyusun Komponen di Jendela ---

        // Baris 0: Judul
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelJudul, gbc);

        // Baris 1: Nama Lengkap
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1;
        add(fieldNama, gbc);

        // Baris 2: Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(fieldUsername, gbc);

        // Baris 3: Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(fieldEmail, gbc);
        
        // Baris 4: Password
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(fieldPassword, gbc);
        
        // Baris 5: Tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTombol.add(tombolBatal);
        panelTombol.add(tombolDaftar);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(panelTombol, gbc);
        
        // --- Menambahkan Aksi pada Tombol ---

        // Aksi untuk Tombol "Daftar"
        tombolDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Ambil semua data dari field input
                String nama = fieldNama.getText();
                String username = fieldUsername.getText();
                String email = fieldEmail.getText();
                String password = new String(fieldPassword.getPassword());

                // 2. Lakukan validasi sederhana (pastikan tidak ada yang kosong)
                if (nama.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Register.this, 
                        "Semua kolom harus diisi!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
                    return; // Hentikan proses jika ada yang kosong
                }

                // 3. Buat objek Pelanggan dengan data dari form
                Pelanggan pelangganBaru = new Pelanggan();
                pelangganBaru.setNamaLengkap(nama);
                pelangganBaru.setUsername(username);
                pelangganBaru.setEmail(email);
                pelangganBaru.setPasswordHash(password); // Untuk sementara, password masih teks biasa. Nanti akan di-hash oleh AuthService.

                // 4. Buat objek DAO dan panggil metode simpan()
                PelangganDAO pelangganDAO = new PelangganDAO();
                pelangganDAO.simpan(pelangganBaru);

                // 5. Beri pesan sukses dan tutup jendela registrasi
                JOptionPane.showMessageDialog(Register.this, 
                    "Registrasi Berhasil! Silakan login dengan akun baru Anda.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                dispose(); // Menutup jendela registrasi
            }
        });

        // Aksi untuk Tombol "Batal"
        tombolBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Langsung menutup jendela dialog pendaftaran
                dispose();
            }
        });
    }
}
