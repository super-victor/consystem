<template>
  <div class="aside">
    <!-- 这是Aside -->
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          unique-opened="ture"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-home"></i>
              <span>首 页</span>
            </template>
            <el-menu-item-group>
              <router-link to="/main/mymeeting">
                <el-menu-item index="1-1"> 我的会议 </el-menu-item>
              </router-link>
              <router-link to="/main/message">
                <el-menu-item index="1-2">消息提醒</el-menu-item>
              </router-link>
              <router-link to="">
                <el-menu-item index="1-3">工具服务</el-menu-item>
              </router-link>
              <router-link to="/main/quickmeeting">
                <el-menu-item index="1-4">快速会议</el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>工具管理</span>
            </template>
            <el-menu-item-group>
              <router-link to="/main/delphi">
                <el-menu-item index="2-1">德尔菲</el-menu-item>
              </router-link>
              <router-link to="/main/swot">
                <el-menu-item index="2-2">SWOT</el-menu-item>
              </router-link>
              <router-link to="/main/competitive">
                <el-menu-item index="2-3">竞争性假设</el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-s-cooperation"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item-group>
              <router-link to="/main/log">
                <el-menu-item index="3-1">日志管理 </el-menu-item>
              </router-link>
              <!-- <el-menu-item index="3-2">
          </el-menu-item> -->
              <router-link to="/main/usermanage">
                <el-menu-item index="3-2">用户管理 </el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>个人页</span>
            </template>
            <el-menu-item-group>
              <router-link to="/main/personalcenter">
                <el-menu-item index="4-1">个人中心 </el-menu-item>
              </router-link>
              <!-- <el-menu-item index="3-2">
          </el-menu-item> -->
              <router-link to="/main/personalsetting">
                <el-menu-item index="4-2">个人设置 </el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>会议</span>
            </template>
            <el-menu-item-group>
              <router-link to="/main/allmeestings">
                <el-menu-item index="4-1">全部会议 </el-menu-item>
              </router-link>
              <router-link to="/main/meetingsummary">
                <el-menu-item index="4-2">会议纪要 </el-menu-item>
              </router-link>
              <router-link to="/main/meetingmanage">
                <el-menu-item index="4-3">会议管理 </el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "",
  props: {
    isadmin: {
      type: Boolean,
      require: true,
    },
  },
  data() {
    return {};
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {},

  watch: {},
};
</script>
<style lang='less' scoped>
.aside {
  width: 100%;
}
a {
  text-decoration: none;
  // color: red;
}
</style>