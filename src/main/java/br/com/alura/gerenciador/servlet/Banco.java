package br.com.alura.gerenciador.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> lista = new ArrayList<>();
	private static Integer chaveSquencial = 1;
	
	static {
		Empresa emp1 = new Empresa();
		Empresa emp2 = new Empresa();
		emp1.setNome("Alura");
		emp1.setId(chaveSquencial++);
		emp2.setNome("Caelum");
		emp2.setId(chaveSquencial++);
		lista.add(emp1);
		lista.add(emp2);
	}
	
	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSquencial++);
		Banco.lista.add(empresa);
	}
	
	public List<Empresa> listar(){ 
        return Banco.lista;
    }

	public void removeEmpresa(Integer id) {
		Iterator<Empresa> it = lista.iterator();
		
		while(it.hasNext()){
			Empresa emp = it.next();
			if(emp.getId() == id) {
				it.remove();
			}
		}
		
	}

	public Empresa buscaEmpresaId(Integer id) {
		for (Empresa empresa: lista) { 
	        if(empresa.getId() == id)  {
	            return empresa;
	        }

	    }
	    return null;
	}

}
