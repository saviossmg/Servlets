package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/entrada")
public class AutorizacaoFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request  = (HttpServletRequest) servletRequest;
		HttpServletResponse response  = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");

		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean acaoProtegida = ehAcaoProtegida(paramAcao);
		
		System.out.println("usuarioNaoEstaLogado: "+usuarioNaoEstaLogado);
		System.out.println("acaoProtegida: "+acaoProtegida);
		
		if(acaoProtegida && usuarioNaoEstaLogado) {
			response.sendRedirect("entrada?acao=LoginForm");
			return;
		} 
		
		chain.doFilter(request, response);
	}
	
	private boolean ehAcaoProtegida(String paramAcao) {
		switch (paramAcao) {
			case "Login":
			case "LoginForm":
				return false;
			default:
				return true;
		}
		
	}

}