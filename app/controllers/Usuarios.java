package controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import enums.NivelUsuario;
import enums.StatusLivro;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Validation;
import play.data.validation.Validation.ValidationResult;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller{
	
	public static void form() {  
		List<NivelUsuario> niveis = Arrays.asList(NivelUsuario.values());
		Usuario usu= (Usuario)Cache.get("usuario");
		Cache.clear();
		render(usu, niveis);
		
		listarAdm();
		
	}
	public static void salvar(Usuario usu, String senha) {
		if(senha.equals("")== false) {
			usu.senha = senha;
			if(senha.length()<6) {
				
				Validation.addError("usu.senha", "A senha deve conter no minimo 6 digitos");
				
			}
		}Validation.valid("Usiario",usu);
		
		
		
		if(Validation.hasErrors()) {
			Validation.keep();
			flash.error("falha ao salvar usuario");
			Cache.set("usuario", usu);
			form();
		} 
		
		if(validation.hasErrors()) {
			Validation.keep();
			flash.error("falha ao salvar usuario."); 
			Cache.set("usuario", usu);
			form();
			
		}
		
		usu.save();
		flash.success("Usuario cadastrado com sucesso!");
		form();
	}
	public static void listarAdm() {
		String busca = params.get("busca");
		List<Usuario> lista;
		if(busca == null) {
			lista = Usuario.findAll();	
		}else {
			lista = Usuario.find("nome like ?1 or email like ?1 order by nome ","%"+busca+"%").fetch();
		}
		render(lista);
	}
	public static void editar(long id) {
		Usuario usu = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usu);
		
	}
	public static void deletar(long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		
		listarAdm();
		
	}

}
