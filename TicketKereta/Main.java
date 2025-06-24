package TicketKereta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Kelas Main adalah sebuah JFrame
public class Main extends JFrame {

    // 1. Semua komponen GUI dideklarasikan sebagai field dari kelas Main
    private JLabel labelJudul = new JLabel("LOGIN SISTEM TIKET KERETA");
    private JLabel labelUsername = new JLabel("Username:");
    private JTextField fieldUsername = new JTextField();
    private JLabel labelPassword = new JLabel("Password:");
    private JPasswordField fieldPassword = new JPasswordField();
    private JButton tombolLogin = new JButton("Login");
    private JButton tombolDaftar = new JButton("Daftar Akun Baru");

    // 2. Constructor: Kode untuk membangun jendela diletakkan di sini
    public Main() {
        // --- Pengaturan Dasar Jendela ---
        setTitle("Halaman Login");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat JPanel dengan background
        JPanel panelBackground = new JPanel(new GridBagLayout()) {
            private Image backgroundImage;
            {
                try {
                    backgroundImage = new ImageIcon(getClass().getResource("/resources/62776a168c685.jpg")).getImage();
                } catch (Exception e) {
                    System.err.println("Gagal memuat gambar background: " + e.getMessage());
                    setBackground(Color.DARK_GRAY); 
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

        // Konfigurasi Font dan Warna Teks
        labelJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelJudul.setForeground(Color.WHITE);
        labelUsername.setForeground(Color.WHITE);
        labelPassword.setForeground(Color.WHITE);

        // Menambahkan Komponen ke Panel Background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panelBackground.add(labelJudul, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panelBackground.add(labelUsername, gbc);
        gbc.gridx = 1; panelBackground.add(fieldUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelBackground.add(labelPassword, gbc);
        gbc.gridx = 1; panelBackground.add(fieldPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panelBackground.add(tombolLogin, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panelBackground.add(tombolDaftar, gbc);
        
        // Jadikan panelBackground sebagai panel utama
        this.setContentPane(panelBackground);

        // Menambahkan ActionListener untuk Tombol Login
        tombolLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fieldUsername.getText();
                String plainPassword = new String(fieldPassword.getPassword());

                if (username.isEmpty() || plainPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(Main.this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PelangganDAO dao = new PelangganDAO();
                Pelanggan user = dao.getByUsername(username);

                if (user != null) {
                    if (plainPassword.equals(user.getPasswordHash())) {
                        HalamanUtama halamanUtama = new HalamanUtama(user);
                        halamanUtama.setVisible(true);
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Password yang Anda masukkan salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Username '" + username + "' tidak ditemukan!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Menambahkan ActionListener untuk Tombol Daftar
        tombolDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register registerView = new Register();
                registerView.setVisible(true);
            }
        });
    }
        
    // 3. Metode main hanya untuk menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}