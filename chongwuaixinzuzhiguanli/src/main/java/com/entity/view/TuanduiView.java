package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.TuanduiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 团队活动
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("tuandui")
public class TuanduiView extends TuanduiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 团队活动类型的值
	*/
	@ColumnInfo(comment="团队活动类型的字典表值",type="varchar(200)")
	private String tuanduiValue;




	public TuanduiView() {

	}

	public TuanduiView(TuanduiEntity tuanduiEntity) {
		try {
			BeanUtils.copyProperties(this, tuanduiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 团队活动类型的值
	*/
	public String getTuanduiValue() {
		return tuanduiValue;
	}
	/**
	* 设置： 团队活动类型的值
	*/
	public void setTuanduiValue(String tuanduiValue) {
		this.tuanduiValue = tuanduiValue;
	}




	@Override
	public String toString() {
		return "TuanduiView{" +
			", tuanduiValue=" + tuanduiValue +
			"} " + super.toString();
	}
}
