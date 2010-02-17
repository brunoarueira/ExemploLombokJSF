package br.lombokjsf.example.controller;

import java.util.LinkedHashSet;

import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import br.lombokjsf.example.enums.EstadoEnum;
import br.lombokjsf.example.model.Noticia;
import br.lombokjsf.example.persistence.NoticiaPersistence;

public class NoticiaController {
	private String titulo;
	
	private EstadoEnum estado;
	
	private Noticia noticia;
	
	private NoticiaPersistence noticiaPersistence;
	
	public NoticiaController() {
		this.noticiaPersistence = new NoticiaPersistence();		
	}
			
	public EstadoEnum getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	
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
	
	public void cadastrar() {
		this.noticiaPersistence.save(this.noticia);
	}
	
	public void editar() {
		this.noticiaPersistence.update(this.noticia);
	}
	
	public void salvar() {
		if (this.estado.equals(EstadoEnum.CADASTRAR))
			cadastrar();
		else
			editar();
	}
	
	public void limpar() {
		this.noticia = new Noticia();
	}
	
	public DataModel getListaNoticias() {
		LinkedHashSet noticias = this.noticiaPersistence.findSQLCreate("select n from Noticia n");
		ArrayDataModel adm;
		
		if (noticias.isEmpty())
			adm = new ArrayDataModel();
		else
			adm = new ArrayDataModel(noticias.toArray());
		
		return adm;
	}
	
	public boolean isHasMessages() {
		return FacesContext.getCurrentInstance().getMessages().hasNext();
	}
}