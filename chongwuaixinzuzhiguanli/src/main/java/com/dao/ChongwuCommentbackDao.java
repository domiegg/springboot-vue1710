package com.dao;

import com.entity.ChongwuCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ChongwuCommentbackView;

/**
 * 宠物评价 Dao 接口
 *
 * @author 
 */
public interface ChongwuCommentbackDao extends BaseMapper<ChongwuCommentbackEntity> {

   List<ChongwuCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
