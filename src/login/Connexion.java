package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loader.Loader;
import mediatheque.Mediatheque;
import user.User;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Connection connectMySQL(String url, String log, String mdp) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			return DriverManager.getConnection(url, log, mdp);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

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

		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
	}
}
