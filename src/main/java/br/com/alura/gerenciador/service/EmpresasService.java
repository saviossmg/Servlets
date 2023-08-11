package br.com.alura.gerenciador.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import br.com.alura.gerenciador.model.Banco;
import br.com.alura.gerenciador.model.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> empresas = new Banco().listar();
		
		String header = request.getHeader("Accept");
		String format = "";
		
		if(header == null) {
			response.getWriter().print("{'message': 'no content'}");
		} else {
			boolean isXml =  header.contains("xml");
			
			if(isXml) {
				XStream xstream = new XStream(new StaxDriver());
				xstream.alias("empresa", Empresa.class);
				format = xstream.toXML(empresas); 				
				response.setContentType("application/xml");
			} else {
				Gson gson = new Gson();
			    format = gson.toJson(empresas); 
			    response.setContentType("application/json");
			}
			
			response.getWriter().print(format);
		}
	}
	
}