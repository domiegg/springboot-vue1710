
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
 * 志愿者申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhiyuanzheShenqing")
public class ZhiyuanzheShenqingController {
    private static final Logger logger = LoggerFactory.getLogger(ZhiyuanzheShenqingController.class);

    private static final String TABLE_NAME = "zhiyuanzheShenqing";

    @Autowired
    private ZhiyuanzheShenqingService zhiyuanzheShenqingService;


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
    private TuanduiCollectionService tuanduiCollectionService;//团队活动收藏
    @Autowired
    private TuanduiCommentbackService tuanduiCommentbackService;//团队活动评价
    @Autowired
    private TuanduiOrderService tuanduiOrderService;//团队活动报名
    @Autowired
    private YonghuService yonghuService;//用户
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
        PageUtils page = zhiyuanzheShenqingService.queryPage(params);

        //字典表数据转换
        List<ZhiyuanzheShenqingView> list =(List<ZhiyuanzheShenqingView>)page.getList();
        for(ZhiyuanzheShenqingView c:list){
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
        ZhiyuanzheShenqingEntity zhiyuanzheShenqing = zhiyuanzheShenqingService.selectById(id);
        if(zhiyuanzheShenqing !=null){
            //entity转view
            ZhiyuanzheShenqingView view = new ZhiyuanzheShenqingView();
            BeanUtils.copyProperties( zhiyuanzheShenqing , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(zhiyuanzheShenqing.getYonghuId());
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
    public R save(@RequestBody ZhiyuanzheShenqingEntity zhiyuanzheShenqing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhiyuanzheShenqing:{}",this.getClass().getName(),zhiyuanzheShenqing.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zhiyuanzheShenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZhiyuanzheShenqingEntity> queryWrapper = new EntityWrapper<ZhiyuanzheShenqingEntity>()
            .eq("yonghu_id", zhiyuanzheShenqing.getYonghuId())
            .in("zhiyuanzhe_shenqing_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhiyuanzheShenqingEntity zhiyuanzheShenqingEntity = zhiyuanzheShenqingService.selectOne(queryWrapper);
        if(zhiyuanzheShenqingEntity==null){
            zhiyuanzheShenqing.setInsertTime(new Date());
            zhiyuanzheShenqing.setZhiyuanzheShenqingYesnoTypes(1);
            zhiyuanzheShenqing.setCreateTime(new Date());
            zhiyuanzheShenqingService.insert(zhiyuanzheShenqing);
            return R.ok();
        }else {
            if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes()==1) {

                return R.error(511,"有相同的待审核的数据");
            }



            else if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhiyuanzheShenqingEntity zhiyuanzheShenqing, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhiyuanzheShenqing:{}",this.getClass().getName(),zhiyuanzheShenqing.toString());
        ZhiyuanzheShenqingEntity oldZhiyuanzheShenqingEntity = zhiyuanzheShenqingService.selectById(zhiyuanzheShenqing.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zhiyuanzheShenqing.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(zhiyuanzheShenqing.getZhiyuanzheShenqingText()) || "null".equals(zhiyuanzheShenqing.getZhiyuanzheShenqingText())){
                zhiyuanzheShenqing.setZhiyuanzheShenqingText(null);
        }
        if("".equals(zhiyuanzheShenqing.getZhiyuanzheShenqingYesnoText()) || "null".equals(zhiyuanzheShenqing.getZhiyuanzheShenqingYesnoText())){
                zhiyuanzheShenqing.setZhiyuanzheShenqingYesnoText(null);
        }

            zhiyuanzheShenqingService.updateById(zhiyuanzheShenqing);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZhiyuanzheShenqingEntity zhiyuanzheShenqingEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,zhiyuanzheShenqingEntity:{}",this.getClass().getName(),zhiyuanzheShenqingEntity.toString());

        ZhiyuanzheShenqingEntity oldZhiyuanzheShenqing = zhiyuanzheShenqingService.selectById(zhiyuanzheShenqingEntity.getId());//查询原先数据

        if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes() == 2){//通过
            YonghuEntity yonghuEntity = yonghuService.selectById(zhiyuanzheShenqingEntity.getYonghuId());
            yonghuEntity.setYonghuTypes(2);
            yonghuService.updateById(yonghuEntity);
//            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingTypes();
        }else if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes() == 3){//拒绝
//            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingTypes();
        }
        zhiyuanzheShenqingEntity.setZhiyuanzheShenqingShenheTime(new Date());//审核时间
        zhiyuanzheShenqingService.updateById(zhiyuanzheShenqingEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhiyuanzheShenqingEntity> oldZhiyuanzheShenqingList =zhiyuanzheShenqingService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        zhiyuanzheShenqingService.deleteBatchIds(Arrays.asList(ids));

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
            List<ZhiyuanzheShenqingEntity> zhiyuanzheShenqingList = new ArrayList<>();//上传的东西
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
                            ZhiyuanzheShenqingEntity zhiyuanzheShenqingEntity = new ZhiyuanzheShenqingEntity();
//                            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingUuidNumber(data.get(0));                    //报名编号 要改的
//                            zhiyuanzheShenqingEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingText(data.get(0));                    //理由 要改的
//                            zhiyuanzheShenqingEntity.setInsertTime(date);//时间
//                            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingYesnoText(data.get(0));                    //审核回复 要改的
//                            zhiyuanzheShenqingEntity.setZhiyuanzheShenqingShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            zhiyuanzheShenqingEntity.setCreateTime(date);//时间
                            zhiyuanzheShenqingList.add(zhiyuanzheShenqingEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("zhiyuanzheShenqingUuidNumber")){
                                    List<String> zhiyuanzheShenqingUuidNumber = seachFields.get("zhiyuanzheShenqingUuidNumber");
                                    zhiyuanzheShenqingUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhiyuanzheShenqingUuidNumber = new ArrayList<>();
                                    zhiyuanzheShenqingUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhiyuanzheShenqingUuidNumber",zhiyuanzheShenqingUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<ZhiyuanzheShenqingEntity> zhiyuanzheShenqingEntities_zhiyuanzheShenqingUuidNumber = zhiyuanzheShenqingService.selectList(new EntityWrapper<ZhiyuanzheShenqingEntity>().in("zhiyuanzhe_shenqing_uuid_number", seachFields.get("zhiyuanzheShenqingUuidNumber")));
                        if(zhiyuanzheShenqingEntities_zhiyuanzheShenqingUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhiyuanzheShenqingEntity s:zhiyuanzheShenqingEntities_zhiyuanzheShenqingUuidNumber){
                                repeatFields.add(s.getZhiyuanzheShenqingUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhiyuanzheShenqingService.insertBatch(zhiyuanzheShenqingList);
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
        PageUtils page = zhiyuanzheShenqingService.queryPage(params);

        //字典表数据转换
        List<ZhiyuanzheShenqingView> list =(List<ZhiyuanzheShenqingView>)page.getList();
        for(ZhiyuanzheShenqingView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhiyuanzheShenqingEntity zhiyuanzheShenqing = zhiyuanzheShenqingService.selectById(id);
            if(zhiyuanzheShenqing !=null){


                //entity转view
                ZhiyuanzheShenqingView view = new ZhiyuanzheShenqingView();
                BeanUtils.copyProperties( zhiyuanzheShenqing , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zhiyuanzheShenqing.getYonghuId());
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
    public R add(@RequestBody ZhiyuanzheShenqingEntity zhiyuanzheShenqing, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhiyuanzheShenqing:{}",this.getClass().getName(),zhiyuanzheShenqing.toString());
        Wrapper<ZhiyuanzheShenqingEntity> queryWrapper = new EntityWrapper<ZhiyuanzheShenqingEntity>()
            .eq("zhiyuanzhe_shenqing_uuid_number", zhiyuanzheShenqing.getZhiyuanzheShenqingUuidNumber())
            .eq("yonghu_id", zhiyuanzheShenqing.getYonghuId())
            .eq("zhiyuanzhe_shenqing_text", zhiyuanzheShenqing.getZhiyuanzheShenqingText())
            .in("zhiyuanzhe_shenqing_yesno_types", new Integer[]{1,2})
            .eq("zhiyuanzhe_shenqing_yesno_text", zhiyuanzheShenqing.getZhiyuanzheShenqingYesnoText())
//            .notIn("zhiyuanzhe_shenqing_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhiyuanzheShenqingEntity zhiyuanzheShenqingEntity = zhiyuanzheShenqingService.selectOne(queryWrapper);
        if(zhiyuanzheShenqingEntity==null){
            zhiyuanzheShenqing.setInsertTime(new Date());
            zhiyuanzheShenqing.setZhiyuanzheShenqingYesnoTypes(1);
            zhiyuanzheShenqing.setCreateTime(new Date());
        zhiyuanzheShenqingService.insert(zhiyuanzheShenqing);

            return R.ok();
        }else {
            if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(zhiyuanzheShenqingEntity.getZhiyuanzheShenqingYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

