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
      <el-table :data="historymeeting" style="width: 100%" height="580px">
        <el-table-column label="会议名称" align="center" width="240">
          <template slot-scope="scope">
            <p>{{ scope.row.name }}</p>
          </template>
        </el-table-column>
        <el-table-column label="开始时间" align="center" width="220">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="结束时间" align="center" width="220">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发起人" align="center" width="200">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="320" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="info"
              @click="showDetails(scope.$index, scope.row)"
              >详情</el-button
            >

            <el-button
              size="mini"
              type="primary"
              @click="showSummary(scope.$index, scope.row)"
              >会议纪要</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
            <el-button size="mini"
              type="success" @click="showToolDetails(scope.$index, scope.row)">工具</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 会议纪要 -->
    <el-dialog
      title="提示"
      :visible.sync="SummarydialogVisible"
      width="81%"
      :before-close="handleClose"
    >
      <quill-editor
        v-if="disable"
        v-model="sunmmary.content"
        :class="[disabled ? 'is-disabled' : '']"
        :options="editorOption"
        ref="myQuillEditor"
        class="myQuillEditor"
        @focus="onMouseover($event)"
        @blur="onMouseleave"
        style="height: 350px; width: 96%"
      ></quill-editor>
      <!-- <div v-else v-html="sunmmary.content"></div> -->
      <div v-else class="ql-container ql-snow">
        <div class="ql-editor" v-html="sunmmary.content"></div>
      </div>
      <div class="foot">
        <span slot="footer" class="dialog-footer">
          <el-button
            type="primary"
            @click="SubmitSunmmary"
            v-if="disable && modifyDisable"
            >上 传</el-button
          >
          <el-button
            type="primary"
            @click="ModifySunmmary"
            v-if="!disable && modifyDisable"
            >修改</el-button
          >
        </span>
      </div>
    </el-dialog>

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

<!-- 工具使用情况 -->
    <el-dialog
      title="工具"
      :visible.sync="toolDetailsdialogVisible"
      width="50%"
      :before-close="handleClose"
    >
    <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="德尔菲" name="first">
      <delphi-details :mid="toolmid"></delphi-details>
    </el-tab-pane>
    <el-tab-pane label="SWOT" name="second">SWOT</el-tab-pane>
  </el-tabs>
    </el-dialog>

  </div>
</template>

<script>
import { quillEditor } from "vue-quill-editor";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import * as Quill from "quill"; // 引入编辑器
import ImageResize from "quill-image-resize-module";
Quill.register("modules/imageResize", ImageResize);
import MeetingManageApi from "../../service/MeetingManage";
import { mapState } from "vuex";

import Utils from '../../Utils/util'
import DelphiDetails from '../ToolDetails/DelphiDetails.vue';
export default {
  name: "",
  props: ["historymeeting"],
  data() {
    return {
      disable: false,
      user: "Tom",
      modifyDisable: true,
      sunmmary: {
        content: "",
        mid: null,
      },
      historymeeting: null,
      SummarydialogVisible: false,
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
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
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
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
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
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
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
          Initiator: "Tom",
          address: "上海市普陀区金沙江路 1518 弄",
        },
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
      editorOption: {
        placeholder: "请在这里输入",
        modules: {
          toolbar: {
            container: [
              ["bold", "italic", "underline", "strike"], //加粗，斜体，下划线，删除线
              ["blockquote", "code-block"], //引用，代码块
              [{ header: 1 }, { header: 2 }], // 标题，键值对的形式；1、2表示字体大小
              [{ list: "ordered" }, { list: "bullet" }], //列表
              [{ script: "sub" }, { script: "super" }], // 上下标
              [{ indent: "-1" }, { indent: "+1" }], // 缩进
              [{ direction: "rtl" }], // 文本方向
              [{ size: ["small", false, "large", "huge"] }], // 字体大小
              [{ header: [1, 2, 3, 4, 5, 6, false] }], //几级标题
              [{ color: [] }, { background: [] }], // 字体颜色，字体背景颜色
              [{ font: [] }], //字体
              [{ align: [] }], //对齐方式
              ["clean"], //清除字体样式
              ["image", "video"], //上传图片、上传视频
            ],
          },

          imageResize: {
            displayStyles: {
              backgroundColor: "black",
              border: "none",
              color: "white",
            },
            modules: ["Resize", "DisplaySize", "Toolbar"],
          },
        },
      },
      meetDetailsObj: {},
      meetDetailsdialogVisible: false,
      toolDetailsdialogVisible:false,
      toolmid:null,
    };
  },

  components: {
    quillEditor,
    DelphiDetails,
  },

  created() {
    this.getAllMeetings();
  },
  computed: {
    ...mapState(["userInfo"]),
  },

  beforeMount() {},

  mounted() {},

  methods: {
    // onMouseover(event){
    //       if(this.disable){
    //           this.$refs.myQuillEditor.quill.enable(false);
    //           // event.enable(false)

    //       }
    //   },
    //   /**
    //    * @method 鼠标离开
    //   */
    //   onMouseleave(){
    //       if(this.disable){
    //           this.$refs.myQuillEditor.quill.enable(true);
    //       }
    //   },
    showToolDetails(index,row){
      this.toolDetailsdialogVisible=!this.toolDetailsdialogVisible
      this.toolmid=row.mid
    },
    showDetails(index, row) {
      this.meetDetailsdialogVisible = !this.meetDetailsdialogVisible;
      this.meetDetailsObj = row
    },
    ModifySunmmary() {
      this.disable = !this.disable;
    },
    showSummary(index, row) {
      this.sunmmary.mid = row.mid;
      // console.log(row)
      if (this.userInfo.uid == row.uid) {
        this.modifyDisable = true;
      } else {
        this.modifyDisable = false;
      }
      // console.log(row)
      MeetingManageApi.findSunmmary({
        mid: row.mid,
      }).then((res) => {
        this.SummarydialogVisible = !this.SummarydialogVisible;
        // console.log(res)
        if (res.status == 200) {
          this.sunmmary.content = res.object.content;
        } else {
          this.disable = !this.disable;
        }
      });
    },
    SubmitSunmmary() {
      // console.log(this.sunmmary)
      MeetingManageApi.addSunmmary({
        mid: this.sunmmary.mid,
        content: this.sunmmary.content,
      }).then((res) => {
        this.$message({
          message: res.msg,
          type: "success",
        });
        this.SummarydialogVisible = !this.SummarydialogVisible;
        this.disable = !this.disable;
      });
    },
    handleClose(done) {
      // alert("close")
      this.sunmmary.content = "";
      this.sunmmary.mid = null;
      if (this.disable) {
        this.disable = !this.disable;
      }
      done();
    },
    isInitiator(user) {
      if (user == this.user) {
        return true;
      } else {
        return false;
      }
    },
    getAllMeetings() {
      MeetingManageApi.getMyMeeting().then((res) => {
        this.historymeeting = res.object.historymeeting;
        // this.futuremeeting=res.object.futuremeeting
        // this.historymeeting=res.object.historymeeting
        // console.log("historymeeting");
        // console.log(this.historymeeting);
        this.historymeeting.forEach(element => {
          element.endtime=Utils.getYMDHMS(element.endtime)
          element.starttime=Utils.getYMDHMS(element.starttime)
        });
      });
    },
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
.el-dialog_wrapper {
  height: 700px;
}
.el-table {
  border-radius: 10px;
  margin-bottom: 15px;
}
.foot {
  margin-top: 50px;
}
.myQuillEditor {
  contenteditable: true;
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