package TicketKereta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pemesanan {

    private int idPemesanan;
    private String kodeBooking;
    private int idPelanggan;
    private int idJadwal;
    private int jumlahTiket;
    private BigDecimal totalBayar;
    private String statusPembayaran;
    private LocalDateTime waktuPemesanan;
    private LocalDateTime waktuBatasBayar;

    // Constructor kosong yang bersih
    public Pemesanan() {
    }
    
    // Constructor lengkap
    public Pemesanan(int idPemesanan, String kodeBooking, int idPelanggan, int idJadwal, int jumlahTiket, BigDecimal totalBayar, String statusPembayaran, LocalDateTime waktuPemesanan, LocalDateTime waktuBatasBayar) {
        this.idPemesanan = idPemesanan;
        this.kodeBooking = kodeBooking;
        this.idPelanggan = idPelanggan;
        this.idJadwal = idJadwal;
        this.jumlahTiket = jumlahTiket;
        this.totalBayar = totalBayar;
        this.statusPembayaran = statusPembayaran;
        this.waktuPemesanan = waktuPemesanan;
        this.waktuBatasBayar = waktuBatasBayar;
    }

        public int getIdPemesanan() { return idPemesanan; }
        public void setIdPemesanan(int idPemesanan) { this.idPemesanan = idPemesanan; }
        public String getKodeBooking() { return kodeBooking; }
        public void setKodeBooking(String kodeBooking) { this.kodeBooking = kodeBooking; }
        public int getIdPelanggan() { return idPelanggan; }
        public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }
        public int getIdJadwal() { return idJadwal; }
        public void setIdJadwal(int idJadwal) { this.idJadwal = idJadwal; }
        public int getJumlahTiket() { return jumlahTiket; }
        public void setJumlahTiket(int jumlahTiket) { this.jumlahTiket = jumlahTiket; }
        public BigDecimal getTotalBayar() { return totalBayar; }
        public void setTotalBayar(BigDecimal totalBayar) { this.totalBayar = totalBayar; }
        public String getStatusPembayaran() { return statusPembayaran; }
        public void setStatusPembayaran(String statusPembayaran) { this.statusPembayaran = statusPembayaran; }
        public LocalDateTime getWaktuPemesanan() { return waktuPemesanan; }
        public void setWaktuPemesanan(LocalDateTime waktuPemesanan) { this.waktuPemesanan = waktuPemesanan; }
        public LocalDateTime getWaktuBatasBayar() { return waktuBatasBayar; }
        public void setWaktuBatasBayar(LocalDateTime waktuBatasBayar) { this.waktuBatasBayar = waktuBatasBayar; }

    void setKodeTicket(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setWaktuPembelian(LocalDateTime now) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getKodeTicket() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getWaktuPembelian() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}