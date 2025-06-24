package TicketKereta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Kelas Model ini merepresentasikan satu baris data dari tabel 'jadwal_perjalanan'.
 */
public class JadwalPerjalanan {

    // 1. Semua field dibuat private
    private int idJadwal;
    private String kodeJadwal;
    private int idKereta;
    private String stasiunKeberangkatan;
    private String stasiunTujuan;
    private LocalDateTime waktuKeberangkatan;
    private LocalDateTime estimasiWaktuTiba;
    private BigDecimal hargaTiket;
    private int sisaKursi;

    // 2. Wajib ada constructor kosong yang bersih seperti ini
    public JadwalPerjalanan() {
    }

    // 3. Constructor lengkap (ini opsional, tapi baik untuk ada)
    public JadwalPerjalanan(int idJadwal, String kodeJadwal, int idKereta, String stasiunKeberangkatan, String stasiunTujuan, LocalDateTime waktuKeberangkatan, LocalDateTime estimasiWaktuTiba, BigDecimal hargaTiket, int sisaKursi) {
        this.idJadwal = idJadwal;
        this.kodeJadwal = kodeJadwal;
        this.idKereta = idKereta;
        this.stasiunKeberangkatan = stasiunKeberangkatan;
        this.stasiunTujuan = stasiunTujuan;
        this.waktuKeberangkatan = waktuKeberangkatan;
        this.estimasiWaktuTiba = estimasiWaktuTiba;
        this.hargaTiket = hargaTiket;
        this.sisaKursi = sisaKursi;
    }

    // 4. Getter dan Setter yang lengkap dan benar
    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getKodeJadwal() {
        return kodeJadwal;
    }

    public void setKodeJadwal(String kodeJadwal) {
        this.kodeJadwal = kodeJadwal;
    }

    public int getIdKereta() {
        return idKereta;
    }

    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }

    public String getStasiunKeberangkatan() {
        return stasiunKeberangkatan;
    }

    public void setStasiunKeberangkatan(String stasiunKeberangkatan) {
        this.stasiunKeberangkatan = stasiunKeberangkatan;
    }

    public String getStasiunTujuan() {
        return stasiunTujuan;
    }

    public void setStasiunTujuan(String stasiunTujuan) {
        this.stasiunTujuan = stasiunTujuan;
    }

    public LocalDateTime getWaktuKeberangkatan() {
        return waktuKeberangkatan;
    }

    public void setWaktuKeberangkatan(LocalDateTime waktuKeberangkatan) {
        this.waktuKeberangkatan = waktuKeberangkatan;
    }

    public LocalDateTime getEstimasiWaktuTiba() {
        return estimasiWaktuTiba;
    }

    public void setEstimasiWaktuTiba(LocalDateTime estimasiWituTiba) {
        this.estimasiWaktuTiba = estimasiWituTiba;
    }

    public BigDecimal getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(BigDecimal hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public int getSisaKursi() {
        return sisaKursi;
    }

    public void setSisaKursi(int sisaKursi) {
        this.sisaKursi = sisaKursi;
    }
}