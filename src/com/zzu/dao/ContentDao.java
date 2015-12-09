package com.zzu.dao;

import com.zzu.modle.Content;
/**
 * 
 * @author xingjiali
 *
 */
public interface ContentDao {
	public Content getContent(long contentid);

	public long addContent(Content content);
}
