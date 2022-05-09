<template>
  <div>
    <div class="competehead">
      <p>假设名称:</p>
      <el-input v-model="input" placeholder="请输入内容"></el-input>
      <p>创建时间:</p>
      <el-date-picker
        v-model="serachdate"
        align="right"
        type="date"
        placeholder="选择日期"
        :picker-options="pickerOptions"
      >
      </el-date-picker>
      <el-button type="primary">
        <i class="el-icon-search"></i>
      </el-button>
      <el-button type="primary" @click="competitionAdd" class="swotadd"
        >新增假设</el-button
      >
    </div>
    <el-table :data="competeData" style="width: 100%">
      <el-table-column label="SWOT名称" width="400" align="center">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.hname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" width="400" align="center">
        <template slot-scope="scope">
          <p>{{ scope.row.createtime }}</p>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="400">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="success"
            @click="handlePreview(scope.$index, scope.row)"
          >
            预览
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 新建假设 -->
    <el-dialog
      title="新建假设"
      :visible.sync="competitionAddDialogVisible"
      width="50%"
      center
      class="editDialog"
      :before-close="addcolse"
    >
      <p>名称</p>
      <el-input placeholder="请输入假设的名称" v-model="hypothAdd.name"></el-input>
      <p>假设</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="hypothAdd.hypoth"
      >
      </el-input>
      <p>证据</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="hypothAdd.evidence"
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="competitionAddDialogVisible = !competitionAddDialogVisible">取 消</el-button>
        <el-button type="primary" @click="AddHypoty">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 假设编辑 -->
    <el-dialog
      title="新建假设"
      :visible.sync="competitionEditDialogVisible"
      width="50%"
      center
      class="editDialog"
    >
      <p>名称</p>
      <el-input placeholder="请输入假设的名称" v-model="competeDataEdit.hname"></el-input>
      <p>假设</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="competeDataEdit.hypoth"
      >
      </el-input>
      <p>证据</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="competeDataEdit.evidence"
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="competitionEditDialogVisible = !competitionEditDialogVisible">取 消</el-button>
        <el-button type="primary" @click="updateHypoth">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 假设预览 -->
    <el-dialog
      title="新建假设"
      :visible.sync="competitionViewDialogVisible"
      width="50%"
      center
      class="editDialog"
    >
      <p>名称</p>
      <el-input placeholder="请输入假设的名称" v-model="competeDataView.hname" disabled></el-input>
      <p>假设</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="competeDataView.hypoth"
        disabled
      >
      </el-input>
      <p>证据</p>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        disabled
        v-model="competeDataView.evidence"
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="competitionViewDialogVisible = !competitionViewDialogVisible">取 消</el-button>
        <el-button type="primary" @click="addSwotSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import HypotyApi from '../service/Hypoty'
import util from '../Utils/util'
export default {
  name: "",
  props: [""],
  data() {
    return {
      competitionAddDialogVisible: false,
      competitionViewDialogVisible:false,
      competitionEditDialogVisible:false,
      hypothAdd:{
        name:'',
        hypoth:'',
        evidence:''
      },
      competeData: [
        {
          name: "假设一",
          createtime: "2022-5-4",
          hypoth: "",
          evidence: "",
          uid: 1,
        },
        {
          name: "假设二",
          createtime: "2022-5-4",
          hypoth: "",
          evidence: "",
          uid: 1,
        },
        {
          name: "假设三",
          createtime: "2022-5-4",
          hypoth: "",
          evidence: "",
          uid: 1,
        },
        {
          name: "假设四",
          createtime: "2022-5-4",
          hypoth: "",
          evidence: "",
          uid: 1,
        },
        {
          name: "假设五",
          createtime: "2022-5-4",
          hypoth: "",
          evidence: "",
          uid: 1,
        },
      ],
      competeDataView: {},
      competeDataEdit: {},
    };
  },

  components: {},
  
  created(){
    this.getAllHypoth()
  },

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
    addcolse(done){
      done()
    },
    // 修改假设
    updateHypoth(){
      HypotyApi.updateHypoth({
        hid:this.competeDataEdit.hid,
        hname:this.competeDataEdit.hname,
        hypoth:this.competeDataEdit.hypoth,
        evidence:this.competeDataEdit.evidence
      }).then(res=>{
        if(res.status==200){
          this.$message({
            message:res.msg,
            type:'success'
          })
        }else{
          this.$message({
            message:res.msg,
            type:'warning'
          })
        }
        this.competitionEditDialogVisible=!this.competitionEditDialogVisible
        this.competeDataEdit={}
      })
    },
    // 新增假设
    AddHypoty(){
      HypotyApi.addHypoty({
        hname:this.hypothAdd.name,
        hypoth:this.hypothAdd.hypoth,
        evidence:this.hypothAdd.evidence
      }).then(res=>{
        if(res.status==200){
          this.$message({
            message:res.msg,
            type:'success'
          })
        }else{
          this.$message({
            message:res.msg,
            type:'warning'
          })
        }
        this.competitionAddDialogVisible=!this.competitionAddDialogVisible
        this.hypothAdd.name='',
        this.hypothAdd.hypoth='',
        this.hypothAdd.evidence=''
      })
    },
    //获取所有假设 
    getAllHypoth(){
      HypotyApi.getAllHypoth()
      .then(res=>{
        this.competeData=res.object
        this.competeData.forEach(e=>{
          e.createtime=util.getYMDHMS(e.createtime)
        })
      })
    },
    // 编辑竞争性假设
    handleEdit(index, row) {
      this.competeDataEdit = row;
      this.competitionEditDialogVisible=!this.competitionEditDialogVisible
    },
    // 预览竞争性假设
    handlePreview(index, row) {
      this.competeDataView = row;
      console.log()
      this.competitionViewDialogVisible=!this.competitionViewDialogVisible
    },
    // 删除竞争性假设
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(()=>{
        HypotyApi.deleteHypoth({
          hid:row.hid
        }).then(res=>{
          if(res.status==200){
            this.$message({
              message:'删除成功',
              type:'success'
            })
            this.getAllHypoth()
          }else{
            this.$message({
              message:res.msg,
              type:'warning'
            })
          }
        })
      }).catch(()=>{
        this.$message({
          type:'info',
          message:'删除已取消'
        })
      })
    },
    competitionAdd() {
      this.competitionAddDialogVisible = !this.competitionAddDialogVisible;
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.competehead {
  display: flex;
  background-color: white;
  height: 60px;
  align-items: center;
  justify-items: flex-end;
  .swotadd {
    margin-left: auto;
    margin-right: 15px;
  }
  p {
    display: flex;
    align-items: center;
    text-align: center;
    margin: 0;
    line-height: 40px;
  }
  .el-input {
    display: flex;
    align-items: center;
    width: 200px;
    height: 40px;
    margin-left: 10px;
    margin-right: 10px;
  }
  .el-button {
    display: flex;
    align-items: center;
    height: 40px;
  }
  .searchBtn {
    margin-left: auto;
  }
  margin-bottom: 15px;
}
</style>