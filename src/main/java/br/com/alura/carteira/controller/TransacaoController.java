package br.com.alura.carteira.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private List<Transacao> transacoes = new ArrayList<>();
	
	@GetMapping
	public List<TransacaoDto> listar() {
		
	    // Conversão SEM ModelMapper
		//List<TransacaoDto> transacoesDto = new ArrayList<>();
		//for (Transacao transacao : transacoes) {
		//	TransacaoDto dto = new TransacaoDto();
		//	dto.setTicker(transacao.getTicker());
		//	dto.setPreco(transacao.getPreco());
		//	dto.setQuantidade(transacao.getQuantidade());
		//	dto.setTipo(transacao.getTipo());
		//	
		//	transacoesDto.add(dto);			
		//}
		//return transacoesDto;
			
		// Conversão com ModelMapper
		ModelMapper modelMapper = new ModelMapper();
		return transacoes
				.stream()
				.map(t -> modelMapper.map(t, TransacaoDto.class))
				.collect(Collectors.toList());
	}	
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid TransacaoFormDto dto) {

		// Sem ModelMapper
		//Transacao transacao = new Transacao(
		//		dto.getTicker(),
		//		dto.getData(),
		//		dto.getPreco(),
		//		dto.getQuantidade(),
		//		dto.getTipo());
		
		// Com ModelMapper
		ModelMapper modelMapper = new ModelMapper();
		Transacao transacao = modelMapper.map(dto, Transacao.class);
		
		transacoes.add(transacao);

	}
}
