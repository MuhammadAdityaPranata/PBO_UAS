package CRUD;

import java.util.List;
import javax.swing.JOptionPane;

public class Main {
    private static final WisataDAO wisataDAO = new WisataDAO();
    
    public static void main(String[] args) {
        String menu = """
                      === Aplikasi CRUD Wisata ===
                      1. Tambah Wisata
                      2. Edit Wisata
                      3. Hapus Wisata
                      4. Tampilkan Wisata
                      0. Keluar
                      
                      Pilih menu (1/2/3/4/0): """;
        
        int pilihan = -1;
        while (pilihan != 0) {
            try {
                pilihan = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (pilihan) {
                    case 1 -> tambahWisata();
                    case 2 -> editWisata();
                    case 3 -> hapusWisata();
                    case 4 -> tampilkanWisata();
                    case 0 -> System.out.println("Terima kasih!");
                    default -> JOptionPane.showMessageDialog(null, "Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!");
            }
        }
    }
    
    private static void tambahWisata() {
        String nama = JOptionPane.showInputDialog("Masukkan nama wisata:");
        String info = JOptionPane.showInputDialog("Masukkan informasi wisata:");
        
        Wisata wisata = new Wisata();
        wisata.setNamaWisata(nama);
        wisata.setInfoWisata(info);
        
        wisataDAO.tambahWisata(wisata);
        JOptionPane.showMessageDialog(null, "Data wisata berhasil ditambahkan!");
    }
    
    private static void editWisata() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Masukkan ID wisata yang ingin diedit:"));
        String nama = JOptionPane.showInputDialog("Masukkan nama wisata baru:");
        String info = JOptionPane.showInputDialog("Masukkan informasi wisata baru:");
        
        Wisata wisata = new Wisata();
        wisata.setIdWisata(id);
        wisata.setNamaWisata(nama);
        wisata.setInfoWisata(info);
        
        wisataDAO.updateWisata(wisata);
        JOptionPane.showMessageDialog(null, "Data wisata berhasil diupdate!");
    }
    
    private static void hapusWisata() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Masukkan ID wisata yang ingin dihapus:"));
        wisataDAO.hapusWisata(id);
        JOptionPane.showMessageDialog(null, "Data wisata berhasil dihapus!");
    }
    
    private static void tampilkanWisata() {
        List<Wisata> wisataList = wisataDAO.getAllWisata();
        StringBuilder sb = new StringBuilder("Daftar Wisata:\n\n");
        for (Wisata wisata : wisataList) {
            sb.append("ID: ").append(wisata.getIdWisata()).append("\n");
            sb.append("Nama: ").append(wisata.getNamaWisata()).append("\n");
            sb.append("Info: ").append(wisata.getInfoWisata()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
