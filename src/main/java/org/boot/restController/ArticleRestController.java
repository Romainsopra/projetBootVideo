package org.boot.restController;

import java.util.List;
import java.util.Optional;

import org.boot.model.Article;
import org.boot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/article")
public class ArticleRestController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Article>> findAll() {
		return list();
	}
	
	private ResponseEntity<List<Article>>list() {
		return new ResponseEntity<List<Article>>(articleRepository.findAll(),HttpStatus.OK);
	}
	
	
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



