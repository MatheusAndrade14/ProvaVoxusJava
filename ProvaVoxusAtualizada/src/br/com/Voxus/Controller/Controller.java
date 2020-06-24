package br.com.Voxus.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Voxus.DAO.DAO;
import br.com.Voxus.Exception.DBException;
import br.com.Voxus.Pagamento.Pagamento;
import br.com.Voxus.Singleton.EntityManagerFactorySingleton;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		double valor = Double.parseDouble(request.getParameter("valor"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = sdf.parse(request.getParameter("data"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String comentario = request.getParameter("comentarios");

		Pagamento nota = new Pagamento(titulo, valor, data, comentario);
		nota.setImpostoExterno(nota.calcImposto());

		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();

		DAO dao = new DAO(em);
		dao.cadastrar(nota);

		List<Pagamento> lista = dao.listar();

		try {
			dao.salvar();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listar.jsp").forward(request, response);
	}

}
