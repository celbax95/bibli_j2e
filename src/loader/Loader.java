package loader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/soi", loadOnStartup = 1)

public class Loader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String URL = "jdbc:mysql://localhost:3306/j2e?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	public static final String LOG = "jdbc";
	public static final String MDP = "";

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		try {
			Class.forName("persistantdata.MediathequeData");
			System.out.println("Mediatheque chargee !");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
