package com.springboot.empc.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/server")
public class MockServ extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            // Uses throws exception
            throws ServletException, IOException {
        resp.setContentType("text/html");

        // sets Content Type
        PrintWriter ab = resp.getWriter();

        String Name = req.getParameter("Name");

        // thiswill return value
        ab.println(" Welcome User " + Name);

        // this will print the Name Welcome User
        ab.close();
    }
}