
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
 * 团队活动
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tuandui")
public class TuanduiController {
    private static final Logger logger = LoggerFactory.getLogger(TuanduiController.class);

    private static final String TABLE_NAME = "tuandui";

    @Autowired
    private TuanduiService tuanduiService;


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
    private TuanduiCollectionService tuanduiCollectionService;//团队活动收藏
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
        params.put("tuanduiDeleteStart",1);params.put("tuanduiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = tuanduiService.queryPage(params);

        //字典表数据转换
        List<TuanduiView> list =(List<TuanduiView>)page.getList();
        for(TuanduiView c:list){
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
        TuanduiEntity tuandui = tuanduiService.selectById(id);
        if(tuandui !=null){
            //entity转view
            TuanduiView view = new TuanduiView();
            BeanUtils.copyProperties( tuandui , view );//把实体数据重构到view中
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
    public R save(@RequestBody TuanduiEntity tuandui, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .eq("tuandui_name", tuandui.getTuanduiName())
            .eq("tuandui_types", tuandui.getTuanduiTypes())
            .eq("tuandui_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if(tuanduiEntity==null){
            tuandui.setTuanduiDelete(1);
            tuandui.setInsertTime(new Date());
            tuandui.setCreateTime(new Date());
            tuanduiService.insert(tuandui);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TuanduiEntity tuandui, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());
        TuanduiEntity oldTuanduiEntity = tuanduiService.selectById(tuandui.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(tuandui.getTuanduiPhoto()) || "null".equals(tuandui.getTuanduiPhoto())){
                tuandui.setTuanduiPhoto(null);
        }
        if("".equals(tuandui.getTuanduiContent()) || "null".equals(tuandui.getTuanduiContent())){
                tuandui.setTuanduiContent(null);
        }

            tuanduiService.updateById(tuandui);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TuanduiEntity> oldTuanduiList =tuanduiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<TuanduiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TuanduiEntity tuanduiEntity = new TuanduiEntity();
            tuanduiEntity.setId(id);
            tuanduiEntity.setTuanduiDelete(2);
            list.add(tuanduiEntity);
        }
        if(list != null && list.size() >0){
            tuanduiService.updateBatchById(list);
        }

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
            List<TuanduiEntity> tuanduiList = new ArrayList<>();//上传的东西
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
                            TuanduiEntity tuanduiEntity = new TuanduiEntity();
//                            tuanduiEntity.setTuanduiName(data.get(0));                    //团队活动名称 要改的
//                            tuanduiEntity.setTuanduiUuidNumber(data.get(0));                    //团队活动编号 要改的
//                            tuanduiEntity.setTuanduiPhoto("");//详情和图片
//                            tuanduiEntity.setHuodongTime(sdf.parse(data.get(0)));          //活动时间 要改的
//                            tuanduiEntity.setTuanduiTypes(Integer.valueOf(data.get(0)));   //团队活动类型 要改的
//                            tuanduiEntity.setTuanduiContent("");//详情和图片
//                            tuanduiEntity.setTuanduiDelete(1);//逻辑删除字段
//                            tuanduiEntity.setInsertTime(date);//时间
//                            tuanduiEntity.setCreateTime(date);//时间
                            tuanduiList.add(tuanduiEntity);


                            //把要查询是否重复的字段放入map中
                                //团队活动编号
                                if(seachFields.containsKey("tuanduiUuidNumber")){
                                    List<String> tuanduiUuidNumber = seachFields.get("tuanduiUuidNumber");
                                    tuanduiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tuanduiUuidNumber = new ArrayList<>();
                                    tuanduiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tuanduiUuidNumber",tuanduiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //团队活动编号
                        List<TuanduiEntity> tuanduiEntities_tuanduiUuidNumber = tuanduiService.selectList(new EntityWrapper<TuanduiEntity>().in("tuandui_uuid_number", seachFields.get("tuanduiUuidNumber")).eq("tuandui_delete", 1));
                        if(tuanduiEntities_tuanduiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TuanduiEntity s:tuanduiEntities_tuanduiUuidNumber){
                                repeatFields.add(s.getTuanduiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [团队活动编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tuanduiService.insertBatch(tuanduiList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<TuanduiView> returnTuanduiViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("tuanduiYesnoTypes",2);
        PageUtils pageUtils = tuanduiOrderService.queryPage(params1);
        List<TuanduiOrderView> orderViewsList =(List<TuanduiOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(TuanduiOrderView orderView:orderViewsList){
            Integer tuanduiTypes = orderView.getTuanduiTypes();
            if(typeMap.containsKey(tuanduiTypes)){
                typeMap.put(tuanduiTypes,typeMap.get(tuanduiTypes)+1);
            }else{
                typeMap.put(tuanduiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("tuanduiTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("tuanduiYesnoTypes",2);
            PageUtils pageUtils1 = tuanduiService.queryPage(params2);
            List<TuanduiView> tuanduiViewList =(List<TuanduiView>)pageUtils1.getList();
            returnTuanduiViewList.addAll(tuanduiViewList);
            if(returnTuanduiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("tuanduiYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = tuanduiService.queryPage(params);
        if(returnTuanduiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnTuanduiViewList.size();//要添加的数量
            List<TuanduiView> tuanduiViewList =(List<TuanduiView>)page.getList();
            for(TuanduiView tuanduiView:tuanduiViewList){
                Boolean addFlag = true;
                for(TuanduiView returnTuanduiView:returnTuanduiViewList){
                    if(returnTuanduiView.getId().intValue() ==tuanduiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnTuanduiViewList.add(tuanduiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnTuanduiViewList = returnTuanduiViewList.subList(0, limit);
        }

        for(TuanduiView c:returnTuanduiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnTuanduiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = tuanduiService.queryPage(params);

        //字典表数据转换
        List<TuanduiView> list =(List<TuanduiView>)page.getList();
        for(TuanduiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiEntity tuandui = tuanduiService.selectById(id);
            if(tuandui !=null){


                //entity转view
                TuanduiView view = new TuanduiView();
                BeanUtils.copyProperties( tuandui , view );//把实体数据重构到view中

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
    public R add(@RequestBody TuanduiEntity tuandui, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tuandui:{}",this.getClass().getName(),tuandui.toString());
        Wrapper<TuanduiEntity> queryWrapper = new EntityWrapper<TuanduiEntity>()
            .eq("tuandui_name", tuandui.getTuanduiName())
            .eq("tuandui_uuid_number", tuandui.getTuanduiUuidNumber())
            .eq("tuandui_types", tuandui.getTuanduiTypes())
            .eq("tuandui_delete", tuandui.getTuanduiDelete())
//            .notIn("tuandui_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TuanduiEntity tuanduiEntity = tuanduiService.selectOne(queryWrapper);
        if(tuanduiEntity==null){
            tuandui.setTuanduiDelete(1);
            tuandui.setInsertTime(new Date());
            tuandui.setCreateTime(new Date());
        tuanduiService.insert(tuandui);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

