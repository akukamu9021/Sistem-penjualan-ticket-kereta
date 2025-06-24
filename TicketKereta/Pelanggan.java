/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketKereta;
import java.time.LocalDateTime;
/**
 *
 * @author USER90
 */
public class Pelanggan {
    private int idPelanggan;
    private String namaLengkap;
    private String username;
    private String email;
    private String passwordHash;
    private String preferensiKelas;
    private String noTelepon;
    private LocalDateTime tanggalRegistrasi;

    // Constructor kosong, ini penting!
    public Pelanggan() {
    }

    // Constructor lengkap (opsional, tapi baik untuk ada)
    public Pelanggan(int idPelanggan, String namaLengkap, String username, String email, String passwordHash, String preferensiKelas, String noTelepon, LocalDateTime tanggalRegistrasi) {
        this.idPelanggan = idPelanggan;
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.preferensiKelas = preferensiKelas;
        this.noTelepon = noTelepon;
        this.tanggalRegistrasi = tanggalRegistrasi;
    }

    // --- Getters dan Setters yang benar ---

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPreferensiKelas() {
        return preferensiKelas;
    }

    public void setPreferensiKelas(String preferensiKelas) {
        this.preferensiKelas = preferensiKelas;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public LocalDateTime getTanggalRegistrasi() {
        return tanggalRegistrasi;
    }

    public void setTanggalRegistrasi(LocalDateTime tanggalRegistrasi) {
        this.tanggalRegistrasi = tanggalRegistrasi;
    }
}
