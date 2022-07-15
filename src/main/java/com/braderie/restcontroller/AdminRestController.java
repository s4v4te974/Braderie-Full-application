/**
 * Package Rest
 */
package com.braderie.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braderie.entity.Article;
import com.braderie.service.serviceimpl.AdminServiceImpl;
import com.braderie.service.serviceimpl.PanierServiceImpl;

/**
 * @author JRSS Restful Admin
 */

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "*")
public class AdminRestController {

	@Autowired
	AdminServiceImpl adminService;

	@Autowired
	PanierServiceImpl panierService;

	@PostMapping(path = "/createArticle", consumes = "application/json")
	public Article createArticle(@RequestBody Article article) {
		return adminService.createArticleAdmin(article);
	}

	@PutMapping(path = "/updateArticle")
	public Article updateArticle(@RequestBody Article article) {
		return adminService.updateArticleAdmin(article);
	}

	@DeleteMapping(path = "/removeAdmin/{id}")
	public void deleteArticle(@PathVariable("id") Integer idArticle) {
		adminService.deleteArticle(idArticle);
		adminService.removeArticleAdmin(idArticle);
	}
}
