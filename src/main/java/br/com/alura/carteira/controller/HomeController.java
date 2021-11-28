package br.com.alura.carteira.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/home")
@Api(tags = "Home")
public class HomeController {

	@GetMapping
	@ApiOperation("Autenticar usuario")
	public String home() {
		return "Teste deploy automatico";
	}
}
