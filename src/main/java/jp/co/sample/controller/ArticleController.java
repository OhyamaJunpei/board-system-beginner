package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Article;
import jp.co.sample.form.ArticleForm;
import jp.co.sample.repository.ArticleRepository;

/**
 * 記事情報をフォワードするcontroller.
 * 
 * @author junpei.oyama
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	/**
	 * 初期画面を表示するメソッド.
	 * 
	 * @param model モデル
	 * @return 入力画面
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
	
	/**
	 * 入力された情報を受け取りDBにinsertするメソッド.
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping("/articlePost")
	public String articlePost(Article article) {
		Article postArticle = new Article();
		
		postArticle.setName(article.getName());
		postArticle.setContent(article.getContent());
		
		articleRepository.insert(postArticle);
		
		return "forward:index";
	}
	
}
