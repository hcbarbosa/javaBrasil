package br.com.hcb.javaBrasil.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import br.com.hcb.javaBrasil.entities.EntradaValor;

@Controller
public class CepController {

	@RequestMapping(value = "/consultarCep", method = RequestMethod.POST)
	public String validar(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String resposta = "Não foi encontrado dados para esse CEP";
		String estilo = "danger"; // danger
		
		try{
			// sem uso de api			
//			URL url = new URL("http://viacep.com.br/ws/"+texto+"/json");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			InputStream data = conn.getInputStream();
			
			ViaCEPClient cliente = new ViaCEPClient();
			ViaCEPEndereco data = cliente.getEndereco(texto);
			if(data != null){
				resposta = "Endereço: <br>";
				resposta += data.getLogradouro() + ", " + data.getBairro() + "<br>";
				resposta += data.getLocalidade() + " - " + data.getUf() + "<br>";
				estilo = "success";		
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

		model.addAttribute("resposta", resposta);
		model.addAttribute("estilo", estilo);
		model.addAttribute("texto", texto);
		return "cep";
	}
}
