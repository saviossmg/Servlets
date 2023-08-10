package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovaEmpresaServlet
 */
@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Cadastrando nova empresa");
		
		String nomeEmpresa = req.getParameter("nome");
		String paramDataEmpresa = req.getParameter("data");
		
		
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
        banco.adiciona(empresa);
        
        //PrintWriter out = resp.getWriter();     
		//out.println("<html><body>Empresa " + nomeEmpresa + " cadastrada com sucesso!</body></html>");
        //Chamar o JSP
        //RequestDispatcher rd = req.getRequestDispatcher("/novaEmpresaCriada.jsp");
        
        //RequestDispatcher rd = req.getRequestDispatcher("/listaEmpresas");
        //req.setAttribute("empresa", empresa.getNome());
        //rd.forward(req, resp);
        
        resp.sendRedirect("listaEmpresas");
	}

}
