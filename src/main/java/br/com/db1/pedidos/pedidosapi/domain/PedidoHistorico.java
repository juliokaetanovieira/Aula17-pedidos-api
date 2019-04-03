package br.com.db1.pedidos.pedidosapi.domain;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;

@Entity
@Table(name = "pedido_historico")
public class PedidoHistorico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime data;
	private StatusPedido status;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;
	
	public PedidoHistorico(Pedido pedido, StatusPedido status){
		Checker.naoNulo(status, "status");
		this.data = LocalDateTime.now();
		this.pedido = pedido;
		this.status = status;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public StatusPedido getStatus(){
		return this.status;
	}
	
	public void setStatus(StatusPedido status) {
		this.status = status;
	}

}
