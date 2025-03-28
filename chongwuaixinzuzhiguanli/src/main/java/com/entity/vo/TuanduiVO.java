package com.entity.vo;

import com.entity.TuanduiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 团队活动
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tuandui")
public class TuanduiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 团队活动名称
     */

    @TableField(value = "tuandui_name")
    private String tuanduiName;


    /**
     * 团队活动编号
     */

    @TableField(value = "tuandui_uuid_number")
    private String tuanduiUuidNumber;


    /**
     * 团队活动照片
     */

    @TableField(value = "tuandui_photo")
    private String tuanduiPhoto;


    /**
     * 活动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "huodong_time")
    private Date huodongTime;


    /**
     * 团队活动类型
     */

    @TableField(value = "tuandui_types")
    private Integer tuanduiTypes;


    /**
     * 团队活动介绍
     */

    @TableField(value = "tuandui_content")
    private String tuanduiContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "tuandui_delete")
    private Integer tuanduiDelete;


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
	 * 设置：团队活动名称
	 */
    public String getTuanduiName() {
        return tuanduiName;
    }


    /**
	 * 获取：团队活动名称
	 */

    public void setTuanduiName(String tuanduiName) {
        this.tuanduiName = tuanduiName;
    }
    /**
	 * 设置：团队活动编号
	 */
    public String getTuanduiUuidNumber() {
        return tuanduiUuidNumber;
    }


    /**
	 * 获取：团队活动编号
	 */

    public void setTuanduiUuidNumber(String tuanduiUuidNumber) {
        this.tuanduiUuidNumber = tuanduiUuidNumber;
    }
    /**
	 * 设置：团队活动照片
	 */
    public String getTuanduiPhoto() {
        return tuanduiPhoto;
    }


    /**
	 * 获取：团队活动照片
	 */

    public void setTuanduiPhoto(String tuanduiPhoto) {
        this.tuanduiPhoto = tuanduiPhoto;
    }
    /**
	 * 设置：活动时间
	 */
    public Date getHuodongTime() {
        return huodongTime;
    }


    /**
	 * 获取：活动时间
	 */

    public void setHuodongTime(Date huodongTime) {
        this.huodongTime = huodongTime;
    }
    /**
	 * 设置：团队活动类型
	 */
    public Integer getTuanduiTypes() {
        return tuanduiTypes;
    }


    /**
	 * 获取：团队活动类型
	 */

    public void setTuanduiTypes(Integer tuanduiTypes) {
        this.tuanduiTypes = tuanduiTypes;
    }
    /**
	 * 设置：团队活动介绍
	 */
    public String getTuanduiContent() {
        return tuanduiContent;
    }


    /**
	 * 获取：团队活动介绍
	 */

    public void setTuanduiContent(String tuanduiContent) {
        this.tuanduiContent = tuanduiContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getTuanduiDelete() {
        return tuanduiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setTuanduiDelete(Integer tuanduiDelete) {
        this.tuanduiDelete = tuanduiDelete;
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
