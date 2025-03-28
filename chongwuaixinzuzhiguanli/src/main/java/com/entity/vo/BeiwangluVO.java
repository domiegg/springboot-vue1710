package com.entity.vo;

import com.entity.BeiwangluEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 备忘录
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("beiwanglu")
public class BeiwangluVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 备忘录名称
     */

    @TableField(value = "beiwanglu_name")
    private String beiwangluName;


    /**
     * 备忘录编号
     */

    @TableField(value = "beiwanglu_uuid_number")
    private String beiwangluUuidNumber;


    /**
     * 备忘录内容
     */

    @TableField(value = "beiwanglu_content")
    private String beiwangluContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "beiwanglu_delete")
    private Integer beiwangluDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：备忘录名称
	 */
    public String getBeiwangluName() {
        return beiwangluName;
    }


    /**
	 * 获取：备忘录名称
	 */

    public void setBeiwangluName(String beiwangluName) {
        this.beiwangluName = beiwangluName;
    }
    /**
	 * 设置：备忘录编号
	 */
    public String getBeiwangluUuidNumber() {
        return beiwangluUuidNumber;
    }


    /**
	 * 获取：备忘录编号
	 */

    public void setBeiwangluUuidNumber(String beiwangluUuidNumber) {
        this.beiwangluUuidNumber = beiwangluUuidNumber;
    }
    /**
	 * 设置：备忘录内容
	 */
    public String getBeiwangluContent() {
        return beiwangluContent;
    }


    /**
	 * 获取：备忘录内容
	 */

    public void setBeiwangluContent(String beiwangluContent) {
        this.beiwangluContent = beiwangluContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getBeiwangluDelete() {
        return beiwangluDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setBeiwangluDelete(Integer beiwangluDelete) {
        this.beiwangluDelete = beiwangluDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
