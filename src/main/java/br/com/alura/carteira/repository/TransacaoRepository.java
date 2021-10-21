package br.com.alura.carteira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	@Query("select new br.com.alura.carteira.dto.ItemCarteiraDto("
			+ "t.ticker, " 
			+ "sum(CASE WHEN(t.tipo = 'COMPRA') THEN t.quantidade ELSE (t.quantidade * -1) END), "
			+ "(select sum(CASE WHEN(t2.tipo = 'COMPRA') THEN t2.quantidade ELSE (t2.quantidade * -1) END) from Transacao t2)) " 
			+ "FROM Transacao t "
			+ "GROUP BY t.ticker")
	List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();
	
	;	
	
}