package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Comment;

/**
 * コメントを操作するメソッドを持ったrepository.
 * 
 * @author junpei.oyama
 *
 */
@Repository
public class CommentRepository {

	/** DB操作を行うためNamedParameterJdbcTempleteをDI*/
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};
	
	/**
	 * article_idに対応したコメントを検索するメソッド.
	 * 
	 * @param articleId コメントした記事のid
	 * @return コメント一覧リスト
	 */
	public List<Comment> findByArticleId(int articleId){
		String sql = "SELECT id,name,content,article_id FROM comments WHERE article_id = :articleId";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return commentList;
	}
	
	/**
	 * commentを投稿するメソッド.
	 * 
	 * @param comment domain
	 * 
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId)";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		
		template.update(sql, param);
	}
	
	/**
	 * commentを削除するメソッド.
	 * 
	 * @param articleId コメントした記事のid
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		
		template.update(sql, param);
	}
	
	
}
