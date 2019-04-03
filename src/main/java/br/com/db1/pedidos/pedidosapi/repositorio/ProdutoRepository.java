package br.com.db1.pedidos.pedidosapi.repositorio;

import org.springframework.data.repository.CrudRepository;
import br.com.db1.pedidos.pedidosapi.domain.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{
	
	Produto findByCodigo(String codigo);
	/*
	List<Produto> findByValor(Double valor);
	List<Produto> findByValorBeetween(Double valor1, Double valor2);
	*/
	
	/*
	@Query(nativeQuery=true, value="select * from produto")
	List<Produto> findByAtivoIsFalse();*/

}
