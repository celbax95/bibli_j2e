package vuemedia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import user.User;

public class AddDoc extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = (User) ses.getAttribute("user");

		if (u == null) {
			resp.sendRedirect("./log");
			return;
		}

		String type = req.getParameter("type");

		List<String> colN = null;
		List<String> colT = null;

		List<String> types = getTypes();

		if (type != null) {
			setColumnInfos(type, colN, colT);
		}

		req.setAttribute("colN", colN);
		req.setAttribute("colT", colT);
		req.setAttribute("bibli", u.isBibliothecaire());

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueMedia.jsp").forward(req, resp);
	}

	private List<String> getTypes() {
		String req = "SELCT type FROM DocType";

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> types = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req);

			if (r.next())
				types.add(r.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;
	}

	private void setColumnInfos(String type, List<String> colN, List<String> colT) {
		String req = "SELCT * FROM TABLE";
		req.replaceFirst("TABLE", type);

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		colN = new ArrayList<>();
		colT = new ArrayList<>();

		try {
			Statement s = c.createStatement();

			ResultSet r = s.executeQuery(req);

			if (r.next()) {
				ResultSetMetaData rs = r.getMetaData();
				for (int i = 0, nb = rs.getColumnCount(); i < nb; i++) {
					colN.add(rs.getColumnName(i));
					colT.add(rs.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
