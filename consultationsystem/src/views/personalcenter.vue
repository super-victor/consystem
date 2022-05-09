<template>
  <div class="head">
    <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="个人信息" name="first">
      <personinfo></personinfo>
    </el-tab-pane>
    <el-tab-pane label="个人设置" name="second">
      <personalsetting></personalsetting>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
import Personalsetting from './psrsernal/personalsetting.vue';
import Personinfo from './psrsernal/personinfo.vue';
export default {
  name: "",
  props: [""],
  data() {
    return {
     activeName:'first'
    };
  },

  components: {Personinfo, Personalsetting, Personalsetting,  },

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {},

  watch: {},
};
</script>
<style lang='less' scoped>
.head{
  // background-color: red;
  border-radius: 10px;
}
</style>