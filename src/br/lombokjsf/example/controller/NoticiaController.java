package br.lombokjsf.example.controller;

import br.lombokjsf.example.model.Noticia;

public class NoticiaController {
	private String titulo;
	
	private Noticia noticia;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
}