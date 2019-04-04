package br.com.db1.pedidos.pedidosapi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.db1.pedidos.pedidosapi.infraestrutura.Checker;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	private String cpf;
	
	@Column(name = "nome", length = 250, nullable = false)
	private String nome;
	
	@Column(name = "statuscli", length = 30, nullable = false)
	private StatusCliente statuscli;
	
	protected Cliente() {} 
	
	public Cliente(String nome, String cpf){		
		Checker.naoNulo(nome, "nome do cliente");
		Checker.naoNulo(cpf, "CPF do cliente");
		Checker.cpfOnzeChar(cpf);
				

		this.nome = nome;
		this.cpf = cpf;
		this.statuscli = StatusCliente.ATIVO;
		
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getCpf(){
		return this.cpf;
	}
	
	public StatusCliente getStatus() {
		return statuscli;
	}
	
	public boolean isAtivo() {
		return StatusCliente.ATIVO.equals(this.statuscli);
	}
	
	public void  inativarCliente(){
		 this.statuscli = StatusCliente.INATIVO;
	}
	

	

	
	
}
