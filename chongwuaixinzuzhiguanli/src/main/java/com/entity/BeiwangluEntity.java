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
 * 备忘录
 *
 * @author 
 * @email
 */
@TableName("beiwanglu")
public class BeiwangluEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BeiwangluEntity() {

	}

	public BeiwangluEntity(T t) {
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
     * 备忘录名称
     */
    @ColumnInfo(comment="备忘录名称",type="varchar(200)")
    @TableField(value = "beiwanglu_name")

    private String beiwangluName;


    /**
     * 备忘录编号
     */
    @ColumnInfo(comment="备忘录编号",type="varchar(200)")
    @TableField(value = "beiwanglu_uuid_number")

    private String beiwangluUuidNumber;


    /**
     * 备忘录内容
     */
    @ColumnInfo(comment="备忘录内容",type="longtext")
    @TableField(value = "beiwanglu_content")

    private String beiwangluContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "beiwanglu_delete")

    private Integer beiwangluDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
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
	 * 获取：备忘录名称
	 */
    public String getBeiwangluName() {
        return beiwangluName;
    }
    /**
	 * 设置：备忘录名称
	 */

    public void setBeiwangluName(String beiwangluName) {
        this.beiwangluName = beiwangluName;
    }
    /**
	 * 获取：备忘录编号
	 */
    public String getBeiwangluUuidNumber() {
        return beiwangluUuidNumber;
    }
    /**
	 * 设置：备忘录编号
	 */

    public void setBeiwangluUuidNumber(String beiwangluUuidNumber) {
        this.beiwangluUuidNumber = beiwangluUuidNumber;
    }
    /**
	 * 获取：备忘录内容
	 */
    public String getBeiwangluContent() {
        return beiwangluContent;
    }
    /**
	 * 设置：备忘录内容
	 */

    public void setBeiwangluContent(String beiwangluContent) {
        this.beiwangluContent = beiwangluContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getBeiwangluDelete() {
        return beiwangluDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setBeiwangluDelete(Integer beiwangluDelete) {
        this.beiwangluDelete = beiwangluDelete;
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
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Beiwanglu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", beiwangluName=" + beiwangluName +
            ", beiwangluUuidNumber=" + beiwangluUuidNumber +
            ", beiwangluContent=" + beiwangluContent +
            ", beiwangluDelete=" + beiwangluDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
