package vuemedia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;
import user.User;

public class VueMediatheque extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		User u = (User) ses.getAttribute("user");

		req.setAttribute("bibli", u.isBibliothecaire());
		req.setAttribute("docs", Mediatheque.getInstance().tousLesDocuments());

		this.getServletContext().getRequestDispatcher("/WEB-INF/vueMedia.jsp").forward(req, resp);

	}

}
