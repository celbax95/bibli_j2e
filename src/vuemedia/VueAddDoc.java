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

public class VueAddDoc extends HttpServlet {

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
			colN = getColumnName(type);
		}

		List<Integer> err = null;
		if (req.getParameter("askVerif") != null) {
			err = verif(req, colN);
			System.out.println(err);
		}

		if (!(err == null) && !err.isEmpty())
			req.setAttribute("err", err);
		req.setAttribute("types", types);
		req.setAttribute("colN", colN);
		req.setAttribute("colT", colT);
		req.setAttribute("bibli", u.isBibliothecaire());

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueAddDoc.jsp").forward(req, resp);
	}

	private List<String> getColumnName(String type) {
		String req = "SELECT d.*, t.* FROM Document d, TABLE t";
		req = req.replaceFirst("TABLE", type);

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> colN = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req);

			if (r.next()) {
				ResultSetMetaData rs = r.getMetaData();
				for (int i = 1, nb = rs.getColumnCount(); i <= nb; i++) {
					String cn = rs.getColumnName(i).toLowerCase();
					if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte")) {
						colN.add(rs.getColumnName(i));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colN;
	}

	private List<String> getColumnType(String type) {
		String req = "SELECT d.*, t.* FROM Document d, TABLE t";
		req = req.replaceFirst("TABLE", type);

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> colT = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req);

			if (r.next()) {
				ResultSetMetaData rs = r.getMetaData();
				for (int i = 1, nb = rs.getColumnCount(); i <= nb; i++) {
					String cn = rs.getColumnName(i).toLowerCase();
					if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte")) {
						colT.add(rs.getColumnTypeName(i));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colT;
	}

	private List<String> getTypes() {
		String req = "SELECT type FROM DocType";

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> types = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req);

			while (r.next())
				types.add(r.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;
	}

	private List<Integer> verif(HttpServletRequest req, List<String> colN) {
		String type = req.getParameter("type");

		List<String> colT = getColumnType(type);

		List<Integer> err = new ArrayList<>();

		for (int i = 0, c = colN.size(); i < c; i++) {
			String n = colN.get(i);
			String v = req.getParameter(n);

			if (v == null || v.equals("") || (colT.get(i).equals("INT") && !v.matches("[0-9]+")))
				err.add(i);
		}
		return err;
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
