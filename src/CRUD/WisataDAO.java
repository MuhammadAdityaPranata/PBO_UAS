package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WisataDAO {
    private Connection connection;
    
    public WisataDAO() {
        connection = DatabaseConnection.getConnection();
    }
    
    // Method untuk menambahkan data wisata
    public void tambahWisata(Wisata wisata) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO wisata (nama_wisata, info_wisata) VALUES (?, ?)");
            ps.setString(1, wisata.getNamaWisata());
            ps.setString(2, wisata.getInfoWisata());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method untuk menghapus data wisata berdasarkan id
    public void hapusWisata(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM wisata WHERE id_wisata=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method untuk mengupdate data wisata
    public void updateWisata(Wisata wisata) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE wisata SET nama_wisata=?, info_wisata=? WHERE id_wisata=?");
            ps.setString(1, wisata.getNamaWisata());
            ps.setString(2, wisata.getInfoWisata());
            ps.setInt(3, wisata.getIdWisata());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method untuk mendapatkan semua data wisata
    public List<Wisata> getAllWisata() {
        List<Wisata> wisataList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM wisata");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wisata wisata = new Wisata();
                wisata.setIdWisata(rs.getInt("id_wisata"));
                wisata.setNamaWisata(rs.getString("nama_wisata"));
                wisata.setInfoWisata(rs.getString("info_wisata"));
                wisataList.add(wisata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wisataList;
    }
}
