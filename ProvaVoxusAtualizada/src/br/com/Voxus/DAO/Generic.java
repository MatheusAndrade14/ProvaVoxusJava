package br.com.Voxus.DAO;

import javax.persistence.EntityManager;

public class Generic {
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public Generic(EntityManager em) {
		this.em = em;

	}
}
