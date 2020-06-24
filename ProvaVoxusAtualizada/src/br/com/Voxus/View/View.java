package br.com.Voxus.View;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.Voxus.Pagamento.Pagamento;

public class View {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("oracle");
		EntityManager em = fabrica.createEntityManager();

		// Pagamento nota = new
		// Pagamento("Bicicleta",1700.00,Calendar.getInstance(),null);

		// nota.setImpostoExterno(nota.calcImposto());

		// em.persist(nota);
		em.getTransaction().begin();
		em.getTransaction().commit();

		em.close();
		fabrica.close();
	}

}
