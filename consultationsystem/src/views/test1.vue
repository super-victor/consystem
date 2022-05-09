<template>
  <div>
    <el-button @click="test">弹出</el-button>
  </div>
</template>

<script>

  export default {
    name:'',
    props:[''],
    data () {
      return {

      };
    },

    components: {},

    computed: {},

    beforeMount() {},

    mounted() {},

    methods: {
      test(){
        this.$message({
          message:'da',
          type:'success'
        })
      }
    },

    watch: {}

  }

</script>
<style lang='' scoped>

</style>