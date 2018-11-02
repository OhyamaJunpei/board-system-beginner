package jp.co.sample.domain;

import java.util.List;

/**
 * 記事の情報を表すドメイン.
 * 
 * @author junpei.oyama
 *
 */
public class Article {
	
	/** 記事id */
	private Integer id;
	/** 記事を投稿した人の名前 */
	private String name;
	/** 記事内容 */
	private String content;
	/** 記事に対するコメントのリスト */
	private List<Comment> commentList;
	
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
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
}
