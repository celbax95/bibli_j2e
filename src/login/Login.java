package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		/*
		 * out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>" +
		 * getClass().getSimpleName() + "</title>\r\n" +
		 * "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
		 * "</head>");
		 *
		 * out.println("<body bgcolor=\"white\">");
		 *
		 * out.println("<form action=\"/projet/connexion\" method=\"get\">\r\n" +
		 * "<h2 style=\"text-decoration: underline;\">Connexion</h2>\r\n" +
		 * "<div class=\"input\">\r\n" + "<label for=\"log\">Nom :</label>\r\n" +
		 * "<input type=\"text\" id=\"pwd\" value=\"celbax95\" name=\"log\">\r\n" +
		 * "</div>\r\n" + "\r\n" + "<div class=\"input\">\r\n" +
		 * "<label for=\"pwd\">Mot de passe :</label>\r\n" +
		 * "<input type=\"password\" id=\"pwd\" value=\"MON_MOT_DE_PASSE\" name=\"pwd\">\r\n"
		 * + "</div>\r\n" + "\r\n" + "<div class=\"button\">\r\n" +
		 * "<button type=\"submit\">Valider</button>\r\n" + "</div>\r\n" + "\r\n" +
		 * "</form>");
		 *
		 * out.println("</body>"); out.println("</html>");
		 */
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

}
