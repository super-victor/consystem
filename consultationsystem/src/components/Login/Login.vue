<template>
  <div class="test">
    <div class="login">
    <div>
      <p>在线会商系统</p>
    </div>
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="用户名" prop="username">
        <el-input
          type="text"
          v-model="ruleForm.username"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
    </el-form>
    <div class="flexContainer">
          <div><el-button type="primary" @click="login()">登录</el-button></div>
          <div><el-button type="primary">重置</el-button></div>
          <!-- <el-button type="primary">重置</el-button> -->
        </div>
  </div>
  </div>
</template>

<script>
import LoginApi from '@/service/UserLogin';
import {mapMutations,mapState} from 'vuex';
export default {
  name: "",
  props: [""],
  data() {
    return {
      ruleForm: {
        username: 'lisi',
        password: '66666',
      },
      rules:{
        username: [
            {required:true,message:"请输入用户名",trigger:'blur'}
          ],
        password:[
          {required:true,message:"请输入密码",trigger:'blur'}
        ]
      },
    };
  },

  components: {},

  computed: {
    ...mapState(['userInfo']),
    loginJudeg(){
      if(this.ruleForm.username==null||this.ruleForm.password==null){
        return false;
      }else{
        return true;
      }
    }
  },

  beforeMount() {},

  mounted() {},

  methods: {
    ...mapMutations(['GET_USERINFO']),
    login() {
      if(this.ruleForm.username==''||this.ruleForm.password==''){
          this.$message({
            message:'请填写用户名或密码',
            type:'warning'
          })
      }
      else{
        LoginApi.loginRequest({
          username:this.ruleForm.username,
          password:this.ruleForm.password,
        }).then(res=>{
          if(res.status!=200){
            this.$message({
              message:res.msg,
              type:'warning'
            })
          }else{
            this.GET_USERINFO(res.object)
            this.$router.replace('home')
            this.$message({
              message:'登录成功',
              type:'success'
            })
          }
        }).catch(err=>{
          console.log(err)
          this.$message({
            message:'登录失败',
            type:'error'
          })
        })
        // this.$router.replace("home"); 
        // this.$message({
        //   message:'登录成功',
        //   type:'success'
        // })
      }
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.test{
  background-image: image('@/asserts/lohin.jpg');
  height: 775px;
}
.login{
  border-radius: 15px;
  background-clip: padding-box;
  width: 350px;
  // margin: 180px auto;
  position: relative;
  left: 520px;
  top: 250px;
  padding: 15px 35px 15px 35px;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cacaca;
  background-color: white;
  p{
    font-size: 20px;
  }
}
el-form-item__content{
  height: 0;
  width: 0;
}
.flexContainer{
  display: flex;
  justify-content: center;
  div{
    margin:  0 25px;
  }
}
</style>