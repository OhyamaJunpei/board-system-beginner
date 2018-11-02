package jp.co.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.ArticleForm;

/**
 * 記事情報をフォワードするcontroller.
 * 
 * @author junpei.oyama
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	private ArticleForm articleForm;
	
	@RequestMapping("/index")
	public String index() {
		
	}
	
}
