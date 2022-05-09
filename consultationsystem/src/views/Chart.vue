<template>
  <div id="app">
    <!-- <div class="toolbar">
      <toolbar></toolbar>
    </div> -->
    <div class="sidebar">
      <card ></card>
      <list :mid="this.$route.query.mid"></list>
    </div>
    <div class="main">
      <chattitle :mid="this.$route.query.mid" :name="this.$route.query.name"></chattitle>
      <message :mid="this.$route.query.mid"></message>
      <usertext :mid="this.$route.query.mid"></usertext>
    </div>
  </div>
</template>

<script>
  import card from '../components/basic/chat/card.vue'
  import list from '../components/basic/chat/list.vue'
  import message from '../components/basic/chat/message.vue'
  import usertext from '../components/basic/chat/usertext.vue'
  import toolbar from "../components/basic/chat/toolbar";
  import chattitle from "../components/basic/chat/chattitle"
import { mapState } from 'vuex'

  export default {
    name: 'ChatRoom',
    data () {
      return {
        mid:null
      }
    },
    methods:{
    },
    mounted:function() {
      //初始化数据
      // this.$store.dispatch('initData');
      // //连接WebSocket服务
      // alert(this.$route)\
      if(this.$store.state.stomp==null){
        }
        this.$store.dispatch('connect',{
          mid:this.$route.query.mid,
          uid:this.userInfo.uid
        });

    },
    computed:{
      ...mapState(['userInfo'])
    },
    created () {
      
      // //在页面加载时读取sessionStorage里的状态信息
      // if (sessionStorage.getItem("state") ) {
      //   this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
      // }

      //在页面刷新时将vuex里的最新信息保存到sessionStorage里
      // window.addEventListener("beforeunload",()=>{
      //   sessionStorage.setItem("state",JSON.stringify(this.$store.state))
      // })
    },
    components:{
      toolbar,
      card,
      list,
      message,
      usertext,
      chattitle
    }
  }
</script>

<style lang="less" scoped>
  #app {
    margin: 20px auto;
    width: 1250px;
    height: 650px;
    overflow: hidden;
    border-radius: 10px;
    .sidebar, .main ,.toolbar{
      height: 100%;
    }
    .toolbar{
      float: left;
      color: #f4f4f4;
      background-color: #2e3238;
      width: 60px;
    }
    .sidebar {
      float: left;
      color: #000000;
      background-color: #ECEAE8;
      width: 240px;
    }
    .main {
      position: relative;
      overflow: hidden;
      background-color: #eee;
    }
  }
</style>

