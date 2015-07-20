package com.barauna.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Secundario")
public class Secundario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Secundario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Pessoa p = (Pessoa)request.getAttribute("pessoa");
		out.println("<html>");
		out.println("<body>");
		out.println("<p>i´m secondary servlet</p>");
		out.println("<p>" + request.getServletContext().getInitParameter("cidade") + "</p>");
		out.println("<p>" + p.getNome() + "</p>");
		out.println("</body>");
		out.println("</html>");	
	}

}
