package com.entity.model;

import com.entity.JuanzengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 捐赠
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JuanzengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String juanzengUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 捐赠
     */
    private String juanzengText;


    /**
     * 金额
     */
    private Double newMoney;


    /**
     * 捐赠时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
