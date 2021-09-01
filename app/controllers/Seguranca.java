package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

	@Before
	static void verificar() {
		if (session.contains("usuario.email") == false) {
			Login.form();
		}
		
	}
	@Before(unless={"Usuarios.listar"})
	static void permissoes(){
		if(session.get("usuario.nivel").equals("ADMINISTRADOR") == false && session.get("usuario.nivel").equals("FUNCIONARIO") == false) {
			renderTemplate("acessoNegado.html");
		}
		
	}
	
}
