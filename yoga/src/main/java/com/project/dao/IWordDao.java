package com.project.dao;

import org.apache.ibatis.annotations.Insert;

import com.project.bean.WordsBean;

public interface IWordDao {
	/**
	 * 增加留言
	 * @param stuId 被留言人
	 * @param word 留言内容
	 * @return 影响行数
	 */
	@Insert("insert into t_words(w_userid,w_showid,w_content) values(#{w_userid},#{w_showid},#{w_content})")
	int addWord(WordsBean words);
}
