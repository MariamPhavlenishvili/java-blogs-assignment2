package com.exam.assignment;

import com.exam.assignment.models.Post;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// sql connection
import java.sql.*;


import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "postsServlet", value = "/posts-servlet")
public class PostsServlet extends HttpServlet {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/blogs";
        String username = "root";
        String password = "password";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = new ArrayList<>();

        try {
            // Establish the connection
            Connection conn = getConnection();

            // Create a statement and execute the query
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Posts";
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set and add the posts to the list
            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("metatitle"),
                        rs.getString("category"),
                        rs.getString("content"),
                        rs.getString("Author"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                posts.add(post);
            }

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Title</th><th>Metatitle</th><th>Content</th><th>Author</th><th>CreatedAt</th></tr></thead>");
            out.println("<tbody>");
            posts.forEach(post -> {
                out.println("<tr>");
                out.println("<td>" + post.getId() + "</td>");
                out.println("<td>" + post.getTitle() + "</td>");
                out.println("<td>" + post.getMetaTitle() + "</td>");
                out.println("<td>" + post.getContent() + "</td>");
                out.println("<td>" + post.getAuthor() + "</td>");
                out.println("<td>" + post.getCreatedAt() + "</td>");
                out.println("</tr>");
            });
            out.println("</tbody>");
            out.println("</table>");

            // Close the connection, statement, and result set
//            rs.close();
//            stmt.close();
//            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String mTitle = req.getParameter("Mtitle");
        String category = req.getParameter("category");
        String content = req.getParameter("content");
        String author = req.getParameter("author");

        PrintWriter out = resp.getWriter();

        String query = "INSERT INTO Posts (title, metatitle, category, content, Author) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, title);
            stmt.setString(2, mTitle);
            stmt.setString(3, category);
            stmt.setString(4, content);
            stmt.setString(5, author);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
//                out.println("<h1>New post created successfully</h1>");
                String redirectUrl = "posts-servlet";
                resp.setStatus(HttpServletResponse.SC_FOUND); // 302 status code
                resp.setHeader("Location", redirectUrl);
            } else {
                out.println("<h1>Error creating post</h1>");
            }

            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<h1>Error creating post</h1>");
            e.printStackTrace();
        }
    }


}