package controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import models.Livro;
import models.StatusLivro;
import play.cache.Cache;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Livros extends Controller{
	
	public static void form() {
		
		
		List<StatusLivro> status = Arrays.asList(StatusLivro.values());
		Livro liv = (Livro)Cache.get("livro");
		Cache.set("livro", null);
		render(status, liv);	
	}
	public static void salvar(@play.data.validation.Valid Livro liv) {
		
		if(Validation.hasErrors()) { 
			validation.keep();
			
			flash.error("falha ao salvar.");
			Cache.set("livro", liv);
			form();
		}
		
		liv.save();
		flash.success("Salvo com sucesso!");
		listarAdm();
	}
	public static void listarAdm() {
		String busca = params.get("busca");
		List<Livro> lista;
		if(busca == null) {
			lista = Livro.findAll();	
		}else {
			lista = Livro.find("nome like ?1 or genero like ?1 order by nome ","%"+busca+"%").fetch();
		}
		render(lista);
	}
	
	public static void editar(long id) {
		Livro liv = Livro.findById(id);
		List<StatusLivro> status = Arrays.asList(StatusLivro.values());
		
		renderTemplate("Livros/form.html", liv, status);

	}
	public static void deletar(long id) {
		Livro liv = Livro.findById(id);
		if(liv.status == StatusLivro.disponivel) {
		liv.delete();
		}
		listarAdm();
		}

}
