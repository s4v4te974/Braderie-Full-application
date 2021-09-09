/**
 * 
 */
package com.springangularbraderie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangularbraderie.model.Article;
import com.springangularbraderie.repository.IAdminRep;

import lombok.Data;

/**
 * @author Stephane Kouotze CDA7
 *
 */
@Service
@Data
public class AdminService implements IAdminService {
	
	@Autowired
	IAdminRep adminRep;

	// methode du crud
	
	// Create (insert - put)
	
	// read All  ( select - get )
	public Article getOneArticle(Article p_article) {
		
		
		
		return null;
	}
	
	// read One
	
	// update ( update - put )
	
	// delete ( delete - delete) 
	
	
	
}
