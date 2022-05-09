<template>
    <div id="toolbar">
    <el-image class="imgProfile"
              :src="user.userProfile"
              :preview-src-list="[user.userProfile]"
              :alt="user.nickname">
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image>
    <div id="btnBar">
      <div class="topBtnBar">
        <el-tooltip  class="item" effect="dark" content="进入群聊" placement="right">
        <el-button @click="chooseChatList('群聊')" class="toolBtn" size="small"><i class="fa fa-comments fa-2x" aria-hidden="true"></i></el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="用户列表" placement="right">
        <el-button @click="chooseChatList('私聊')" class="toolBtn" size="small"><i class="fa fa-address-book-o fa-2x" aria-hidden="true"></i></el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="与机器人聊天" placement="right">
          <el-button @click="chooseChatList('机器人')" class="toolBtn" size="small"><i class="fa fa-android fa-2x" aria-hidden="true"></i></el-button>
        </el-tooltip>
      </div>
      <div class="bottomBtnBar">
        <el-tooltip class="item" effect="dark" content="个人中心" placement="right">
          <el-button class="toolBtn" size="small"><i class="fa fa-user fa-2x" aria-hidden="true"></i></el-button>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="更多" placement="right">
          <el-popover
                  placement="right"
                  width="180"
                  trigger="click"
                  popper-class="moreListPopoverClass"
                   >
            <ul id="moreList">
              <li @click="showFeedbackDialog" >意见反馈</li>
              <li>举报</li>
              <li @click="clearChatHistory">清空聊天记录</li>
            </ul>
            <el-button slot="reference" class="toolBtn" size="small"><i class="fa fa-bars fa-2x" aria-hidden="true"></i></el-button>
          </el-popover>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="退出" placement="right">
        <el-button @click="exitSystem" class="toolBtn" size="small"><i class="fa fa-sign-out fa-2x" aria-hidden="true"></i></el-button>
        </el-tooltip>
      </div>
    </div>
    <el-dialog title="意见反馈" :visible.sync="feedBackDialogVisible" class="feedbackDialog">
      <textarea class="feedbackInput" v-model="feedBackContent">

      </textarea>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleFeedbackSend">确 定</el-button>
        <el-button @click="feedBackDialogVisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

  export default {
    name:'',
    props:[''],
    data () {
      return {

      };
    },

    components: {},

    computed: {},

    beforeMount() {},

    mounted() {},

    methods: {},

    watch: {}

  }

</script>
<style lang='less' scoped>
  #toolbar{
    width: 100%;
    height: 100%;
    #btnBar{
      width: 100%;
      height: 82%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
    .imgProfile{
      width: 40px;
      height: 40px;
      horiz-align: center;
      margin: 25px 10px;
    }
    .toolBtn{
      background-color: #2e3238;
      border: 0;
      margin: 5px 5px;
    }
    .feedbackDialog{
      width: 1000px;
      height: 800px;
      margin: 10px auto;
      //background-color: #ECEAE8;
    }
    .feedbackInput{
      width: 450px;
      height: 200px;
      resize: none;
      padding: 0;
      margin: 0;
    }
  }

    #moreList{
    margin: 0px;
    padding: 0px;
    background-color: #2e3238;
    li {
      padding-top: 14px;
      padding-bottom: 14px;
      padding-left: 5px;
      //padding-right: 40px;
      //border-bottom: 1px solid #292C33;
      list-style: none;
      cursor: pointer;
      &:hover {
        background-color: #abaaaa;
      }
    }
  }
  .el-popover.moreListPopoverClass{
    height:150px;
    width:150px;
   // margin: 0px;
    margin-left: 10px;
    padding: 0px;
    overflow-x: hidden;
    overflow-y: hidden;
    background-color:#2e3238;
    border:none;
  }
</style>