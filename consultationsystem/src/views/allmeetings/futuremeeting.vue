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
      <el-table :data="futuremeeting" style="width: 100%" height="580px">
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
            <span style="margin-left: 10px">{{ scope.row.Initiator }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="240" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="info" @click="handleEdit(scope.$index, scope.row)"
              >详情</el-button
            >
            <el-button size="mini" type="primary" v-if="isInitiator(scope.row.Initiator)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

  export default {
    name:'',
    props:['futuremeeting'],
    data () {
      return {
          
        user:'Tom',
        tableData: [
        {
          starttime: "2022-4-10",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          starttime: "2022-4-10",
          endtime: "2022-4-10",
          name: "如何实现在线会商系统",
          Initiator: "Bob",
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
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      };
    },

    components: {},

    computed: {
        ...mapState(['userInfo'])
    },

    beforeMount() {},

    mounted() {},

    methods: {
        isInitiator(user){
            if(user==this.user){
                return true
            }else{
                return false
            }
        }
    },

    watch: {}

  }

</script>
<style lang='less' scoped>
.head{
    display: flex;
    background-color: white;
    margin-bottom: 10px;
    align-items: center;
    justify-items: flex-end;
    border-radius: 10px;
    p{
        line-height: 40px;
        margin-left: 15px;
        margin-right: 15px;
    }
    .el-input{
        width: 150px;
        display: flex;
        align-items: center;
        margin-right: 20px;
    }
}
.el-table{
  border-radius: 10px;
  margin-bottom: 15px;
}
</style>