
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
 * 流浪
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/liulang")
public class LiulangController {
    private static final Logger logger = LoggerFactory.getLogger(LiulangController.class);

    private static final String TABLE_NAME = "liulang";

    @Autowired
    private LiulangService liulangService;


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
        params.put("liulangDeleteStart",1);params.put("liulangDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = liulangService.queryPage(params);

        //字典表数据转换
        List<LiulangView> list =(List<LiulangView>)page.getList();
        for(LiulangView c:list){
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
        LiulangEntity liulang = liulangService.selectById(id);
        if(liulang !=null){
            //entity转view
            LiulangView view = new LiulangView();
            BeanUtils.copyProperties( liulang , view );//把实体数据重构到view中
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
    public R save(@RequestBody LiulangEntity liulang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,liulang:{}",this.getClass().getName(),liulang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<LiulangEntity> queryWrapper = new EntityWrapper<LiulangEntity>()
            .eq("liulang_name", liulang.getLiulangName())
            .eq("liulang_types", liulang.getLiulangTypes())
            .eq("liulang_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LiulangEntity liulangEntity = liulangService.selectOne(queryWrapper);
        if(liulangEntity==null){
            liulang.setLiulangDelete(1);
            liulang.setInsertTime(new Date());
            liulang.setCreateTime(new Date());
            liulangService.insert(liulang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LiulangEntity liulang, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,liulang:{}",this.getClass().getName(),liulang.toString());
        LiulangEntity oldLiulangEntity = liulangService.selectById(liulang.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(liulang.getLiulangPhoto()) || "null".equals(liulang.getLiulangPhoto())){
                liulang.setLiulangPhoto(null);
        }
        if("".equals(liulang.getLiulangContent()) || "null".equals(liulang.getLiulangContent())){
                liulang.setLiulangContent(null);
        }

            liulangService.updateById(liulang);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LiulangEntity> oldLiulangList =liulangService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LiulangEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LiulangEntity liulangEntity = new LiulangEntity();
            liulangEntity.setId(id);
            liulangEntity.setLiulangDelete(2);
            list.add(liulangEntity);
        }
        if(list != null && list.size() >0){
            liulangService.updateBatchById(list);
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
            List<LiulangEntity> liulangList = new ArrayList<>();//上传的东西
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
                            LiulangEntity liulangEntity = new LiulangEntity();
//                            liulangEntity.setLiulangName(data.get(0));                    //流浪名称 要改的
//                            liulangEntity.setLiulangUuidNumber(data.get(0));                    //流浪编号 要改的
//                            liulangEntity.setLiulangPhoto("");//详情和图片
//                            liulangEntity.setLiulangTypes(Integer.valueOf(data.get(0)));   //流浪类型 要改的
//                            liulangEntity.setLiulangContent("");//详情和图片
//                            liulangEntity.setLiulangDelete(1);//逻辑删除字段
//                            liulangEntity.setInsertTime(date);//时间
//                            liulangEntity.setCreateTime(date);//时间
                            liulangList.add(liulangEntity);


                            //把要查询是否重复的字段放入map中
                                //流浪编号
                                if(seachFields.containsKey("liulangUuidNumber")){
                                    List<String> liulangUuidNumber = seachFields.get("liulangUuidNumber");
                                    liulangUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> liulangUuidNumber = new ArrayList<>();
                                    liulangUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("liulangUuidNumber",liulangUuidNumber);
                                }
                        }

                        //查询是否重复
                         //流浪编号
                        List<LiulangEntity> liulangEntities_liulangUuidNumber = liulangService.selectList(new EntityWrapper<LiulangEntity>().in("liulang_uuid_number", seachFields.get("liulangUuidNumber")).eq("liulang_delete", 1));
                        if(liulangEntities_liulangUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LiulangEntity s:liulangEntities_liulangUuidNumber){
                                repeatFields.add(s.getLiulangUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [流浪编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        liulangService.insertBatch(liulangList);
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
        List<LiulangView> returnLiulangViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("liulangYesnoTypes",2);
        PageUtils pageUtils = liulangOrderService.queryPage(params1);
        List<LiulangOrderView> orderViewsList =(List<LiulangOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(LiulangOrderView orderView:orderViewsList){
            Integer liulangTypes = orderView.getLiulangTypes();
            if(typeMap.containsKey(liulangTypes)){
                typeMap.put(liulangTypes,typeMap.get(liulangTypes)+1);
            }else{
                typeMap.put(liulangTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("liulangTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("liulangYesnoTypes",2);
            PageUtils pageUtils1 = liulangService.queryPage(params2);
            List<LiulangView> liulangViewList =(List<LiulangView>)pageUtils1.getList();
            returnLiulangViewList.addAll(liulangViewList);
            if(returnLiulangViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("liulangYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = liulangService.queryPage(params);
        if(returnLiulangViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnLiulangViewList.size();//要添加的数量
            List<LiulangView> liulangViewList =(List<LiulangView>)page.getList();
            for(LiulangView liulangView:liulangViewList){
                Boolean addFlag = true;
                for(LiulangView returnLiulangView:returnLiulangViewList){
                    if(returnLiulangView.getId().intValue() ==liulangView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnLiulangViewList.add(liulangView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnLiulangViewList = returnLiulangViewList.subList(0, limit);
        }

        for(LiulangView c:returnLiulangViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnLiulangViewList);
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
        PageUtils page = liulangService.queryPage(params);

        //字典表数据转换
        List<LiulangView> list =(List<LiulangView>)page.getList();
        for(LiulangView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LiulangEntity liulang = liulangService.selectById(id);
            if(liulang !=null){


                //entity转view
                LiulangView view = new LiulangView();
                BeanUtils.copyProperties( liulang , view );//把实体数据重构到view中

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
    public R add(@RequestBody LiulangEntity liulang, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,liulang:{}",this.getClass().getName(),liulang.toString());
        Wrapper<LiulangEntity> queryWrapper = new EntityWrapper<LiulangEntity>()
            .eq("liulang_name", liulang.getLiulangName())
            .eq("liulang_uuid_number", liulang.getLiulangUuidNumber())
            .eq("liulang_types", liulang.getLiulangTypes())
            .eq("liulang_delete", liulang.getLiulangDelete())
//            .notIn("liulang_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LiulangEntity liulangEntity = liulangService.selectOne(queryWrapper);
        if(liulangEntity==null){
            liulang.setLiulangDelete(1);
            liulang.setInsertTime(new Date());
            liulang.setCreateTime(new Date());
        liulangService.insert(liulang);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

