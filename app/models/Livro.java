package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Livro extends Model{
	
	@Required
	public String nome;
	
	@Required
	public String autor;
	
	@Required
	public String genero;
	
	public StatusLivro status;
	
}