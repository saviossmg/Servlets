package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.action.Acao;
import br.com.alura.gerenciador.action.AlterarEmpresa;
import br.com.alura.gerenciador.action.ListarEmpresas;
import br.com.alura.gerenciador.action.MostrarEmpresa;
import br.com.alura.gerenciador.action.NovaEmpresa;
import br.com.alura.gerenciador.action.NovaEmpresaForm;
import br.com.alura.gerenciador.action.RemoveEmpresa;

@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("acao");
		
		String nomeDaClasse = "br.com.alura.gerenciador.action." + paramAcao;

		try {
			 executarComGenerics(request,response,nomeDaClasse);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	private void executarComGenerics(HttpServletRequest request, HttpServletResponse response,String nomeDaClasse) throws Exception {
		Class classeCarregada = Class.forName(nomeDaClasse);
		//Object objeto = classeCarregada.getDeclaredConstructor().newInstance();
		Acao acao = (Acao) classeCarregada.newInstance();
		String nome = acao.executa(request, response);
		
		String[] tipoEndereco = nome.split(":");
		
		if (tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEndereco[1]);
		}
	}

	@SuppressWarnings("unused")
	private void executarController(HttpServletRequest request, HttpServletResponse response, String paramAcao) throws ServletException, IOException {
		String nomeJsp = null;
		
		switch (paramAcao) {
			case "ListarEmpresas":
				ListarEmpresas acaoListar = new ListarEmpresas();
				nomeJsp = acaoListar.executa(request, response);
				break;
			case "RemoverEmpresa":
				RemoveEmpresa acaoRemover = new RemoveEmpresa();
				nomeJsp = acaoRemover.executa(request, response);
				break;
			case "MostrarEmpresa":
				MostrarEmpresa acaoMostrar = new MostrarEmpresa();
				nomeJsp = acaoMostrar.executa(request, response);
				break;
			case "AlterarEmpresa":
				AlterarEmpresa acaoAlterar = new AlterarEmpresa();
				nomeJsp = acaoAlterar.executa(request, response);
				break;
			case "NovaEmpresa":
				NovaEmpresa acaoNova = new NovaEmpresa();
				nomeJsp = acaoNova.executa(request, response);
				break;
			case "NovaEmpresaForm":
				NovaEmpresaForm acaoNovaForm = new NovaEmpresaForm();
				nomeJsp = acaoNovaForm.executa(request, response);
				break;
			default:
				nomeJsp = "forward:erro.html";
		}

		String[] tipoEndereco = nomeJsp.split(":");

		if (tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEndereco[1]);
		}
	}

}