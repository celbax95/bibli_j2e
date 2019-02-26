package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://localhost:3306/j2e?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String LOG = "jdbc";
	private static final String MDP = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection c = connectMySQL(URL, LOG, MDP);

		HttpSession ses = req.getSession();

		System.out.println("Connecte a la base de donnees");

		String nom = req.getParameter("log");
		String mdp = req.getParameter("pwd");

		boolean logIn = log(c, nom, mdp);
		User user = new User(nom, mdp, isBibli(c, nom, mdp));

		if (logIn) {
			ses.setAttribute("user", user);
		}

		req.setAttribute("logIn", logIn);
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
	}

	private boolean isBibli(Connection c, String nom, String mdp) {
		String req = "SELECT COUNT(*) FROM Utilisateur WHERE login = ? AND pass = ? AND bibli = 1";
		ResultSet res = null;
		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setString(1, nom);
			s.setString(2, mdp);
			res = s.executeQuery();

			return (res != null && res.next() && res.getInt(1) > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean log(Connection c, String nom, String mdp) {
		String req = "SELECT COUNT(*) FROM Utilisateur WHERE login = ? AND pass = ?";
		ResultSet res = null;

		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setString(1, nom);
			s.setString(2, mdp);
			res = s.executeQuery();

			return (res != null && res.next() && res.getInt(1) > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

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
}
