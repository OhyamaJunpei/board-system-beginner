package jp.co.sample.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "index";
	}
	
}
