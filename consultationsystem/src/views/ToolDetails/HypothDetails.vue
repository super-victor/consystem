<template>
  <div>
    <el-collapse v-model="activeName" accordion @change="handlechange">
      <el-collapse-item
        :title="item.hypoth.hname"
        :name="index"
        v-for="(item, index) in HypothData"
        :key="index"
      >
        <div>
          <!-- <p>名称</p>
          <el-input
            placeholder="请输入假设的名称"
            v-model="competeDataView.hname"
            disabled
          ></el-input> -->
          <p>假设</p>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="item.hypoth.hypoth"
            disabled
          >
          </el-input>
          <p>证据</p>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            disabled
            v-model="item.hypoth.evidence"
          >
          </el-input>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import HypotyApi from "../../service/Hypoty";
export default {
  name: "",
  props: ["mid"],
  data() {
    return {
      HypothData: null,
      activeName: "1",
    };
  },

  components: {},

  computed: {},

  created() {
    this.HypothDetails();
  },

  beforeMount() {},

  mounted() {},

  methods: {
    HypothDetails() {
      HypotyApi.HypothDetails({
        mid: this.mid,
      }).then((res) => {
        this.HypothData = res.object;
      });
    },
  },

  watch: {
      mid:{
      handler(newMid,oldMid){
          if(newMid!=oldMid){
            this.mid=newMid
            this.HypothDetails()
          }
      },
      immediate:true,
      deep:true
    }
  },
};
</script>
<style lang='less' scoped>
</style>