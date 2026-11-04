
package rmimahasiswa;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIMahasiswaClient {

    public static void main(String[] args) {
        try {
            // Menghubungkan Client ke RMI Server
            Registry registry = LocateRegistry.getRegistry(
                    "localhost", 1099);

            // Mengambil layanan MahasiswaService
            MahasiswaService service =
                    (MahasiswaService) registry.lookup(
                            "MahasiswaService");

            System.out.println("================================");
            System.out.println("CLIENT BERHASIL TERHUBUNG");
            System.out.println("================================");

            // Menampilkan seluruh mahasiswa
            System.out.println("\nDAFTAR MAHASISWA:");

            List<String> data = service.getAllMahasiswa();

            for (String mahasiswa : data) {
                System.out.println(mahasiswa);
            }

            // Menampilkan jumlah mahasiswa
            System.out.println("\nJumlah Mahasiswa: "
                    + service.getJumlahMahasiswa());

            // Mencari mahasiswa berdasarkan nama
            System.out.println("\nPENCARIAN NAMA AHMAD:");

            List<String> hasil =
                    service.cariMahasiswaByNama("Ahmad");

            for (String mahasiswa : hasil) {
                System.out.println(mahasiswa);
            }

        } catch (Exception e) {
            System.out.println("Client gagal terhubung!");
            e.printStackTrace();
        }
    }
}
