package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = req.getParameter("log");
		String mdp = req.getParameter("pwd");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");

		out.println("<title> " + getClass().getSimpleName() + " </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<p>" + nom + "</p>");
		out.println("<p>" + mdp + "</p>");
		out.println("");

		out.println("</body>");
		out.println("</html>");
	}

}
