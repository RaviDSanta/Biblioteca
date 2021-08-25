package models;

import javax.persistence.Entity;

import net.sf.oval.constraint.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	@Required(message="campo do vazio")
	public String nome;
	@Required
	@Email(message="email invalido")
	public String email;
	@Required
	public String senha;
	@Required
	public int nivel;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
	
}