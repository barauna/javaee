package com.barauna.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value= "/Principal", name="Principal", initParams = {
				@WebInitParam(name="cidade", value="vix"),
				@WebInitParam(name="estado", value="es")
			})
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean desvia = true;
		Pessoa alguem = new Pessoa("Leo", 41);
		request.setAttribute("pessoa", alguem);
		//normal(request, response);
		
		if(desvia) {
			dispatch(request, response);
		}
	}

	private void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Secundario");
		rd.forward(request, response);
	}

	private void normal(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		System.out.println(getInitParameter("cidade"));
		System.out.println(getInitParameter("estado"));
		
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		
		String pais = request.getLocale().getCountry();
		String server = request.getServerName();
		String metodo = request.getMethod();
		int porta = request.getServerPort();
		
		Enumeration<String> atributos = request.getHeaderNames();
				
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>hello " + nome + " " + sobrenome + "</p>");
		
		out.println("<table>");
		while(atributos.hasMoreElements()){
			out.println("<tr>");
			String atributo = atributos.nextElement();
			String valor = request.getHeader(atributo).toString();
			out.println("<td>" + atributo + ":</td><td>" + valor + "</td>");
			out.println("</tr>");
		}
		
		out.println("<tr>");
		out.println("<td>Pais:</td>");
		out.println("<td>" + pais  + "</td>");
		out.println("</tr>");
				
		out.println("<tr>");
		out.println("<td>Server:</td>");
		out.println("<td>" + server  + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Metodo:</td>");
		out.println("<td>" + metodo  + "</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Porta:</td>");
		out.println("<td>" + porta  + "</td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("<br><br>");
		out.println("<a href=principal.html>Voltar</a><br>");
		out.println("<a href=Secundario>Secundario</a>");	
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
