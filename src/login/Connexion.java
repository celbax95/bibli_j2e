package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;
import user.User;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Mediatheque m = Mediatheque.getInstance();

		HttpSession ses = req.getSession();

		System.out.println("Connecte a la base de donnees");

		String nom = req.getParameter("log");
		String mdp = req.getParameter("pwd");

		User user = null;

		user = (User) m.getUser(nom, mdp);

		if (user != null) {
			ses.setAttribute("user", user);
		}

		req.setAttribute("logIn", user != null);

		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
	}
}
