package com.entity.vo;

import com.entity.ZhiyuanzheShenqingEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 志愿者申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zhiyuanzhe_shenqing")
public class ZhiyuanzheShenqingVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "zhiyuanzhe_shenqing_uuid_number")
    private String zhiyuanzheShenqingUuidNumber;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 理由
     */

    @TableField(value = "zhiyuanzhe_shenqing_text")
    private String zhiyuanzheShenqingText;


    /**
     * 志愿者申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 报名状态
     */

    @TableField(value = "zhiyuanzhe_shenqing_yesno_types")
    private Integer zhiyuanzheShenqingYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "zhiyuanzhe_shenqing_yesno_text")
    private String zhiyuanzheShenqingYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "zhiyuanzhe_shenqing_shenhe_time")
    private Date zhiyuanzheShenqingShenheTime;


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
    public String getZhiyuanzheShenqingUuidNumber() {
        return zhiyuanzheShenqingUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setZhiyuanzheShenqingUuidNumber(String zhiyuanzheShenqingUuidNumber) {
        this.zhiyuanzheShenqingUuidNumber = zhiyuanzheShenqingUuidNumber;
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
	 * 设置：理由
	 */
    public String getZhiyuanzheShenqingText() {
        return zhiyuanzheShenqingText;
    }


    /**
	 * 获取：理由
	 */

    public void setZhiyuanzheShenqingText(String zhiyuanzheShenqingText) {
        this.zhiyuanzheShenqingText = zhiyuanzheShenqingText;
    }
    /**
	 * 设置：志愿者申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：志愿者申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getZhiyuanzheShenqingYesnoTypes() {
        return zhiyuanzheShenqingYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setZhiyuanzheShenqingYesnoTypes(Integer zhiyuanzheShenqingYesnoTypes) {
        this.zhiyuanzheShenqingYesnoTypes = zhiyuanzheShenqingYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getZhiyuanzheShenqingYesnoText() {
        return zhiyuanzheShenqingYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setZhiyuanzheShenqingYesnoText(String zhiyuanzheShenqingYesnoText) {
        this.zhiyuanzheShenqingYesnoText = zhiyuanzheShenqingYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getZhiyuanzheShenqingShenheTime() {
        return zhiyuanzheShenqingShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setZhiyuanzheShenqingShenheTime(Date zhiyuanzheShenqingShenheTime) {
        this.zhiyuanzheShenqingShenheTime = zhiyuanzheShenqingShenheTime;
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
