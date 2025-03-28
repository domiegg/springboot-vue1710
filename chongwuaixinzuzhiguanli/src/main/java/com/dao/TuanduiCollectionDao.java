package com.dao;

import com.entity.TuanduiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TuanduiCollectionView;

/**
 * 团队活动收藏 Dao 接口
 *
 * @author 
 */
public interface TuanduiCollectionDao extends BaseMapper<TuanduiCollectionEntity> {

   List<TuanduiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
