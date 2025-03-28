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
 * 志愿者申请
 *
 * @author 
 * @email
 */
@TableName("zhiyuanzhe_shenqing")
public class ZhiyuanzheShenqingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZhiyuanzheShenqingEntity() {

	}

	public ZhiyuanzheShenqingEntity(T t) {
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
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "zhiyuanzhe_shenqing_uuid_number")

    private String zhiyuanzheShenqingUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 理由
     */
    @ColumnInfo(comment="理由",type="longtext")
    @TableField(value = "zhiyuanzhe_shenqing_text")

    private String zhiyuanzheShenqingText;


    /**
     * 志愿者申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="志愿者申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "zhiyuanzhe_shenqing_yesno_types")

    private Integer zhiyuanzheShenqingYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "zhiyuanzhe_shenqing_yesno_text")

    private String zhiyuanzheShenqingYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "zhiyuanzhe_shenqing_shenhe_time")

    private Date zhiyuanzheShenqingShenheTime;


    /**
     * 创建时间  listShow
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
	 * 获取：报名编号
	 */
    public String getZhiyuanzheShenqingUuidNumber() {
        return zhiyuanzheShenqingUuidNumber;
    }
    /**
	 * 设置：报名编号
	 */

    public void setZhiyuanzheShenqingUuidNumber(String zhiyuanzheShenqingUuidNumber) {
        this.zhiyuanzheShenqingUuidNumber = zhiyuanzheShenqingUuidNumber;
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
	 * 获取：理由
	 */
    public String getZhiyuanzheShenqingText() {
        return zhiyuanzheShenqingText;
    }
    /**
	 * 设置：理由
	 */

    public void setZhiyuanzheShenqingText(String zhiyuanzheShenqingText) {
        this.zhiyuanzheShenqingText = zhiyuanzheShenqingText;
    }
    /**
	 * 获取：志愿者申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：志愿者申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getZhiyuanzheShenqingYesnoTypes() {
        return zhiyuanzheShenqingYesnoTypes;
    }
    /**
	 * 设置：报名状态
	 */

    public void setZhiyuanzheShenqingYesnoTypes(Integer zhiyuanzheShenqingYesnoTypes) {
        this.zhiyuanzheShenqingYesnoTypes = zhiyuanzheShenqingYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getZhiyuanzheShenqingYesnoText() {
        return zhiyuanzheShenqingYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setZhiyuanzheShenqingYesnoText(String zhiyuanzheShenqingYesnoText) {
        this.zhiyuanzheShenqingYesnoText = zhiyuanzheShenqingYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getZhiyuanzheShenqingShenheTime() {
        return zhiyuanzheShenqingShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setZhiyuanzheShenqingShenheTime(Date zhiyuanzheShenqingShenheTime) {
        this.zhiyuanzheShenqingShenheTime = zhiyuanzheShenqingShenheTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ZhiyuanzheShenqing{" +
            ", id=" + id +
            ", zhiyuanzheShenqingUuidNumber=" + zhiyuanzheShenqingUuidNumber +
            ", yonghuId=" + yonghuId +
            ", zhiyuanzheShenqingText=" + zhiyuanzheShenqingText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", zhiyuanzheShenqingYesnoTypes=" + zhiyuanzheShenqingYesnoTypes +
            ", zhiyuanzheShenqingYesnoText=" + zhiyuanzheShenqingYesnoText +
            ", zhiyuanzheShenqingShenheTime=" + DateUtil.convertString(zhiyuanzheShenqingShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
