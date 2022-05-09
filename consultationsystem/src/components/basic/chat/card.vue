<template>
  <div id="card">
	  <p>用户列表</p>
	  <el-divider></el-divider>
  	<header>
<!--  		<img class="avatar" :src="user.userProfile" :alt="user.nickname">-->
<!--  		<p class="name">{{user.nickname}}</p>-->
  	</header>
  	<footer>
          <!-- <el-button type="primary">asd</el-button> -->
  	</footer>
		<!-- <el-input class="search" type="text" size="mini" v-model="$store.state.filterKey" placeholder="搜索" prefix-icon="el-icon-search"></el-input>
        <el-button type="primary" size="mini"><i class="el-icon-search"></i></el-button> -->
		<!-- <el-button class="searchBtn" size="mini" type="primary"><i class="el-icon-search" aria-hidden="true"></i></el-button> -->
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

    methods: {},

    watch: {}

  }

</script>
<style lang='less' scoped>
#card{
    padding: 12px;
    margin-bottom: 5px;
    .avatar{
  	width: 40px;
  	height: 40px;
  	vertical-align: middle;/*这个是图片和文字居中对齐*/
  }
  .name {
  	display: inline-block;
  	padding: 10px;
  	margin-bottom: 15px;
  	font-size: 16px;
  }
  .search {
  	// background-color: #DBD9D8;
	width: 150px;
  	height: 30px;
  	line-height: 30px;
  	padding: 0 10px;
  	border: 0;
  	border-radius: 4px;
  	outline: none;/*鼠标点击后不会出现蓝色边框*/
    color: #FFF;
  }
	.searchBtn{
		background-color: #DBD9D8;
		border: 0;
		margin-left: 3px;
	}
}
#card .el-input__inner {
		// background-color: #DBD9D8;
		outline: none;/*鼠标点击后不会出现蓝色边框*/
	}
</style>