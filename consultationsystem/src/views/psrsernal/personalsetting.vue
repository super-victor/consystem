<template>
  <div>
    <div class="head"></div>
    <div class="body">
      <el-row type="flex" justify="space-around">
        <el-col :span="6">
          <div class="left">
            <div class="avatar">
              <el-avatar :src="userInfo.avatarurl"></el-avatar>
            </div>
            <div>
              <div>
                <p v-if="disable">
                  {{ userInfo.username }}
                </p>
              </div>
              <div class="usernamemodify" v-if="!disable">
                <p>用户名:</p>
                <el-input v-model="userInfo.username"></el-input>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="17">
          <div class="right">
            <div class="content">
              <div class="info email">
                <span><p>邮箱:</p></span>
                <el-input v-model="userInfo.email" :disabled="disable"></el-input>
              </div>
              <div class="info">
                <span><p>电话:</p></span>
                <el-input v-model="userInfo.phone" :disabled="disable"></el-input>
              </div>
              <div class="info">
                <span><p>角色:</p></span>
                <div class="isadmin">
                  <el-radio v-model="userInfo.isadmin" label="0" disabled
                    >普通用户</el-radio
                  >
                  <el-radio v-model="userInfo.isadmin" label="1" disabled
                    >管理员</el-radio
                  >
                </div>
              </div>
              <div class="info">
                <span><p>是否禁用:</p></span>
                <div class="isforbid">
                  <el-radio v-model="userInfo.isforbid" label="0" disabled
                    >否</el-radio
                  >
                  <el-radio v-model="userInfo.isforbid" label="1" disabled
                    >是</el-radio
                  >
                </div>
              </div>
              <div class="option">
                <el-button type="primary" @click="modify">修改</el-button>
                <el-button v-if="!disable" type="primary" >确认</el-button>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: "",
  props: [""],
  data() {
    return {
      // userInfo: {
      //   account: "",
      //   email: "12654123@qq.com",
      //   username: "Tom",
      //   isadmin: "0",
      //   isforbid: "1",
      //   password: "",
      //   phone: "15246512346",
      //   uid: "",
      //   avatarUrl: require("../../assets/1.webp"),
      // },
      disable:true
    };
  },

  components: {},

  computed: {
    ...mapState(['userInfo'])
    },

  beforeMount() {

  },

  mounted() {},

  methods: {
    modify(){
      this.disable=!this.disable
    }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.body {
  // background-color: white;
  height: 650px;
}
.left {
  // background-color: red;
  background-color: white;
  height: 600px;
  border-radius: 10px;
  .avatar {
    padding-top: 150px;
  }
  p {
    font-size: 30px;
  }
  .el-avatar {
    width: 200px;
    height: 200px;
  }
}
.right {
  background-color: white;
  height: 600px;
  border-radius: 10px;
  p {
    margin-left: 15px;
  }
  .el-input {
    margin-left: 15px;
    width: 250px;
  }
  .content{
    // background-color: red;
    .email{
      padding-top: 170px;
    }
  }
}
.info {
  display: flex;
  align-items: center;
  align-content: center;
  padding-left: 250px;
  padding-top: 5px ;
  .isadmin{
    display: flex;
    align-items: center;
    margin-left: 15px;
  }
  .isforbid{
    display: flex;
    align-items: center;
    margin-left: 15px;
  }
}
.option{
  margin-top: 20px;
  margin-left: -80px;
}
.usernamemodify{
  width: 150px;
  margin-left: 90px;
}
</style>