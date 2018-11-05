package jp.co.sample.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Article;

/**
 * 記事を操作するメソッドをもつrepository.
 * 
 * @author junpei.oyama
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 記事一覧を取得するメソッド.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
		
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	}
	
	private SimpleJdbcInsert insert;
	
	/**
	 * 自動採番されたidを取得するためのメソッド.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("articles");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 記事を投稿するメソッド.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
//		String sql = "INSERT INTO articles(name, content) VALUES(:name, :content)";
//		
//		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
//		
//		template.update(sql, param);
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		Number key = insert.executeAndReturnKey(param);
		article.setId(key.intValue());
		
	}
	
	/**
	 * 記事を削除するメソッド.
	 * 
	 * @param articleId 記事のid
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "DELETE FROM articles WHERE id = :articleId";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		template.update(sql, param);
	}
	
	
}
