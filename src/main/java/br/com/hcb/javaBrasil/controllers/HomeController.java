package br.com.hcb.javaBrasil.controllers;

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
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.NITValidator;
import br.com.caelum.stella.validation.TituloEleitoralValidator;
import br.com.hcb.javaBrasil.entities.EntradaValor;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/validar", method = RequestMethod.POST)
	public String validar(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String tipo = entradaValor.getTipo();
		String resposta = "Válido :)";
		String estilo = "success"; // danger
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("HCBValidationMessages", new Locale("pt","BR"));  
		MessageProducer messageProducer = new ResourceBundleMessageProducer(resourceBundle);  
		boolean isFormatted = false;  		

		if (tipo.equals("CPF")) {
			CPFValidator cpfValidator = new CPFValidator(messageProducer, isFormatted);

			try {
				cpfValidator.assertValid(texto);
			} catch (InvalidStateException e) {
				for (ValidationMessage message : e.getInvalidMessages()) {  
					resposta = message.getMessage();  
	            } 
				estilo = "danger";
			}

		} else if (tipo.equals("CNPJ")) {
			CNPJValidator cnpjValidator = new CNPJValidator(messageProducer, isFormatted);

			try {
				cnpjValidator.assertValid(texto);
			} catch (InvalidStateException e) {
				for (ValidationMessage message : e.getInvalidMessages()) {  
					resposta = message.getMessage();  
	            } 
				estilo = "danger";
			}
		} else if (tipo.equals("NIT")) {
			NITValidator nitValidator = new NITValidator(messageProducer, isFormatted);

			try {
				nitValidator.assertValid(texto);
			} catch (InvalidStateException e) {
				for (ValidationMessage message : e.getInvalidMessages()) {  
					resposta = message.getMessage();  
	            } 
				estilo = "danger";
			}
		} else if (tipo.equals("TITULO")) {
			TituloEleitoralValidator tituloValidator = new TituloEleitoralValidator(messageProducer, isFormatted);

			try {
				tituloValidator.assertValid(texto);
			} catch (InvalidStateException e) {
				for (ValidationMessage message : e.getInvalidMessages()) {  
					resposta = message.getMessage();  
	            } 
				estilo = "danger";
			}
		} else {
			resposta = "Não foi possível validar este tipo de entrada.";
			estilo = "warning";
		}

		model.addAttribute("resposta", resposta);
		model.addAttribute("estilo", estilo);
		return "index";
	}

}
