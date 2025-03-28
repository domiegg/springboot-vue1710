package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 宠物
 *
 * @author 
 * @email
 */
@TableName("chongwu")
public class ChongwuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChongwuEntity() {

	}

	public ChongwuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 宠物名称
     */
    @ColumnInfo(comment="宠物名称",type="varchar(200)")
    @TableField(value = "chongwu_name")

    private String chongwuName;


    /**
     * 宠物编号
     */
    @ColumnInfo(comment="宠物编号",type="varchar(200)")
    @TableField(value = "chongwu_uuid_number")

    private String chongwuUuidNumber;


    /**
     * 宠物照片
     */
    @ColumnInfo(comment="宠物照片",type="varchar(200)")
    @TableField(value = "chongwu_photo")

    private String chongwuPhoto;


    /**
     * 宠物类型
     */
    @ColumnInfo(comment="宠物类型",type="int(11)")
    @TableField(value = "chongwu_types")

    private Integer chongwuTypes;


    /**
     * 宠物介绍
     */
    @ColumnInfo(comment="宠物介绍",type="longtext")
    @TableField(value = "chongwu_content")

    private String chongwuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "chongwu_delete")

    private Integer chongwuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：宠物名称
	 */
    public String getChongwuName() {
        return chongwuName;
    }
    /**
	 * 设置：宠物名称
	 */

    public void setChongwuName(String chongwuName) {
        this.chongwuName = chongwuName;
    }
    /**
	 * 获取：宠物编号
	 */
    public String getChongwuUuidNumber() {
        return chongwuUuidNumber;
    }
    /**
	 * 设置：宠物编号
	 */

    public void setChongwuUuidNumber(String chongwuUuidNumber) {
        this.chongwuUuidNumber = chongwuUuidNumber;
    }
    /**
	 * 获取：宠物照片
	 */
    public String getChongwuPhoto() {
        return chongwuPhoto;
    }
    /**
	 * 设置：宠物照片
	 */

    public void setChongwuPhoto(String chongwuPhoto) {
        this.chongwuPhoto = chongwuPhoto;
    }
    /**
	 * 获取：宠物类型
	 */
    public Integer getChongwuTypes() {
        return chongwuTypes;
    }
    /**
	 * 设置：宠物类型
	 */

    public void setChongwuTypes(Integer chongwuTypes) {
        this.chongwuTypes = chongwuTypes;
    }
    /**
	 * 获取：宠物介绍
	 */
    public String getChongwuContent() {
        return chongwuContent;
    }
    /**
	 * 设置：宠物介绍
	 */

    public void setChongwuContent(String chongwuContent) {
        this.chongwuContent = chongwuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getChongwuDelete() {
        return chongwuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setChongwuDelete(Integer chongwuDelete) {
        this.chongwuDelete = chongwuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Chongwu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", chongwuName=" + chongwuName +
            ", chongwuUuidNumber=" + chongwuUuidNumber +
            ", chongwuPhoto=" + chongwuPhoto +
            ", chongwuTypes=" + chongwuTypes +
            ", chongwuContent=" + chongwuContent +
            ", chongwuDelete=" + chongwuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
