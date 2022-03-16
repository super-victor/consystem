<template>
  <div class="home">
    <el-container>
      <el-header>
        <the-header />
      </el-header>
      <el-container>
        <el-aside width="200px">
          <the-aside></the-aside>
        </el-aside>
        <el-container>
          <el-main>
            <div class="main">
              <router-view></router-view>
            </div>
          </el-main>
          <el-footer>
            <the-footer></the-footer>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import TheHeader from "../components/basic/TheHeader.vue";
import TheAside from "../components/basic/TheAside.vue";
import TheFooter from "../components/basic/TheFooter.vue";
import Main from "../components/basic/Main.vue";
export default {
  name: "",
  props: [""],
  data() {
    return {
      idadmin:true
    };
  },

  components: {
    TheHeader,
    TheAside,
    TheFooter,
    Main,
  },

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {},

  watch: {},
};
</script>
<style lang='less' scoped>
.main{
  height: 670px;
  width: 96%;
  margin: 20px 20px auto;
}
.home {
  padding: 0px;
  margin: 0px;
  height: 100%;
}
.el-header,
.el-footer {
  background-color:wi#b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: white;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  // line-height: 160px;
  padding: 0;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.el-header{
  height: 48px;
  background-color: rgb(0,21,41);
}
</style>