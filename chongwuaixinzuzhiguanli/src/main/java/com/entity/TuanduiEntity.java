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
 * 团队活动
 *
 * @author 
 * @email
 */
@TableName("tuandui")
public class TuanduiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TuanduiEntity() {

	}

	public TuanduiEntity(T t) {
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
     * 团队活动名称
     */
    @ColumnInfo(comment="团队活动名称",type="varchar(200)")
    @TableField(value = "tuandui_name")

    private String tuanduiName;


    /**
     * 团队活动编号
     */
    @ColumnInfo(comment="团队活动编号",type="varchar(200)")
    @TableField(value = "tuandui_uuid_number")

    private String tuanduiUuidNumber;


    /**
     * 团队活动照片
     */
    @ColumnInfo(comment="团队活动照片",type="varchar(200)")
    @TableField(value = "tuandui_photo")

    private String tuanduiPhoto;


    /**
     * 活动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="活动时间",type="timestamp")
    @TableField(value = "huodong_time")

    private Date huodongTime;


    /**
     * 团队活动类型
     */
    @ColumnInfo(comment="团队活动类型",type="int(11)")
    @TableField(value = "tuandui_types")

    private Integer tuanduiTypes;


    /**
     * 团队活动介绍
     */
    @ColumnInfo(comment="团队活动介绍",type="longtext")
    @TableField(value = "tuandui_content")

    private String tuanduiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "tuandui_delete")

    private Integer tuanduiDelete;


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
	 * 获取：团队活动名称
	 */
    public String getTuanduiName() {
        return tuanduiName;
    }
    /**
	 * 设置：团队活动名称
	 */

    public void setTuanduiName(String tuanduiName) {
        this.tuanduiName = tuanduiName;
    }
    /**
	 * 获取：团队活动编号
	 */
    public String getTuanduiUuidNumber() {
        return tuanduiUuidNumber;
    }
    /**
	 * 设置：团队活动编号
	 */

    public void setTuanduiUuidNumber(String tuanduiUuidNumber) {
        this.tuanduiUuidNumber = tuanduiUuidNumber;
    }
    /**
	 * 获取：团队活动照片
	 */
    public String getTuanduiPhoto() {
        return tuanduiPhoto;
    }
    /**
	 * 设置：团队活动照片
	 */

    public void setTuanduiPhoto(String tuanduiPhoto) {
        this.tuanduiPhoto = tuanduiPhoto;
    }
    /**
	 * 获取：活动时间
	 */
    public Date getHuodongTime() {
        return huodongTime;
    }
    /**
	 * 设置：活动时间
	 */

    public void setHuodongTime(Date huodongTime) {
        this.huodongTime = huodongTime;
    }
    /**
	 * 获取：团队活动类型
	 */
    public Integer getTuanduiTypes() {
        return tuanduiTypes;
    }
    /**
	 * 设置：团队活动类型
	 */

    public void setTuanduiTypes(Integer tuanduiTypes) {
        this.tuanduiTypes = tuanduiTypes;
    }
    /**
	 * 获取：团队活动介绍
	 */
    public String getTuanduiContent() {
        return tuanduiContent;
    }
    /**
	 * 设置：团队活动介绍
	 */

    public void setTuanduiContent(String tuanduiContent) {
        this.tuanduiContent = tuanduiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getTuanduiDelete() {
        return tuanduiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setTuanduiDelete(Integer tuanduiDelete) {
        this.tuanduiDelete = tuanduiDelete;
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
        return "Tuandui{" +
            ", id=" + id +
            ", tuanduiName=" + tuanduiName +
            ", tuanduiUuidNumber=" + tuanduiUuidNumber +
            ", tuanduiPhoto=" + tuanduiPhoto +
            ", huodongTime=" + DateUtil.convertString(huodongTime,"yyyy-MM-dd") +
            ", tuanduiTypes=" + tuanduiTypes +
            ", tuanduiContent=" + tuanduiContent +
            ", tuanduiDelete=" + tuanduiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
