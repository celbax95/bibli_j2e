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

		if (!u.isBibliothecaire()) {
			req.setAttribute("b", new String[][] { { "Ma médiathèque", "./bibli_j2e/vueMediatheque" },
				{ "Mes emprunts", "./bibli_j2e/emprunts" } });

		} else {
			req.setAttribute("b", new String[][] { { "Médiathèque", "./bibli_j2e/vueMediatheque" },
				{ "Ajouter des documents", "./bibli_j2e/emprunts" } });
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(req, resp);

	}
}
