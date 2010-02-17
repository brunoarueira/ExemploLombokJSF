package br.lombokjsf.example.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the noticias database table.
 * 
 */
@Entity
@Table(name="noticias")
public class Noticia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;

	private String conteudo;

	private String titulo;

    public Noticia() {
    }

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}