package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Emprestimo extends Model {

	@ManyToOne
	public Usuario usuario;
	
	@OneToOne
	public Livro livro;
	
	public String dataExpiracao;
}
