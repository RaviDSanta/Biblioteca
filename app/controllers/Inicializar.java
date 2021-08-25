package controllers;

import models.Livro;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;


@OnApplicationStart
public class Inicializar extends Job {
	
	public void dojob() {
		
		if(Livro.count() == 0) {
			
			Fixtures.loadModels("data-biblioteca.yml");
			
		}
		
	}

}
