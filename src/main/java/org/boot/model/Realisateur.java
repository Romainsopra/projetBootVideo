package org.boot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.boot.jsonView.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "realisateur")
public class Realisateur {
	@Id
	@SequenceGenerator(name = "seqRealisateur", sequenceName = "seq_realisateur", initialValue = 100, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRealisateur")
	@Column(name = "id_realisateur")
	private Integer id;
	@JsonView(JsonViews.FilmAvecRealisateur.class)
	@Column(name = "prenom_realisateur", length = 200)
	private String prenom;
	@Column(name = "nom_realisateur", length = 200)
	@JsonView(JsonViews.FilmAvecRealisateur.class)
	private String nom;
	@Version
	private int version;
	@OneToMany(mappedBy = "key.realisateur")
	@JsonView(JsonViews.RealisateurAvecFilm.class)
	private Set<FilmRealisateur> films;

	public Realisateur() {

	}

	public Set<FilmRealisateur> getFilms() {
		return films;
	}

	public void setFilms(Set<FilmRealisateur> films) {
		this.films = films;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Realisateur other = (Realisateur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
