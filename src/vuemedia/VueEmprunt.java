package vuemedia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loader.Loader;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import user.User;

public class VueEmprunt extends HttpServlet {

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

		String rech = req.getParameter("rech");

		if (idDoc != null) {
			retour(idDoc);
			resp.sendRedirect("./vueEmprunt" + (rech != null ? "?rech=" + rech : ""));
			return;
		}

		req.setAttribute("rech", rech);

		req.setAttribute("bibli", u.isBibliothecaire());

		List<Document> ld = Mediatheque.getInstance().tousLesDocuments();

		List<Integer> le = getEmprunts(u.getId());

		if (le != null) {
			for (int i = 0, c = ld.size(); i < c; i++) {
				Object[] o = ld.get(i).affiche();
				boolean ct = false;
				for (Integer in : le) {
					if ((Boolean) o[0] == true && (Integer) o[1] == in) {
						ct = true;
						break;
					}
				}
				if (!ct) {
					ld.remove(i--);
					c--;
				}
			}
		}

		req.setAttribute("docs", ld);

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueEmprunt.jsp").forward(req, resp);
	}

	private List<Integer> getEmprunts(int idUser) {
		String req = "Select idDoc FROM Emprunt WHERE idUtilisateur = ?";
		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		ResultSet res;

		List<Integer> a = new ArrayList<>();

		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, idUser);
			res = s.executeQuery();

			while (res.next()) {
				a.add(res.getInt(1));
			}
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void retour(Integer idDoc) {
		String req = "DELETE FROM Emprunt WHERE idDoc = ?";
		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		try {
			PreparedStatement s = c.prepareStatement(req);
			s.setInt(1, idDoc);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Mediatheque m = Mediatheque.getInstance();
		m.retour(m.getDocument(idDoc));
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
