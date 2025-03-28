
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
 * 流浪订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/liulangOrder")
public class LiulangOrderController {
    private static final Logger logger = LoggerFactory.getLogger(LiulangOrderController.class);

    private static final String TABLE_NAME = "liulangOrder";

    @Autowired
    private LiulangOrderService liulangOrderService;


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
        PageUtils page = liulangOrderService.queryPage(params);

        //字典表数据转换
        List<LiulangOrderView> list =(List<LiulangOrderView>)page.getList();
        for(LiulangOrderView c:list){
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
        LiulangOrderEntity liulangOrder = liulangOrderService.selectById(id);
        if(liulangOrder !=null){
            //entity转view
            LiulangOrderView view = new LiulangOrderView();
            BeanUtils.copyProperties( liulangOrder , view );//把实体数据重构到view中
            //级联表 流浪
            //级联表
            LiulangEntity liulang = liulangService.selectById(liulangOrder.getLiulangId());
            if(liulang != null){
            BeanUtils.copyProperties( liulang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLiulangId(liulang.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(liulangOrder.getYonghuId());
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
    public R save(@RequestBody LiulangOrderEntity liulangOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,liulangOrder:{}",this.getClass().getName(),liulangOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            liulangOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        liulangOrder.setCreateTime(new Date());
        liulangOrder.setInsertTime(new Date());
        liulangOrderService.insert(liulangOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LiulangOrderEntity liulangOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,liulangOrder:{}",this.getClass().getName(),liulangOrder.toString());
        LiulangOrderEntity oldLiulangOrderEntity = liulangOrderService.selectById(liulangOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            liulangOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            liulangOrderService.updateById(liulangOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LiulangOrderEntity> oldLiulangOrderList =liulangOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        liulangOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<LiulangOrderEntity> liulangOrderList = new ArrayList<>();//上传的东西
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
                            LiulangOrderEntity liulangOrderEntity = new LiulangOrderEntity();
//                            liulangOrderEntity.setLiulangOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            liulangOrderEntity.setLiulangId(Integer.valueOf(data.get(0)));   //流浪 要改的
//                            liulangOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            liulangOrderEntity.setLiulangOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            liulangOrderEntity.setInsertTime(date);//时间
//                            liulangOrderEntity.setCreateTime(date);//时间
                            liulangOrderList.add(liulangOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("liulangOrderUuidNumber")){
                                    List<String> liulangOrderUuidNumber = seachFields.get("liulangOrderUuidNumber");
                                    liulangOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> liulangOrderUuidNumber = new ArrayList<>();
                                    liulangOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("liulangOrderUuidNumber",liulangOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<LiulangOrderEntity> liulangOrderEntities_liulangOrderUuidNumber = liulangOrderService.selectList(new EntityWrapper<LiulangOrderEntity>().in("liulang_order_uuid_number", seachFields.get("liulangOrderUuidNumber")));
                        if(liulangOrderEntities_liulangOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LiulangOrderEntity s:liulangOrderEntities_liulangOrderUuidNumber){
                                repeatFields.add(s.getLiulangOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        liulangOrderService.insertBatch(liulangOrderList);
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
        PageUtils page = liulangOrderService.queryPage(params);

        //字典表数据转换
        List<LiulangOrderView> list =(List<LiulangOrderView>)page.getList();
        for(LiulangOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LiulangOrderEntity liulangOrder = liulangOrderService.selectById(id);
            if(liulangOrder !=null){


                //entity转view
                LiulangOrderView view = new LiulangOrderView();
                BeanUtils.copyProperties( liulangOrder , view );//把实体数据重构到view中

                //级联表
                    LiulangEntity liulang = liulangService.selectById(liulangOrder.getLiulangId());
                if(liulang != null){
                    BeanUtils.copyProperties( liulang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLiulangId(liulang.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(liulangOrder.getYonghuId());
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
    public R add(@RequestBody LiulangOrderEntity liulangOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,liulangOrder:{}",this.getClass().getName(),liulangOrder.toString());
            LiulangEntity liulangEntity = liulangService.selectById(liulangOrder.getLiulangId());
            if(liulangEntity == null){
                return R.error(511,"查不到该流浪");
            }
            // Double liulangNewMoney = liulangEntity.getLiulangNewMoney();

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
//            double balance = yonghuEntity.getNewMoney() - liulangEntity.getLiulangNewMoney()*liulangOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            liulangOrder.setLiulangOrderTypes(101); //设置订单状态为已申请认养
//            liulangOrder.setLiulangOrderTruePrice(liulangEntity.getLiulangNewMoney()*liulangOrder.getBuyNumber()); //设置实付价格
            liulangOrder.setYonghuId(userId); //设置订单支付人id
            liulangOrder.setLiulangOrderUuidNumber(String.valueOf(new Date().getTime()));
            liulangOrder.setInsertTime(new Date());
            liulangOrder.setCreateTime(new Date());
                liulangOrderService.insert(liulangOrder);//新增订单
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

            LiulangOrderEntity liulangOrder = liulangOrderService.selectById(id);//当前表service
            Integer liulangId = liulangOrder.getLiulangId();
            if(liulangId == null)
                return R.error(511,"查不到该流浪");
            LiulangEntity liulangEntity = liulangService.selectById(liulangId);
            if(liulangEntity == null)
                return R.error(511,"查不到该流浪");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
//                Double money = liulangEntity.getLiulangNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
//                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额




            liulangOrder.setLiulangOrderTypes(102);//设置订单状态为已取消申请
            liulangOrderService.updateAllColumnById(liulangOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            liulangService.updateById(liulangEntity);//更新订单中流浪的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer liulangCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            LiulangOrderEntity liulangOrder = liulangOrderService.selectById(id);
        if(liulangOrder == null)
            return R.error(511,"查不到该订单");
        Integer liulangId = liulangOrder.getLiulangId();
        if(liulangId == null)
            return R.error(511,"查不到该流浪");

        LiulangCommentbackEntity liulangCommentbackEntity = new LiulangCommentbackEntity();
            liulangCommentbackEntity.setId(id);
            liulangCommentbackEntity.setLiulangId(liulangId);
            liulangCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            liulangCommentbackEntity.setLiulangCommentbackText(commentbackText);
            liulangCommentbackEntity.setInsertTime(new Date());
            liulangCommentbackEntity.setReplyText(null);
            liulangCommentbackEntity.setUpdateTime(null);
            liulangCommentbackEntity.setCreateTime(new Date());
            liulangCommentbackService.insert(liulangCommentbackEntity);

            liulangOrder.setLiulangOrderTypes(105);//设置订单状态为已评价
            liulangOrderService.updateById(liulangOrder);//根据id更新
            return R.ok();
    }

    /**
     * 同意认养
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        LiulangOrderEntity  liulangOrderEntity = liulangOrderService.selectById(id);
        liulangOrderEntity.setLiulangOrderTypes(103);//设置订单状态为已同意认养
        liulangOrderService.updateById( liulangOrderEntity);

        return R.ok();
    }


    /**
     * 认养
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        LiulangOrderEntity  liulangOrderEntity = liulangOrderService.selectById(id);
        liulangOrderEntity.setLiulangOrderTypes(104);//设置订单状态为认养
        liulangOrderService.updateById( liulangOrderEntity);
        return R.ok();
    }

}

