
package webmahasiswa.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rmimahasiswa.MahasiswaService;
import webmahasiswa.rmi.RMIConnection;

@WebServlet("/tambahMahasiswa")
public class TambahMahasiswaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nim = request.getParameter("nim");
        String nama = request.getParameter("nama");

        try {
            MahasiswaService service = RMIConnection.getService();
            service.tambahMahasiswa(nim, nama);

            response.sendRedirect(
                    request.getContextPath() + "/mahasiswa");

        } catch (Exception e) {
            throw new ServletException(
                    "Gagal menambahkan mahasiswa", e);
        }
    }
}
