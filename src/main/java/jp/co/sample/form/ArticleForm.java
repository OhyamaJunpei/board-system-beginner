package jp.co.sample.form;

/**
 * 記事の入力form.
 * 
 * @author junpei.oyama
 *
 */
public class ArticleForm {
	
	/** 記事を投稿する人の名前 */
	private String name;
	/** 記事内容 */
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
