<template>
  <div class="quickmeeting">
    <div class="header">
      <p>快速会议</p>
    </div>
    <div class="body">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row gutter="20">
          <el-col :span="12">
            <el-form-item class="meetingname" label="会议名称：" label-width="100px">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="会议主题：" label-width="100px">
              <el-input v-model="form.theme"></el-input>
            </el-form-item>
            <el-form-item label="会议简介：" label-width="100px">
              <el-input type="textarea" v-model="form.desc"></el-input>
            </el-form-item>
            <el-form-item label="主持人：" label-width="100px">
              <el-input v-model="userInfo.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="开始时间">
              <el-date-picker
                v-model="form.starttime"
                type="datetime"
                placeholder="选择日期时间"
                value-format="yyyy-MM-dd HH:mm:ss"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间">
              <el-date-picker
                v-model="form.endtime"
                type="datetime"
                placeholder="选择日期时间"
                value-format="yyyy-MM-dd HH:mm:ss"
              >
              </el-date-picker>
            </el-form-item>

            <el-form-item label="备注：">
              <el-input type="textarea" v-model="form.remark"></el-input>
            </el-form-item>
            <!-- <el-form-item>
              <el-button type="primary" @click="onSubmit">立即创建</el-button>
              <el-button>取消</el-button>
            </el-form-item> -->
          </el-col>
          <el-col :span="11">
            <div>
              <span>参会人员</span>
              <div class="meetters">
                <el-table
                  ref="multipleTable"
                  :data="tableData"
                  tooltip-effect="dark"
                  height="468px"
                  style="width: 100%"
                  @selection-change="handleSelectionChange"
                >
                  <el-table-column type="selection" width="55" align="center">
                  </el-table-column>
                  <el-table-column
                    prop="username"
                    label="姓名"
                    width="545"
                    align="center"
                  >
                  </el-table-column>
                </el-table>
              </div>
            </div>

            <!-- <el-button @click="getCheckedNodes">查询</el-button> -->
          </el-col>
        </el-row>
      </el-form>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button>取消</el-button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import UserManageApi from '../service/UserManage'
import MeetingManageApi from '../service/MeetingManage'
export default {
  name: "",
  props: [""],
  data() {
    return {
      form: {
        name: "",
        theme: "",
        hostmanId:'',
        starttime: "",
        endtime: "",
        remark:'',
      },
      tableData: [
        {
          date: "2016-05-03",
          name: "TOM",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "das",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "miji",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-01",
          name: "fdsf",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-08",
          name: "eretr",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-06",
          name: "gtryt",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-07",
          name: "grgrt",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      multipleSelection: [],
      attendIds:[],
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },

  components: {},

  created(){
    this.getAllUsers()
  },

  computed: {
    ...mapState(['userInfo'])
  },

  beforeMount() {},

  mounted() {},

  methods: {
    getCheckedNodes() {
      console.log(this.$refs.tree.getCheckedNodes());
    },
    getCheckedKeys() {
      console.log(this.$refs.tree.getCheckedKeys());
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      
    },
    onSubmit(){
        this.form.hostmanId=this.userInfo.uid;
        this.multipleSelection.forEach(item=>{
          this.attendIds.push(item.uid)
        })
        // console.log(this.attendIds)
        // console.log(this.form)
        MeetingManageApi.addMeeting({
          name:this.form.name,
          theme:this.form.theme,
          hostmanId:this.userInfo.uid,
          starttime:this.form.starttime,
          endtime:this.form.endtime,
          remark:this.form.remark,
          attendsId:this.attendIds
        }).then(res=>{
          console.log(res)
          if(res.status==200){
            this.$message({
              message:res.msg,
              type:'success'
            })
          }
        })
    },
    //所有的用户
    getAllUsers(){
      UserManageApi.getAllUsers()
      .then(res=>{
        this.tableData=res.object;
      })
    }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.header {
  // font-size: 20px;
  height: 50px;
  background-color: white;
  display: flex;
  justify-content: left;
  justify-items: left;
  border-radius: 10px;
  p {
    font-size: 20px;
    display: flex;
    align-items: center;
    // left: 5px;
    margin-left: 15px;
  }
}
.body {
  background-color: white;
  margin-top: 10px;
  min-height: 600px;
  margin-bottom: 10px;
  border-radius: 10px;
}
.el-input {
  // width: 50px;
  // height: 10px;
}
.meetters {
  // display: flex;
  width: 600px;
  .el-table {
  }
}
.meetingname{
  margin-top: 10px;
}
</style>