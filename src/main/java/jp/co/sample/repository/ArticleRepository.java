package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import jp.co.sample.domain.Article;

/**
 * 記事を操作するメソッドをもつrepository.
 * 
 * @author junpei.oyama
 *
 */
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
	 * 記事一覧を取得するメソッド
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY name";
		
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	}
	
	public void insert(Article article) {
		String sql = "INSERT INTO articles(id, name, content) VALUES(:id, :name, :content)";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		
		template.update(sql, param);
		
	}
}
