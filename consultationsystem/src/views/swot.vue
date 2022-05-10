<template>
  <div>
    <div class="swothead">
      <p>SWOT名称:</p>
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
      <el-button type="primary" @click="swotAdd" class="swotadd"
        >新建SWOT</el-button
      >
    </div>
    <div>
      <el-table :data="swotData" style="width: 100%">
        <el-table-column label="SWOT名称" width="400" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.swotname }}</span>
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
      <!-- 新建SWOT -->
      <el-dialog
        title="新建SWOT"
        :visible.sync="swotAddDialogVisible"
        width="50%"
        center
        class="editDialog"  
      >
        <p>名称</p>
        <el-input
          v-model="swotAddData.name"
          placeholder="请输入SWOT的名称"
        ></el-input>
        <div class="swotbox">
          <div class="swotboxehead advantege">
            <p>优点</p>
            <el-button @click="advantegeAdd"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotAddData.advantage" :key="index">
              <el-input v-model="swotAddData.advantage[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead disadvantege">
            <p>弱点</p>
            <el-button @click="disadvantageAdd"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotAddData.disadvantage" :key="index">
              <el-input v-model="swotAddData.disadvantage[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead opportunity">
            <p>机会</p>
            <el-button @click="opportunityAdd"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotAddData.opportunity" :key="index">
              <el-input v-model="swotAddData.opportunity[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead threaten">
            <p>威胁</p>
            <el-button @click="threatenAdd"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotAddData.threaten" :key="index">
              <el-input v-model="swotAddData.threaten[index]"></el-input>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addSwotSubmit"
            >确 定</el-button
          >
        </span>
      </el-dialog>
      <!-- 预览SWOT -->
      <el-dialog
        title="预览SWOT"
        :visible.sync="swotPreviewDialogVisible"
        width="50%"
        center
        class="editDialog"
      >
        <p>名称</p>
        <el-input
          v-model="swotPreviewObj.swotname"
          placeholder="请输入SWOT的名称"
          disabled
        ></el-input>
        <div class="swotbox">
          <div class="swotboxehead advantege">
            <p>优点</p>
          </div>
          <div class="content">
            <div v-for="(item, index) in swotPreviewObj.advantege" :key="index">
              <el-input v-model="swotPreviewObj.advantege[index]" disabled></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead disadvantege">
            <p>弱点</p>
          </div>
          <div class="content">
            <div
              v-for="(item, index) in swotPreviewObj.disadvantege"
              :key="index"
            >
              <el-input v-model="swotPreviewObj.disadvantege[index]" disabled></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead opportunity">
            <p>机会</p>
          </div>
          <div class="content">
            <div
              v-for="(item, index) in swotPreviewObj.opportunity"
              :key="index"
            >
              <el-input v-model="swotPreviewObj.opportunity[index]" disabled></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead threaten">
            <p>威胁</p>
          </div>
          <div class="content">
            <div v-for="(item, index) in swotPreviewObj.threaten" :key="index">
              <el-input v-model="swotPreviewObj.threaten[index]" disabled></el-input>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="centerDialogVisible = false"
            >确 定</el-button
          >
        </span>
      </el-dialog>
      <!-- 修改SWOT -->
      <el-dialog
        title="修改SWOT"
        :visible.sync="swotEditDialogVisible"
        width="50%"
        center
        class="editDialog"
      >
        <p>名称</p>
        <el-input
          v-model="swotEdit.name"
          placeholder="请输入SWOT的名称"
        ></el-input>
        <div class="swotbox">
          <div class="swotboxehead advantege">
            <p>优点</p>
            <el-button @click="advantegeEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.advantage" :key="index">
              <el-input v-model="swotEdit.advantage[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead disadvantege">
            <p>弱点</p>
            <el-button @click="disadvantageEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.disadvantage" :key="index">
              <el-input v-model="swotEdit.disadvantage[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead opportunity">
            <p>机会</p>
            <el-button @click="opportunityEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.opportunity" :key="index">
              <el-input v-model="swotEdit.opportunity[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead threaten">
            <p>威胁</p>
            <el-button @click="threatenEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加优势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.threaten" :key="index">
              <el-input v-model="swotEdit.threaten[index]"></el-input>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="centerDialogVisible = false"
            >确 定</el-button
          >
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import SwotApi from '../service/Swot'
import Utils from '../Utils/util'
export default {
  name: "",
  props: [""],
  data() {
    return {
      serachdate: "",
      swotPreviewDialogVisible: false,
      swotPreviewObj: null,
      swotData: [
        {
          id: "1",
          date: "2016-05-02",
          name: "开展软件业务的优缺点",
          advantege: ["时间早"],
          disadvantege: [],
          opportunity: [],
          threaten: [],
        },
      ],
      swotEditDate: {},
      swotEditDialogVisible: false,
      swotAddDialogVisible: false,
      swotEdit: {
        name: null,
        advantage: [],
        disadvantage: [],
        opportunity: [],
        threaten: [],
      },
      swotAddData: {
        name: null,
        advantage: [],
        disadvantage: [],
        opportunity: [],
        threaten: [],
      },
    };
  },

  components: {},

  created() {
    // this.swotPreviewObj=th
    console.log("getUsersAllSwot")
    this.getUsersAllSwot()
  },

  computed: {},

  beforeMount() {},

  mounted() {
    this.swotPreviewObj = this.swotData[0];
  },

  methods: {
    addSwotSubmit(){
      this.swotAddDialogVisible = !this.swotAddDialogVisible;
      console.log(this.swotAddData)
      SwotApi.addSwot({
        name:this.swotAddData.name,
        advantege:this.swotAddData.advantage,
        disadvantege:this.swotAddData.disadvantage,
        opportunity:this.swotAddData.opportunity,
        threaten:this.swotAddData.threaten
      }).then((res)=>{
        console.log(res)
        if(res.status==200){
          this.$message({
            message:res.msg,
            type:'success'
          })
        }
          this.swotAddData.name=null;
          this.swotAddData.advantage=[]
          this.swotAddData.disadvantage=[]
          this.swotAddData.opportunity=[]
          this.swotAddData.threaten=[]
      })
    },
    getUsersAllSwot(){
      SwotApi.getUsersAllSwot()
      .then((res)=>{
        res.object.forEach((e)=>{
          e.createtime=Utils.getYMDHMS(e.createtime)
        })
        this.swotData=res.object
      })
      // console.log(this.swotData)
    },
    swotAdd() {
      this.swotAddDialogVisible = !this.swotAddDialogVisible;
    },
    advantegeAdd() {
      this.swotAddData.advantage.push("");
    },
    disadvantageAdd() {
      this.swotAddData.disadvantage.push("");
    },
    opportunityAdd() {
      this.swotAddData.opportunity.push("");
    },
    threatenAdd() {
      this.swotAddData.threaten.push("");
    },

    // SWOT编辑的方法
    advantegeEdit() {
      this.swotEdit.advantage.push("");
    },
    disadvantageEdit() {
      this.swotEdit.disadvantage.push("");
    },
    opportunityEdit() {
      this.swotEdit.opportunity.push("");
    },
    threatenEdit() {
      this.swotEdit.threaten.push("");
    },

    // 

    handleEdit(index, row) {
      this.swotEditDialogVisible = !this.swotEditDialogVisible;
      this.swotEdit=this.swotData[index]
    },

    handlePreview(index, row) {
      this.swotPreviewDialogVisible = !this.swotPreviewDialogVisible;
      this.swotPreviewObj = this.swotData[index];
      console.log(this.swotPreviewObj)
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.swothead {
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
.swotbox {
  // display: flex;
  .swotboxehead {
    margin-top: 5px;
    display: flex;
    color: white;
    padding: 0;
    width: 100%;
    justify-items: flex-end;
    .el-button {
      border: 0;
      color: white;
      margin-left: auto;
    }
  }
  .advantege {
    background-color: rgb(2, 146, 64);
    .el-button {
      background-color: rgb(2, 146, 64);
    }
  }
  .disadvantege {
    background-color: rgb(189, 91, 50);
    .el-button {
      background-color: rgb(189, 91, 50);
    }
  }
  .opportunity {
    background-color: rgb(20, 51, 104);
    .el-button {
      background-color: rgb(20, 51, 104);
    }
  }
  .threaten {
    background-color: red;
    .el-button {
      background-color: red;
    }
  }
}
p {
  text-align: center;
}
.content {
  clear: both;
}
.el-table{
  border-radius: 10px;
}
</style>