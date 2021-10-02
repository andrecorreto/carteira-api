package br.com.alura.carteira.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.repository.TransacaoRepository;

@Service
public class TransacaoService {
	@Autowired
	private TransacaoRepository transacaoRepository;	
	private ModelMapper modelMapper = new ModelMapper();

	public List<TransacaoDto> listar() {
		List<Transacao> transacoes = transacaoRepository.findAll();
		return transacoes.stream().map(t -> modelMapper.map(t, TransacaoDto.class)).collect(Collectors.toList());
	}

	@Transactional
	public void cadastrar(TransacaoFormDto dto) {
		Transacao transacao = modelMapper.map(dto, Transacao.class);
		transacao.setId(null); // zera o id da transação gerado indevidamente pelo ModelMapper
		
		transacaoRepository.save(transacao);
	}
}
