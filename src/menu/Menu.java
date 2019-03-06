package menu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;

public class Menu extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = (User) ses.getAttribute("user");

		if (u == null) {
			resp.sendRedirect("./log");
			return;
		}

		if (!u.isBibliothecaire()) {
			req.setAttribute("b",
					new String[][] { { "Ma médiathèque", "./vueMedia" }, { "Mes emprunts", "./vueEmprunt" } });

		} else {
			req.setAttribute("b",
					new String[][] { { "Médiathèque", "./vueMedia" }, { "Ajouter des documents", "./vueAddDoc" } });
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(req, resp);

	}
}
