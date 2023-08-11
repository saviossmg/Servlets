package br.com.alura.cliente;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

public class ClienteWebService {
	
	public static void main(String[] args) throws Exception {
		
		String conteudoJson = Request
				.Post("http://localhost:8080/gerenciador/empresas")
				.addHeader("Accept", "application/json")
		        .execute()
		        .returnContent()
		        .asString();
		
		System.out.println("JSON: "+conteudoJson);
		
		String conteudoXml = Request
				.Post("http://localhost:8080/gerenciador/empresas")
				.addHeader("Accept", "application/xml")
		        .execute()
		        .returnContent()
                .asString();
		
		System.out.println("XML: "+conteudoXml.toString());
    }

}
