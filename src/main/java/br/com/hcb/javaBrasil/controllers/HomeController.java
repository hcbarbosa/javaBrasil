package br.com.hcb.javaBrasil.controllers;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.stella.MessageProducer;
import br.com.caelum.stella.ResourceBundleMessageProducer;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.NumericToWordsConverter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.NITValidator;
import br.com.caelum.stella.validation.TituloEleitoralValidator;
import br.com.caelum.stella.validation.Validator;
import br.com.hcb.javaBrasil.entities.EntradaValor;

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
		return "numeros";
	}

	@RequestMapping(value = "/validar", method = RequestMethod.POST)
	public String validar(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String tipo = entradaValor.getTipo();
		String resposta = "Válido :)";
		String estilo = "success"; // danger
		
		if(tipo != "" && texto != ""){
			Validator<String> validator = criaValidador(tipo);
			
			if(validator != null){
				try {
					validator.assertValid(texto);
				} catch (InvalidStateException e) {
					for (ValidationMessage message : e.getInvalidMessages()) {  
						resposta = message.getMessage();  
		            } 
					estilo = "danger";
				}
			}
		}else {
			resposta = "Não foi possível validar sua entrada.";
			estilo = "warning";
		}

		model.addAttribute("resposta", resposta);
		model.addAttribute("estilo", estilo);
		model.addAttribute("texto", texto);
		return "validador";
	}
	

	@RequestMapping(value = "/converterNumeros", method = RequestMethod.POST)
	public String converterNumeros(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String estilo = "success";
		String resposta = "";
		
		NumericToWordsConverter converter = new NumericToWordsConverter(new FormatoDeReal());
		
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
	
		return "numeros";
	}
	

	private Validator<String> criaValidador(String tipo) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("HCBValidationMessages", new Locale("pt","BR"));  
		MessageProducer messageProducer = new ResourceBundleMessageProducer(resourceBundle);  
		boolean isFormatted = false;  		
		Validator<String> validator = null;
		
		switch (tipo) {
		case "CPF":
			validator = new CPFValidator(messageProducer, isFormatted);
			break;
		case "CNPJ":
			validator = new CNPJValidator(messageProducer, isFormatted);
			break;
		case "NIT":
			validator = new NITValidator(messageProducer, isFormatted);
			break;
		case "TITULO":
			validator = new TituloEleitoralValidator(messageProducer, isFormatted);
			break;

		default:
			break;
		}
		
		return validator;
	}
}
