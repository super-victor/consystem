<template>
  <div>
    <div class="messageghead">
      <p>消息提醒</p>
    </div>
    <div class="messages">
      <el-tabs
        v-model="activeName"
        @tab-click="handleClick"
        tab-position="left"
      >
        <div class="reminder">
          <!-- <el-card shadow="hover">
            <p>会议预约</p>
            <p>用户张三在2022-03-23 19:40预约了会议，会议开始时间为2022-03-24 10:40，结束时间2022-03-24 11:40.
              主题是：如何解决工作效率问题。
            </p>
            <div>时间：2022-03-23 20:40</div>
          </el-card>
          <el-card shadow="hover">
            <p>会议预约</p>
            <p>用户张龙在2022-03-23 21:40预约了会议，会议开始时间为2022-03-25 10:40，结束时间2022-03-25 11:40.
              主题是：如何解决食堂卫生条件差的问题。
            </p>
            <div>时间：2022-03-23 22:40</div>
          </el-card> -->
          <el-card v-for="(item,index) in reminderList " :key="index" shadow="hover">
            <p>{{item.title}}</p>
            <p>{{item.content}}</p>
            <div>时间：{{item.createtime}}</div>
          </el-card>
        </div>
        <!-- <el-tab-pane label="会议提醒" name="first">
        </el-tab-pane>
        <el-tab-pane label="异常信息" name="second">角色管理d</el-tab-pane>
        <el-tab-pane label="定时任务补偿" name="fourth"
          >定时任务补偿</el-tab-pane
        > -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
import MeetingManageApi from'../service/MeetingManage'
import util from '../Utils/util'
export default {
  name: "",
  props: [""],
  data() {
    return {
      reminderList:{}
    };
  },

  components: {},

  computed: {
    getReminder(item){
      return item
    }
  },

  created(){
    this.getAllReminder()
  },

  beforeMount() {},

  mounted() {},

  methods: {
    getAllReminder(){
      MeetingManageApi.getAllReminder()
      .then(res=>{
        if(res.status==200){
          this.reminderList=res.object
          this.reminderList.forEach(element => {
            element.createtime=util.getYMDHMS(element.createtime)
          });
        }
        // console.log(res)
      })
    }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.reminder{
    height: 580px;
    overflow-y:scroll;
    // left: 50px;
}
.messageghead {
  // background-color: rgba(177, 177, 177,0.2);
  height: 60px;
  margin-bottom: 10px;
  display: flex;
  justify-content: left;
  justify-items: left;
  background-color: white;
  border-radius: 10px;
  p {
    font-size: 20px;
    display: flex;
    align-items: center;
    margin-left: 15px;
  }
}
.messages {
  background-color: white;
  height: 580px;
  border-radius: 10px;
}
.el-card{
  // margin: 10px 10px 10px 10px;
  margin-left: 25px;
  margin-bottom: 10px;
  margin-top: 10px;
  width: 1200px;
  text-align: left;
  border-radius: 10px;
}
</style>