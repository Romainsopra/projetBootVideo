package org.boot.restController;

import java.util.List;
import java.util.Optional;

import org.boot.jsonView.JsonViews;
import org.boot.jsonView.JsonViews.Common;
import org.boot.model.Film;
import org.boot.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/film")
public class FilmRestController {

	@Autowired
	private FilmRepository filmRepository;
	
	@JsonView(JsonViews.FilmAvecRealisateur.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Film>> findAll() {
		return list();
	}
	
	private ResponseEntity<List<Film>> list() {
		return new ResponseEntity<List<Film>>(filmRepository.findAllCustom(), HttpStatus.OK);
	}
	
	@JsonView(Common.class)
	@GetMapping("/searchFilm/{titre}")
	public ResponseEntity<List<Film>> searchFilm(@PathVariable (name="titre") String titre) {
		return findSome(titre);
	}
	
	public  ResponseEntity<List<Film>> findSome(String titre) {
		return new ResponseEntity<List<Film>>(filmRepository.findByTitreIgnoreCaseContaining(titre) , HttpStatus.OK);
	}
	
	@JsonView(Common.class)
	@GetMapping("/searchFilmR/{realisateur}")
	public ResponseEntity<List<Film>> searchFilmR(@PathVariable (name="realisateur") String r) {
		return findSomeR(r.toLowerCase());
	}
	
	public  ResponseEntity<List<Film>> findSomeR(String r) {
		return new ResponseEntity<List<Film>>(filmRepository.findAllCustom(r) , HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Delete(@PathVariable (name="id") Integer id) {
		
		Optional<Film> opt = filmRepository.findById(id);
		if (opt.isPresent()) {
			filmRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
