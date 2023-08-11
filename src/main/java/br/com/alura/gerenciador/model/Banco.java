package br.com.alura.gerenciador.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	private static Integer chaveSquencial = 1;
	
	static {
		Empresa emp1 = new Empresa();
		Empresa emp2 = new Empresa();
		emp1.setNome("Alura");
		emp1.setId(chaveSquencial++);
		emp2.setNome("Caelum");
		emp2.setId(chaveSquencial++);
		listaEmpresas.add(emp1);
		listaEmpresas.add(emp2);
		
		Usuario u1 = new Usuario();
		u1.setLogin("savio");
		u1.setSenha("12345");
		Usuario u2 = new Usuario();
		u2.setLogin("elisa");
		u2.setSenha("12345");
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		
	}
	
	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSquencial++);
		Banco.listaEmpresas.add(empresa);
	}
	
	public List<Empresa> listar(){ 
        return Banco.listaEmpresas;
    }

	public void removeEmpresa(Integer id) {
		Iterator<Empresa> it = listaEmpresas.iterator();
		
		while(it.hasNext()){
			Empresa emp = it.next();
			if(emp.getId() == id) {
				it.remove();
			}
		}
		
	}

	public Empresa buscaEmpresaId(Integer id) {
		for (Empresa empresa: listaEmpresas) { 
	        if(empresa.getId() == id)  {
	            return empresa;
	        }

	    }
	    return null;
	}
	
	public Usuario existeUsuario(String login, String senha) {
	    for(Usuario usuario : listaUsuarios) {
	        if(usuario.ehIgual(login, senha)) { 
	            return usuario;

	        }
	    }
	    return null;
	}

}
