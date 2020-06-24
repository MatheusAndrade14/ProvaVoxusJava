package br.com.Voxus.Pagamento;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;

@Entity
@SequenceGenerator(name = "id", sequenceName = "SQ_TB_PAGAMENTO", allocationSize = 1)
@Table(name = "TB_PAGAMENTO")
public class Pagamento {

	@Id
	@Column(name = "ID_PAGAMENTO")
	@GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "TITULO", nullable = false)
	@Length(min = 5, max = 100)
	private String titulo;

	@Column(name = "VALOR", nullable = false)
	private double valor;

	@Column(name = "DATA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	// Imposto 5% do valor
	@Column(name = "IMPOSTO_EXTERNO", nullable = false)
	private double impostoExterno;

	@Column(name = "COMENTARIOS")
	private String comentarios;

	public double calcImposto() {
		impostoExterno = valor * 0.05;
		return impostoExterno;
	}

	


	public Pagamento() {
		super();
	}

	public Pagamento(@Length(min = 5, max = 100) String titulo, double valor, Date data, String comentarios) {
		super();
		this.titulo = titulo;
		this.valor = valor;
		this.data = data;
		this.comentarios = comentarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getImpostoExterno() {
		return impostoExterno;
	}

	public void setImpostoExterno(double impostoExterno) {
		this.impostoExterno = impostoExterno;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
