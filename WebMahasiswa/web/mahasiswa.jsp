
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<String> data =
        (List<String>) request.getAttribute("daftarMahasiswa");

    Integer jumlah =
        (Integer) request.getAttribute("jumlahMahasiswa");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Data Mahasiswa</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 30px;
        }

        .container {
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            color: #243b55;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #243b55;
            color: white;
        }

        .info {
            background: #e8f0fe;
            padding: 12px;
            border-radius: 5px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #243b55;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        form {
    margin-top: 15px;
    margin-bottom: 20px;
}

input {
    padding: 10px;
    margin-right: 8px;
    margin-bottom: 8px;
}

button {
    padding: 10px 15px;
    background: #243b55;
    color: white;
    border: none;
    cursor: pointer;
}
    </style>
</head>

<body>

<div class="container">

    <h2>Data Mahasiswa</h2>
    
    
<h3>Pencarian Mahasiswa</h3>

<form action="cariMahasiswa" method="get">
    <input type="text"
           name="nama"
           placeholder="Cari berdasarkan nama..."
           value="<%= request.getAttribute("kataKunci") != null
                   ? request.getAttribute("kataKunci") : "" %>">

    <button type="submit">Cari</button>

    <a href="mahasiswa">Tampilkan Semua</a>
</form>

    
    <h3>Tambah Mahasiswa</h3>

<form action="tambahMahasiswa" method="post">
    <input type="text" name="nim"
           placeholder="Masukkan NIM" required>

    <input type="text" name="nama"
           placeholder="Masukkan Nama Mahasiswa" required>

    <button type="submit">Tambah Mahasiswa</button>
</form>

    <div class="info">
        Jumlah Mahasiswa:
        <strong><%= jumlah != null ? jumlah : 0 %></strong>
    </div>

    
<table>
    <tr>
        <th>No.</th>
        <th>NIM</th>
        <th>Nama Mahasiswa</th>
        <th>Aksi</th>
    </tr>

    <%
        if (data != null && !data.isEmpty()) {
            int no = 1;

            for (String mahasiswa : data) {
                String[] bagian = mahasiswa.split(" - ", 2);
                String nim = bagian[0];
                String nama = bagian.length > 1 ? bagian[1] : "";
    %>

    <tr>
        <td><%= no++ %></td>
        <td><%= nim %></td>
        <td><%= nama %></td>

        <td>
            <form action="<%= request.getContextPath() %>/editMahasiswa"
                  method="post">
                <input type="hidden" name="nim"
                       value="<%= nim %>">

                <input type="text" name="nama"
                       value="<%= nama %>" required>

                <button type="submit">Edit</button>
            </form>

            <form action="<%= request.getContextPath() %>/hapusMahasiswa"
                  method="post"
                  onsubmit="return confirm('Yakin ingin menghapus data ini?');">

                <input type="hidden" name="nim"
                       value="<%= nim %>">

                <button type="submit">Hapus</button>
            </form>
        </td>
    </tr>

    <%
            }
        } else {
    %>

    <tr>
        <td colspan="4">Belum ada data mahasiswa.</td>
    </tr>

    <%
        }
    %>
</table>


    <a href="dashboard.jsp">Kembali ke Dashboard</a>

</div>

</body>
</html>
