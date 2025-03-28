package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ChongwuCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 宠物评价
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("chongwu_commentback")
public class ChongwuCommentbackView extends ChongwuCommentbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 宠物
					 
		/**
		* 宠物 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer chongwuYonghuId;
		/**
		* 宠物名称
		*/

		@ColumnInfo(comment="宠物名称",type="varchar(200)")
		private String chongwuName;
		/**
		* 宠物编号
		*/

		@ColumnInfo(comment="宠物编号",type="varchar(200)")
		private String chongwuUuidNumber;
		/**
		* 宠物照片
		*/

		@ColumnInfo(comment="宠物照片",type="varchar(200)")
		private String chongwuPhoto;
		/**
		* 宠物类型
		*/
		@ColumnInfo(comment="宠物类型",type="int(11)")
		private Integer chongwuTypes;
			/**
			* 宠物类型的值
			*/
			@ColumnInfo(comment="宠物类型的字典表值",type="varchar(200)")
			private String chongwuValue;
		/**
		* 宠物介绍
		*/

		@ColumnInfo(comment="宠物介绍",type="longtext")
		private String chongwuContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer chongwuDelete;
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



	public ChongwuCommentbackView() {

	}

	public ChongwuCommentbackView(ChongwuCommentbackEntity chongwuCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, chongwuCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 宠物
		/**
		* 获取：宠物 的 用户
		*/
		public Integer getChongwuYonghuId() {
			return chongwuYonghuId;
		}
		/**
		* 设置：宠物 的 用户
		*/
		public void setChongwuYonghuId(Integer chongwuYonghuId) {
			this.chongwuYonghuId = chongwuYonghuId;
		}

		/**
		* 获取： 宠物名称
		*/
		public String getChongwuName() {
			return chongwuName;
		}
		/**
		* 设置： 宠物名称
		*/
		public void setChongwuName(String chongwuName) {
			this.chongwuName = chongwuName;
		}

		/**
		* 获取： 宠物编号
		*/
		public String getChongwuUuidNumber() {
			return chongwuUuidNumber;
		}
		/**
		* 设置： 宠物编号
		*/
		public void setChongwuUuidNumber(String chongwuUuidNumber) {
			this.chongwuUuidNumber = chongwuUuidNumber;
		}

		/**
		* 获取： 宠物照片
		*/
		public String getChongwuPhoto() {
			return chongwuPhoto;
		}
		/**
		* 设置： 宠物照片
		*/
		public void setChongwuPhoto(String chongwuPhoto) {
			this.chongwuPhoto = chongwuPhoto;
		}
		/**
		* 获取： 宠物类型
		*/
		public Integer getChongwuTypes() {
			return chongwuTypes;
		}
		/**
		* 设置： 宠物类型
		*/
		public void setChongwuTypes(Integer chongwuTypes) {
			this.chongwuTypes = chongwuTypes;
		}


			/**
			* 获取： 宠物类型的值
			*/
			public String getChongwuValue() {
				return chongwuValue;
			}
			/**
			* 设置： 宠物类型的值
			*/
			public void setChongwuValue(String chongwuValue) {
				this.chongwuValue = chongwuValue;
			}

		/**
		* 获取： 宠物介绍
		*/
		public String getChongwuContent() {
			return chongwuContent;
		}
		/**
		* 设置： 宠物介绍
		*/
		public void setChongwuContent(String chongwuContent) {
			this.chongwuContent = chongwuContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getChongwuDelete() {
			return chongwuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setChongwuDelete(Integer chongwuDelete) {
			this.chongwuDelete = chongwuDelete;
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
		return "ChongwuCommentbackView{" +
			", chongwuName=" + chongwuName +
			", chongwuUuidNumber=" + chongwuUuidNumber +
			", chongwuPhoto=" + chongwuPhoto +
			", chongwuContent=" + chongwuContent +
			", chongwuDelete=" + chongwuDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
