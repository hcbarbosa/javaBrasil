package br.com.hcb.javaBrasil.controllers;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.stella.inwords.InteiroSemFormato;
import br.com.caelum.stella.inwords.NumericToWordsConverter;
import br.com.hcb.javaBrasil.entities.EntradaValor;

@Controller
public class ConversorController {

	@RequestMapping(value = "/converterNumeros", method = RequestMethod.POST)
	public String converterNumeros(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String estilo = "success";
		String resposta = "";
		
		NumericToWordsConverter converter = new NumericToWordsConverter(new InteiroSemFormato()); 
		//para usar o número como se fosse dinheiro passa como parametro new FormatoDeReal()
		
		if(texto.isEmpty()){
			estilo = "danger";
			resposta = "Não foi possível converter.";
		}else{
			BigDecimal numeros = new BigDecimal(texto);
			resposta = converter.toWords(numeros.doubleValue());
		}
		
		model.addAttribute("resposta", resposta);
		model.addAttribute("estilo", estilo);
		model.addAttribute("texto", texto);
	
		return "conversor";
	}
}
