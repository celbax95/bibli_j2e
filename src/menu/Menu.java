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
			req.setAttribute("b", new String[][] { { "Ma m�diath�que", "./projet/vueMediatheque" },
					{ "Mes emprunts", "./projet/emprunts" } });

		} else {
			req.setAttribute("b", new String[][] { { "M�diath�que", "./projet/vueMediatheque" },
					{ "Ajouter des documents", "./projet/emprunts" } });
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(req, resp);

	}
}
