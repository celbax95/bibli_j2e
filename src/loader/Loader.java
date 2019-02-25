package loader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/soi", loadOnStartup = 1)

public class Loader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

}
