package com.entity.model;

import com.entity.LiulangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 流浪
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LiulangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 流浪名称
     */
    private String liulangName;


    /**
     * 流浪编号
     */
    private String liulangUuidNumber;


    /**
     * 流浪照片
     */
    private String liulangPhoto;


    /**
     * 流浪类型
     */
    private Integer liulangTypes;


    /**
     * 流浪介绍
     */
    private String liulangContent;


    /**
     * 逻辑删除
     */
    private Integer liulangDelete;


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
