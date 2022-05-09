<template>
  <div>
    <div class="head">
      <p>会议名称:</p>
      <el-input v-model="input" placeholder="请输入内容"></el-input>
      <el-button type="primary">
        <i class="el-icon-search"></i>
      </el-button>
    </div>
    <div class="body">
      <el-table :data="inmeeting" style="width: 100%" height="580px">
        <el-table-column label="会议名称" align="center" width="240">
          <template slot-scope="scope">
            <p>{{ scope.row.name }}</p>
          </template>
        </el-table-column>
        <el-table-column label="开始时间" align="center" width="240">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="结束时间" align="center" width="240">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发起人" align="center" width="240">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="240" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="info"
              @click="showDetails(scope.$index, scope.row)"
              >详情</el-button
            >
            <el-button
              size="mini"
              type="success"
              @click="handleDelete(scope.$index, scope.row)"
              >参加</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 详情 -->
    <el-dialog
      title="会议详情"
      :visible.sync="meetDetailsdialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <el-form :model="meetDetailsObj" disabled>
          <el-form-item label="活动名称" :label-width="formLabelWidth" >
            <el-input v-model="meetDetailsObj.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主持人" :label-width="formLabelWidth">
            <el-input v-model="meetDetailsObj.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="开始时间" :label-width="formLabelWidth">
            <el-input :placeholder="meetDetailsObj.starttime" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="结束时间" :label-width="formLabelWidth">
            <el-input :placeholder="meetDetailsObj.endtime" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主题" :label-width="formLabelWidth">
            <el-input type="textarea" autosize v-model="meetDetailsObj.theme" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" :label-width="formLabelWidth">
            <el-input type="textarea" autosize v-model="meetDetailsObj.remark" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
    </el-dialog>
  </div>
</template>

<script>
import MeetingManageApi from "../../service/MeetingManage";
export default {
  name: "",
  props: ["inmeeting"],
  data() {
    return {
      meetDetailsdialogVisible: false,
      tableData: [
        {
          starttime: "2022-4-10",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
          brief:
            "关于如何实现，讨论各个系统的联系和区别，怎样实现也是讨论的重点之一",
          theme: "测试如何实现一个毕业设计系统",
          remarks: "单纯的测试一下",
          users: [],
        },
        {
          starttime: "2022-03-29 03:00:00",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          starttime: "2022-4-10",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Jack",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          starttime: "2022-4-10",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Bob",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      // inmeeting:null,
      meetDetailsObj: null,
    };
  },

  created() {
    // console.log("inmeeting")
    // this.getAllMeetings()
    // console.log(this.inmeeting)
  },
  components: {},

  computed: {},

  beforeMount() {},

  mounted() {
    this.meetDetailsObj = this.tableData[0];
  },

  methods: {
    showDetails(index, row) {
      this.meetDetailsdialogVisible = !this.meetDetailsdialogVisible;
      this.meetDetailsObj = row
    },
    // getAllMeetings(){
    //     MeetingManageApi.getMyMeeting().
    //     then((res)=>{
    //       this.inmeeting=res.object.inmeeting
    //       // this.futuremeeting=res.object.futuremeeting
    //       // this.historymeeting=res.object.historymeeting
    //       console.log("inmeetings")
    //       console.log(this.inmeeting)
    //     })
    //   }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.head {
  display: flex;
  background-color: white;
  margin-bottom: 10px;
  align-items: center;
  justify-items: flex-end;
  border-radius: 10px;
  p {
    line-height: 40px;
    margin-left: 15px;
    margin-right: 15px;
  }
  .el-input {
    width: 150px;
    display: flex;
    align-items: center;
    margin-right: 20px;
  }
}
.el-table {
  border-radius: 10px;
  margin-bottom: 15px;
}
</style>