package com.hit.memoryunits;

public class Page<T> extends java.lang.Object implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	protected T content;
	protected long pageId;
	
	
	Page(java.lang.Long id, T content){
		setPageId(id);
		setContent(content);
	}

	public java.lang.Long getPageId(){
		return this.pageId;
	}

	public void setPageId(java.lang.Long pageId) {
		this.pageId = pageId;
	}

	public T getContent() {
		return this.content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override	
	public boolean equals(java.lang.Object obj) {
		if(obj.hashCode() == this.getPageId().hashCode())
			return true;
		return false;
	}

	@Override	
	public java.lang.String toString(){
		return super.toString();
	}
}
