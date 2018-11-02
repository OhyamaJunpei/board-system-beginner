package jp.co.sample.domain;

/**
 * コメント情報を表すドメイン.
 * 
 * @author junpei.oyama
 *
 */
public class Comment {
	
	/** コメントid */
	private Integer id;
	/** コメントした人の名前 */
	private String name;
	/** コメント内容 */
	private String content;
	/** コメントした記事のid */
	private Integer articleId;
	
	/** getter,setter */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
}
