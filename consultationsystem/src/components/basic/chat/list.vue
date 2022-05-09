<template>
  <div id="list">
   <ul>
     <li v-for="(item, index) in users" :key="index">
       <el-image class="avatar"
												
												:src="item.avatarurl"
												:alt="item.username"
                        >
								<div slot="error" class="image-slot">
									<i class="el-icon-picture-outline"></i>
								</div>
			</el-image>
      <p class="name">{{item.username}}</p>
     </li>
   </ul>
  </div>
</template>

<script>
import MeetingManageApi from '../../../service/MeetingManage'
export default {
  name: "",
  props: ["mid"],
  data() {
    return {
      users:[
        {
          username:'tom',
          avatarurl:require('../../../assets/0.png')
        },
        {
          username:'tom',
          avatarurl:require('../../../assets/0.png')
        },
        {
          username:'tom',
          avatarurl:require('../../../assets/0.png')
        }
      ]
    };
  },



  components: {},

  created(){
	  this.getAllUsersByMid()
  },
  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
	  getAllUsersByMid(){
		  MeetingManageApi.getAllUsersByMid({
			  mid:this.mid
		  }).then((res)=>{
			  console.log(res)
			  this.users=res.object
		  })
	  }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
#list {
	ul{
		margin-left: 0px;
		padding-left: 0px;
		margin-left: 2px;
	}
	li {
		padding-top: 14px;
		padding-bottom: 14px;
		//padding-right: 40px;
		//border-bottom: 1px solid #292C33;
		list-style: none;
		cursor: pointer;
		&:hover {
			background-color: #D8D6D6;
		}
	}
  li.active {/*注意这个是.不是冒号:*/
			background-color: #C8C6C6;
	}
	.avatar {
		border-radius: 45px;
		width: 30px;
		height: 30px;
		vertical-align: middle;
	}
	.name {
		display: inline-block;
		margin-left: 15px;
		margin-top: 0px;
		margin-bottom: 0px;
	}
	.stateItem {//在线状态的样式
		/*position: absolute;*/
		/*left: 160px;*/
		//margin-left: 100px;
		//margin-right: 10px;
	}
	.userList{
		max-height: 600px;
	}
	.el-scrollbar__wrap.default-scrollbar__wrap {
		overflow-x: auto;
	}
}
</style>