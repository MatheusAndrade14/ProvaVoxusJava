package br.com.Voxus.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.Voxus.Exception.DBException;
import br.com.Voxus.Pagamento.Pagamento;

public class DAO extends Generic{
	public DAO(EntityManager entityManager) {
		super(entityManager);
	}

	public void salvar() throws DBException {


		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().getRollbackOnly();
			throw new DBException("Erro no commit", e);
		}

	}

	public String cadastrar(Pagamento p) {
		

		em.persist(p);
		

		return "cadastro efetuado com sucesso";

	}

	public List<Pagamento> listar() {
		
		TypedQuery<Pagamento> query = em.createQuery("from Pagamento p", Pagamento.class);
		return query.getResultList();

	}

	public void remover(int id) {
		
		javax.persistence.Query query = em.createQuery("delete from Pagamento p where p.id = :id").setParameter("id",
				id);
		em.getTransaction().begin();
		int result = query.executeUpdate();
		em.getTransaction().commit();
	}

	public void atualizarValor(double valor, int id) {
		
		javax.persistence.Query query = em.createQuery("update from Pagamento p set valor = :vl" + " where p.id = :id")
				.setParameter("vl", valor)
				.setParameter("id", id);
		em.getTransaction().begin();
		int result = query.executeUpdate();
		em.getTransaction().commit();
	}

}
