package br.com.Voxus.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import br.com.Voxus.DAO.DAO;
import br.com.Voxus.DAO.DAOLerXLSX;
import br.com.Voxus.Exception.DBException;
import br.com.Voxus.Pagamento.Pagamento;
import br.com.Voxus.Singleton.EntityManagerFactorySingleton;

 
public class Upload extends HttpServlet {
	
	private File diretorio;
 
	@Override
	public void init(ServletConfig config) throws ServletException {
                super.init(config);
		String path = config.getInitParameter("diretorio");
		diretorio = new File(path);
		diretorio.mkdirs();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeDoCampo = null ;
		PrintWriter out = response.getWriter();
		String msg = null ;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			 msg ="Voce não enviou um arquivo!>";
			return;
		}
		
		for (String elemento : request.getParameterMap().keySet()) {
			System.out.println(elemento);
		}
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(diretorio);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items ) {
			    if (!item.isFormField()) {
			        processUploadedFile(item);
			         nomeDoCampo = "C:/Users/mathe/Documents/upload/" + item.getName();
			        System.out.println(nomeDoCampo);
			    } else {
			    	//para inputs que nao sao 'file', isFormField() é verdadeiro 
			    	 nomeDoCampo = item.getFieldName();
				String valorDoCampo = item.getString();
				System.out.println(nomeDoCampo + ": " + valorDoCampo);
			    }
			}
			
			
			 msg = "Arquivo gravado!";
			
		} catch (Exception e) {
			 msg = "Erro ao escrever no arquivo!";
			return;
		}
		
		DAOLerXLSX dao = new DAOLerXLSX();
		
		dao.fileName = nomeDoCampo;
		
		try {
			dao.LerXlsx();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		DAO dao1 = new DAO(em);
		List<Pagamento> lista = dao1.listar();

		try {
			dao1.salvar();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);

		request.setAttribute("lista", lista);
		request.getRequestDispatcher("listar.jsp").forward(request, response);
	}
 
	private void processUploadedFile(FileItem item) throws Exception {
		String fileName = item.getName();
		File uploadedFile = new File(diretorio, fileName);
	    item.write(uploadedFile);
	}
	
}