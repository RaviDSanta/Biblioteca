package controllers;

import enums.NivelUsuario;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {



	public static void form() {
		if(Usuario.count()==0) {
		Usuario u = new Usuario();
		u.email = "admin@admin.com";
		u.nome = "admin";
		u.senha = "123";
		u.nivel = NivelUsuario.ADMINISTRADOR;
		u.save();
		
		form();
	}
		render();
	}

	public static void logar(String email, String senha) {

		Usuario usu = Usuario.find("email = ?1	and senha = ?2", email, Crypto.passwordHash(senha)).first();

		if (usu == null) {
			flash.error("email ou senha invalido");
			form();	
		} else {
			session.put("usuario.email", usu.email);
			session.put("usuario.nivel", usu.nivel);
			session.put("usuario.nome", usu.nome);


			Livros.listarAdm();
		}
	}

	public static void sair() {
		session.clear();
		Login.form();
	}
}
