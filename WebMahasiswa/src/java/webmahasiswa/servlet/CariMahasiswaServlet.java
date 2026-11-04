
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

@WebServlet("/cariMahasiswa")
public class CariMahasiswaServlet extends HttpServlet {

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

        String nama = request.getParameter("nama");

        try {
            MahasiswaService service = RMIConnection.getService();

            List<String> hasil =
                    service.cariMahasiswaByNama(
                            nama == null ? "" : nama.trim());

            request.setAttribute("daftarMahasiswa", hasil);
            request.setAttribute("jumlahMahasiswa",
                    service.getJumlahMahasiswa());
            request.setAttribute("kataKunci", nama);

            request.getRequestDispatcher(
                    "/mahasiswa.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                    "Gagal mencari mahasiswa", e);
        }
    }
}

