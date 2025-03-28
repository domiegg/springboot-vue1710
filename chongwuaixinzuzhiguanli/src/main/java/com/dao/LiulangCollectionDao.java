package com.dao;

import com.entity.LiulangCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LiulangCollectionView;

/**
 * 流浪收藏 Dao 接口
 *
 * @author 
 */
public interface LiulangCollectionDao extends BaseMapper<LiulangCollectionEntity> {

   List<LiulangCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
