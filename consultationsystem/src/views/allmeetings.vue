<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="正在进行" name="first">
        <Inmeeting :inmeeting="inmeeting"></Inmeeting>
    </el-tab-pane>
    <el-tab-pane label="即将开始" name="second">
        <Futuremeeting :futuremeeting="futuremeeting"></Futuremeeting>
    </el-tab-pane>
    <el-tab-pane label="历史会议" name="third">
        <Historymeeting :historymeeting="historymeeting"></Historymeeting>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
import Futuremeeting from './allmeetings/futuremeeting.vue';
import Historymeeting from './allmeetings/historymeeting.vue';
import Inmeeting from './allmeetings/inmeeting.vue';
import MeetingManageApi from '../service/MeetingManage'

import Utils from '../Utils/util'
  export default {
    name:'',
    props:[''],
    data () {
      return {
        activeName:'first',
        inmeeting:null,
        futuremeeting:null,
        historymeeting:null
      };
    },

    components: {
      Futuremeeting,
      Inmeeting,
      Historymeeting
    },

    computed: {},
    beforeMount() {},
    created(){
      this.getAllMeetings()
    },
    mounted() {},

    methods: {
      getAllMeetings(){
        MeetingManageApi.getMyMeeting().
        then((res)=>{
          this.inmeeting=res.object.inmeeting
          this.futuremeeting=res.object.futuremeeting
          this.historymeeting=res.object.historymeeting
          this.inmeeting.forEach(element => {
            element.starttime=Utils.getYMDHMS(element.starttime)
            element.endtime=Utils.getYMDHMS(element.endtime)
          });
          this.futuremeeting.forEach(element=>{
            element.starttime=Utils.getYMDHMS(element.starttime)
            element.endtime=Utils.getYMDHMS(element.endtime)
          })
          this.historymeeting.forEach(element=>{
            element.starttime=Utils.getYMDHMS(element.starttime)
            element.endtime=Utils.getYMDHMS(element.endtime)
          })
          console.log("allmeetings")
          console.log(this.inmeeting)
          console.log(this.futuremeeting)
          console.log(this.historymeeting)
        })
      }
    },

    watch: {}

  }

</script>
<style lang='less' scoped>

</style>