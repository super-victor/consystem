<template>
  <div>
    <img
      class="avatar-img"
      :src='url'
      :style="{ width: size + 'px', height: size + 'px' }"
    />
  </div>
</template>

<script>

  export default {
    name:'',
    props:{
        url:{
            type:String,
            require: true
        },
        size:{
            type:Number
        }
    },
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
.avatar-img {
  border-radius: 50%;
  object-fit: cover;
  display: block;
}
</style>