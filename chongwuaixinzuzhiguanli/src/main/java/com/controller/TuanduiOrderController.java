
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
 * 团队活动报名
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tuanduiOrder")
public class TuanduiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(TuanduiOrderController.class);

    private static final String TABLE_NAME = "tuanduiOrder";

    @Autowired
    private TuanduiOrderService tuanduiOrderService;


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
        PageUtils page = tuanduiOrderService.queryPage(params);

        //字典表数据转换
        List<TuanduiOrderView> list =(List<TuanduiOrderView>)page.getList();
        for(TuanduiOrderView c:list){
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
        TuanduiOrderEntity tuanduiOrder = tuanduiOrderService.selectById(id);
        if(tuanduiOrder !=null){
            //entity转view
            TuanduiOrderView view = new TuanduiOrderView();
            BeanUtils.copyProperties( tuanduiOrder , view );//把实体数据重构到view中
            //级联表 团队活动
            //级联表
            TuanduiEntity tuandui = tuanduiService.selectById(tuanduiOrder.getTuanduiId());
            if(tuandui != null){
            BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setTuanduiId(tuandui.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(tuanduiOrder.getYonghuId());
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
    public R save(@RequestBody TuanduiOrderEntity tuanduiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tuanduiOrder:{}",this.getClass().getName(),tuanduiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            tuanduiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        tuanduiOrder.setCreateTime(new Date());
        tuanduiOrder.setInsertTime(new Date());
        tuanduiOrderService.insert(tuanduiOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TuanduiOrderEntity tuanduiOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tuanduiOrder:{}",this.getClass().getName(),tuanduiOrder.toString());
        TuanduiOrderEntity oldTuanduiOrderEntity = tuanduiOrderService.selectById(tuanduiOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            tuanduiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            tuanduiOrderService.updateById(tuanduiOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TuanduiOrderEntity> oldTuanduiOrderList =tuanduiOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        tuanduiOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<TuanduiOrderEntity> tuanduiOrderList = new ArrayList<>();//上传的东西
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
                            TuanduiOrderEntity tuanduiOrderEntity = new TuanduiOrderEntity();
//                            tuanduiOrderEntity.setTuanduiOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            tuanduiOrderEntity.setTuanduiId(Integer.valueOf(data.get(0)));   //团队活动 要改的
//                            tuanduiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            tuanduiOrderEntity.setTuanduiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            tuanduiOrderEntity.setInsertTime(date);//时间
//                            tuanduiOrderEntity.setCreateTime(date);//时间
                            tuanduiOrderList.add(tuanduiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("tuanduiOrderUuidNumber")){
                                    List<String> tuanduiOrderUuidNumber = seachFields.get("tuanduiOrderUuidNumber");
                                    tuanduiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tuanduiOrderUuidNumber = new ArrayList<>();
                                    tuanduiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tuanduiOrderUuidNumber",tuanduiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<TuanduiOrderEntity> tuanduiOrderEntities_tuanduiOrderUuidNumber = tuanduiOrderService.selectList(new EntityWrapper<TuanduiOrderEntity>().in("tuandui_order_uuid_number", seachFields.get("tuanduiOrderUuidNumber")));
                        if(tuanduiOrderEntities_tuanduiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TuanduiOrderEntity s:tuanduiOrderEntities_tuanduiOrderUuidNumber){
                                repeatFields.add(s.getTuanduiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tuanduiOrderService.insertBatch(tuanduiOrderList);
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
        PageUtils page = tuanduiOrderService.queryPage(params);

        //字典表数据转换
        List<TuanduiOrderView> list =(List<TuanduiOrderView>)page.getList();
        for(TuanduiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TuanduiOrderEntity tuanduiOrder = tuanduiOrderService.selectById(id);
            if(tuanduiOrder !=null){


                //entity转view
                TuanduiOrderView view = new TuanduiOrderView();
                BeanUtils.copyProperties( tuanduiOrder , view );//把实体数据重构到view中

                //级联表
                    TuanduiEntity tuandui = tuanduiService.selectById(tuanduiOrder.getTuanduiId());
                if(tuandui != null){
                    BeanUtils.copyProperties( tuandui , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTuanduiId(tuandui.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(tuanduiOrder.getYonghuId());
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
    public R add(@RequestBody TuanduiOrderEntity tuanduiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tuanduiOrder:{}",this.getClass().getName(),tuanduiOrder.toString());
            TuanduiEntity tuanduiEntity = tuanduiService.selectById(tuanduiOrder.getTuanduiId());
            if(tuanduiEntity == null){
                return R.error(511,"查不到该团队活动");
            }
            // Double tuanduiNewMoney = tuanduiEntity.getTuanduiNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//            if(yonghuEntity.getNewMoney() == null)
//                return R.error(511,"用户金额不能为空");
//            double balance = yonghuEntity.getNewMoney() - tuanduiEntity.getTuanduiNewMoney()*tuanduiOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            tuanduiOrder.setTuanduiOrderTypes(101); //设置订单状态为已申请
//            tuanduiOrder.setTuanduiOrderTruePrice(tuanduiEntity.getTuanduiNewMoney()*tuanduiOrder.getBuyNumber()); //设置实付价格
            tuanduiOrder.setYonghuId(userId); //设置订单支付人id
            tuanduiOrder.setTuanduiOrderUuidNumber(String.valueOf(new Date().getTime()));
            tuanduiOrder.setInsertTime(new Date());
            tuanduiOrder.setCreateTime(new Date());
                tuanduiOrderService.insert(tuanduiOrder);//新增订单
            //更新第一注册表
//            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }


    /**
    * 取消申请
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            TuanduiOrderEntity tuanduiOrder = tuanduiOrderService.selectById(id);//当前表service
            Integer tuanduiId = tuanduiOrder.getTuanduiId();
            if(tuanduiId == null)
                return R.error(511,"查不到该团队活动");
            TuanduiEntity tuanduiEntity = tuanduiService.selectById(tuanduiId);
            if(tuanduiEntity == null)
                return R.error(511,"查不到该团队活动");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
//                Double money = tuanduiEntity.getTuanduiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
//                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额




            tuanduiOrder.setTuanduiOrderTypes(102);//设置订单状态为已取消申请
            tuanduiOrderService.updateAllColumnById(tuanduiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            tuanduiService.updateById(tuanduiEntity);//更新订单中团队活动的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer tuanduiCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            TuanduiOrderEntity tuanduiOrder = tuanduiOrderService.selectById(id);
        if(tuanduiOrder == null)
            return R.error(511,"查不到该订单");
        Integer tuanduiId = tuanduiOrder.getTuanduiId();
        if(tuanduiId == null)
            return R.error(511,"查不到该团队活动");

        TuanduiCommentbackEntity tuanduiCommentbackEntity = new TuanduiCommentbackEntity();
            tuanduiCommentbackEntity.setId(id);
            tuanduiCommentbackEntity.setTuanduiId(tuanduiId);
            tuanduiCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            tuanduiCommentbackEntity.setTuanduiCommentbackText(commentbackText);
            tuanduiCommentbackEntity.setInsertTime(new Date());
            tuanduiCommentbackEntity.setReplyText(null);
            tuanduiCommentbackEntity.setUpdateTime(null);
            tuanduiCommentbackEntity.setCreateTime(new Date());
            tuanduiCommentbackService.insert(tuanduiCommentbackEntity);

            tuanduiOrder.setTuanduiOrderTypes(105);//设置订单状态为已评价
            tuanduiOrderService.updateById(tuanduiOrder);//根据id更新
            return R.ok();
    }

    /**
     * 同意
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        TuanduiOrderEntity  tuanduiOrderEntity = tuanduiOrderService.selectById(id);
        tuanduiOrderEntity.setTuanduiOrderTypes(103);//设置订单状态为已同意
        tuanduiOrderService.updateById( tuanduiOrderEntity);

        return R.ok();
    }


    /**
     * 参加
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        TuanduiOrderEntity  tuanduiOrderEntity = tuanduiOrderService.selectById(id);
        tuanduiOrderEntity.setTuanduiOrderTypes(104);//设置订单状态为参加
        tuanduiOrderService.updateById( tuanduiOrderEntity);
        return R.ok();
    }

}

