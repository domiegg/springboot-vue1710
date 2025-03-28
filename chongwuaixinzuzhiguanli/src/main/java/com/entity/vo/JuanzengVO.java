package com.entity.vo;

import com.entity.JuanzengEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 捐赠
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("juanzeng")
public class JuanzengVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "juanzeng_uuid_number")
    private String juanzengUuidNumber;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 捐赠
     */

    @TableField(value = "juanzeng_text")
    private String juanzengText;


    /**
     * 金额
     */

    @TableField(value = "new_money")
    private Double newMoney;


    /**
     * 捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：报名编号
	 */
    public String getJuanzengUuidNumber() {
        return juanzengUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setJuanzengUuidNumber(String juanzengUuidNumber) {
        this.juanzengUuidNumber = juanzengUuidNumber;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：捐赠
	 */
    public String getJuanzengText() {
        return juanzengText;
    }


    /**
	 * 获取：捐赠
	 */

    public void setJuanzengText(String juanzengText) {
        this.juanzengText = juanzengText;
    }
    /**
	 * 设置：金额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：金额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 设置：捐赠时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：捐赠时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
