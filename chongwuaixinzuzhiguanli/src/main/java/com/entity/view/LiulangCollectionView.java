package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LiulangCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 流浪收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("liulang_collection")
public class LiulangCollectionView extends LiulangCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String liulangCollectionValue;

	//级联表 流浪
		/**
		* 流浪名称
		*/

		@ColumnInfo(comment="流浪名称",type="varchar(200)")
		private String liulangName;
		/**
		* 流浪编号
		*/

		@ColumnInfo(comment="流浪编号",type="varchar(200)")
		private String liulangUuidNumber;
		/**
		* 流浪照片
		*/

		@ColumnInfo(comment="流浪照片",type="varchar(200)")
		private String liulangPhoto;
		/**
		* 流浪类型
		*/
		@ColumnInfo(comment="流浪类型",type="int(11)")
		private Integer liulangTypes;
			/**
			* 流浪类型的值
			*/
			@ColumnInfo(comment="流浪类型的字典表值",type="varchar(200)")
			private String liulangValue;
		/**
		* 流浪介绍
		*/

		@ColumnInfo(comment="流浪介绍",type="longtext")
		private String liulangContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer liulangDelete;
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



	public LiulangCollectionView() {

	}

	public LiulangCollectionView(LiulangCollectionEntity liulangCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, liulangCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getLiulangCollectionValue() {
		return liulangCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setLiulangCollectionValue(String liulangCollectionValue) {
		this.liulangCollectionValue = liulangCollectionValue;
	}


	//级联表的get和set 流浪

		/**
		* 获取： 流浪名称
		*/
		public String getLiulangName() {
			return liulangName;
		}
		/**
		* 设置： 流浪名称
		*/
		public void setLiulangName(String liulangName) {
			this.liulangName = liulangName;
		}

		/**
		* 获取： 流浪编号
		*/
		public String getLiulangUuidNumber() {
			return liulangUuidNumber;
		}
		/**
		* 设置： 流浪编号
		*/
		public void setLiulangUuidNumber(String liulangUuidNumber) {
			this.liulangUuidNumber = liulangUuidNumber;
		}

		/**
		* 获取： 流浪照片
		*/
		public String getLiulangPhoto() {
			return liulangPhoto;
		}
		/**
		* 设置： 流浪照片
		*/
		public void setLiulangPhoto(String liulangPhoto) {
			this.liulangPhoto = liulangPhoto;
		}
		/**
		* 获取： 流浪类型
		*/
		public Integer getLiulangTypes() {
			return liulangTypes;
		}
		/**
		* 设置： 流浪类型
		*/
		public void setLiulangTypes(Integer liulangTypes) {
			this.liulangTypes = liulangTypes;
		}


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

		/**
		* 获取： 流浪介绍
		*/
		public String getLiulangContent() {
			return liulangContent;
		}
		/**
		* 设置： 流浪介绍
		*/
		public void setLiulangContent(String liulangContent) {
			this.liulangContent = liulangContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getLiulangDelete() {
			return liulangDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setLiulangDelete(Integer liulangDelete) {
			this.liulangDelete = liulangDelete;
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
		return "LiulangCollectionView{" +
			", liulangCollectionValue=" + liulangCollectionValue +
			", liulangName=" + liulangName +
			", liulangUuidNumber=" + liulangUuidNumber +
			", liulangPhoto=" + liulangPhoto +
			", liulangContent=" + liulangContent +
			", liulangDelete=" + liulangDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
