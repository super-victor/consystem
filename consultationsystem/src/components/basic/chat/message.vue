<template>
  <div id="message" v-scroll-bottom="sessions">
    <!-- <div v-if="currentSession && currentSession.username != '群聊'">
      <ul>
        <li
          :v-for="
            entry in sessions[user.username + '#' + currentSession.username]
          "
        >
          <p class="time">
            <span>{{ entry.date | time }}</span>
          </p>
          <div class="main" :class="{ self: entry.self }">
            <p class="username">{{ entry.fromNickname }}</p>
            <img
              class="avatar"
              :src="entry.self ? user.userProfile : currentSession.userProfile"
              alt=""
            />
            <p v-if="entry.messageTypeId == 1" class="text">
              {{ entry.content }}
            </p>
            <img
              v-if="entry.messageTypeId == 2"
              :src="entry.content"
              class="img"
            />
          </div>
        </li>
      </ul>
    </div> -->
    <div>
      <ul>
        <li v-for="entry in sessions['groupChat'][mid]" :key="entry.id">
          <p class="time">
            <span>{{ entry.createTime | time }}</span>
          </p>
          <div class="main" :class="{ self: entry.fromId == userInfo.uid }">
            <div class="ahead">
              <img
                class="avatar"
                :src="
                  entry.fromId == userInfo.uid
                    ? userInfo.avatarurl
                    : entry.fromProfile
                "
                alt=""
              />
              <p class="username">{{ entry.fromName }}</p>
            </div>
            <div v-if="entry.messageTypeId == 1">
              <p class="text" v-html="entry.content"></p>
            </div>
            <!-- <div v-else>
              图片预览与无法加载图片的图标
              <el-image
                :src="entry.content"
                :preview-src-list="[entry.content]"
                class="img"
              >
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </div> -->
            <div v-if="entry.messageTypeId == 2">
              <el-card class="box-card selfcard">
                <div class="cardtitle"><p>德尔菲问卷</p></div>
                <el-divider></el-divider>
                <div>
                  <p>
                问卷名：{{entry.content.dpname}}
                  </p>
                </div>
                <div>
                <el-button type="primary" size="mini" @click="giveDelphiAnswer(entry.content)">回答</el-button>
                </div>
              </el-card>
            </div>
            <div v-if="entry.messageTypeId == 4">
              <el-card class="box-card selfcard">
                <div class="cardtitle"><p>swot问卷</p></div>
                <el-divider></el-divider>
                <div>
                  <p>
                问卷名：{{entry.content.swotname}}
                  </p>
                </div>
                <div>
                <el-button type="primary" size="mini" @click="giveSwotAnswer(entry.content)">回答</el-button>
                </div>
              </el-card>
            </div>
            <div v-if="entry.messageTypeId == 6">
              <el-card class="box-card selfcard">
                <div class="cardtitle"><p>竞争性假设</p></div>
                <el-divider></el-divider>
                <div>
                  <p>
                问卷名：{{entry.content.hname}}
                  </p>
                </div>
                <div>
                <el-button type="primary" size="mini" @click="giveHypothAnswer(entry.content)">回答</el-button>
                <el-button type="primary" size="mini" @click="agree(entry.content.hid)">赞成</el-button>
                <el-button type="primary" size="mini" @click="disagreeHypoth(entry.content.hid)">反对</el-button>
                </div>
              </el-card>
            </div>
            <div v-if="entry.messageTypeId == 7">
              <el-card class="box-card selfcard">
                <div class="cardtitle"><p>文件</p></div>
                <el-divider></el-divider>
                <div>
                  <p>
                {{entry.content.name}}
                  </p>
                </div>
                <div>
                <el-button type="primary" size="mini" ><a :href="entry.content.fid">下载</a></el-button>
                </div>
              </el-card>
            </div>
            <img
            v-if="entry.messageTypeId == 3"
            :src="entry.content"
            class="img"
            >
          </div> 
        </li>
      </ul>
    </div>
<!-- 回答德尔菲问卷 -->
     <el-dialog
        :title="QuestionnairePreviewObj.dpname"
        :visible.sync="QuestionnairepreviewVisible"
        width="30%"
        :before-close="handleClose"
        class="questionpreview"
      >
        <div >
          <div
            v-for="(item, index) in QuestionnairePreviewObj.delphis"
            :key="index"
            
          >
            <div>{{ index + 1 }},{{ item.dname }}</div>
            <div v-if="checkType(item.type, '1')">
              <div class="chcekboxs">
                <div v-for="(item1, index1) in item.anwser" :key="index1">
                  <el-radio
                    :label="index1+1"
                    v-model="ans[index].value"
                    >{{ String.fromCharCode(0x41 + index1) + "."
                    }}{{ item1 }}</el-radio 
                  >
                </div>
              </div>
            </div>
            <div v-if="checkType(item.type, '2')">
              <div class="chcekboxs">
                <div v-for="(item1, index1) in item.anwser" :key="index1">
                  <el-checkbox-group v-model="ans[index].list">
                    <el-checkbox
                      :label="
                        index1+1
                      "
                    >
                      {{ String.fromCharCode(0x41 + index1) + "." }} {{ item1 }}
                    </el-checkbox>
                  </el-checkbox-group>
                </div>
              </div>
            </div>
            <div v-if="checkType(item.type, '3')">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="ans[index].text"
              >
              </el-input>
            </div>
          </div>
        </div>
        <el-button type="primary" size="mini" @click="submitDelphi(QuestionnairePreviewObj.dpid)">提交</el-button>
      </el-dialog>

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

      <!-- 回答SWOT问卷 -->
      <el-dialog
        title="修改SWOT"
        :visible.sync="swotEditDialogVisible"
        width="50%"
        center
        class="editDialog"
      >
        <p>名称</p>
        <el-input
          v-model="swotEdit.swotname"
          disabled
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
            <div v-for="(item, index) in swotEdit.advantege" :key="index">
              <el-input v-model="swotEdit.advantege[index]"></el-input>
            </div>
             <div v-for="(item, index) in swotEditAdd.advantege" :key="index">
              <el-input v-model="swotEditAdd.advantege[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead disadvantege">
            <p>弱点</p>
            <el-button @click="disadvantageEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加劣势</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.disadvantege" :key="index">
              <el-input v-model="swotEdit.disadvantege[index]"></el-input>
            </div>
            <div v-for="(item, index) in swotEditAdd.disadvantege" :key="index">
              <el-input v-model="swotEditAdd.disadvantege[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead opportunity">
            <p>机会</p>
            <el-button @click="opportunityEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加机会</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.opportunity" :key="index">
              <el-input v-model="swotEdit.opportunity[index]"></el-input>
            </div>
            <div v-for="(item, index) in swotEditAdd.opportunity" :key="index">
              <el-input v-model="swotEditAdd.opportunity[index]"></el-input>
            </div>
          </div>
        </div>
        <div class="swotbox">
          <div class="swotboxehead threaten">
            <p>威胁</p>
            <el-button @click="threatenEdit"
              ><i class="el-icon-circle-plus-outline"></i>添加威胁</el-button
            >
          </div>
          <div class="content">
            <div v-for="(item, index) in swotEdit.threaten" :key="index">
              <el-input v-model="swotEdit.threaten[index]"></el-input>
            </div>
            <div v-for="(item, index) in swotEditAdd.threaten" :key="index">
              <el-input v-model="swotEditAdd.threaten[index]"></el-input>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="SubmitSwot"
            >确 定</el-button
          >
        </span>
      </el-dialog>
  </div>
</template>

<script>
import Vue from 'vue';
import { mapState } from "vuex";
import DelphiApi from '../../../service/Delphi'
import FileApi from '../../../service/FileService'
import HypotyApi from '../../../service/Hypoty'
import SwotApi from '../../../service/Swot'
export default {
  name: "",
  props: ["mid"],
  data() {
    return {
      QuestionnairepreviewVisible: false,
      QuestionnairePreviewObj:{
        QuestionnaireAnswer:[]
      },
      ans:[],
      swotEditDialogVisible:false,
      competitionViewDialogVisible:false,
      swotEdit:{},
      competeDataView:{},
      swotEditAdd:{
        advantege:[],
        disadvantege:[],
        opportunity:[],
        threaten:[]
      }
    };
  },

  components: {},

  computed: {
    ...mapState(["sessions", "userInfo"]),
  },

  beforeMount() {},

  directives: {
    /*这个是vue的自定义指令,官方文档有详细说明*/
    // 发送消息后滚动到底部,这里无法使用原作者的方法，也未找到合理的方法解决，暂用setTimeout的方法模拟
    "scroll-bottom"(el) {
      //console.log(el.scrollTop);
      setTimeout(function () {
        el.scrollTop += 9999;
      }, 1);
    },
  },
  mounted() {
    console.log('sessions = ', this.sessions)
    console.log('userInfo = ', this.userInfo)
  },

  methods: {
    // 同意假设
    agree(hid){
      HypotyApi.giveHypothAnswer({
        hid:hid,
        uid:this.userInfo.uid,
        answer:1,
        mid:this.mid
      }).then((res)=>{
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
      })
    },
    disagreeHypoth(hid){
      HypotyApi.giveHypothAnswer({
        hid:hid,
        uid:this.userInfo.uid,
        answer:0,
        mid:this.mid
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
      })
    },
    giveHypothAnswer(Hypoth){
      this.competeDataView=Hypoth
      this.competitionViewDialogVisible=!this.competitionViewDialogVisible
    },
    downloadFile(myfile){
        FileApi.downLoadFile({
          fid:myfile.fid
        })
    },
    submitDelphi(dpid){
      console.log(this.QuestionnairePreviewObj.dpid)
      DelphiApi.addQuestionnaireAnswer({
        mid:this.mid,
        dpid:dpid,
        answer:JSON.stringify(this.ans)
      }).then(res=>{
        this.QuestionnairepreviewVisible=!this.QuestionnairepreviewVisible
      })
    },

    SubmitSwot(){
      // console.log(this.swotEditAdd)
      // console.log(this.swotEdit )
        console.log("this.swotEditAdd")
        console.log(this.swotEditAdd)
        SwotApi.giveSwotAnswer({
          mid:this.mid,
          sid:this.swotEdit.sid,
          advantege:this.swotEditAdd.advantege.length==0?[""]:this.swotEditAdd.advantege,
          disadvantege:this.swotEditAdd.disadvantege==0?[""]:this.swotEditAdd.disadvantege,
          opportunity:this.swotEditAdd.opportunity==0?[""]:this.swotEditAdd.opportunity,
          threaten:this.swotEditAdd.threaten==0?[""]:this.swotEditAdd.threaten
        }).then(res=>{

        })
        this.swotEditAdd={
          advantege:[],
          disadvantege:[],
          opportunity:[],
          threaten:[]
        }
      if(this.swotEdit.uid=this.userInfo.uid){
        // console.log(this.swotEdit.advantege)
          // this.swotEdit.advantege.forEach((e)=>{
          //   this.swotEditAdd.advantege.push(e)
          // })
          // this.swotEdit.disadvantege.forEach((e)=>{
          //   this.swotEditAdd.disadvantege.push(e)
          // })
          // this.swotEdit.threaten.forEach((e)=>{
          //   this.swotEditAdd.threaten.push(e)
          // })
          // this.swotEdit.opportunity.forEach((e)=>{
          //   this.swotEditAdd.opportunity.push(e)
          // })
          // this.swotEditAdd.disadvantege.push(this.swotEdit.disadvantege)
          // this.swotEditAdd.opportunity.push(this.swotEdit.opportunity)
          // this.swotEditAdd.threaten.push(this.swotEdit.threaten)
      }
    },
    //回答SWOT
    giveSwotAnswer(content){
      this.swotEdit=content
      SwotApi.getSwotAnswer({
        mid:this.mid,
        sid:content.sid
      }).then(res=>{
        this.swotEdit=res.object
      })
      // console.log(this.swotEdit)
      this.swotEditDialogVisible=!this.swotEditDialogVisible
      // alert("回答")
    },

    advantegeEdit() {
      this.swotEditAdd.advantege.push("");
    },
    disadvantageEdit() {
      this.swotEditAdd.disadvantege.push("");
    },
    opportunityEdit() {
      this.swotEditAdd.opportunity.push("");
    },
    threatenEdit() {
      this.swotEditAdd.threaten.push("");
    },

    // 回答德尔菲问卷表
    giveDelphiAnswer(entry){
      DelphiApi.findOneDelphiAnswer({
        mid:this.mid,
        dpid:entry.dpid
      }).then((res)=>{
        if(res.status==200){
          this.$message({
            message:'你已作答',
            type:'success'
          })
        }else{          
            this.QuestionnairePreviewObj=entry
            this.QuestionnairepreviewVisible=!this.QuestionnairepreviewVisible
            console.log("打印entry")
            console.log(entry)
            this.QuestionnairePreviewObj['QuestionnaireAnswer']=[]
            this.ans=[]
            entry.delphis.forEach(element => {
              if(element.type==1){
                this.ans.push({
                  type:1,
                  value:1
                })
              }
              if(element.type==2){
                this.ans.push({
                  type:2,
                  list:[]
                })
              }
              if(element.type==3){
                this.ans.push({
                  type:3,
                  text:""
                })
              }
            });
        }
      })
    },
    //判断类型
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
#message {
  padding: 15px;
  height: 60%;
  max-height: 63%;
  overflow-y: scroll;
  overflow-x: hidden;
  ul {
    list-style-type: none;
    padding-left: 0px;
    li {
      margin-bottom: 15px;
    }
  }
  .time {
    text-align: center;
    margin: 7px 0;
    > span {
      display: inline-block;
      padding: 0 18px;
      font-size: 12px;
      color: #fff;
      background-color: #dcdcdc;
      border-radius: 2px;
    }
  }
  .main {
    text-align: left;
    .ahead {
      display: flex;
    }
    .avatar {
      // float: left;
      margin: 0 10px 0 0;
      border-radius: 50%;
      width: 30px;
      height: 30px;
    }
    .text {
      display: inline-block;
      padding: 0 10px;
      max-width: 80%;
      background-color: #fafafa;
      border-radius: 4px;
      line-height: 30px;
    }
    .img {
      // display: inline-block;
      // height: 100px;
      width: 100px;
      margin-top: 15px;
      // size: 100px;
    }
    .username {
      // position: relative;
      // left: 35px;
      // top: 11px;
      text-align: center;
      margin: 0 0;
      padding: 0 0;
      border-radius: 4px;
      line-height: 30px;
      font-size: 15px;
      color: grey;
    }
  }
  .self {
    text-align: right;
    /deep/.selfcard{
      display: flex;
      margin-top: 16px;
      width: 200px;
      margin-left: 760px;
      .el-card__body{
        padding-top: 5px;
        padding-left: 5px;
        width: 200px;
      }
      .el-divider{
        margin-top: 0;
        margin-bottom: 0;
      }
      p{
        margin: 0;
        text-align: left;
      }
      .el-button{
        margin-top: 5px;
      }
      .cardtitle{
        margin-bottom: 0;
        margin-top: 0;
      }
    }
    .avatar {
      display: flex;
      // float: right;
      margin-left: 930px;
      // margin: 0 0 0 10px;
      border-radius: 50%;
      width: 30px;
      height: 30px;
    }
    .text {
      display: inline-block;
      padding: 0 10px;
      max-width: 80%;
      background-color: #b2e281;
      border-radius: 4px;
      line-height: 30px;
    }
    .img {
      // display: inline-block;
      // justify-content: ri;
      // height: 100px;
      // width: 100px;
      // margin-top: 15px;
    }
    .username {
      text-align: center;
      margin-left: -110px;
      // top: 11px;
      width: 200px;
      line-height: 30px;
      font-size: 15px;
      color: grey;
    }
  }
}
/deep/.el-dialog__body{
  overflow-y: scroll;
  height: 200px;
}
.questionpreview {
  // display: flex;
  // justify-items: left;
  // justify-content: left;
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
</style>