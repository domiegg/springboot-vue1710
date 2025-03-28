package com.dao;

import com.entity.TuanduiCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TuanduiCommentbackView;

/**
 * 团队活动评价 Dao 接口
 *
 * @author 
 */
public interface TuanduiCommentbackDao extends BaseMapper<TuanduiCommentbackEntity> {

   List<TuanduiCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
