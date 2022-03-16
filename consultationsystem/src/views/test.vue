<template>
  <div>
    <el-button type="text" @click="dialogVisible=!dialogVisible"
      >点击打开 Dialog</el-button
    >

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Login from '../components/Login/Login.vue';
import Shouye from './shouye.vue';
export default {
  name: "",
  props: [""],
  data() {
    return { dialogVisible: false};
  },

  components: {Shouye, Login},

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
  },
  watch: {},
};
</script>
<style lang='' scoped>
</style>