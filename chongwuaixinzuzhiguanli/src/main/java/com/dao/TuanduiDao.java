package com.dao;

import com.entity.TuanduiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TuanduiView;

/**
 * 团队活动 Dao 接口
 *
 * @author 
 */
public interface TuanduiDao extends BaseMapper<TuanduiEntity> {

   List<TuanduiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
