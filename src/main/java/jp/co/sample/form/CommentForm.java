package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

/**
 * コメントの入力フォーム.
 * 
 * @author junpei.oyama
 *
 */
public class CommentForm {
	
	/** コメントした記事のid */
	private String articleId;
	/** コメントした人の名前 */
	@NotBlank(message="コメント者名を入力してください")
	private String name;
	/** コメント内容 */
	@NotBlank(message="コメント内容を入力してください")
	private String content;
	
	/** getter,setter */
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	
}
