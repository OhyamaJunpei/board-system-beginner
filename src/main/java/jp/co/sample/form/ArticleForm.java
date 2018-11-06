package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

/**
 * 記事の入力form.
 * 
 * @author junpei.oyama
 *
 */
public class ArticleForm {
	
	/** 記事を投稿する人の名前 */
	@NotBlank(message="投稿者名を入力してください")
	private String name;
	/** 記事内容 */
	@NotBlank(message="記事内容を入力してください")
	private String content;
	
	/** getter,setter */
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
