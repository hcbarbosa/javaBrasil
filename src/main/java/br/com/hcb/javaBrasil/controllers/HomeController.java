package br.com.hcb.javaBrasil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/validador")
	public String validador() {
		return "validador";
	}
	
	@RequestMapping("/conversor")
	public String conversor() {
		return "conversor";
	}
	
	@RequestMapping("/cotacao")
	public String cotacao() {
		return "cotacao";
	}
	
	@RequestMapping("/cep")
	public String cep() {
		return "cep";
	}
}
