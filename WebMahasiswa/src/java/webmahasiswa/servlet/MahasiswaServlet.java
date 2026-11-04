
package webmahasiswa.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rmimahasiswa.MahasiswaService;
import webmahasiswa.rmi.RMIConnection;

@WebServlet("/mahasiswa")
public class MahasiswaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("admin") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        try {
            MahasiswaService service =
                    RMIConnection.getService();

            List<String> data =
                    service.getAllMahasiswa();

            request.setAttribute("daftarMahasiswa", data);

            request.setAttribute(
                    "jumlahMahasiswa",
                    service.getJumlahMahasiswa());

            request.getRequestDispatcher(
                    "/mahasiswa.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                    "Gagal mengambil data mahasiswa", e);
        }
    }
}
