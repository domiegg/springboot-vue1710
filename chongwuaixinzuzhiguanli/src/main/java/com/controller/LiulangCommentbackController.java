
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
 * 流浪评价
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/liulangCommentback")
public class LiulangCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(LiulangCommentbackController.class);

    private static final String TABLE_NAME = "liulangCommentback";

    @Autowired
    private LiulangCommentbackService liulangCommentbackService;


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
        PageUtils page = liulangCommentbackService.queryPage(params);

        //字典表数据转换
        List<LiulangCommentbackView> list =(List<LiulangCommentbackView>)page.getList();
        for(LiulangCommentbackView c:list){
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
        LiulangCommentbackEntity liulangCommentback = liulangCommentbackService.selectById(id);
        if(liulangCommentback !=null){
            //entity转view
            LiulangCommentbackView view = new LiulangCommentbackView();
            BeanUtils.copyProperties( liulangCommentback , view );//把实体数据重构到view中
            //级联表 流浪
            //级联表
            LiulangEntity liulang = liulangService.selectById(liulangCommentback.getLiulangId());
            if(liulang != null){
            BeanUtils.copyProperties( liulang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLiulangId(liulang.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(liulangCommentback.getYonghuId());
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
    public R save(@RequestBody LiulangCommentbackEntity liulangCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,liulangCommentback:{}",this.getClass().getName(),liulangCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            liulangCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        liulangCommentback.setCreateTime(new Date());
        liulangCommentback.setInsertTime(new Date());
        liulangCommentbackService.insert(liulangCommentback);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LiulangCommentbackEntity liulangCommentback, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,liulangCommentback:{}",this.getClass().getName(),liulangCommentback.toString());
        LiulangCommentbackEntity oldLiulangCommentbackEntity = liulangCommentbackService.selectById(liulangCommentback.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            liulangCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(liulangCommentback.getLiulangCommentbackText()) || "null".equals(liulangCommentback.getLiulangCommentbackText())){
                liulangCommentback.setLiulangCommentbackText(null);
        }
        if("".equals(liulangCommentback.getReplyText()) || "null".equals(liulangCommentback.getReplyText())){
                liulangCommentback.setReplyText(null);
        }
        liulangCommentback.setUpdateTime(new Date());

            liulangCommentbackService.updateById(liulangCommentback);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LiulangCommentbackEntity> oldLiulangCommentbackList =liulangCommentbackService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        liulangCommentbackService.deleteBatchIds(Arrays.asList(ids));

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
            List<LiulangCommentbackEntity> liulangCommentbackList = new ArrayList<>();//上传的东西
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
                            LiulangCommentbackEntity liulangCommentbackEntity = new LiulangCommentbackEntity();
//                            liulangCommentbackEntity.setLiulangId(Integer.valueOf(data.get(0)));   //流浪 要改的
//                            liulangCommentbackEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            liulangCommentbackEntity.setLiulangCommentbackText(data.get(0));                    //评价内容 要改的
//                            liulangCommentbackEntity.setInsertTime(date);//时间
//                            liulangCommentbackEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            liulangCommentbackEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            liulangCommentbackEntity.setCreateTime(date);//时间
                            liulangCommentbackList.add(liulangCommentbackEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        liulangCommentbackService.insertBatch(liulangCommentbackList);
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
        PageUtils page = liulangCommentbackService.queryPage(params);

        //字典表数据转换
        List<LiulangCommentbackView> list =(List<LiulangCommentbackView>)page.getList();
        for(LiulangCommentbackView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LiulangCommentbackEntity liulangCommentback = liulangCommentbackService.selectById(id);
            if(liulangCommentback !=null){


                //entity转view
                LiulangCommentbackView view = new LiulangCommentbackView();
                BeanUtils.copyProperties( liulangCommentback , view );//把实体数据重构到view中

                //级联表
                    LiulangEntity liulang = liulangService.selectById(liulangCommentback.getLiulangId());
                if(liulang != null){
                    BeanUtils.copyProperties( liulang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLiulangId(liulang.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(liulangCommentback.getYonghuId());
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
    public R add(@RequestBody LiulangCommentbackEntity liulangCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,liulangCommentback:{}",this.getClass().getName(),liulangCommentback.toString());
        liulangCommentback.setCreateTime(new Date());
        liulangCommentback.setInsertTime(new Date());
        liulangCommentbackService.insert(liulangCommentback);

            return R.ok();
        }

}

