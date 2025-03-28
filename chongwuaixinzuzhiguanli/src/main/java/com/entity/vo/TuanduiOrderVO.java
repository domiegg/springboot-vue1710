package com.entity.vo;

import com.entity.TuanduiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 团队活动报名
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tuandui_order")
public class TuanduiOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "tuandui_order_uuid_number")
    private String tuanduiOrderUuidNumber;


    /**
     * 团队活动
     */

    @TableField(value = "tuandui_id")
    private Integer tuanduiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 订单类型
     */

    @TableField(value = "tuandui_order_types")
    private Integer tuanduiOrderTypes;


    /**
     * 订单创建时间
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
	 * 设置：订单编号
	 */
    public String getTuanduiOrderUuidNumber() {
        return tuanduiOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setTuanduiOrderUuidNumber(String tuanduiOrderUuidNumber) {
        this.tuanduiOrderUuidNumber = tuanduiOrderUuidNumber;
    }
    /**
	 * 设置：团队活动
	 */
    public Integer getTuanduiId() {
        return tuanduiId;
    }


    /**
	 * 获取：团队活动
	 */

    public void setTuanduiId(Integer tuanduiId) {
        this.tuanduiId = tuanduiId;
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
	 * 设置：订单类型
	 */
    public Integer getTuanduiOrderTypes() {
        return tuanduiOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setTuanduiOrderTypes(Integer tuanduiOrderTypes) {
        this.tuanduiOrderTypes = tuanduiOrderTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
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
