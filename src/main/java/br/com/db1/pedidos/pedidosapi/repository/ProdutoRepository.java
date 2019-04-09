package br.com.db1.pedidos.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.db1.pedidos.pedidosapi.domain.entity.Produto;
import br.com.db1.pedidos.pedidosapi.domain.entity.StatusProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Produto findByCodigo(String codigo);
	
	Iterable<Produto> findByStatus(StatusProduto statusprod);
	

}
