package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.WordsBean;

public interface IWordDao {
	
	//  查询我的留言
	@Select("SELECT * FROM t_words WHERE w_showid = #{id};")
	public List<WordsBean> findWords(String id);
	
	/*//  删除我的留言
	@Delete("DELETE FROM t_words WHERE w_id = #{id};")
	public void deleteWords(String id);*/
	
	//  留言
	@Insert("INSERT INTO t_words(w_content,w_time,w_userid,w_showid) VALUES(#{w_content},#{w_time},#{w_userid},#{w_showid})")
	public int insertWords(WordsBean wordsBean);

}
