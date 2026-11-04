package rmimahasiswa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MahasiswaService extends Remote {

    // Menampilkan seluruh data mahasiswa
    List<String> getAllMahasiswa() throws RemoteException;

    // Mencari mahasiswa berdasarkan NIM
    String getMahasiswaByNim(String nim) throws RemoteException;

    // Mencari mahasiswa berdasarkan nama
    List<String> cariMahasiswaByNama(String nama)
            throws RemoteException;

    // Menambahkan mahasiswa
    void tambahMahasiswa(String nim, String nama)
            throws RemoteException;

    // Memperbarui data mahasiswa
    void updateMahasiswa(String nim, String namaBaru)
            throws RemoteException;

    // Menghapus mahasiswa
    void hapusMahasiswa(String nim) throws RemoteException;

    // Menghitung jumlah mahasiswa
    int getJumlahMahasiswa() throws RemoteException;

    // Login administrator
    boolean loginAdmin(String username, String password)
            throws RemoteException;
}