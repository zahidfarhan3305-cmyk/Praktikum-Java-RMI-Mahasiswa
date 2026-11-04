
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrator</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            margin: 0;
        }

        .header {
            background: #243b55;
            color: white;
            padding: 20px;
        }

        .container {
            padding: 30px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        a {
            display: inline-block;
            padding: 12px 18px;
            background: #243b55;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px;
        }

        .logout {
            background: #c0392b;
        }
    </style>
</head>

<body>

    <div class="header">
        <h2>Dashboard Administrator</h2>
        <p>Selamat datang, ${sessionScope.admin}!</p>
    </div>

    <div class="container">

        <div class="card">
            <h3>Manajemen Data Mahasiswa</h3>
            <p>Kelola data mahasiswa melalui sistem ini.</p>

            <a href="mahasiswa">Data Mahasiswa</a>
            <a href="logout" class="logout">Logout</a>
        </div>

    </div>

</body>
</html>
