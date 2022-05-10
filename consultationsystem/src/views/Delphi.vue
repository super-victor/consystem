<template>
  <div>
    <div class="head">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <!-- 问卷列表 -->
        <el-tab-pane label="问卷列表" name="first">
          <div class="listhead">
            <div class="inputname"><p>问卷名称:</p></div>
            <el-input
              v-model="input"
              placeholder="请输入内容"
              class="listinput"
            ></el-input>
            <el-button type="primary" size="medium">搜索</el-button>
            <el-button type="primary" size="medium" @click="QuestionnaireAdd"
              >新增</el-button
            >
          </div>
          <el-table :data="QuestionNaires" style="width: 100%">
            <el-table-column
              prop="createtime"
              label="创建日期"
              width="400"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="dpname"
              label="问卷名称"
              width="400"
              align="center"
            >
            </el-table-column>
            <el-table-column label="操作" width="400" align="center">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
                  type="primary"
                  >编辑</el-button
                >
                <el-button
                  size="mini"
                  @click="QuestionnairePreview(scope.$index, scope.row)"
                  type="success"
                  >预览</el-button
                >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <!-- 问卷问题库 -->
        <el-tab-pane label="问题库" name="second">
          <div class="listhead">
            <div class="inputname"><p>问题名称:</p></div>
            <el-input
              v-model="input"
              placeholder="请输入内容"
              class="listinput"
            ></el-input>
            <el-button type="primary" size="medium">搜索</el-button>
            <el-button type="primary" size="medium" @click="questionAdd"
              >添加</el-button
            >
          </div>
          <el-table :data="wenjuan" style="width: 100%">
            <el-table-column
              prop="createtime"
              label="创建日期"
              width="300"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="dname"
              label="问题名称"
              width="300"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="dtype"
              label="类型"
              width="300"
              align="center"
            >
            </el-table-column>
            <el-table-column label="操作" width="00" align="center">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="questionhandleEdit(scope.$index, scope.row)"
                  type="primary"
                  >编辑</el-button
                >
                <el-button
                  size="mini"
                  @click="Previewhandle(scope.$index, scope.row)"
                  type="success"
                  >预览</el-button
                >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleQuestionDelete(scope.$index, scope.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table></el-tab-pane
        >
      </el-tabs>
      <!-- 新增问卷 -->
      <el-dialog
        title="新增问卷"
        :visible.sync="QuestionnaireAddVisible"
        width="30%"
        :before-close="handleClose"
        class="questionpreview"
      >
        <p style="font-size:20px  ">问卷名称</p>
        <el-input v-model="questionnairedpname" class="qname"></el-input>
        <el-checkbox-group v-model="didlist">
          <div v-for="(item, index) in wenjuan" :key="index" class="cehckitem">
            <el-checkbox :label="item.did" 
              >{{ item.dname }}({{ item.dtype }})
              </el-checkbox>
          </div>
        </el-checkbox-group>
        <!-- <div v-for="(item,index) in wenjuan" :key="index">
          
        </div> -->
        <el-button type="primary" @click="QuestionnaireAddBtn">新增</el-button>
      </el-dialog>
      <!-- 问卷预览 -->
      <el-dialog
        :title="QuestionnairePreviewObj.dpname"
        :visible.sync="QuestionnairepreviewVisible"
        :before-close="handleClose"
        class="questionpreview"
      >
        <div>
          <div
            v-for="(item, index) in QuestionnairePreviewObj.delphis"
            :key="index"
          >
            <p>{{ index + 1 }}.{{ item.dname }}</p>
            <div v-if="checkType(item.type, '1')">
              <!-- <div v-for="(item,index) in QuestionnairePreviewObj.delphis" :key="index">
                {{item}}
              </div> -->
              <!-- <div v-for="(item,index)in QuestionnairePreviewObj" :key="index">
                {{item}}
              </div> -->
              <div class="chcekboxs">
                <div v-for="(item1, index1) in item.anwser" :key="index1">
                  <el-radio
                    :label="
                      QuestionnairePreviewObj.delphis[index].anwser[index1]
                    "
                    >{{ String.fromCharCode(0x41 + index) + "."
                    }}{{ item1 }}</el-radio
                  >
                </div>
              </div>
            </div>
            <div v-if="checkType(item.type, '2')">
              <div class="chcekboxs">
                <div v-for="(item1, index1) in item.anwser" :key="index1">
                  <el-checkbox
                    :label="
                      QuestionnairePreviewObj.delphis[index].anwser[index1]
                    "
                  >
                    {{ String.fromCharCode(0x41 + index1) + "." }} {{ item1 }}
                  </el-checkbox>
                </div>
              </div>
            </div>
            <div v-if="checkType(item.type, '3')">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="textarea"
              >
              </el-input>
            </div>
          </div>
        </div>
      </el-dialog>

      <!-- 新增问题 -->
      <el-dialog
        title="新增问题"
        :visible.sync="questionAdddialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <p>问题名称</p>
        <el-input v-model="delphiadd.dname" placeholder="请输入内容"></el-input>
        <p>问题类型</p>
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>

        <div v-if="isRadio || isSelection">
          <p>填写选项</p>
          <el-input
            v-model="delphiadd.delphia"
            placeholder="请输入内容"
          ></el-input>
          <el-input
            v-model="delphiadd.delphib"
            placeholder="请输入内容"
          ></el-input>
          <el-input
            v-model="delphiadd.delphic"
            placeholder="请输入内容"
          ></el-input>
          <el-input
            v-model="delphiadd.delphid"
            placeholder="请输入内容"
          ></el-input>
        </div>
        <div>
          <el-button type="primary" class="delphiadd-btn" @click="delphiAdd"
            >添加</el-button
          >
        </div>
      </el-dialog>
      <!-- 问题预览 -->
      <el-dialog
        title="问题预览"
        :visible.sync="PreviewdialogVisible"
        width="30%"
        :before-close="handleClose"
        class="questionpreview"
      >
        <p>问题名称</p>
        <el-input
          v-model="previewobj.dname"
          disabled
          placeholder="请输入内容"
        ></el-input>
        <div v-if="checkType(previewobj.type, '1')">
          <p>问题选项</p>
          <div class="radiogroup">
            <el-radio-group>
              <div v-for="(item, index) in previewobj.anwser" :key="index">
                <el-radio :label="index" disabled
                  >{{ String.fromCharCode(0x41 + index) + "."
                  }}{{ item }}</el-radio
                >
              </div>
            </el-radio-group>
          </div>
        </div>
        <div v-if="checkType(previewobj.type, '2')">
          <p>问题选项</p>
          <div class="chcekboxs">
            <div v-for="(item, index) in previewobj.anwser" :key="index">
              <el-checkbox disabled :label="index">
                {{ String.fromCharCode(0x41 + index) + "." }} {{ item }}
              </el-checkbox>
            </div>
          </div>
        </div>
        <div v-if="checkType(previewobj.type, '3')">
          <p>回答</p>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="textarea"
            disabled
          >
          </el-input>
        </div>
      </el-dialog>
      <!-- 问题编辑-->
      <el-dialog
        title="编辑"
        :visible.sync="questionEdit"
        width="30%"
        :before-close="handleClose"
        class="questionpreview"
      >
        <p>问题名称</p>
        <el-input
          v-model="delphiedit.dname"
          placeholder="请输入内容"
        ></el-input>
        <div v-if="checkType(delphiedit.type, '1')">
          <p>问题选项</p>
          <div class="chcekboxs">
            <div v-for="(item, index) in delphiedit.anwser" :key="index">
              <p>{{ String.fromCharCode(0x41 + index) + "." }}</p>
              <el-input v-if="index==0"
                v-model="delphiedit.delphia"
              ></el-input>
              <el-input v-if="index==1"
                v-model="delphiedit.delphib"
              ></el-input>
              <el-input v-if="index==2"
                v-model="delphiedit.delphic"
              ></el-input>
              <el-input v-if="index==3"
                v-model="delphiedit.delphid"
              ></el-input>
            </div>
          </div>
        </div>
        <div v-if="checkType(delphiedit.type, '2')">
          <p>问题选项</p>
          <div class="chcekboxs">
            <div v-for="(item, index) in delphiedit.anwser" :key="index">
              <p>{{ String.fromCharCode(0x41 + index) + "." }}</p>
              <el-input v-if="index==0"
                v-model="delphiedit.delphia"
              ></el-input>
              <el-input v-if="index==1"
                v-model="delphiedit.delphib"
              ></el-input>
              <el-input v-if="index==2"
                v-model="delphiedit.delphic"
              ></el-input>
              <el-input v-if="index==3"
                v-model="delphiedit.delphid"
              ></el-input>
            </div>
          </div>
        </div>
        <div v-if="checkType(delphiedit.type, '3')">
          <p>回答</p>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="textarea"
            disabled
          >
          </el-input>
        </div>
        <el-button type="primary" @click="modifySubmit">修改</el-button>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import DelphiApi from "../service/Delphi";
import utils from "../Utils/util";
export default {
  name: "",
  props: [""],
  data() {
    return {
      questionnairedpname:'',
      options: [
        {
          value: "1",
          label: "单选题",
        },
        {
          value: "2",
          label: "多选",
        },
        {
          value: "3",
          label: "简答题",
        },
      ],
      value: "",
      radio: "1",
      activeName: "first",
      //    问卷列表
      QuestionNaires: [
        {
          date: "2016-05-02",
          name: "关于协同办公需求1",
          wenjuan: [
            {
              name: "你对这个的看法",
              type: "1",
              date: "2016-05-02",
              anwser: ["好东西", "确实", "可以", "嗯"],
            },
            {
              name: "你对这个的看法",
              type: "2",
              date: "2016-05-02",
              anwser: ["好东西", "确实", "可以", "嗯"],
            },
            {
              name: "你对这个的看法",
              type: "3",
              date: "2016-05-02",
              anwser: ["好东西", "确实", "可以", "嗯"],
            },
          ],
        },
      ],
      questionAdddialogVisible: false,
      QuestionnairepreviewVisible: false,
      PreviewdialogVisible: false,
      questionEdit: false,
      //   问卷问题
      wenjuan: [
        {
          name: "你对这个的看法",
          type: "1",
          date: "2016-05-02",
          anwser: ["好东西", "确实", "可以", "嗯"],
        },
        {
          name: "你对这个的看法",
          type: "2",
          date: "2016-05-02",
          anwser: ["好东西", "确实", "可以", "嗯"],
        },
        {
          name: "你对这个的看法",
          type: "3",
          date: "2016-05-02",
          anwser: ["好东西", "确实", "可以", "嗯"],
        },
      ],
      previewobj: null,
      delphiedit: null,
      QuestionnairePreviewObj: null,
      QuestionnaireAddVisible: false,
      didlist: [],
      delphiadd: {
        type: "",
        dname: "",
        delphia: "",
        delphib: "",
        delphic: "",
        delphid: "",
        type: this.value,
      },
    };
  },

  components: {},

  computed: {
    ...mapState(["userInfo"]),
    isRadio() {
      return this.value == "1";
    },
    isSelection() {
      return this.value == "2";
    },
    isbriefAnswer() {
      return this.value == "3";
    },
  },

  beforeMount() {},

  created() {
    this.previewobj = this.wenjuan[0];
    this.delphiedit = this.wenjuan[0];
    this.QuestionnairePreviewObj = this.QuestionNaires[0];
    this.getAllDelphi();
    this.getAllQuestionNaire()
  },

  mounted() {},


  methods: {

    handleQuestionDelete(index,row){
        DelphiApi.deleteDelphi({
          did:row.did
        }).then(res=>{
          if(res.status==400){
          this.$message({
            message:res.msg,
            type:'warn'
          })
        }
        if(res.status==200){
          this.$message({
            message:res.msg,
            type:"success"
          })
          this.getAllDelphi()
        }
        })
    },
    modifySubmit(){
      console.log(this.delphiedit)
      DelphiApi.DelphiModify(
        {
          dname: this.delphiedit.dname,
          type: this.delphiedit.type,
          delphia: this.delphiedit.delphia,
          delphib: this.delphiedit.delphib,
          delphic: this.delphiedit.delphic,
          delphid: this.delphiedit.delphid,
          did:this.delphiedit.did
        }
      ).then(res=>{
        if(res.status==400){
          this.$message({
            message:res.msg,
            type:'warn'
          })
        }
        if(res.status==200){
          this.$message({
            message:res.msg,
            type:"success"
          })
          this.getAllDelphi()
        }
      })
      this.questionEdit=!this.questionEdit
    },
    QuestionnaireAddBtn(){
        // alert(this.didlist)
        DelphiApi.addQuestionNaire({
          dpname:this.questionnairedpname,
          dids:this.didlist
        }).then(res=>{
          // alert(res.msg)
          this.$message({
            message:res.msg,
            type:'success'
          })
        })
    },

    getAllQuestionNaire(){
      DelphiApi.getAllQuestionNaire()
      .then((res)=>{
        if(res.status==200){
          console.log(res)
          this.QuestionNaires=res.object
          this.QuestionNaires.forEach((element)=>{
            element.createtime=utils.getYMDHMS(element.createtime)
              // element.delphis.anwser=[]
              element.delphis.forEach((e=>{
                  e.anwser=[]
                  e.anwser.push(e.delphia)
                  e.anwser.push(e.delphib)
                  e.anwser.push(e.delphic)
                  e.anwser.push(e.delphid)
              }))
          })
          this.QuestionnairePreviewObj = this.QuestionNaires[0];

          console.log("deerfer")
          console.log(this.QuestionNaires)
          console.log("resobject")
          console.log(res)
        }
      })
    },
    delphiAdd() {
      // alert(this.delphiadd.dname)
      // console.log(this.value)
      this.delphiadd.type = this.value;
      console.log(this.delphiadd);
      DelphiApi.addDelphi({
        dname: this.delphiadd.dname,
        type: this.delphiadd.type,
        delphia: this.delphiadd.delphia,
        delphib: this.delphiadd.delphib,
        delphic: this.delphiadd.delphic,
        delphid: this.delphiadd.delphid,
      }).then((res) => {
        if (res.status == 200) {
          this.$message({
            message: res.msg,
            type: "success",
          });
          this.getAllDelphi();
          this.questionAdddialogVisible = !this.questionAdddialogVisible;
          (this.delphiadd.dname = ""),
            (this.delphiadd.type = ""),
            (this.delphiadd.delphia = ""),
            (this.delphiadd.delphib = ""),
            (this.delphiadd.delphic = ""),
            (this.delphiadd.delphid = "");
        }
      });
    },
    // 获取所有的Delphi信息
    getAllDelphi() {
      DelphiApi.getAllDelphi().then((res) => {
        if (res.status == 200) {
          console.log(res);
          this.wenjuan = res.object;
          this.wenjuan.forEach((element) => {
            element.dtype = this.handleType(element.type);
            element.createtime = utils.getYMDHMS(element.createtime);
            element.anwser = [];
            element.anwser.push(element.delphia);
            element.anwser.push(element.delphib);
            element.anwser.push(element.delphic);
            element.anwser.push(element.delphid);
          });
          // console.log("wenjuan");
          // console.log(this.wenjuan);
        }
      });
    },
    //新增问卷
    QuestionnaireAdd() {
      this.QuestionnaireAddVisible = !this.QuestionnaireAddVisible;
    },
    handleType(s) {
      if (s == "1") {
        return "单选";
      } else if (s == "2") {
        return "多选";
      } else {
        return "简答";
      }
    },
    questionAdd() {
      this.questionAdddialogVisible = !this.questionAdddialogVisible;
    },
    Previewhandle(index, row) {
      //   alert(row);
      this.previewobj = row;
      console.log(this.previewobj);
      this.PreviewdialogVisible = !this.PreviewdialogVisible;
    },
    questionhandleEdit(index, row) {
      this.questionEdit = !this.questionEdit;
      this.delphiedit = row;
    },
    QuestionnairePreview(index, row) {
      this.QuestionnairepreviewVisible = !this.QuestionnairepreviewVisible;
      this.QuestionnairePreviewObj=this.QuestionNaires[index]
    },
    checkType(s, m) {
      if (s == m) {
        return true;
      } else {
        return false;
      }
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.head {
  width: 95%;
  .listhead {
    .inputname {
      float: left;
      height: 40px;
      // line-height: 40px;
      font-size: 20px;
      margin-top: 0;
      margin-right: 5px;
      margin-bottom: 0;
      p {
        margin: 0;
        margin-top: 5px;
        margin-left: 5px;
        text-align: center;
      }
    }
    .listinput {
      width: 200px;
      float: left;
      margin-bottom: 5px;
    }
    .el-button {
      float: left;
      margin-left: 20px;
      height: 40px;
    }
  }
}
/deep/ .el-tabs__nav-scroll {
  background-color: white;
  height: 50px;
}
.el-dialog {
  height: 50px;
}
.el-table{
  border-radius: 10px;
}
.el-radio {
  // float: left;
  display: flex;
  justify-items: left;
  justify-content: left;
}
.questionpreview {
  // display: flex;
  // justify-items: left;
  // justify-content: left;
  overflow-y: scroll;
  .radiogroup {
    display: flex;
    justify-content: left;
    justify-items: left;
  }
  .chcekboxs > div {
    display: flex;
    justify-content: left;
    justify-items: left;
  }
  p {
    display: flex;
    // justify-content: left;
    justify-items: left;
  }
  // .el-radio-group{
  //     // display: flex;
  //     // justify-items: left;
  //     div{
  //         margin: 2px auto;
  //     }
  // }
}
.delphiadd-btn {
  margin-top: 15px;
}
.el-checkbox{
    display: flex;
    justify-items: left;
}
.qname{
  margin-bottom: 15px;
}
.el-tabs__nav-scroll{
  border-radius: 10px;
}
.el-tabs__nav{
  margin-left: 5px;
}
</style>