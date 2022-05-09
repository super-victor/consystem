import Vue from 'vue';
import Store from '../store/index';
import{
  GET_TOKEN,
  SET_USERINFO,
  // UPDATE_USERROLE,
  // UPDATE_USERINFO,
  // UPLOAD_USER_PROGRESS,
  // UPDATE_PAGE_PERMISSIONS,
  // RESET_PAGE_PERMISSIONS,
  // UPDATE_BREAD,
  // ASIDE_CLICK,
  // ITEM_CLICK,
  // MAIN_CLICK,
  // UPDATE_ASIDE_MENU,
  // RESET_ASIDE_MENU
} from './mutation_types'

export default{
  [GET_TOKEN](state,payload){
    console.log(payload)
    state.userToken = payload;
  },
  [SET_USERINFO](state,payload){
    state.userInfo = payload;
  },
  // TODO 完成websockt的注册
  addGroupMessage(state,msg){
    let message=state.sessions['groupChat'];  
      if (!message){
      //   //state.sessions[state.currentHr.username+"#"+msg.to]=[];
        Vue.set(state.sessions,'groupChat',[]);
        // state.sessions['groupChat']=[]
        // Store.commit('SET_SESSIONS')
        //TODO computed不能动态刷新数据
        // Vue.set(state,'groupChat',[])
        // this.$forceUpdate()
      }

      if(state.sessions['groupChat'][msg.mid]==null){
        // state.sessions['groupChat'][msg.mid]=[]
        Vue.set(state.sessions['groupChat'],msg.mid,[])
      }
      // state.sessions['groupChat'].push(msg)
      if(msg.messageTypeId==2){
        msg.content=JSON.parse(msg.content)
      }
      if(msg.messageTypeId==4){
        msg.content=JSON.parse(msg.content)
      }
      if(msg.messageTypeId==6){
        msg.content=JSON.parse(msg.content)
      }
      if(msg.messageTypeId == 7){
        msg.content=JSON.parse(msg.content)
      }
      state.sessions['groupChat'][msg.mid].push({
        fromId:msg.fromId,
        fromName:msg.fromName,
        fromProfile:msg.fromProfile,
        content:msg.content,
        messageTypeId:msg.messageTypeId,
        createTime: msg.createTime,   
      })
  }
  // [UPDATE_USERROLE](state,payload){
  //   state.userRole = payload;
  // },
  // [UPDATE_USERINFO](state,{email,homeAddress,phone}){
  //   state.userInfo.email = email;
  //   state.userInfo.homeAddress = homeAddress;
  //   state.userInfo.phone = phone;
  // },
  // [UPLOAD_USER_PROGRESS](state,payload){
  //   state.userUploadProgress = payload;
  // },
  // [UPDATE_PAGE_PERMISSIONS](state,payload){
  //   let authority = state.userAuthority;
  //   for(let firstPage in authority){
  //     if(!authority[firstPage].children){
  //       let flag = false;
  //       let theRoles = authority[firstPage].role;
  //       for(let role in theRoles){
  //         let theRole = theRoles[role];
  //         if(payload[role]==true){
  //           theRole.own = true;
  //           flag = true;
  //         }
  //       }
  //       if(flag) authority[firstPage].show = true;
  //     }else{
  //       let firstFlag = false;
  //       let theChildren = authority[firstPage].children;
  //       for(let secondPage in theChildren){
  //         let secondFlag = false;
  //         let theRoles = theChildren[secondPage].role;
  //         for(let role in theRoles){
  //           let theRole = theRoles[role];
  //           if(payload[role]==true){
  //             theRole.own = true;
  //             secondFlag = true;
  //           }
  //         }
  //         if(secondFlag){
  //           theChildren[secondPage].show = true;
  //           firstFlag = true;
  //         }
  //       }
  //       if(firstFlag) authority[firstPage].show = true;
  //     }
  //   }
  // },
  // [RESET_PAGE_PERMISSIONS](state,payload){
  //   state.userAuthority = JSON.parse(JSON.stringify(payload));
  // },
  // [UPDATE_BREAD](state,payload){
  //   state.currentBread = payload;
  // },
  // [ASIDE_CLICK](state,payload){
  //   state.asideItem = payload;
  // },
  // [ITEM_CLICK](state,payload){
  //   state.mouseClickIndex = payload;
  // },
  // [MAIN_CLICK](state,payload){
  //   state.allowShowItem = payload;
  // },
  // [UPDATE_ASIDE_MENU](state,payload){
  //   let asideMenu = state.asideMenu.slice(0,6);
  //   let backstageManagement = state.asideMenu.slice(6);
  //   let userAuthority = state.userAuthority;
  //   let itemArr = ['businesscardholder','schedule','addressbook','meetingroommanagement','meetingmanagement','documentcirculation'];
  //   let updateAsideMenu = [];
  //   let i = 0;
  //   for(let item of itemArr){
  //     let authorityObj = userAuthority[item];
  //     let menuObj = asideMenu[i++];
  //     if(authorityObj.show){
  //       if(authorityObj.children){
  //         let childrenArr = [];
  //         for(let child in authorityObj.children){
  //           if(authorityObj.children[child].show) childrenArr.push(authorityObj.children[child].name);
  //         }
  //         menuObj.children = menuObj.children.filter(item=>childrenArr.includes(item.name));
  //       }
  //       updateAsideMenu.push(menuObj);
  //     }
  //   }
  //   if(state.userRole=='normalAdministrator'){
  //     updateAsideMenu.push(backstageManagement[0]);
  //   }
  //   state.asideMenu = updateAsideMenu;
  // },
  // [RESET_ASIDE_MENU](state,payload){
  //   state.asideMenu = JSON.parse(JSON.stringify(payload));
  // },
}