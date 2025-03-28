package com.entity.model;

import com.entity.ZhiyuanzheShenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 志愿者申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZhiyuanzheShenqingModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String zhiyuanzheShenqingUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 理由
     */
    private String zhiyuanzheShenqingText;


    /**
     * 志愿者申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 报名状态
     */
    private Integer zhiyuanzheShenqingYesnoTypes;


    /**
     * 审核回复
     */
    private String zhiyuanzheShenqingYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhiyuanzheShenqingShenheTime;


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
