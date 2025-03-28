package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.TuanduiCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 团队活动收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("tuandui_collection")
public class TuanduiCollectionView extends TuanduiCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String tuanduiCollectionValue;

	//级联表 团队活动
		/**
		* 团队活动名称
		*/

		@ColumnInfo(comment="团队活动名称",type="varchar(200)")
		private String tuanduiName;
		/**
		* 团队活动编号
		*/

		@ColumnInfo(comment="团队活动编号",type="varchar(200)")
		private String tuanduiUuidNumber;
		/**
		* 团队活动照片
		*/

		@ColumnInfo(comment="团队活动照片",type="varchar(200)")
		private String tuanduiPhoto;
		/**
		* 活动时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="活动时间",type="timestamp")
		private Date huodongTime;
		/**
		* 团队活动类型
		*/
		@ColumnInfo(comment="团队活动类型",type="int(11)")
		private Integer tuanduiTypes;
			/**
			* 团队活动类型的值
			*/
			@ColumnInfo(comment="团队活动类型的字典表值",type="varchar(200)")
			private String tuanduiValue;
		/**
		* 团队活动介绍
		*/

		@ColumnInfo(comment="团队活动介绍",type="longtext")
		private String tuanduiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer tuanduiDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户类型
		*/
		@ColumnInfo(comment="用户类型",type="int(11)")
		private Integer yonghuTypes;
			/**
			* 用户类型的值
			*/
			@ColumnInfo(comment="用户类型的字典表值",type="varchar(200)")
			private String yonghuValue;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;



	public TuanduiCollectionView() {

	}

	public TuanduiCollectionView(TuanduiCollectionEntity tuanduiCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, tuanduiCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getTuanduiCollectionValue() {
		return tuanduiCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setTuanduiCollectionValue(String tuanduiCollectionValue) {
		this.tuanduiCollectionValue = tuanduiCollectionValue;
	}


	//级联表的get和set 团队活动

		/**
		* 获取： 团队活动名称
		*/
		public String getTuanduiName() {
			return tuanduiName;
		}
		/**
		* 设置： 团队活动名称
		*/
		public void setTuanduiName(String tuanduiName) {
			this.tuanduiName = tuanduiName;
		}

		/**
		* 获取： 团队活动编号
		*/
		public String getTuanduiUuidNumber() {
			return tuanduiUuidNumber;
		}
		/**
		* 设置： 团队活动编号
		*/
		public void setTuanduiUuidNumber(String tuanduiUuidNumber) {
			this.tuanduiUuidNumber = tuanduiUuidNumber;
		}

		/**
		* 获取： 团队活动照片
		*/
		public String getTuanduiPhoto() {
			return tuanduiPhoto;
		}
		/**
		* 设置： 团队活动照片
		*/
		public void setTuanduiPhoto(String tuanduiPhoto) {
			this.tuanduiPhoto = tuanduiPhoto;
		}

		/**
		* 获取： 活动时间
		*/
		public Date getHuodongTime() {
			return huodongTime;
		}
		/**
		* 设置： 活动时间
		*/
		public void setHuodongTime(Date huodongTime) {
			this.huodongTime = huodongTime;
		}
		/**
		* 获取： 团队活动类型
		*/
		public Integer getTuanduiTypes() {
			return tuanduiTypes;
		}
		/**
		* 设置： 团队活动类型
		*/
		public void setTuanduiTypes(Integer tuanduiTypes) {
			this.tuanduiTypes = tuanduiTypes;
		}


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

		/**
		* 获取： 团队活动介绍
		*/
		public String getTuanduiContent() {
			return tuanduiContent;
		}
		/**
		* 设置： 团队活动介绍
		*/
		public void setTuanduiContent(String tuanduiContent) {
			this.tuanduiContent = tuanduiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getTuanduiDelete() {
			return tuanduiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setTuanduiDelete(Integer tuanduiDelete) {
			this.tuanduiDelete = tuanduiDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}
		/**
		* 获取： 用户类型
		*/
		public Integer getYonghuTypes() {
			return yonghuTypes;
		}
		/**
		* 设置： 用户类型
		*/
		public void setYonghuTypes(Integer yonghuTypes) {
			this.yonghuTypes = yonghuTypes;
		}


			/**
			* 获取： 用户类型的值
			*/
			public String getYonghuValue() {
				return yonghuValue;
			}
			/**
			* 设置： 用户类型的值
			*/
			public void setYonghuValue(String yonghuValue) {
				this.yonghuValue = yonghuValue;
			}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "TuanduiCollectionView{" +
			", tuanduiCollectionValue=" + tuanduiCollectionValue +
			", tuanduiName=" + tuanduiName +
			", tuanduiUuidNumber=" + tuanduiUuidNumber +
			", tuanduiPhoto=" + tuanduiPhoto +
			", huodongTime=" + DateUtil.convertString(huodongTime,"yyyy-MM-dd") +
			", tuanduiContent=" + tuanduiContent +
			", tuanduiDelete=" + tuanduiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
