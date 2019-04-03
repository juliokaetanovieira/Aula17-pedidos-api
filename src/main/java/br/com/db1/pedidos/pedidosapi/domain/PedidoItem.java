package br.com.db1.pedidos.pedidosapi.domain;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;

public class PedidoItem {
	private Produto produto;
	private Double quantidade;
	private Double valorUnitario;

	public PedidoItem(Produto produto, Double quantidade) {
		Checker.naoNulo(produto, "produto");
		Checker.naoNulo(quantidade, "quantidade");
		Checker.maiorQueZero(quantidade, "quantidade");
		
		if(!produto.isAtivo()) {
			throw new RuntimeException("Produto " + produto.getNome() + " est� " + produto.getStatus());
		}

		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = produto.getValor();

	}

	public Produto getProduto() {
		return this.produto;
	}

	public Double getQuantidade() {
		return this.quantidade;
	}
	
	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	public Double getValorTotal() {
		return this.quantidade * this.valorUnitario;
	}

}
