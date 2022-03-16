<template>
  <div id="app">
    <router-view></router-view>
    <!-- <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-link to="/aside">|aside</router-link>
    </div> -->
    <!-- <router-view/> -->
  </div>
</template>

<script>
import TheAside from '../src/components/basic/TheAside.vue'
import Home from '../src/views/Home.vue'
export default {
  components:{
    TheAside,
    Home
  }
}
</script>
<style lang="less">
body{
  padding: 0px;
  margin: 0px;
  height: 100%;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  margin: 0px;
  padding: 0px;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
