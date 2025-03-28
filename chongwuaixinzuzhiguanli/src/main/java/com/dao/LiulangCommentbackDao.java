package com.dao;

import com.entity.LiulangCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LiulangCommentbackView;

/**
 * 流浪评价 Dao 接口
 *
 * @author 
 */
public interface LiulangCommentbackDao extends BaseMapper<LiulangCommentbackEntity> {

   List<LiulangCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
