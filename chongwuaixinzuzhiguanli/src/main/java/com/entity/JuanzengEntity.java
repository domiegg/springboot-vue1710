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
 * 捐赠
 *
 * @author 
 * @email
 */
@TableName("juanzeng")
public class JuanzengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JuanzengEntity() {

	}

	public JuanzengEntity(T t) {
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
    @TableField(value = "juanzeng_uuid_number")

    private String juanzengUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 捐赠
     */
    @ColumnInfo(comment="捐赠",type="longtext")
    @TableField(value = "juanzeng_text")

    private String juanzengText;


    /**
     * 金额
     */
    @ColumnInfo(comment="金额",type="decimal(10,2)")
    @TableField(value = "new_money")

    private Double newMoney;


    /**
     * 捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="捐赠时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
    public String getJuanzengUuidNumber() {
        return juanzengUuidNumber;
    }
    /**
	 * 设置：报名编号
	 */

    public void setJuanzengUuidNumber(String juanzengUuidNumber) {
        this.juanzengUuidNumber = juanzengUuidNumber;
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
	 * 获取：捐赠
	 */
    public String getJuanzengText() {
        return juanzengText;
    }
    /**
	 * 设置：捐赠
	 */

    public void setJuanzengText(String juanzengText) {
        this.juanzengText = juanzengText;
    }
    /**
	 * 获取：金额
	 */
    public Double getNewMoney() {
        return newMoney;
    }
    /**
	 * 设置：金额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：捐赠时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：捐赠时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Juanzeng{" +
            ", id=" + id +
            ", juanzengUuidNumber=" + juanzengUuidNumber +
            ", yonghuId=" + yonghuId +
            ", juanzengText=" + juanzengText +
            ", newMoney=" + newMoney +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
