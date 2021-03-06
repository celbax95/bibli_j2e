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
		if (type != null && req.getParameter("askVerif") != null) {
			err = verif(req, type, colN);
		}

		if (err != null && !err.isEmpty())
			req.setAttribute("err", err);
		else if (err != null && err.isEmpty() && type != null) {
			// Ajout du document
			int typeInt = getType(type);
			Mediatheque.getInstance().nouveauDocument(typeInt, getArgs(type, req));
			resp.sendRedirect("./vueAddDoc?type=" + type + "&ajout=1");
			return;
		}
		req.setAttribute("types", types);
		req.setAttribute("colN", colN);
		req.setAttribute("colT", colT);
		req.setAttribute("bibli", u.isBibliothecaire());

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueAddDoc.jsp").forward(req, resp);
	}

	private Object[] getArgs(String type, HttpServletRequest req) {
		List<Object> l = new ArrayList<>();

		List<String> colN = getColumnName(type);

		for (String s : colN) {
			String p = req.getParameter(s);
			if (p == null || p.equals(""))
				return null;
			l.add(p);
		}

		return l.toArray();
	}

	private List<String> getColumnName(String type) {
		String req1 = "DESCRIBE Document";
		String req2 = "DESCRIBE TABLE";
		req2 = req2.replaceFirst("TABLE", type);

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> colN = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req1);
			String cn = null;
			while (r.next()) {
				cn = r.getString(1);
				if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte"))
					colN.add(r.getString(1));
			}

			r = s.executeQuery(req2);
			while (r.next()) {
				cn = r.getString(1);
				if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte"))
					colN.add(r.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colN;
	}

	private List<String> getColumnType(String type) {
		String req1 = "DESCRIBE Document";
		String req2 = "DESCRIBE TABLE";
		req2 = req2.replaceFirst("TABLE", type);

		Connection c = connectMySQL(Loader.URL, Loader.LOG, Loader.MDP);

		List<String> colT = new ArrayList<>();

		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(req1);

			String cn = null;
			while (r.next()) {
				cn = r.getString(1);
				if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte"))
					colT.add(r.getString(2));
			}

			r = s.executeQuery(req2);
			while (r.next()) {
				cn = r.getString(1);
				if (!cn.toLowerCase().contains("id") && !cn.toLowerCase().contains("emprunte"))
					colT.add(r.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colT;
	}

	private int getType(String type) {
		switch (type.toLowerCase()) {
		case "livre":
			return 0;
		case "cd":
			return 1;
		case "dvd":
			return 2;
		default:
			return -1;
		}
	}

	private List<String> getTypes() {
		String req = "SELECT type FROM DocTypes";

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

	private List<Integer> verif(HttpServletRequest req, String type, List<String> colN) {

		List<String> colT = getColumnType(type);

		List<Integer> err = new ArrayList<>();

		for (int i = 0, c = colN.size(); i < c; i++) {
			String n = colN.get(i);
			String v = req.getParameter(n);

			if (v == null || v.equals("") || (colT.get(i).toLowerCase().matches(".*int.*") && !v.matches("[0-9]+")))
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
