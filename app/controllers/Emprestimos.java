package controllers;

import java.util.Arrays;
import java.util.List;

import enums.NivelUsuario;
import enums.StatusLivro;
import models.Emprestimo;
import models.Livro;
import models.Usuario;
import play.mvc.Controller;

public class Emprestimos extends Controller {

	public static void form() {
		StatusLivro status = StatusLivro.disponivel;
		NivelUsuario nivel = NivelUsuario.COMUM;
		List<Livro> livros = Livro.find("status = ?", status).fetch();
		List<Usuario> usuarios = Usuario.find("nivel = ?", nivel).fetch();
		render(livros, usuarios);
		
	}
	public static void salvar(Emprestimo emp) {
		
		Livro livro = Livro.findById(emp.livro.id);
		
		livro.status = StatusLivro.reservado;
		livro.save();
		emp.save();
		listar();
	}
	
	public static void listar() {
		List<Emprestimo> emprestimos = Emprestimo.findAll();
		render(emprestimos);
	}
	public static void editar(long id) {
		Emprestimo emp = Emprestimo.findById(id);
		List<StatusLivro> status = Arrays.asList(StatusLivro.values());
		
		renderTemplate("Emprestimos/form.html", emp, status);
		
	}
	public static void deletar(long id) {
		Emprestimo emp = Emprestimo.findById(id);
		emp.delete();
		Livro livro = Livro.findById(emp.livro.id);
		
		livro.status = StatusLivro.disponivel;
		livro.save();
		
		
		listar();
	}

}
