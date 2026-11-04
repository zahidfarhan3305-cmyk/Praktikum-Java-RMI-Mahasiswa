package rmimahasiswa;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MahasiswaServiceImpl extends UnicastRemoteObject
        implements MahasiswaService {

    // Penyimpanan data mahasiswa yang mendukung akses bersamaan
    private final ConcurrentHashMap<String, String> mahasiswa;

    // Constructor
    public MahasiswaServiceImpl() throws RemoteException {
        super();

        mahasiswa = new ConcurrentHashMap<>();

        // Data awal mahasiswa
        mahasiswa.put("252601001", "Ahmad");
        mahasiswa.put("252601002", "Budi");
        mahasiswa.put("252601003", "Citra");
    }

    // 1. Menampilkan seluruh mahasiswa
    @Override
    public List<String> getAllMahasiswa() throws RemoteException {

        List<String> hasil = new ArrayList<>();

        mahasiswa.forEach((nim, nama) ->
                hasil.add(nim + " - " + nama));

        return hasil;
    }

    // 2. Mencari mahasiswa berdasarkan NIM
    @Override
    public String getMahasiswaByNim(String nim)
            throws RemoteException {

        String nama = mahasiswa.get(nim);

        if (nama == null) {
            return "Mahasiswa tidak ditemukan";
        }

        return nim + " - " + nama;
    }

    // 3. Mencari mahasiswa berdasarkan nama
    @Override
    public List<String> cariMahasiswaByNama(String nama)
            throws RemoteException {

        List<String> hasil = new ArrayList<>();

        mahasiswa.forEach((nim, namaMahasiswa) -> {

            if (namaMahasiswa.toLowerCase()
                    .contains(nama.toLowerCase())) {

                hasil.add(nim + " - " + namaMahasiswa);
            }
        });

        return hasil;
    }

    // 4. Menambahkan mahasiswa
    @Override
    public void tambahMahasiswa(String nim, String nama)
            throws RemoteException {

        if (nim == null || nim.trim().isEmpty()
                || nama == null || nama.trim().isEmpty()) {
            throw new RemoteException("NIM dan nama wajib diisi!");
        }

        String hasil = mahasiswa.putIfAbsent(
                nim.trim(), nama.trim());

        if (hasil != null) {
            throw new RemoteException("NIM sudah terdaftar!");
        }
    }

    // 5. Memperbarui data mahasiswa
    @Override
    public void updateMahasiswa(String nim, String namaBaru)
            throws RemoteException {

        if (namaBaru == null || namaBaru.trim().isEmpty()) {
            throw new RemoteException("Nama baru wajib diisi!");
        }

        String hasil = mahasiswa.replace(nim, namaBaru.trim());

        if (hasil == null) {
            throw new RemoteException("Mahasiswa tidak ditemukan!");
        }
    }

    // 6. Menghapus mahasiswa
    @Override
    public void hapusMahasiswa(String nim)
            throws RemoteException {

        String hasil = mahasiswa.remove(nim);

        if (hasil == null) {
            throw new RemoteException("Mahasiswa tidak ditemukan!");
        }
    }

    // 7. Menghitung jumlah mahasiswa
    @Override
    public int getJumlahMahasiswa() throws RemoteException {

        return mahasiswa.size();
    }

    // 8. Login administrator
    @Override
    public boolean loginAdmin(String username, String password)
            throws RemoteException {

        return "admin".equals(username)
                && "admin123".equals(password);
    }
}
