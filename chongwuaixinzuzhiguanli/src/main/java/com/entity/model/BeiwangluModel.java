package com.entity.model;

import com.entity.BeiwangluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 备忘录
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BeiwangluModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 备忘录名称
     */
    private String beiwangluName;


    /**
     * 备忘录编号
     */
    private String beiwangluUuidNumber;


    /**
     * 备忘录内容
     */
    private String beiwangluContent;


    /**
     * 逻辑删除
     */
    private Integer beiwangluDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
