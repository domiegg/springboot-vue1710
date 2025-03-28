import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import beiwanglu from '@/views/modules/beiwanglu/list'
    import chongwu from '@/views/modules/chongwu/list'
    import chongwuCollection from '@/views/modules/chongwuCollection/list'
    import chongwuCommentback from '@/views/modules/chongwuCommentback/list'
    import chongwuOrder from '@/views/modules/chongwuOrder/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import juanzeng from '@/views/modules/juanzeng/list'
    import liulang from '@/views/modules/liulang/list'
    import liulangCollection from '@/views/modules/liulangCollection/list'
    import liulangCommentback from '@/views/modules/liulangCommentback/list'
    import liulangOrder from '@/views/modules/liulangOrder/list'
    import singleSeach from '@/views/modules/singleSeach/list'
    import tuandui from '@/views/modules/tuandui/list'
    import tuanduiCollection from '@/views/modules/tuanduiCollection/list'
    import tuanduiCommentback from '@/views/modules/tuanduiCommentback/list'
    import tuanduiOrder from '@/views/modules/tuanduiOrder/list'
    import yonghu from '@/views/modules/yonghu/list'
    import zhiyuanzheShenqing from '@/views/modules/zhiyuanzheShenqing/list'
    import config from '@/views/modules/config/list'
    import dictionaryChongwu from '@/views/modules/dictionaryChongwu/list'
    import dictionaryChongwuCollection from '@/views/modules/dictionaryChongwuCollection/list'
    import dictionaryChongwuOrder from '@/views/modules/dictionaryChongwuOrder/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryLiulang from '@/views/modules/dictionaryLiulang/list'
    import dictionaryLiulangCollection from '@/views/modules/dictionaryLiulangCollection/list'
    import dictionaryLiulangOrder from '@/views/modules/dictionaryLiulangOrder/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionarySingleSeach from '@/views/modules/dictionarySingleSeach/list'
    import dictionaryTuandui from '@/views/modules/dictionaryTuandui/list'
    import dictionaryTuanduiCollection from '@/views/modules/dictionaryTuanduiCollection/list'
    import dictionaryTuanduiOrder from '@/views/modules/dictionaryTuanduiOrder/list'
    import dictionaryYonghu from '@/views/modules/dictionaryYonghu/list'
    import dictionaryZhiyuanzheShenqingYesno from '@/views/modules/dictionaryZhiyuanzheShenqingYesno/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryChongwu',
        name: '宠物类型',
        component: dictionaryChongwu
    }
    ,{
        path: '/dictionaryChongwuCollection',
        name: '收藏表类型',
        component: dictionaryChongwuCollection
    }
    ,{
        path: '/dictionaryChongwuOrder',
        name: '订单类型',
        component: dictionaryChongwuOrder
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryLiulang',
        name: '流浪类型',
        component: dictionaryLiulang
    }
    ,{
        path: '/dictionaryLiulangCollection',
        name: '收藏表类型',
        component: dictionaryLiulangCollection
    }
    ,{
        path: '/dictionaryLiulangOrder',
        name: '订单类型',
        component: dictionaryLiulangOrder
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionarySingleSeach',
        name: '单页数据类型',
        component: dictionarySingleSeach
    }
    ,{
        path: '/dictionaryTuandui',
        name: '团队活动类型',
        component: dictionaryTuandui
    }
    ,{
        path: '/dictionaryTuanduiCollection',
        name: '收藏表类型',
        component: dictionaryTuanduiCollection
    }
    ,{
        path: '/dictionaryTuanduiOrder',
        name: '订单类型',
        component: dictionaryTuanduiOrder
    }
    ,{
        path: '/dictionaryYonghu',
        name: '用户类型',
        component: dictionaryYonghu
    }
    ,{
        path: '/dictionaryZhiyuanzheShenqingYesno',
        name: '报名状态',
        component: dictionaryZhiyuanzheShenqingYesno
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/beiwanglu',
        name: '备忘录',
        component: beiwanglu
      }
    ,{
        path: '/chongwu',
        name: '宠物',
        component: chongwu
      }
    ,{
        path: '/chongwuCollection',
        name: '宠物收藏',
        component: chongwuCollection
      }
    ,{
        path: '/chongwuCommentback',
        name: '宠物评价',
        component: chongwuCommentback
      }
    ,{
        path: '/chongwuOrder',
        name: '宠物订单',
        component: chongwuOrder
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/juanzeng',
        name: '捐赠',
        component: juanzeng
      }
    ,{
        path: '/liulang',
        name: '流浪',
        component: liulang
      }
    ,{
        path: '/liulangCollection',
        name: '流浪收藏',
        component: liulangCollection
      }
    ,{
        path: '/liulangCommentback',
        name: '流浪评价',
        component: liulangCommentback
      }
    ,{
        path: '/liulangOrder',
        name: '流浪订单',
        component: liulangOrder
      }
    ,{
        path: '/singleSeach',
        name: '单页数据',
        component: singleSeach
      }
    ,{
        path: '/tuandui',
        name: '团队活动',
        component: tuandui
      }
    ,{
        path: '/tuanduiCollection',
        name: '团队活动收藏',
        component: tuanduiCollection
      }
    ,{
        path: '/tuanduiCommentback',
        name: '团队活动评价',
        component: tuanduiCommentback
      }
    ,{
        path: '/tuanduiOrder',
        name: '团队活动报名',
        component: tuanduiOrder
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/zhiyuanzheShenqing',
        name: '志愿者申请',
        component: zhiyuanzheShenqing
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
