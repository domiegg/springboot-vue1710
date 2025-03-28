const menu = {
    list() {
        return [
    {
        "backMenu":[
            {
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"管理员管理",
                        "menuJump":"列表",
                        "tableName":"users"
                    }
                ],
                "menu":"管理员管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"用户管理",
                        "menuJump":"列表",
                        "tableName":"yonghu"
                    }
                ],
                "menu":"用户管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"流浪管理",
                        "menuJump":"列表",
                        "tableName":"liulang"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "修改",
                            "删除"
                        ],
                        "menu":"流浪评价管理",
                        "menuJump":"列表",
                        "tableName":"liulangCommentback"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "删除"
                        ],
                        "menu":"流浪收藏管理",
                        "menuJump":"列表",
                        "tableName":"liulangCollection"
                    }
                    ,
                    {
                        "buttons":[
                            "订单",
                            "查看",
                            "删除"
                        ],
                        "menu":"流浪订单管理",
                        "menuJump":"列表",
                        "tableName":"liulangOrder"
                    }
                ],
                "menu":"流浪管理"
            }
            // ,{
            //     "child":[
            //         {
            //             "buttons":[
            //                 "查看",
            //                 "新增",
            //                 "修改",
            //                 "删除"
            //             ],
            //             "menu":"宠物管理",
            //             "menuJump":"列表",
            //             "tableName":"chongwu"
            //         }
            //         ,
            //         {
            //             "buttons":[
            //                 "查看",
            //                 "修改",
            //                 "删除"
            //             ],
            //             "menu":"宠物评价管理",
            //             "menuJump":"列表",
            //             "tableName":"chongwuCommentback"
            //         }
            //         ,
            //         {
            //             "buttons":[
            //                 "查看",
            //                 "删除"
            //             ],
            //             "menu":"宠物收藏管理",
            //             "menuJump":"列表",
            //             "tableName":"chongwuCollection"
            //         }
            //         ,
            //         {
            //             "buttons":[
            //                 "订单",
            //                 "查看",
            //                 "删除"
            //             ],
            //             "menu":"宠物订单管理",
            //             "menuJump":"列表",
            //             "tableName":"chongwuOrder"
            //         }
            //     ],
            //     "menu":"宠物管理"
            // }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"公告管理",
                        "menuJump":"列表",
                        "tableName":"gonggao"
                    }
                ],
                "menu":"公告管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"捐赠管理",
                        "menuJump":"列表",
                        "tableName":"juanzeng"
                    }
                ],
                "menu":"捐赠管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"备忘录管理",
                        "menuJump":"列表",
                        "tableName":"beiwanglu"
                    }
                ],
                "menu":"备忘录管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"团队活动管理",
                        "menuJump":"列表",
                        "tableName":"tuandui"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "修改",
                            "删除"
                        ],
                        "menu":"团队活动评价管理",
                        "menuJump":"列表",
                        "tableName":"tuanduiCommentback"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "删除"
                        ],
                        "menu":"团队活动收藏管理",
                        "menuJump":"列表",
                        "tableName":"tuanduiCollection"
                    }
                    ,
                    {
                        "buttons":[
                            "订单",
                            "查看",
                            "删除"
                        ],
                        "menu":"团队活动报名管理",
                        "menuJump":"列表",
                        "tableName":"tuanduiOrder"
                    }
                ],
                "menu":"团队活动管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除",
                            "审核"
                        ],
                        "menu":"志愿者申请管理",
                        "menuJump":"列表",
                        "tableName":"zhiyuanzheShenqing"
                    }
                ],
                "menu":"志愿者申请管理"
            }
            // ,{
            //     "child":[
            //         {
            //             "buttons":[
            //                 "查看",
            //                 "新增",
            //                 "修改",
            //                 "删除"
            //             ],
            //             "menu":"单页数据管理",
            //             "menuJump":"列表",
            //             "tableName":"singleSeach"
            //         }
            //     ],
            //     "menu":"单页数据管理"
            // }
            ,{
                "child":[
                    // {
                    //     "buttons":[
                    //         "查看",
                    //         "新增",
                    //         "删除",
                    //         "修改"
                    //     ],
                    //     "menu":"宠物类型管理",
                    //     "menuJump":"列表",
                    //     "tableName":"dictionaryChongwu"
                    // }

                    // ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"公告类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryGonggao"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"流浪类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryLiulang"
                    }

                    // ,
                    // {
                    //     "buttons":[
                    //         "查看",
                    //         "新增",
                    //         "删除",
                    //         "修改"
                    //     ],
                    //     "menu":"单页数据类型管理",
                    //     "menuJump":"列表",
                    //     "tableName":"dictionarySingleSeach"
                    // }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"团队活动类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryTuandui"
                    }

                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"用户类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryYonghu"
                    }
                ],
                "menu":"基础数据管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"轮播图管理",
                        "menuJump":"列表",
                        "tableName":"config"
                    }
                ],
                "menu":"轮播图信息"
            }
            /*,{
			    "child":[
			        {
			            "buttons":[
			                "查看"
			            ],
			            "menu":"数据备份",
			            "menuJump":"列表",
			            "tableName":"beifen"
			        },
					{
					    "buttons":[
					        "查看"
					    ],
					    "menu":"数据还原",
					    "menuJump":"列表",
					    "tableName":"huanyuan"
					}
			    ],
			    "menu":"数据库管理"
			}*/
        ],
        "frontMenu":[],
        "hasBackLogin":"是",
        "hasBackRegister":"否",
        "hasFrontLogin":"否",
        "hasFrontRegister":"否",
        "roleName":"管理员",
        "tableName":"users"
    }
]
    }
}
export default menu;
