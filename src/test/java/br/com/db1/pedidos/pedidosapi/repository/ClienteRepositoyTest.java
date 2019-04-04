package br.com.db1.pedidos.pedidosapi.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.db1.pedidos.pedidosapi.domain.entity.Cliente;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusCliente;
import br.com.db1.pedidos.pedidosapi.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoyTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@After
	public void after(){
		clienteRepository.deleteAll();
	}
	
	@Test
	public void deveSalvarUmCliente(){
		Cliente cliente = new Cliente("Julio Vieira", "07717964988");
		
		clienteRepository.save(cliente);
		
		Long count = clienteRepository.count();
		
		Assert.assertEquals(1, count, 0);
		
	}
	
	@Test
	public void deveSalvarClienteAlterado(){
		Cliente cliente = new Cliente("Anna Laura", "12345678910");
		
		clienteRepository.save(cliente);
		
		Cliente clienteSalvo = clienteRepository.findByCpf("12345678910");
		
		clienteSalvo.inativarCliente();
		
		clienteRepository.save(clienteSalvo);
		
		Cliente clienteAlterado = clienteRepository.findByCpf("12345678910");
		
		Assert.assertEquals(StatusCliente.INATIVO, clienteAlterado.getStatus());
	}

}
