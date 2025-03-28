package com.dao;

import com.entity.LiulangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LiulangView;

/**
 * 流浪 Dao 接口
 *
 * @author 
 */
public interface LiulangDao extends BaseMapper<LiulangEntity> {

   List<LiulangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
