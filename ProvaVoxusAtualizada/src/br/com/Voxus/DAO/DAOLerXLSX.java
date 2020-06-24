package br.com.Voxus.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.Voxus.Pagamento.Pagamento;

public class DAOLerXLSX {

	public static String fileName = "" ;

	public void LerXlsx( ) throws IOException, InvalidFormatException {
		List<Pagamento> listaPagamentos = new ArrayList<Pagamento>();

		try {
			FileInputStream arquivo = new FileInputStream(new File(DAOLerXLSX.fileName));
			OPCPackage pkg = OPCPackage.open(new File(fileName));
			
			XSSFWorkbook workbook = new XSSFWorkbook(pkg);


			XSSFSheet sheetPagamentos = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheetPagamentos.iterator();

			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				Pagamento p = new Pagamento();
				listaPagamentos.add(p);
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						p.setTitulo(cell.getStringCellValue());
						break;
					case 1:
						p.setValor(cell.getNumericCellValue());
						p.setImpostoExterno(p.calcImposto());

						break;
					case 2:
						p.setData(cell.getDateCellValue());
						break;
					case 3:
						p.setComentarios(cell.getStringCellValue());
						break;

					}
				}
			}
			arquivo.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		}

		if (listaPagamentos.size() == 0) {
			System.out.println("Nenhum aluno encontrado!");
		}
	     
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("oracle");
		EntityManager em = fabrica.createEntityManager();
		
		em.persist(listaPagamentos.get(0));
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		em.close();
		fabrica.close();
	}

		 
	}

