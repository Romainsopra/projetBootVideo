package org.boot.restController;

import java.util.List;

import org.boot.jsonView.JsonViews.Common;
import org.boot.model.Film;
import org.boot.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/film")
public class FilmRestController {

	@Autowired
	private FilmRepository filmRepository;
	
	@JsonView(Common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Film>> findAll() {
		return list();
	}
	
	private ResponseEntity<List<Film>> list() {
		return new ResponseEntity<List<Film>>(filmRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(Common.class)
	@GetMapping(value = {"/searchFilm"})
	public ResponseEntity<List<Film>> searchFilm(@RequestParam (name="titre") String titre) {
		return findSome(titre);
	}
	
	public  ResponseEntity<List<Film>> findSome(String titre) {
		return new ResponseEntity<List<Film>>(filmRepository.findByTitreIgnoreCaseContaining(titre) , HttpStatus.OK);
	}
	
}
