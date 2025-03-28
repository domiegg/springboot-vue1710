
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 团队活动收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tuanduiCollection")
public class TuanduiCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(TuanduiCollectionController.class);

    private static final String TABLE_NAME = "tuanduiCollection";

    @Autowired
    private TuanduiCollectionService tuanduiCollectionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BeiwangluService beiwangluService;//备忘录
    @Autowired
    private ChongwuService chongwuService;//宠物
    @Autowired
    private ChongwuCollectionService chongwuCollectionService;//宠物收藏
    @Autowired
    private ChongwuCommentbackService chongwuCommentbackService;//宠物评价
    @Autowired
    private ChongwuOrderService chongwuOrderService;//宠物订单
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JuanzengService juanzengService;//捐赠
    @Autowired
    private LiulangService liulangService;//流浪
    @Autowired
    private LiulangCollectionService liulangCollectionService;//流浪收藏
    @Autowired
    private LiulangCommentbackService liulangCommentbackService;//流浪评价
    @Autowired
    private LiulangOrderService liulangOrderService;//流浪订单
    @Autowired
    private SingleSeachService singleSeachService;//单页数据
    @Autowired
    private TuanduiService tuanduiService;//团队活动
    @Autowired
    private TuanduiCommentbackService tuanduiCommentbackService;//团队活动评价
    @Autowired
    private TuanduiOrderService tuanduiOrderService;//团队活动报名
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhiyuanzheShenqingService zhiyuanzheShenqingService;//志愿者申请
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = tuanduiCollectionService.queryPage(params);

        //字典表数据转换
        List<TuanduiCollectionView> list =(List<TuanduiCollectionView>)page.getList();
        for(TuanduiCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiCollectionEntity tuanduiCollection = tuanduiCollectionService.selectById(id);
        if(tuanduiCollection !=null){
            //entity转view
            TuanduiCollectionView view = new TuanduiCollectionView();
            BeanUtils.copyProperties( tuanduiCollection , view );//把实体数据重构到view中
            //级联表 团队活动
            //级联表
            TuanduiEntity tuandui = tuanduiService.selectById(tuanduiCollection.getTuanduiId());
            if(tuandui != null){
            BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setTuanduiId(tuandui.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(tuanduiCollection.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TuanduiCollectionEntity tuanduiCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tuanduiCollection:{}",this.getClass().getName(),tuanduiCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            tuanduiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<TuanduiCollectionEntity> queryWrapper = new EntityWrapper<TuanduiCollectionEntity>()
            .eq("tuandui_id", tuanduiCollection.getTuanduiId())
            .eq("yonghu_id", tuanduiCollection.getYonghuId())
            .eq("tuandui_collection_types", tuanduiCollection.getTuanduiCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiCollectionEntity tuanduiCollectionEntity = tuanduiCollectionService.selectOne(queryWrapper);
        if(tuanduiCollectionEntity==null){
            tuanduiCollection.setInsertTime(new Date());
            tuanduiCollection.setCreateTime(new Date());
            tuanduiCollectionService.insert(tuanduiCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TuanduiCollectionEntity tuanduiCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tuanduiCollection:{}",this.getClass().getName(),tuanduiCollection.toString());
        TuanduiCollectionEntity oldTuanduiCollectionEntity = tuanduiCollectionService.selectById(tuanduiCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            tuanduiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            tuanduiCollectionService.updateById(tuanduiCollection);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TuanduiCollectionEntity> oldTuanduiCollectionList =tuanduiCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        tuanduiCollectionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<TuanduiCollectionEntity> tuanduiCollectionList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            TuanduiCollectionEntity tuanduiCollectionEntity = new TuanduiCollectionEntity();
//                            tuanduiCollectionEntity.setTuanduiId(Integer.valueOf(data.get(0)));   //团队活动 要改的
//                            tuanduiCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            tuanduiCollectionEntity.setTuanduiCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            tuanduiCollectionEntity.setInsertTime(date);//时间
//                            tuanduiCollectionEntity.setCreateTime(date);//时间
                            tuanduiCollectionList.add(tuanduiCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        tuanduiCollectionService.insertBatch(tuanduiCollectionList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = tuanduiCollectionService.queryPage(params);

        //字典表数据转换
        List<TuanduiCollectionView> list =(List<TuanduiCollectionView>)page.getList();
        for(TuanduiCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiCollectionEntity tuanduiCollection = tuanduiCollectionService.selectById(id);
            if(tuanduiCollection !=null){


                //entity转view
                TuanduiCollectionView view = new TuanduiCollectionView();
                BeanUtils.copyProperties( tuanduiCollection , view );//把实体数据重构到view中

                //级联表
                    TuanduiEntity tuandui = tuanduiService.selectById(tuanduiCollection.getTuanduiId());
                if(tuandui != null){
                    BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTuanduiId(tuandui.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(tuanduiCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody TuanduiCollectionEntity tuanduiCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tuanduiCollection:{}",this.getClass().getName(),tuanduiCollection.toString());
        Wrapper<TuanduiCollectionEntity> queryWrapper = new EntityWrapper<TuanduiCollectionEntity>()
            .eq("tuandui_id", tuanduiCollection.getTuanduiId())
            .eq("yonghu_id", tuanduiCollection.getYonghuId())
            .eq("tuandui_collection_types", tuanduiCollection.getTuanduiCollectionTypes())
//            .notIn("tuandui_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiCollectionEntity tuanduiCollectionEntity = tuanduiCollectionService.selectOne(queryWrapper);
        if(tuanduiCollectionEntity==null){
            tuanduiCollection.setInsertTime(new Date());
            tuanduiCollection.setCreateTime(new Date());
        tuanduiCollectionService.insert(tuanduiCollection);

            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

