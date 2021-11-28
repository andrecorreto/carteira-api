package br.com.alura.carteira.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	public String home() {
		return "Teste deploy automatico";
	}
}
