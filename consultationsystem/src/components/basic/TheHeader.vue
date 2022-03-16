<template>
  <div>
    <div class="title">
      <i class="el-icon-s-promotion"></i>
      在线会商系统
    </div>
    <div class="right"  @mouseover="test">
      <el-row class="block-col-2">
  <el-col :span="12">
    <!-- <span class="demonstration">hover 激活</span> -->
    <el-dropdown @command="handleCommand">
      <div class="user">
        <avatar :url="imgUrl" :size="35"></avatar>
        <!-- <el-avatar :src="imgUrl"></el-avatar> -->
        
        <div class="username">{{userInfo.username}}</div>
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item icon="el-icon-user" command='a'>个人中心</el-dropdown-item>
        <el-dropdown-item icon="el-icon-setting" >个人设置</el-dropdown-item>
        <el-dropdown-item icon="el-icon-circle-close"  command='c' @click="logout()">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>  
  </el-col>
      </el-row>
      <!-- <div>
        <avatar :url="imgUrl" :size="24"></avatar>
        <div>{{name}}</div>
      </div> -->
    </div>
  </div>
</template>

<script>
import Avatar from './Avatar.vue';
import {mapMutations,mapState} from 'vuex';
  export default {
    name:'',
    props:[''],
    data () {
      return {
          name:'admin',
          // 访问assets里的文件需要加require
          // imgUrl:require('@/assets/0.png')
          imgUrl:this.userInfo.avatarurl
      };
    },

    components: {Avatar},

    computed: {
      ...mapState(['userInfo']),
      },

    beforeMount() {},

    mounted() {
      },
    created(){
      console.log(this.userInfo)
      this.imgUrl=this.userInfo.avatarurl
    },
    methods: {
      ...mapMutations(['GET_USERINFO','userinfo']),
      test(){
        
      },
      handleCommand(command){
        if(command=='c'){
          this.logout()
        }
      },
      logout(){
          alert("退出")
      }
    },

    watch: {}

  }

</script>
<style lang='less' scoped>
.title{
  color: white;
  font-size: 30px;
  float: left;
  left: 0;
}
.right{
  color: white;
  float: right;
  right: 50px;
  width: 114px;
  // background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  div{
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
.user{
  color: white;
  display: flex;
  justify-items: center;
  align-items: center;
  .div{
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
.username{
  height: 60px;
  line-height: 60px;
  font-size: 25px;
  margin-left: 5px;
}
#id{
  width: 20px;
}
</style>