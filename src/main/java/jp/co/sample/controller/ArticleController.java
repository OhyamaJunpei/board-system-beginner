package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Article;
import jp.co.sample.domain.Comment;
import jp.co.sample.form.ArticleForm;
import jp.co.sample.form.CommentForm;
import jp.co.sample.repository.ArticleRepository;
import jp.co.sample.repository.CommentRepository;

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
	
	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
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
		for(Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
		}
		
		model.addAttribute("articleList", articleList);
		
//		List<Comment> commentList = commentRepository.findByArticleId(articleId);
//		model.addAttribute("commentList", commentList);
		
		return "bbs";
	}
	
	/**
	 * 入力された情報を受け取りDBにinsertするメソッド.
	 * 
	 * @param article
	 * @return
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm) {
//		Article postArticle = new Article();
//		
//		postArticle.setName(article.getName());
//		postArticle.setContent(article.getContent());
//		
//		articleRepository.insert(postArticle);
		
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		
		return "redirect:index";
	}
	
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		System.out.println(comment.getArticleId());
		commentRepository.insert(comment);
	
		return "redirect:index";
	}
	
	@RequestMapping("/delete")
	public String delete(CommentForm commentForm) {
		Comment comment = new Comment();
		comment.setArticleId(Integer.parseInt(commentForm.getArticleId()));
		
		commentRepository.deleteByArticleId(comment.getArticleId());
		System.out.println(comment.getArticleId());
		articleRepository.deleteByArticleId(comment.getArticleId());
		return "redirect:index";
	}
}
