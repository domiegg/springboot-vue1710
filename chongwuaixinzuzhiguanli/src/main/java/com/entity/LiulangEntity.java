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
 * 流浪
 *
 * @author 
 * @email
 */
@TableName("liulang")
public class LiulangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LiulangEntity() {

	}

	public LiulangEntity(T t) {
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
     * 流浪名称
     */
    @ColumnInfo(comment="流浪名称",type="varchar(200)")
    @TableField(value = "liulang_name")

    private String liulangName;


    /**
     * 流浪编号
     */
    @ColumnInfo(comment="流浪编号",type="varchar(200)")
    @TableField(value = "liulang_uuid_number")

    private String liulangUuidNumber;


    /**
     * 流浪照片
     */
    @ColumnInfo(comment="流浪照片",type="varchar(200)")
    @TableField(value = "liulang_photo")

    private String liulangPhoto;


    /**
     * 流浪类型
     */
    @ColumnInfo(comment="流浪类型",type="int(11)")
    @TableField(value = "liulang_types")

    private Integer liulangTypes;


    /**
     * 流浪介绍
     */
    @ColumnInfo(comment="流浪介绍",type="longtext")
    @TableField(value = "liulang_content")

    private String liulangContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "liulang_delete")

    private Integer liulangDelete;


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
	 * 获取：流浪名称
	 */
    public String getLiulangName() {
        return liulangName;
    }
    /**
	 * 设置：流浪名称
	 */

    public void setLiulangName(String liulangName) {
        this.liulangName = liulangName;
    }
    /**
	 * 获取：流浪编号
	 */
    public String getLiulangUuidNumber() {
        return liulangUuidNumber;
    }
    /**
	 * 设置：流浪编号
	 */

    public void setLiulangUuidNumber(String liulangUuidNumber) {
        this.liulangUuidNumber = liulangUuidNumber;
    }
    /**
	 * 获取：流浪照片
	 */
    public String getLiulangPhoto() {
        return liulangPhoto;
    }
    /**
	 * 设置：流浪照片
	 */

    public void setLiulangPhoto(String liulangPhoto) {
        this.liulangPhoto = liulangPhoto;
    }
    /**
	 * 获取：流浪类型
	 */
    public Integer getLiulangTypes() {
        return liulangTypes;
    }
    /**
	 * 设置：流浪类型
	 */

    public void setLiulangTypes(Integer liulangTypes) {
        this.liulangTypes = liulangTypes;
    }
    /**
	 * 获取：流浪介绍
	 */
    public String getLiulangContent() {
        return liulangContent;
    }
    /**
	 * 设置：流浪介绍
	 */

    public void setLiulangContent(String liulangContent) {
        this.liulangContent = liulangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getLiulangDelete() {
        return liulangDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setLiulangDelete(Integer liulangDelete) {
        this.liulangDelete = liulangDelete;
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
        return "Liulang{" +
            ", id=" + id +
            ", liulangName=" + liulangName +
            ", liulangUuidNumber=" + liulangUuidNumber +
            ", liulangPhoto=" + liulangPhoto +
            ", liulangTypes=" + liulangTypes +
            ", liulangContent=" + liulangContent +
            ", liulangDelete=" + liulangDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
