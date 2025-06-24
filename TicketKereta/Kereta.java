/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketKereta;

/**
 *
 * @author USER90
 */
public class Kereta {
    int idKereta;
    String namaKereta;
    String kelasLayanan;
    int kapasitasTotal;

    public Kereta(int idKereta, String namaKereta, String kelasLayanan, int kapasitasTotal) {
        this.idKereta = idKereta;
        this.namaKereta = namaKereta;
        this.kelasLayanan = kelasLayanan;
        this.kapasitasTotal = kapasitasTotal;
    }

    public int getIdKereta() {
        return idKereta;
    }

    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public String getKelasLayanan() {
        return kelasLayanan;
    }

    public void setKelasLayanan(String kelasLayanan) {
        this.kelasLayanan = kelasLayanan;
    }

    public int getKapasitasTotal() {
        return kapasitasTotal;
    }

    public void setKapasitasTotal(int kapasitasTotal) {
        this.kapasitasTotal = kapasitasTotal;
    }
}
