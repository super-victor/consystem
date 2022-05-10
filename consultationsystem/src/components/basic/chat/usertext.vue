<template>
  <div id="uesrtext">
    <div>
      <el-popover
        placement="top-start"
        width="400"
        trigger="click"
        class="emoBox"
      >
        <div class="emotionList">
          <a
            href="javascript:void(0);"
            @click="getEmo(index)"
            v-for="(item, index) in faceList"
            :key="index"
            class="emotionItem"
            >{{ item }}</a
          >
          <!-- <a href="#" v-for="(item,index) in faceList" :key="index">{{item}}</a> -->
        </div>
        <el-button
          id="emojiBtn"
          class="emotionSelect"
          slot="reference"
          size="big"
        >
          <i class="el-icon-s-opportunity" aria-hidden="true"></i>
        </el-button>
      </el-popover>

      <el-upload
        class="upload-btn"
        :before-upload="beforeAvatarUpload"
        :on-success="imgSuccess"
        :on-error="imgError"
        :show-file-list="false"
        :on-change="onChangeFile"
        accept=".jpg,.jpeg,.png,.JPG,JPEG,.PNG,.gif,.GIF"
      >
        <el-button id="uploadImgBtn" size="big">
          <i class="el-icon-picture-outline"></i>
        </el-button>
      </el-upload>
      <div class="option">
        <!-- <el-button id="moreoption" size="big" @click="showTools">
          <i class="el-icon-circle-plus-outline"></i>
        </el-button> -->
        <el-popover
          placement="top-start"
          width="20"
          height="40px"
          trigger="click"
        >
          <el-button type="text" @click="getDelphis()">德尔菲</el-button>
          <el-button type="text" @click="getSwot()">SWOT</el-button>
          <el-button type="text" @click="getHypoth()"> 竞争性假设</el-button>
          <el-upload
            class="upload-btn"
            :on-success="imgSuccess"
            :on-error="imgError"
            :show-file-list="false"
            :on-change="onUploadFile"
            accept=".jpg,.jpeg,.png,.JPG,JPEG,.PNG,.gif,.GIF"
          >
            <el-button id="uploadImgBtn" type="text" size="big">
              文件上传
              <!-- <i class="el-icon-picture-outline"></i> -->
            </el-button>
          </el-upload>
          <!-- <el-upload
            class="upload-btn"
            :on-change="onUploadFile"
            :show-file-list="false"
            :auto-upload="false"
          >
            <el-button size="big">
              <el-button type="text">上传文件</el-button>
            </el-button>
          </el-upload> -->
          <el-button id="moreoption" size="big" slot="reference">
            <i class="el-icon-circle-plus-outline"></i>
          </el-button>
        </el-popover>
      </div>
    </div>

    <textarea
      id="textarea"
      placeholder="按 Ctrl + Enter 发送"
      v-model="content"
      @keydown="addMessage"
    >
    </textarea>
    <el-button
      id="sendBtn"
      type="primary"
      size="mini"
      @click="addMessageByClick"
      >发送(S)</el-button
    >

    <!-- 德尔菲 -->
    <el-dialog title="德尔菲问卷" :visible.sync="DelphiDatadialogTableVisible">
      <el-table :data="DelphiData" style="width: 100%">
        <el-table-column
          prop="createtime"
          label="创建日期"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="dpname"
          label="问卷名称"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="useDelphiQuestionNaire(scope.$index, scope.row)"
              type="primary"
              >使用</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!--  SWOT选择 -->
    <el-dialog title="SWOT问卷" :visible.sync="SwotDatadialogTableVisible">
      <el-table :data="swotData" style="width: 100%">
        <el-table-column
          prop="createtime"
          label="创建日期"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="swotname"
          label="问卷名称"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="useSwot(scope.$index, scope.row)"
              type="primary"
              >使用</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 竞争性假设 -->
    <el-dialog title="竞争性假设" :visible.sync="HypothDatadialogTableVisible">
      <el-table :data="HypothData" style="width: 100%">
        <el-table-column
          prop="createtime"
          label="创建日期"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="hname"
          label="问卷名称"
          width="250"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="useHypoth(scope.$index, scope.row)"
              type="primary"
              >使用</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from "vuex";
import appData from "../../../Utils/emoji.json";
import DelphiApi from "../../../service/Delphi";
import HypotyApi from '../../../service/Hypoty'
import Utils from "../../../Utils/util";
import fileApi from "../../../service/FileService";
import SwotApi from "../../../service/Swot";
export default {
  name: "",
  props: ["mid"],
  data() {
    return {
      showtoolsVisible: false,
      DelphiDatadialogTableVisible: false,
      SwotDatadialogTableVisible: false,
      HypothDatadialogTableVisible: false,
      faceList: [], //表情包数据
      content: "",
      datalist: ["das", "dax", "dac"],
      DelphiData: [],
      swotData: [],
      HypothData:[],
      imageFile: null,
      uploaFile: null,
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
    };
  },

  created() {
    for (let i in appData) {
      //读取json文件保存数据给数组
      this.faceList.push(appData[i].char);
      // console.log(this.faceList)
    }
  },

  components: {},

  computed: {
    ...mapState(["currentSession", "userInfo", "userToken"]),
  },

  beforeMount() {},

  mounted() {
    // for (let i in appData) {
    //   //读取json文件保存数据给数组
    //   this.faceList.push(appData[i].char);
    //   console.log(this.faceList)
    // }
    // console.log(this.faceList)
  },

  methods: {

    // 使用竞争性假设
    useHypoth(index,row){
      let msgObj = new Object();
      // let obj = new Object();

      msgObj.content = JSON.stringify(row);
      console.log(row);
      msgObj.messageTypeId = 6;
      msgObj.fromId = this.userInfo.uid;
      msgObj.mid = this.mid;
      this.$store.state.stomp.send(
        "/ws/groupChat/" + this.mid,
        {
          token: this.userToken,
        },
        JSON.stringify(msgObj)
      );
      this.HypothDatadialogTableVisible=!this.HypothDatadialogTableVisible
    },
    // 竞争性假设
    getHypoth(){
      HypotyApi.getAllHypoth().
      then(res=>{
          if(res.status==200){
            this.HypothData=res.object
            this.HypothData.forEach(e=>{
              e.createtime=Utils.getYMDHMS(e.createtime)
            })
            this.HypothDatadialogTableVisible=!this.HypothDatadialogTableVisible
            console.log(this.HypothData)
          }
      })
    },
    // 上传文件
    onUploadFile(file) {
      // console.log("上传文件")
      this.uploaFile = file.raw;
      console.log(this.uploaFile);
      fileApi
        .uploadFile({
          file: this.uploaFile,
        })
        .then((res) => {
          if (res.status == 200) {
            let msgObj = new Object();
            let fobj = new Object();
            fobj.name = file.name;
            fobj.fid = res.object.fid;
            console.log(fobj);
            msgObj.content = JSON.stringify(fobj);
            msgObj.messageTypeId = 7;
            msgObj.mid = this.mid;
            this.$store.state.stomp.send(
              "/ws/groupChat/" + this.mid,
              {
                token: this.userToken,
              },
              JSON.stringify(msgObj)
            );
          }
        });
    },

    beforeAvatarUpload(file) {
      //判断图片大小
      let isLt1M = file.size / 1024 / 1024 < 1;
      console.log(file);
      if (!isLt1M) {
        this.$message.error("上传图片大小不能超过 1MB!");
      }
      //判断图片的格式
      let imgType = file.name.substring(file.name.lastIndexOf(".") + 1);
      imgType = imgType.toLowerCase();
      let isImg =
        imgType === "jpg" ||
        imgType === "png" ||
        imgType === "jpeg" ||
        imgType === "gif";
      if (!isImg) {
        this.$message.error("上传图片格式不符合要求！");
      }
      return isLt1M && isImg;
    },
    onChangeFile(file) {
      if (this.imageFile != file.raw || this.imageFile == null) {
        this.imageFile = file.raw;
        // console.log("imageFile");
        // console.log(this.imageFile);
        // console.log(file);
        // console.log(this.beforeAvatarUpload(file));
        if (this.beforeAvatarUpload(file)) {
          fileApi
            .uploadImg({
              file: this.imageFile,
            })
            .then((res) => {
              console.log(res.msg);
              let msgObj = new Object();
              msgObj.content = res.msg;
              msgObj.messageTypeId = 3;
              msgObj.mid = this.mid;
              this.$store.state.stomp.send(
                "/ws/groupChat/" + this.mid,
                {
                  token: this.userToken,
                },
                JSON.stringify(msgObj)
              );
            });
        }
      }
    },

    // 使用Swot
    useSwot(index, row) {
      alert("user")
      SwotApi.useSwot({
        mid:this.mid,
        sid:row.sid
      })
      let msgObj = new Object();
      msgObj.content = JSON.stringify(row);
      console.log(row);
      // console.log(msgObj)
      msgObj.messageTypeId = 4;
      msgObj.fromId = this.userInfo.uid;
      msgObj.mid = this.mid;
      this.$store.state.stomp.send(
        "/ws/groupChat/" + this.mid,
        {
          token: this.userToken,
        },
        JSON.stringify(msgObj)
      );
      this.SwotDatadialogTableVisible = !this.SwotDatadialogTableVisible;
    },
    //获得用户的所有Swot问卷
    getSwot() {
      // alert("swot")
      this.SwotDatadialogTableVisible = !this.SwotDatadialogTableVisible;
      this.getUsersAllSwot();
      // console.log(this.swotData)
    },

    getUsersAllSwot() {
      SwotApi.getUsersAllSwot().then((res) => {
        res.object.forEach((e) => {
          e.createtime = Utils.getYMDHMS(e.createtime);
        });
        this.swotData = res.object;
        // console.log(this.swotData)
      });
      // console.log(this.swotData)
    },

    //获取用户的所有德尔菲问卷
    getDelphis() {
      this.DelphiDatadialogTableVisible = !this.DelphiDatadialogTableVisible;
      DelphiApi.getAllQuestionNaire().then((res) => {
        this.DelphiData = res.object;
        this.DelphiData.forEach((element) => {
          element.createtime = Utils.getYMDHMS(element.createtime);
          // element.delphis.anwser=[]
          element.delphis.forEach((e) => {
            e.anwser = [];
            e.anwser.push(e.delphia);
            e.anwser.push(e.delphib);
            e.anwser.push(e.delphic);
            e.anwser.push(e.delphid);
          });
          // element.delphis.anwser.push(element.delphis.delphia);
          // element.delphis.anwser.push(element.delphis.delphib);
          // element.delphis.anwser.push(element.delphis.delphic);
          // element.delphis.anwser.push(element.delphis.delphid);
        });
        // console.log(this.DelphiData);
      });
    },

    //使用德尔菲问卷
    useDelphiQuestionNaire(index, row) {
      // console.log(row);
      // console.log(row);
      let msgObj = new Object();
      msgObj.content = JSON.stringify(row);
      msgObj.messageTypeId = 2;
      msgObj.fromId = this.userInfo.uid;
      msgObj.mid = this.mid;
      this.$store.state.stomp.send(
        "/ws/groupChat/" + this.mid,
        {
          token: this.userToken,
        },
        JSON.stringify(msgObj)
      );
    },
    showTools() {
      this.showtoolsVisible = !this.showtoolsVisible;
    },
    getEmo(index) {
      var textArea = document.getElementById("textarea");
      //将选中的表情插入到输入文本的光标之后
      function changeSelectedText(obj, str) {
        if (window.getSelection) {
          // 非IE浏览器
          textArea.setRangeText(str);
          // 在未选中文本的情况下，重新设置光标位置
          textArea.selectionStart += str.length;
          textArea.focus();
        } else if (document.selection) {
          // IE浏览器
          obj.focus();
          var sel = document.selection.createRange();
          sel.text = str;
        }
      }
      changeSelectedText(textArea, this.faceList[index]);
      this.content = textArea.value; // 要同步data中的数据
      // console.log(this.faceList[index]);
      return;
    },
    addMessageByClick() {
      if (this.content.length == 0 || this.content.match(/^[ ]*$/)) {
        this.$message({
          message: "不能发送空白信息",
          type: "warning",
        });
        return;
      }
      let msgObj = new Object();
      msgObj.content = this.content;
      msgObj.messageTypeId = 1;
      msgObj.fromId = this.userInfo.uid;
      msgObj.mid = this.mid;
      // TODO websocket 的IP地址不对
      // alert(this.content)
      //发送群聊消息
      // if (this.currentSession.username=="群聊"){
      //   console.log(this.content);
      this.$store.state.stomp.send(
        "/ws/groupChat/" + this.mid,
        {
          token: this.userToken,
        },
        JSON.stringify(msgObj)
      );
      // }
      // //给机器人发送消息
      // if (this.currentSession.username=="机器人"){
      //   msgObj.fromNickname=this.$store.state.currentUser.nickname;
      //   msgObj.to='机器人';
      //   this.$store.state.stomp.send("/ws/robotChat",{},JSON.stringify(msgObj));
      //   //保存该条记录到session
      //   this.$store.commit('addMessage',msgObj);
      // }
      // //发送私聊消息
      // else{
      //   msgObj.from=this.$store.state.currentUser.username;
      //   msgObj.fromNickname=this.$store.state.currentUser.nickname;
      //   msgObj.to=this.currentSession.username;
      //   this.$store.state.stomp.send("/ws/chat",{},JSON.stringify(msgObj));
      //   //提交私聊消息记录
      //   this.$store.commit('addMessage',msgObj);
      // }
      // //清空输入框
      this.content = "";
    },
    addMessage(e) {
      if (e.ctrlKey && e.keyCode === 13 && this.content.length) {
        this.addMessageByClick();
        // alert("敲击热键")
      }
    },
    test() {
      alert("ds");
      this.$message({
        message: "das",
        type: "success",
      });
    },
  },

  watch: {},
};
</script>
<style lang="less">
.el-popover {
  height: 200px;
  width: 450px;
  overflow: scroll;
  overflow-x: auto;
}
</style>
<style lang='less' scoped>
.upload-btn {
  float: left;
}
.el-button {
  float: left;
  i {
    font-size: 22px;
  }
}
#uesrtext {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 30%;
  border: solid 1px #ddd;
  background-color: white;
  > textarea {
    padding: 10px;
    width: 95%;
    height: 58%;
    border: none;
    outline: none;
    resize: none; //禁止拉伸
    font-size: 20px;
  }
  #sendBtn {
    float: right;
    bottom: 2px;
    margin-right: 10px;
    padding-bottom: 10px;
  }
  #uploadImgBtn {
    border: none;
    padding-bottom: 0px;
    margin-bottom: 0px;
    padding-left: 15px;
  }
  #moreoption {
    border: none;
    padding-bottom: 0px;
    margin-bottom: 0px;
    padding-left: 0px;
    background-color: white;
  }
  .option {
    /deep/.el-popover {
      height: 40px;
    }
  }
  #uploadImgBtn:hover {
    background-color: white;
  }
  #emojiBtn {
    border: none;
    padding-right: 0px;
    padding-bottom: 0px;
    margin-bottom: 0px;
  }
  #emojiBtn:hover {
    background-color: white;
  }
  /deep/.upload-btn {
    display: inline-block;
  }
}
.emotionList {
  display: flex;
  flex-wrap: wrap;
  padding: 5px;
}
.emotionItem {
  width: 10%;
  font-size: 20px;
  text-align: center;
}
/*包含以下四种的链接*/
.emotionItem {
  text-decoration: none;
}
/*正常的未被访问过的链接*/
.emotionItem:link {
  text-decoration: none;
}
/*已经访问过的链接*/
.emotionItem:visited {
  text-decoration: none;
}
/*鼠标划过(停留)的链接*/
.emotionItem:hover {
  text-decoration: none;
}
/* 正在点击的链接*/
.emotionItem:active {
  text-decoration: none;
}
</style>