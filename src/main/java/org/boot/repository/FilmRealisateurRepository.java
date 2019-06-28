package org.boot.repository;

import org.boot.model.FilmRealisateur;
import org.boot.model.FilmRealisateurPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRealisateurRepository extends JpaRepository<FilmRealisateur, FilmRealisateurPK> {

}
