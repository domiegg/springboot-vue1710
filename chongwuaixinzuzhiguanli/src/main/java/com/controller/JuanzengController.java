
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
 * 捐赠
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/juanzeng")
public class JuanzengController {
    private static final Logger logger = LoggerFactory.getLogger(JuanzengController.class);

    private static final String TABLE_NAME = "juanzeng";

    @Autowired
    private JuanzengService juanzengService;


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
        PageUtils page = juanzengService.queryPage(params);

        //字典表数据转换
        List<JuanzengView> list =(List<JuanzengView>)page.getList();
        for(JuanzengView c:list){
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
        JuanzengEntity juanzeng = juanzengService.selectById(id);
        if(juanzeng !=null){
            //entity转view
            JuanzengView view = new JuanzengView();
            BeanUtils.copyProperties( juanzeng , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(juanzeng.getYonghuId());
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
    public R save(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            juanzeng.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JuanzengEntity> queryWrapper = new EntityWrapper<JuanzengEntity>()
            .eq("yonghu_id", juanzeng.getYonghuId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengEntity juanzengEntity = juanzengService.selectOne(queryWrapper);
        if(juanzengEntity==null){
            juanzeng.setInsertTime(new Date());
            juanzeng.setCreateTime(new Date());
            juanzengService.insert(juanzeng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());
        JuanzengEntity oldJuanzengEntity = juanzengService.selectById(juanzeng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            juanzeng.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(juanzeng.getJuanzengText()) || "null".equals(juanzeng.getJuanzengText())){
                juanzeng.setJuanzengText(null);
        }

            juanzengService.updateById(juanzeng);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JuanzengEntity> oldJuanzengList =juanzengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        juanzengService.deleteBatchIds(Arrays.asList(ids));

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
            List<JuanzengEntity> juanzengList = new ArrayList<>();//上传的东西
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
                            JuanzengEntity juanzengEntity = new JuanzengEntity();
//                            juanzengEntity.setJuanzengUuidNumber(data.get(0));                    //报名编号 要改的
//                            juanzengEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            juanzengEntity.setJuanzengText(data.get(0));                    //捐赠 要改的
//                            juanzengEntity.setNewMoney(data.get(0));                    //金额 要改的
//                            juanzengEntity.setInsertTime(date);//时间
//                            juanzengEntity.setCreateTime(date);//时间
                            juanzengList.add(juanzengEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("juanzengUuidNumber")){
                                    List<String> juanzengUuidNumber = seachFields.get("juanzengUuidNumber");
                                    juanzengUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> juanzengUuidNumber = new ArrayList<>();
                                    juanzengUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("juanzengUuidNumber",juanzengUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<JuanzengEntity> juanzengEntities_juanzengUuidNumber = juanzengService.selectList(new EntityWrapper<JuanzengEntity>().in("juanzeng_uuid_number", seachFields.get("juanzengUuidNumber")));
                        if(juanzengEntities_juanzengUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JuanzengEntity s:juanzengEntities_juanzengUuidNumber){
                                repeatFields.add(s.getJuanzengUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        juanzengService.insertBatch(juanzengList);
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
        PageUtils page = juanzengService.queryPage(params);

        //字典表数据转换
        List<JuanzengView> list =(List<JuanzengView>)page.getList();
        for(JuanzengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JuanzengEntity juanzeng = juanzengService.selectById(id);
            if(juanzeng !=null){


                //entity转view
                JuanzengView view = new JuanzengView();
                BeanUtils.copyProperties( juanzeng , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(juanzeng.getYonghuId());
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
    public R add(@RequestBody JuanzengEntity juanzeng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,juanzeng:{}",this.getClass().getName(),juanzeng.toString());
        Wrapper<JuanzengEntity> queryWrapper = new EntityWrapper<JuanzengEntity>()
            .eq("juanzeng_uuid_number", juanzeng.getJuanzengUuidNumber())
            .eq("yonghu_id", juanzeng.getYonghuId())
            .eq("juanzeng_text", juanzeng.getJuanzengText())
//            .notIn("juanzeng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JuanzengEntity juanzengEntity = juanzengService.selectOne(queryWrapper);
        if(juanzengEntity==null){
            YonghuEntity yonghuEntity = yonghuService.selectById(juanzeng.getYonghuId());

            Double newMoney1 = juanzeng.getNewMoney();
            Double newMoney = yonghuEntity.getNewMoney();

            if(newMoney<newMoney1)
                return R.error(511,"余额不足请及时充值");
            yonghuEntity.setNewMoney(newMoney-newMoney1);
            yonghuService.updateById(yonghuEntity);
            juanzeng.setInsertTime(new Date());
            juanzeng.setCreateTime(new Date());
        juanzengService.insert(juanzeng);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

