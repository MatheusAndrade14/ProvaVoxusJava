package br.com.Voxus.Controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
 * Servlet implementation class ControllerEditar
 */
@WebServlet("/ControllerEditar")
public class ControllerEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerEditar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int codigo = Integer.parseInt(request.getParameter("id"));
		double valor = Double.parseDouble(request.getParameter("valor"));

		String msg = "Atualizado com sucesso";
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();

		DAO dao = new DAO(em);
		dao.atualizarValor(valor, codigo);
 
		List<Pagamento> lista = dao.listar();

		try {
			dao.salvar();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		request.setAttribute("msg", msg);
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listar.jsp").forward(request, response);
	}

}
