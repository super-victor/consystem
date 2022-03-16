<template>
  <div>
    <div class="logtitle">
      <p>日志信息</p>
    </div>
    <el-table
      :data="
        tableData.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      :default-sort = "{prop: 'date', order: 'descending'}"
      height="670"
      :hight-row-style="{height:'20px'}"
    >
      <el-table-column label="Date" prop="date" sortable
      
        :filters="[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]"
      :filter-method="filterHandler" align="center"
      > </el-table-column>
      <el-table-column label="Name" prop="name"  align="center" sortable> </el-table-column>
      <el-table-column label="Type" prop="type"  align="center" sortable> </el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          >
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
</template>

<script>
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
    };
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      }
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.logtitle{
  height: 50px;
  // background-color: burlywood;
  float: left;
  margin-bottom: 10px;
  p{
    font-size: 20px;
  }
}
</style>