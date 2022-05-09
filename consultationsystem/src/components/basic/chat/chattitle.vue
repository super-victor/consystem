<template>
  <div class="chatTitle">
      <p>{{name}}</p>
    <!-- <span class="titleName">{{this.$store.state.currentSession.nickname?this.$store.state.currentSession.nickname:""}}</span> -->
    <el-button class="moreBtn" size="small" icon="el-icon-more"></el-button>
  </div>
</template>

<script>

  export default {
    name:'',
    props:['mid','name'],
    data () {
      return {

      };
    },

    components: {},

    computed: {},

    beforeMount() {},

    mounted() {},

    methods: {},

    watch: {}

  }

</script>
<style lang='less' scoped>
.chatTitle{
    height: 50px;
    width: 100%;
    font-size: 20px;
    display: flex;
    justify-content: space-between;
    border-bottom: 0.5px solid #c7d2db;
  }
  .moreBtn{
    background-color: #eee;
    border: 0;
    height: 40px;
  }
  .titleName{
    margin: 10px 20px;
  }
</style>