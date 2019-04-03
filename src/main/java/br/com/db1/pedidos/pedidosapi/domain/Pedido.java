package br.com.db1.pedidos.pedidosapi.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;


public class Pedido {
	private static final int QUANTIDADE_MAXIMA_ITENS = 10;
	private String codigo;
	private StatusPedido statusped;
	private LocalDateTime data;
	private Cliente cliente;
	
	@OneToMany(mappedBy= "pedido", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PedidoHistorico> historicos = new ArrayList<>();
	private List<PedidoItem> itens = new ArrayList<>();
	

	public Pedido(String codigo, Cliente cliente) {
		Checker.naoNulo(codigo, "c�digo");
		Checker.naoNulo(cliente, "cliente");
		this.verificarClienteAtivo();
		this.codigo = codigo;
		this.cliente = cliente;
		this.novoHistoricoStatus();

	}
	
	public void addItem(Produto produto, Double quantidade){
		this.verificarStatusPedidoParaAlterar();
		
		if(this.itens.size() == QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Quantidade m�xima de itens excedida: " + QUANTIDADE_MAXIMA_ITENS);
		}
		
		this.itens.add(new PedidoItem(produto, quantidade));
	}
	

	public void removerItem(Produto produto){
		this.verificarStatusPedidoParaAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}
	
	public void  faturar(){
		if(!StatusPedido.ABERTO.equals(this.statusped)) {
			throw new RuntimeException("Pedido est� " + this.statusped);
		}
		
		if(this.itens.size() == 0 || this.itens.size() > QUANTIDADE_MAXIMA_ITENS) {
			throw new RuntimeException("Pedido deve ter no min�mo 1 item e no m�ximo 10 itens. Quantidade atual: " + this.itens.size());
		}
		
		this.verificarClienteAtivo();
		
		this.statusped = StatusPedido.FATURADO;
		this.novoHistoricoStatus();
	}
	
	public void cancelar(){
		this.verificarStatusPedidoParaAlterar();
		this.statusped = StatusPedido.CANCELADO;
		this.novoHistoricoStatus();
	}

	public void reabrir(){
		if(!StatusPedido.CANCELADO.equals(this.statusped)) {
			throw new RuntimeException("Pedido est� " + this.statusped);
		}
		
		this.statusped = StatusPedido.ABERTO;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public StatusPedido getStatus() {
		return statusped;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public List<PedidoHistorico> getHistoricos(){
		return Collections.unmodifiableList(historicos);
	}
	
	public List<PedidoItem> getItens(){
		return Collections.unmodifiableList(itens);
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	private void novoHistoricoStatus() {
		PedidoHistorico historico = new PedidoHistorico(this, this.statusped);
		this.historicos.add(historico);
		this.data = historico.getData();
	}
	
	private void verificarClienteAtivo() {
		if(!cliente.isAtivo()) {
			throw new RuntimeException("Pedido est� " + this.statusped);
		}
	}
	
	private void verificarStatusPedidoParaAlterar() {
		if(!StatusPedido.ABERTO.equals(this.statusped)) {
			throw new RuntimeException("Pedido est� " + this.statusped);
		}
	}

}
