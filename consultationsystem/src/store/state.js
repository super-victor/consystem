export default{
  //****全局用户信息
  // ip1 = '192.168.31.116';//局域网
  // ip2 = '121.196.40.164';//服务器
  // ip3 = '192.168.43.245';
  // ip4 = '101.201.254.63';
  // currentIP:'192.168.31.116',
  // userUploadProgress:0,
  // userToken:null,
  userInfo:{},
  sessions:{
    groupChat:[]
  }, //聊天记录
  stomp:null
  // 
  // userRole:'',
  // userAuthority:{
  //   user:{
  //     name:"用户信息",
  //     show:false,
  //     role:{
  //       '0001':{
  //         name:"获取个人资料",
  //         own:false
  //       },
  //       '0002':{
  //         name:"修改个人资料",
  //         own:false
  //       }
  //     }
  //   },
  //   businesscardholder:{
  //     name:"名片夹",
  //     show:false,
  //     role:{
  //       '0003':{
  //         name:"获取自己所拥有的名片夹",
  //         own:false
  //       },
  //       '0004':{
  //         name:"删除名片夹",
  //         own:false
  //       },
  //       '0005':{
  //         name:"增加名片夹",
  //         own:false
  //       },
  //       '0006':{
  //         name:"修改名片夹名称",
  //         own:false
  //       },
  //       '0007':{
  //         name:"直接手动添加名片",
  //         own:false
  //       },
  //       '0008':{
  //         name:"修改名片",
  //         own:false
  //       },
  //       '0009':{
  //         name:"分享名片",
  //         own:false
  //       },
  //       '000A':{
  //         name:"删除名片",
  //         own:false
  //       },
  //       '000B':{
  //         name:"查找名片",
  //         own:false
  //       }
  //     }
  //   },
  //   schedule:{
  //     name:"日程安排",
  //     show:false,
  //     children:{
  //       mine:{
  //         name:"我的日程",
  //         show:false,
  //         role:{
  //           '0016':{
  //             name:"获取自己所有的个人日程",
  //             own:false
  //           },
  //           '0012':{
  //             name:"修改个人日程信息",
  //             own:false
  //           },
  //           '0013':{
  //             name:"删除个人日程",
  //             own:false
  //           },
  //           '002H':{
  //             name:"获取日程信息",
  //             own:false
  //           }
  //         }
  //       },
  //       companyschedule:{
  //         name:"公司日程",
  //         show:false,
  //         role:{
  //           '0017':{
  //             name:"获取自己所有的公司日程",
  //             own:false
  //           },
  //           '002X':{
  //             name:"获取日程信息",
  //             own:false
  //           }
  //         }
  //       },
  //       createschedule:{
  //         name:"新建日程",
  //         show:false,
  //         role:{
  //           '0011':{
  //             name:"添加个人日程",
  //             own:false
  //           },
  //           '000X':{
  //             name:"添加公司日程",
  //             own:false
  //           }
  //         }
  //       },
  //       schedulemanagement:{
  //         name:"日程管理",
  //         show:false,
  //         role:{
  //           '000Y':{
  //             name:"修改公司日程信息",
  //             own:false
  //           },
  //           '000Z':{
  //             name:"删除公司日程",
  //             own:false
  //           }
  //         }
  //       }
  //     }
  //   },
  //   addressbook:{
  //     name:"通讯录",
  //     show:false,
  //     role:{
  //       '000E':{
  //         name:"获取所有员工的通讯录",
  //         own:false
  //       },
  //       '000F':{
  //         name:"获取所有部门的部门名称",
  //         own:false
  //       },
  //       '000G':{
  //         name:"分页获取员工通讯录",
  //         own:false
  //       }
  //     }
  //   },
  //   meetingroommanagement:{
  //     name:"会议室管理",
  //     show:false,
  //     children:{
  //       equipment:{
  //         name:"设施管理",
  //         show:false,
  //         role:{
  //           '000Q':{
  //             name:"获取所有的设备信息",
  //             own:false
  //           },
  //           '000S':{
  //             name:"获取所有的会议室信息",
  //             own:false
  //           },
  //           '000K':{
  //             name:"获取所有设备分类信息",
  //             own:false
  //           },
  //           '000P':{
  //             name:"删除设备",
  //             own:false
  //           },
  //           '000N':{
  //             name:"修改设备",
  //             own:false
  //           },
  //           '000M':{
  //             name:"添加设备",
  //             own:false
  //           }
  //         }
  //       },
  //       room:{
  //         name:"房间管理",
  //         show:false,
  //         role:{
  //           '0026':{
  //             name:"获取某一天某一个会议室的会议预约",
  //             own:false
  //           },
  //           '0032':{
  //             name:"获取所有的会议室信息",
  //             own:false
  //           },
  //           '0019':{
  //             name:"按楼层获取会议室信息",
  //             own:false
  //           },
  //           '0025':{
  //             name:"预约会议",
  //             own:false
  //           }
  //         }
  //       },
  //       appointmentapproval:{
  //         name:"预约审批",
  //         show:false,
  //         role:{
  //           '0024':{
  //             name:"获取未被审批的会议",
  //             own:false
  //           },
  //           '0033':{
  //             name:"获取所有员工通讯录",
  //             own:false
  //           },
  //           '0023':{
  //             name:"审批会议",
  //             own:false
  //           }
  //         }
  //       }
  //     },
  //   },
  //   meetingmanagement:{
  //     name:"会议管理",
  //     show:false,
  //     role:{
  //       '0020':{
  //         name:"获取所有的会议",
  //         own:false
  //       },
  //       '0028':{
  //         name:"修改未审批的预约会议",
  //         own:false
  //       },
  //       '0027':{
  //         name:"删除未审批的预约会议",
  //         own:false
  //       }
  //     }
  //   },
  //   documentcirculation:{
  //     name:"公文流转",
  //     show:false,
  //     children:{
  //       querydraft:{
  //         name:"查询拟稿",
  //         show:false,
  //         role:{
  //           '002S':{
  //             name:"取消自己提交的审核",
  //             own:false
  //           },
  //           '002N':{
  //             name:"获取发文详情",
  //             own:false
  //           },
  //           '002F':{
  //             name:"提交发文审稿",
  //             own:false
  //           },
  //           '002A':{
  //             name:"发文时获取所有审查人",
  //             own:false
  //           },
  //           '002E':{
  //             name:"修改发文",
  //             own:false
  //           },
  //           '002B':{
  //             name:"上传发文附件",
  //             own:false
  //           },
  //           '002C':{
  //             name:"删除发文附件",
  //             own:false
  //           },
  //           '002W':{
  //             name:"将审查未通过的设置成草稿箱",
  //             own:false
  //           },
  //           '002I':{
  //             name:"删除公文",
  //             own:false
  //           },
  //           '002G':{
  //             name:"获取草稿箱",
  //             own:false
  //           },
  //           '002M':{
  //             name:"获取未通过的发文",
  //             own:false
  //           },
  //           '002L':{
  //             name:"获取自己正在审核的发文",
  //             own:false
  //           },
  //         }
  //       },
  //       createdraft:{
  //         name:"新建拟稿",
  //         show:false,
  //         role:{
  //           '002Y':{
  //             name:"发文时获取所有审查人",
  //             own:false
  //           },
  //           '002J':{
  //             name:"修改发文",
  //             own:false
  //           },
  //           '002Z':{
  //             name:"上传发文附件",
  //             own:false
  //           },
  //           '0034':{
  //             name:"删除发文附件",
  //             own:false
  //           },
  //           '002D':{
  //             name:"获取或创建空白发文",
  //             own:false
  //           }
  //         }
  //       },
  //       viewdraftlist:{
  //         name:"公文一览",
  //         show:false,
  //         role:{
  //           '002T':{
  //             name:"获取所有已发布的发文",
  //             own:false
  //           },
  //           '002U':{
  //             name:"获取自己已发布的公文",
  //             own:false
  //           }
  //         }
  //       },
  //       checkdraft:{
  //         name:"拟稿审核",
  //         show:false,
  //         role:{
  //           '0014':{
  //             name:"审稿人获取自己未审稿",
  //             own:false
  //           },
  //           '0015':{
  //             name:"审稿人审稿",
  //             own:false
  //           },
  //           '0031':{
  //             name:"获取发文详情",
  //             own:false
  //           }
  //         }
  //       }
  //     }
  //   }
  // },
  // userAuthorityTemple:{
  //   user:{
  //     name:"用户信息",
  //     show:false,
  //     role:{
  //       '0001':{
  //         name:"获取个人资料",
  //         own:false
  //       },
  //       '0002':{
  //         name:"修改个人资料",
  //         own:false
  //       }
  //     }
  //   },
  //   businesscardholder:{
  //     name:"名片夹",
  //     show:false,
  //     role:{
  //       '0003':{
  //         name:"获取自己所拥有的名片夹",
  //         own:false
  //       },
  //       '0004':{
  //         name:"删除名片夹",
  //         own:false
  //       },
  //       '0005':{
  //         name:"增加名片夹",
  //         own:false
  //       },
  //       '0006':{
  //         name:"修改名片夹名称",
  //         own:false
  //       },
  //       '0007':{
  //         name:"直接手动添加名片",
  //         own:false
  //       },
  //       '0008':{
  //         name:"修改名片",
  //         own:false
  //       },
  //       '0009':{
  //         name:"分享名片",
  //         own:false
  //       },
  //       '000A':{
  //         name:"删除名片",
  //         own:false
  //       },
  //       '000B':{
  //         name:"查找名片",
  //         own:false
  //       }
  //     }
  //   },
  //   schedule:{
  //     name:"日程安排",
  //     show:false,
  //     children:{
  //       mine:{
  //         name:"我的日程",
  //         show:false,
  //         role:{
  //           '0016':{
  //             name:"获取自己所有的个人日程",
  //             own:false
  //           },
  //           '0012':{
  //             name:"修改个人日程信息",
  //             own:false
  //           },
  //           '0013':{
  //             name:"删除个人日程",
  //             own:false
  //           },
  //           '002H':{
  //             name:"获取日程信息",
  //             own:false
  //           }
  //         }
  //       },
  //       companyschedule:{
  //         name:"公司日程",
  //         show:false,
  //         role:{
  //           '0017':{
  //             name:"获取自己所有的公司日程",
  //             own:false
  //           },
  //           '002X':{
  //             name:"获取日程信息",
  //             own:false
  //           }
  //         }
  //       },
  //       createschedule:{
  //         name:"新建日程",
  //         show:false,
  //         role:{
  //           '0011':{
  //             name:"添加个人日程",
  //             own:false
  //           },
  //           '000X':{
  //             name:"添加公司日程",
  //             own:false
  //           }
  //         }
  //       },
  //       schedulemanagement:{
  //         name:"日程管理",
  //         show:false,
  //         role:{
  //           '000Y':{
  //             name:"修改公司日程信息",
  //             own:false
  //           },
  //           '000Z':{
  //             name:"删除公司日程",
  //             own:false
  //           }
  //         }
  //       }
  //     }
  //   },
  //   addressbook:{
  //     name:"通讯录",
  //     show:false,
  //     role:{
  //       '000E':{
  //         name:"获取所有员工的通讯录",
  //         own:false
  //       },
  //       '000F':{
  //         name:"获取所有部门的部门名称",
  //         own:false
  //       },
  //       '000G':{
  //         name:"分页获取员工通讯录",
  //         own:false
  //       }
  //     }
  //   },
  //   meetingroommanagement:{
  //     name:"会议室管理",
  //     show:false,
  //     children:{
  //       equipment:{
  //         name:"设施管理",
  //         show:false,
  //         role:{
  //           '000Q':{
  //             name:"获取所有的设备信息",
  //             own:false
  //           },
  //           '000S':{
  //             name:"获取所有的会议室信息",
  //             own:false
  //           },
  //           '000K':{
  //             name:"获取所有设备分类信息",
  //             own:false
  //           },
  //           '000P':{
  //             name:"删除设备",
  //             own:false
  //           },
  //           '000N':{
  //             name:"修改设备",
  //             own:false
  //           },
  //           '000M':{
  //             name:"添加设备",
  //             own:false
  //           }
  //         }
  //       },
  //       room:{
  //         name:"房间管理",
  //         show:false,
  //         role:{
  //           '0026':{
  //             name:"获取某一天某一个会议室的会议预约",
  //             own:false
  //           },
  //           '0032':{
  //             name:"获取所有的会议室信息",
  //             own:false
  //           },
  //           '0019':{
  //             name:"按楼层获取会议室信息",
  //             own:false
  //           },
  //           '0025':{
  //             name:"预约会议",
  //             own:false
  //           }
  //         }
  //       },
  //       appointmentapproval:{
  //         name:"预约审批",
  //         show:false,
  //         role:{
  //           '0024':{
  //             name:"获取未被审批的会议",
  //             own:false
  //           },
  //           '0033':{
  //             name:"获取所有员工通讯录",
  //             own:false
  //           },
  //           '0023':{
  //             name:"审批会议",
  //             own:false
  //           }
  //         }
  //       }
  //     },
  //   },
  //   meetingmanagement:{
  //     name:"会议管理",
  //     show:false,
  //     role:{
  //       '0020':{
  //         name:"获取所有的会议",
  //         own:false
  //       },
  //       '0028':{
  //         name:"修改未审批的预约会议",
  //         own:false
  //       },
  //       '0027':{
  //         name:"删除未审批的预约会议",
  //         own:false
  //       }
  //     }
  //   },
  //   documentcirculation:{
  //     name:"公文流转",
  //     show:false,
  //     children:{
  //       querydraft:{
  //         name:"查询拟稿",
  //         show:false,
  //         role:{
  //           '002S':{
  //             name:"取消自己提交的审核",
  //             own:false
  //           },
  //           '002N':{
  //             name:"获取发文详情",
  //             own:false
  //           },
  //           '002F':{
  //             name:"提交发文审稿",
  //             own:false
  //           },
  //           '002A':{
  //             name:"发文时获取所有审查人",
  //             own:false
  //           },
  //           '002E':{
  //             name:"修改发文",
  //             own:false
  //           },
  //           '002B':{
  //             name:"上传发文附件",
  //             own:false
  //           },
  //           '002C':{
  //             name:"删除发文附件",
  //             own:false
  //           },
  //           '002W':{
  //             name:"将审查未通过的设置成草稿箱",
  //             own:false
  //           },
  //           '002I':{
  //             name:"删除公文",
  //             own:false
  //           },
  //           '002G':{
  //             name:"获取草稿箱",
  //             own:false
  //           },
  //           '002M':{
  //             name:"获取未通过的发文",
  //             own:false
  //           },
  //           '002L':{
  //             name:"获取自己正在审核的发文",
  //             own:false
  //           },
  //         }
  //       },
  //       createdraft:{
  //         name:"新建拟稿",
  //         show:false,
  //         role:{
  //           '002Y':{
  //             name:"发文时获取所有审查人",
  //             own:false
  //           },
  //           '002J':{
  //             name:"修改发文",
  //             own:false
  //           },
  //           '002Z':{
  //             name:"上传发文附件",
  //             own:false
  //           },
  //           '0034':{
  //             name:"删除发文附件",
  //             own:false
  //           },
  //           '002D':{
  //             name:"获取或创建空白发文",
  //             own:false
  //           }
  //         }
  //       },
  //       viewdraftlist:{
  //         name:"公文一览",
  //         show:false,
  //         role:{
  //           '002T':{
  //             name:"获取所有已发布的发文",
  //             own:false
  //           },
  //           '002U':{
  //             name:"获取自己已发布的公文",
  //             own:false
  //           }
  //         }
  //       },
  //       checkdraft:{
  //         name:"拟稿审核",
  //         show:false,
  //         role:{
  //           '0014':{
  //             name:"审稿人获取自己未审稿",
  //             own:false
  //           },
  //           '0015':{
  //             name:"审稿人审稿",
  //             own:false
  //           },
  //           '0031':{
  //             name:"获取发文详情",
  //             own:false
  //           }
  //         }
  //       }
  //     }
  //   }
  // },
  //****面包屑状态
  // currentBread:[],
  //****全局侧边栏状态
  //侧边栏信息
  // asideMenu:[
  //   {
  //     img:'card_holder',
  //     title:'名片夹',
  //     clickMark:1,
  //     route:'business-card-holder',
  //     children:[
  //       {
  //         name:'共享名片夹',
  //         route:'shared'
  //       }
  //     ]
  //   },
  //   {
  //     img:'schedule',
  //     title:'日程安排',
  //     clickMark:2,
  //     route:'schedule',
  //     children:[
  //       {
  //         name:'我的日程',
  //         route:'mine'
  //       },
  //       {
  //         name:'公司日程',
  //         route:'company-schedule'
  //       },
  //       {
  //         name:'新建日程',
  //         route:'create-schedule'
  //       },
  //       {
  //         name:'日程管理',
  //         route:'schedule-management'
  //       }
  //     ]
  //   },
  //   {
  //     img:'address_book',
  //     title:'通讯录',
  //     clickMark:3,
  //     route:'address-book',
  //     children:[
  //       {
  //         name:'通讯录查询',
  //         route:'inquiry'
  //       }
  //     ]
  //   },
  //   {
  //     img:'meetingroom',
  //     title:'会议室管理',
  //     clickMark:5,
  //     route:'meetingroom-management',
  //     children:[
  //       {
  //         name:'设施管理',
  //         route:'equipment'
  //       },
  //       {
  //         name:'房间管理',
  //         route:'room'
  //       },
  //       {
  //         name:'预约审批',
  //         route:'appointment-approval'
  //       }
  //     ]
  //   },
  //   {
  //     img:'meeting',
  //     title:'会议管理',
  //     clickMark:6,
  //     route:'meeting-management',
  //     children:[
  //       {
  //         name:'会议信息',
  //         route:'information'
  //       }
  //     ]
  //   },
  //   {
  //     img:'send_file',
  //     title:'公文流转',
  //     clickMark:8,
  //     route:'document-circulation',
  //     children:[
  //       {
  //         name:'查询拟稿',
  //         route:'query-draft'
  //       },
  //       {
  //         name:'新建拟稿',
  //         route:'create-draft'
  //       },
  //       {
  //         name:'公文一览',
  //         route:'view-draft-list'
  //       },
  //       {
  //         name:'拟稿审核',
  //         route:'check-draft'
  //       },
  //     ]
  //   },
  //   {
  //     img:'backstage_management',
  //     title:'后台管理',
  //     clickMark:7,
  //     route:'backstage-management',
  //     children:[
  //       {
  //         name:'部门管理',
  //         route:'department'
  //       },
  //       {
  //         name:'员工管理',
  //         route:'staff/all'
  //       },
  //       {
  //         name:'角色管理',
  //         route:'role'
  //       }
  //     ]
  //   }
  // ],
  // asideMenuTemple:[
  //   {
  //     img:'card_holder',
  //     title:'名片夹',
  //     clickMark:1,
  //     route:'business-card-holder',
  //     children:[
  //       {
  //         name:'共享名片夹',
  //         route:'shared'
  //       }
  //     ]
  //   },
  //   {
  //     img:'schedule',
  //     title:'日程安排',
  //     clickMark:2,
  //     route:'schedule',
  //     children:[
  //       {
  //         name:'我的日程',
  //         route:'mine'
  //       },
  //       {
  //         name:'公司日程',
  //         route:'company-schedule'
  //       },
  //       {
  //         name:'新建日程',
  //         route:'create-schedule'
  //       },
  //       {
  //         name:'日程管理',
  //         route:'schedule-management'
  //       }
  //     ]
  //   },
  //   {
  //     img:'address_book',
  //     title:'通讯录',
  //     clickMark:3,
  //     route:'address-book',
  //     children:[
  //       {
  //         name:'通讯录查询',
  //         route:'inquiry'
  //       }
  //     ]
  //   },
  //   {
  //     img:'meetingroom',
  //     title:'会议室管理',
  //     clickMark:5,
  //     route:'meetingroom-management',
  //     children:[
  //       {
  //         name:'设施管理',
  //         route:'equipment'
  //       },
  //       {
  //         name:'房间管理',
  //         route:'room'
  //       },
  //       {
  //         name:'预约审批',
  //         route:'appointment-approval'
  //       }
  //     ]
  //   },
  //   {
  //     img:'meeting',
  //     title:'会议管理',
  //     clickMark:6,
  //     route:'meeting-management',
  //     children:[
  //       {
  //         name:'会议信息',
  //         route:'information'
  //       }
  //     ]
  //   },
  //   {
  //     img:'send_file',
  //     title:'公文流转',
  //     clickMark:8,
  //     route:'document-circulation',
  //     children:[
  //       {
  //         name:'查询拟稿',
  //         route:'query-draft'
  //       },
  //       {
  //         name:'新建拟稿',
  //         route:'create-draft'
  //       },
  //       {
  //         name:'公文一览',
  //         route:'view-draft-list'
  //       },
  //       {
  //         name:'拟稿审核',
  //         route:'check-draft'
  //       },
  //     ]
  //   },
  //   {
  //     img:'backstage_management',
  //     title:'后台管理',
  //     clickMark:7,
  //     route:'backstage-management',
  //     children:[
  //       {
  //         name:'部门管理',
  //         route:'department'
  //       },
  //       {
  //         name:'员工管理',
  //         route:'staff/all'
  //       },
  //       {
  //         name:'角色管理',
  //         route:'role'
  //       }
  //     ]
  //   }
  // ],
  //侧边栏选中信息
  // asideItem:{},
  //主界面是否选中(未选中从而允许侧边栏二级菜单显示)
  // allowShowItem:false,
  //被选中的侧边栏序号
  // mouseClickIndex:0
}