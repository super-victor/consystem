<template>
  <div>
    <div class="meetinghead">
      <p>我的会议</p>
    </div>
    <div class="test">
      <!-- <ul>
      <li v-for="item in meetings" :key="item">
        <el-card shadow="hover" class="box-card">
          我的会议{{ item.createName }}
        </el-card>
      </li>
      </ul> -->
      <div class="meetingcards">
        <div class="meetingnow">
          <div class="head">
            <p>正在进行</p>
          </div>
          <el-divider></el-divider>
          <div  class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="(item,index) in inmeeting" :key="item"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ getYMDHMS(item.starttime) }}</p>
                    <p>结束时间:{{ getYMDHMS(item.endtime) }}</p>
                    <p>创建者:{{ item.username }}</p>
                     <el-divider></el-divider>
                    <el-button type="text" @click="attendMeeting(index)">进入会议</el-button>
                    <el-divider direction="vertical"></el-divider>
                    <el-button type="text" @click="showInmeeting(index)"
                      >详情</el-button
                    >
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
        <div class="meetingfuture">
          <div class="head">
            <p>即将进行</p>
          </div>
          <el-divider></el-divider>
          <div class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="(item,index) in futuremeeting" :key="index"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ getYMDHMS(item.starttime) }}</p>
                    <p>结束时间:{{ getYMDHMS(item.endtime) }}</p>
                    <p>主持人:{{ item.username }}</p>
                    <el-divider></el-divider>
                    <el-button type="text">编辑</el-button>
                    <el-button type="text" @click="showfuturemeeting(index)">详情</el-button>
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
        <div class="meetinghistory">
          <div class="head">
            <p>历史会议</p>
          </div>
          <el-divider></el-divider>
          <div class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="item in historymeeting" :key="item"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ getYMDHMS(item.starttime) }}</p>
                    <p>结束时间:{{ getYMDHMS(item.endtime) }}</p>
                    <p>创建者:{{ item.username }}</p>
                    <!-- <el-button type="text">会议记录</el-button>
                    <el-button type="text">详情</el-button> -->
                    <template class="ant-card-actions" slot="actions">
                    <a>操作一</a>
                    <a>操作二</a>
                  </template>
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
      </div>
      <el-dialog title="会议详情" :visible.sync="dialogFormVisible">
        <el-form :model="inmeetingview" disabled>
          <el-form-item label="活动名称" :label-width="formLabelWidth" >
            <el-input v-model="inmeetingview.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主持人" :label-width="formLabelWidth">
            <el-input v-model="inmeetingview.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="开始时间" :label-width="formLabelWidth">
            <el-input :placeholder="getYMDHMS(inmeetingview.starttime)" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="结束时间" :label-width="formLabelWidth">
            <el-input :placeholder="getYMDHMS(inmeetingview.endtime)" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="结束时间" :label-width="formLabelWidth">
            <el-input :placeholder="getYMDHMS(inmeetingview.endtime)" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主题" :label-width="formLabelWidth">
            <el-input type="textarea" autosize v-model="inmeetingview.theme" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" :label-width="formLabelWidth">
            <el-input type="textarea" autosize v-model="inmeetingview.remark" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <!-- <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"
            >确 定</el-button
          >
        </div> -->
      </el-dialog>
    </div>
  </div>
</template>

<script>

import MeetingManageApi from '../service/MeetingManage'
export default {
  name: "",
  props: [""],
  data() {
    return {
      dialogFormVisible: false,
      meetings: [
        {
          id: 1,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "das",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "dhgf",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "rte",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "ytui",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "534",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "uiy",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "lkjh",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "Thfgom",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "jggr",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "rwewq",
        },
        {
          id: 3,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "yrt",
        },
      ],
      meeting: {
        id: 1,
        createDate: "2020-02-03",
        name: "开题报告",
        startDate: "2020-02-04",
        endDate: "2020-02-05",
        createName: "Tom",
      },
      inmeeting:null,
      futuremeeting:null,
      historymeeting:null,
      inmeetingview:null,
    };
  },

  components: {},

  computed: {},

  created(){
    this.getMymeeting()
  },
  beforeMount() {},

  mounted() {},

  methods: {

    attendMeeting(index){
      console.log(this.inmeeting)
      console.log(this.inmeeting[index])
      this.$router.push({ 
        path: '/main/chat', 
        query: { 
          mid: this.inmeeting[index].mid,
          name:this.inmeeting[index].name
          }
        });
    },
    showInmeeting(index){
      this.inmeetingview=this.inmeeting[index]
      this.dialogFormVisible=!this.dialogFormVisible;
    },
    // showmeetingInfo(index) {
    //   this.dialogFormVisible = !this.dialogFormVisible;
    //   this.meeting=JSON.parse(JSON.stringify(this.meetings[index]))
    // },
// 展示即将开始会议的信息
    showfuturemeeting(index){
      this.inmeetingview=this.futuremeeting[index]
      this.dialogFormVisible=!this.dialogFormVisible;
    },
    getMymeeting(){
      MeetingManageApi.getMyMeeting()
      .then(res=>{
        // console.log(res)
        if(res.status==200){
          this.inmeeting=res.object.inmeeting
          this.futuremeeting=res.object.futuremeeting
          this.historymeeting=res.object.historymeeting
          if(this.inmeeting!=null){
            this.inmeetingview=this.inmeeting[0]
          }
          // console.log(res.object)
        }
      })
    },
    getYMDHMS (timestamp) {
        let time = new Date(timestamp)
        let year = time.getFullYear()
        let month = time.getMonth() + 1
        let date = time.getDate()
        let hours = time.getHours()
        let minute = time.getMinutes()
        let second = time.getSeconds()

        if (month < 10) { month = '0' + month }
        if (date < 10) { date = '0' + date }
        if (hours < 10) { hours = '0' + hours }
        if (minute < 10) { minute = '0' + minute }
        if (second < 10) { second = '0' + second }
        return year + '-' + month + '-' + date + ' ' + hours + ':' + minute + ':' + second
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.meetinghead {
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
    // left: 5px;
  }
}
.meetingnow {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}
.meetingfuture {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}
.meetinghistory {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}

.box-card {
  width: 300px;
  //   border-radius: 20px;
}
.meetingcard {
  background-color: aquamarine;
  justify-content: center;
  justify-items: center;
}

.el-col {
  margin-bottom: 10px;
  margin-top: 10px;
  margin-left: 5px;
}
.el-card:hover {
  width: 305px;
}
.el-dialog{
  .el-form-item{
    margin-bottom: 0px;
  }
  .el-dialog__header{
    padding-top: 20px;
  }
  .el-dialog__body{
    padding-bottom: 20px;
  }
}
</style>