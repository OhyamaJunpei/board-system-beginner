package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Article;
import jp.co.sample.domain.Comment;

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
	
	private static final ResultSetExtractor<List<Article>> ARTICLELIST_RESULT_SET_EXTRACTOR = (rs) -> {
		Map<Integer, Article> map = new LinkedHashMap<>();
		Article article = null;
		while(rs.next()) {
			Integer articleId = rs.getInt("a_id");
			article = map.get(articleId);
			if(article == null) {
				article = new Article();
				article.setId(articleId);
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));
				map.put(articleId, article);
			}
			
			Integer commentId = rs.getInt("c_id");
			if(commentId != null) {
				Comment comment = new Comment();
				comment.setId(commentId);
				comment.setId(articleId);
				comment.setName(rs.getString("c_name"));
				comment.setContent(rs.getString("c_content"));
				comment.setArticleId(rs.getInt("c_article_id"));
				article.getCommentList().add(comment);
			}
		}
		
		return new ArrayList<Article>(map.values());
	};
	
	/**
	 * 記事一覧を取得するメソッド.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
		
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
//		//中級
//		String sqlMiddle = "SELECT a.id as a_id, a.name as a_name, a.content as a_content, c.id as c_id, c.name as c_name, c.content as c_content, c.article_id as c_article_id "
//							+ "FROM articles as a LEFT OUTER JOIN comments as c ON a.id = c.article_id";
//		
//		
//		List<Article> articleList = template.query(sqlMiddle, ARTICLELIST_RESULT_SET_EXTRACTOR);
		
		return articleList;
	}
	
	private SimpleJdbcInsert insert;
	
	/**
	 * 自動採番されたidを取得するためのメソッド.
	 * 記事投稿の時のみ使用.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("articles");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 記事を投稿するメソッド.
	 * insert時に採番されたidを取得.
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
