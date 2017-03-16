package br.com.hcb.javaBrasil.controllers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.convert.ExchangeRateType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.hcb.javaBrasil.entities.EntradaValor;

@Controller
public class CotacaoController {

	@RequestMapping(value = "/converterDinheiro", method = RequestMethod.POST)
	public String converterDinheiro(Model model, @ModelAttribute("entradaValor") EntradaValor entradaValor) {
		String texto = entradaValor.getTexto();
		String estilo = "success";
		String resposta = "";
		
		
		
		if(texto.isEmpty()){
			estilo = "danger";
			resposta = "Não foi possível converter.";
		}else{			
			try{
				BigDecimal valor = new BigDecimal(texto);
				CurrencyUnit dolar = Monetary.getCurrency("USD");
				CurrencyUnit real = Monetary.getCurrency("BRL");
				MonetaryAmount valorEmDolar = FastMoney.of(valor, dolar);
				
				//necessario conexao internet
				ExchangeRateProvider provider = MonetaryConversions.getExchangeRateProvider(ExchangeRateType.ECB);
				CurrencyConversion conversaoAtual = provider.getCurrencyConversion(real);
				ExchangeRate cotacao = conversaoAtual.getExchangeRate(valorEmDolar);
				
				BigDecimal valorUmDolar = new BigDecimal(cotacao.getFactor().toString());
				valorUmDolar.round(new MathContext(4, RoundingMode.HALF_UP));
				String valorFinal = ""+(valorUmDolar.doubleValue() * valor.doubleValue());
				
				estilo = "success";
				resposta = valorFinal;
			}catch (Exception e) {
				estilo = "danger";
				resposta = e.getMessage();
			}
		}
		
		model.addAttribute("resposta", resposta);
		model.addAttribute("estilo", estilo);
		model.addAttribute("texto", texto);
	
		return "cotacao";
	}
}
