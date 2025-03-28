package com.service.impl;

import com.utils.StringUtil;
import com.service.DictionaryService;
import com.utils.ClazzDiff;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import com.dao.LiulangCollectionDao;
import com.entity.LiulangCollectionEntity;
import com.service.LiulangCollectionService;
import com.entity.view.LiulangCollectionView;

/**
 * 流浪收藏 服务实现类
 */
@Service("liulangCollectionService")
@Transactional
public class LiulangCollectionServiceImpl extends ServiceImpl<LiulangCollectionDao, LiulangCollectionEntity> implements LiulangCollectionService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<LiulangCollectionView> page =new Query<LiulangCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
