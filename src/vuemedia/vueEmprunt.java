package vuemedia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loader.Loader;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import user.User;

public class vueEmprunt extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = (User) ses.getAttribute("user");

		if (u == null) {
			resp.sendRedirect("./log");
			return;
		}

		Integer idDoc = (req.getParameter("idDoc") != null ? Integer.valueOf((req.getParameter("idDoc"))) : null);

		if (idDoc != null) {
			emprunter(u, idDoc);
			resp.sendRedirect("./vueMedia");
			return;
		}

		req.setAttribute("bibli", u.isBibliothecaire());
		req.setAttribute("docs", Mediatheque.getInstance().tousLesDocuments());

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueMedia.jsp").forward(req, resp);
	}

	private void emprunter(User u, Integer idDoc) {
		String req = "INSERT INTO emprunt VALUES(?,?)";
		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, idDoc);
			s.setInt(2, u.getId());
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Mediatheque m = Mediatheque.getInstance();
		try {
			m.emprunt(m.getDocument(idDoc), u);
		} catch (EmpruntException e) {
			System.out.println("Impossible d'emprunter ce document.");
		}
	}

	public static Connection connectMySQL(String url, String log, String mdp) {
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
