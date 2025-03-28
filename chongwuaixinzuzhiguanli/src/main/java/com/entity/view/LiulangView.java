package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LiulangEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 流浪
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("liulang")
public class LiulangView extends LiulangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 流浪类型的值
	*/
	@ColumnInfo(comment="流浪类型的字典表值",type="varchar(200)")
	private String liulangValue;




	public LiulangView() {

	}

	public LiulangView(LiulangEntity liulangEntity) {
		try {
			BeanUtils.copyProperties(this, liulangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 流浪类型的值
	*/
	public String getLiulangValue() {
		return liulangValue;
	}
	/**
	* 设置： 流浪类型的值
	*/
	public void setLiulangValue(String liulangValue) {
		this.liulangValue = liulangValue;
	}




	@Override
	public String toString() {
		return "LiulangView{" +
			", liulangValue=" + liulangValue +
			"} " + super.toString();
	}
}
