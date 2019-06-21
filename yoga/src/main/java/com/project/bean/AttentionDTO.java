package com.project.bean;

/**
 * 此类目前未使用
 * 
 * 关注与粉丝数据传输对象
 * 用于显示当前用户的所有关注和粉丝
 * @author Administrator
 *
 */
public class AttentionDTO {
	
	private String id;
	
	private String name;
	
	private String headimg;
	
	private String type;
	
	private String typeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getTypeStr() {
		if(type != null) {
			if("t_student".equals(type)){
				typeStr = "学员";
			}
			if("t_coach".equals(type)){
				typeStr = "教练";
			}
			if("t_gym".equals(type)){
				typeStr = "场馆";
			}
		}
		return typeStr;
	}

	@Override
	public String toString() {
		return "AttentionDTO [id=" + id + ", name=" + name + ", headimg=" + headimg + ", type=" + type + ", typeStr="
				+ typeStr + "]";
	}

}
