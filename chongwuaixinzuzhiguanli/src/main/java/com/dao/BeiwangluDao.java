package com.dao;

import com.entity.BeiwangluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.BeiwangluView;

/**
 * 备忘录 Dao 接口
 *
 * @author 
 */
public interface BeiwangluDao extends BaseMapper<BeiwangluEntity> {

   List<BeiwangluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
