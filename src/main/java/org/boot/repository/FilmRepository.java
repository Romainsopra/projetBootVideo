package org.boot.repository;
import java.util.List;

import org.boot.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmRepository extends JpaRepository<Film, Integer> {

	
	@Query("select distinct f from Film f left join fetch f.realisateurs fr left join fetch fr.key k left join fetch k.realisateur r")
	public List<Film> findAllCustom();
	
	public List<Film> findByTitreIgnoreCaseContaining(String titre);
	
	
	@Query("select distinct f from Film f left join fetch f.realisateurs fr left join fetch fr.key k left join fetch k.realisateur r where lower(r.nom) like %:recherche%")
	public List<Film> findAllCustom(String recherche);
}
