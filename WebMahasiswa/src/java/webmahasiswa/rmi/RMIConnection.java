
package webmahasiswa.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmimahasiswa.MahasiswaService;

public class RMIConnection {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1099;
    private static final String SERVICE_NAME = "MahasiswaService";

    public static MahasiswaService getService() throws Exception {

        Registry registry = LocateRegistry.getRegistry(
                SERVER_HOST, SERVER_PORT);

        return (MahasiswaService) registry.lookup(SERVICE_NAME);
    }
}
