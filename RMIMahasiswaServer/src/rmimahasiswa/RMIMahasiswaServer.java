package rmimahasiswa;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIMahasiswaServer {

    public static void main(String[] args) {
        try {
            // Membuat RMI Registry pada port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Membuat objek layanan mahasiswa
            MahasiswaService service = new MahasiswaServiceImpl();

            // Mendaftarkan layanan ke RMI Registry
            registry.rebind("MahasiswaService", service);

            System.out.println("================================");
            System.out.println("RMI SERVER BERHASIL DIJALANKAN");
            System.out.println("Port       : 1099");
            System.out.println("Service    : MahasiswaService");
            System.out.println("================================");

        } catch (Exception e) {
            System.out.println("Server gagal dijalankan!");
            e.printStackTrace();
        }
    }
}
