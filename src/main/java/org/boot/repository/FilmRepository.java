package org.boot.repository;
import java.util.List;

import org.boot.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

	
	
	public List<Film> findByTitreIgnoreCaseContaining(String titre);
}
