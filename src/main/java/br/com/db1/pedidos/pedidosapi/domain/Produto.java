package br.com.db1.pedidos.pedidosapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo", length = 50, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "valor", precision = 16, scale = 2, nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "statusprod", length = 30, nullable = false)
	private StatusProduto statusprod;
	
	protected Produto() {} //Somente para o JPA

	public Produto(String codigo, String nome, Double valor) {

		Checker.naoNulo(codigo, "c�digo do produto");
		Checker.naoNulo(nome, "nome do produto");
		Checker.naoNulo(valor, "valor do produto");

		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.statusprod = StatusProduto.ATIVO;
	}

	public void inativar() {
		if (!StatusProduto.ATIVO.equals(this.statusprod)) {
			throw new RuntimeException("Produto est� " + this.statusprod);
		}
		this.statusprod = StatusProduto.INATIVO;
	}

	public boolean isAtivo() {
		return StatusProduto.ATIVO.equals(this.statusprod);
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public Double getValor() {
		return this.valor;
	}

	public StatusProduto getStatus() {
		return statusprod;
	}

}
