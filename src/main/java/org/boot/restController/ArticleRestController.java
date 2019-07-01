package org.boot.restController;

import java.util.List;
import java.util.Optional;

import org.boot.jsonView.JsonViews;
import org.boot.model.Article;
import org.boot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/article")
public class ArticleRestController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Article>> findAll() {
		return list();
	}
	
	@JsonView(JsonViews.ArticleAvecFilm.class)
	@GetMapping(value = {"/film"})
	public ResponseEntity<List<Article>> findAllWithFilm() {
		return list();
	}
	
	@JsonView(JsonViews.ArticleAvecFilm.class)
	@GetMapping(value = {"{numeroArticle}/film"})
	public ResponseEntity<Article> findByNumeroArticleWithFilm(@PathVariable(name="numeroArticle") Integer numeroArticle) {
		return findArticleByNumeroArticle(numeroArticle);
	}
	
	private ResponseEntity<List<Article>>list() {
		return new ResponseEntity<List<Article>>(articleRepository.findAll(),HttpStatus.OK);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{numeroArticle}")
	public ResponseEntity<Article> findByNumeroArticle(@PathVariable(name="numeroArticle") Integer numeroArticle) {
		return findArticleByNumeroArticle(numeroArticle);
	}
	
	private ResponseEntity<Article> findArticleByNumeroArticle(Integer numeroArticle) {
		Optional<Article> opt = articleRepository.findById(numeroArticle);
		if (opt.isPresent()) {
			return new ResponseEntity<Article>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}



