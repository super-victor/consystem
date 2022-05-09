<template>
  <div>
    <div class="logtitle">
      <p>日志信息</p>
    </div>
    <div class="body">
    <el-table
      :data="
        logsData.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      :default-sort = "{prop: 'date', order: 'descending'}"
      height="580"
      :hight-row-style="{height:'20px'}"
    >

    <!-- tableData.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        ) -->
      <el-table-column label="Date" prop="date" sortable align="center"> </el-table-column>
      <el-table-column label="Content" prop="content"  align="center" sortable> </el-table-column>
      <el-table-column label="Type" prop="type"  align="center" sortable
      :filters="typetable"
      :filter-method="filterHandler"
      > </el-table-column>
      <el-table-column align="center">
        <template slot="header" slot-scope="">
          <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
          操作
        </template>
        <template slot-scope="scope">
          <!-- <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          > -->
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    </div>
  </div>
</template>

<script>
import LogApi from "../service/Log"
export default {
  name: "",
  props: [""],
  data() {
    return {
      tableData: [
        {
          date: "2016-05-02 20:20",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },

        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "d",
          type:"登录",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          type:'异常',
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          date: "2016-05-01",
          name: "lipi",
          type:'错误',
          address: "上海市普陀区金沙江路 1519 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          type:'jinace',
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
      search: "",
      typetable:null,
      logsData:null
    };
  },

  components: {},

  computed: {},

  beforeMount() {},

  created(){
    if(this.typetable==null){
      this.getAllTypes()
    }
    this.getAllLogs();
  },

  mounted() {},

  methods: {
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      handleEdit(index,row){
          // alert(index)
          // this.getAllTypes()
      },
      handleDelete(index,row){
          // alert(index)
        this.open(index)
      },
      open(index) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          alert(this.logsData[index].lid)
          LogApi.deleteLog({
            lid:this.logsData[index].lid
          }).then(res=>{
            if(res.status==200){
              this.$message({
                message:res.msg,
                type:'success'
              })
              this.getAllTypes()
              this.getAllLogs()
            }
          }).catch(err=>{
            console.log(err)
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
      getAllTypes(){
          LogApi.getAllTypes()
          .then(res=>{
            if(res.status==200){
              this.typetable=res.object
            }
          })
          .catch(err=>{
            console.log(err)
          })
      },

      getAllLogs(){
          LogApi.getAllLogs()
          .then(res=>{
            this.logsData=res.object
            this.logsData.forEach(element => {
              element.date=this.getYMDHMS(element.date)
            });
            // console.log(this.logsData)
          })
          .catch(err=>{
            console.log(err)
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
.logtitle{
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
.body{
  border-radius: 10px;
  .el-table{
    border-radius: 10px;
  }
}
</style>