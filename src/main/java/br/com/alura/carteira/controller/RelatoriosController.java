package br.com.alura.carteira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.service.RelatoriosService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

		@Autowired
		private RelatoriosService service;
	
		@GetMapping("/carteira")
		public List<ItemCarteiraDto> relatorioCarteiraDeInverstimentos() {
			return service.relatorioCarteiraDeInvestimentos();
		}
		
}
