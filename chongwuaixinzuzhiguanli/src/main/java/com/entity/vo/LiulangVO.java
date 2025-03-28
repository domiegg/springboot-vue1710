package com.entity.vo;

import com.entity.LiulangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 流浪
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("liulang")
public class LiulangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 流浪名称
     */

    @TableField(value = "liulang_name")
    private String liulangName;


    /**
     * 流浪编号
     */

    @TableField(value = "liulang_uuid_number")
    private String liulangUuidNumber;


    /**
     * 流浪照片
     */

    @TableField(value = "liulang_photo")
    private String liulangPhoto;


    /**
     * 流浪类型
     */

    @TableField(value = "liulang_types")
    private Integer liulangTypes;


    /**
     * 流浪介绍
     */

    @TableField(value = "liulang_content")
    private String liulangContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "liulang_delete")
    private Integer liulangDelete;


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
	 * 设置：流浪名称
	 */
    public String getLiulangName() {
        return liulangName;
    }


    /**
	 * 获取：流浪名称
	 */

    public void setLiulangName(String liulangName) {
        this.liulangName = liulangName;
    }
    /**
	 * 设置：流浪编号
	 */
    public String getLiulangUuidNumber() {
        return liulangUuidNumber;
    }


    /**
	 * 获取：流浪编号
	 */

    public void setLiulangUuidNumber(String liulangUuidNumber) {
        this.liulangUuidNumber = liulangUuidNumber;
    }
    /**
	 * 设置：流浪照片
	 */
    public String getLiulangPhoto() {
        return liulangPhoto;
    }


    /**
	 * 获取：流浪照片
	 */

    public void setLiulangPhoto(String liulangPhoto) {
        this.liulangPhoto = liulangPhoto;
    }
    /**
	 * 设置：流浪类型
	 */
    public Integer getLiulangTypes() {
        return liulangTypes;
    }


    /**
	 * 获取：流浪类型
	 */

    public void setLiulangTypes(Integer liulangTypes) {
        this.liulangTypes = liulangTypes;
    }
    /**
	 * 设置：流浪介绍
	 */
    public String getLiulangContent() {
        return liulangContent;
    }


    /**
	 * 获取：流浪介绍
	 */

    public void setLiulangContent(String liulangContent) {
        this.liulangContent = liulangContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getLiulangDelete() {
        return liulangDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setLiulangDelete(Integer liulangDelete) {
        this.liulangDelete = liulangDelete;
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
