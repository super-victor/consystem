<template>
  <div id="main">
    <!-- 这是main页!s -->
    <router-view></router-view>

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

    },

    watch: {}

  }

</script>
<style lang='less' scoped>
#main{
  height: 670px;
  // background-color: red;
}
</style>