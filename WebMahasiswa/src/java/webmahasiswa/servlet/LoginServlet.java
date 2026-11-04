
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            MahasiswaService service = RMIConnection.getService();

            boolean berhasil =
                    service.loginAdmin(username, password);

            if (berhasil) {

                HttpSession session = request.getSession();
                session.setAttribute("admin", username);

                response.sendRedirect(
                        request.getContextPath() + "/dashboard.jsp");

            } else {

                request.setAttribute(
                        "pesan", "Username atau password salah!");

                request.getRequestDispatcher(
                        "/login.jsp").forward(request, response);
            }

        } catch (Exception e) {

            throw new ServletException(
                    "Gagal menghubungkan ke RMI Server", e);
        }
    }
}
