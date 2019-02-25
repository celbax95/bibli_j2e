package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL = "jdbc:mysql://localhost:3306/j2e?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String LOG = "jdbc";
	private static final String MDP = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection c = connectMySQL(URL, LOG, MDP);

		System.out.println("Connecte a la base de donnees");

		String nom = req.getParameter("log");
		String mdp = req.getParameter("pwd");

		boolean logIn = log(c, nom, mdp);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");

		out.println("<title> " + getClass().getSimpleName() + " </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		if (logIn) {
			out.println("<p>" + nom + "</p>");
			out.println("<p>" + mdp + "</p>");
		} else {
			out.println("<p>Vous n'etes pas connect�</p>");
		}
		out.println("");

		out.println("</body>");
		out.println("</html>");
	}

	private boolean log(Connection c, String nom, String mdp) {
		String req = "SELECT COUNT(*) FROM Utilisateur WHERE login = ? AND pass = ?";
		ResultSet res = null;

		try {
			PreparedStatement s = c.prepareStatement(req);
			System.out.println(s);
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
