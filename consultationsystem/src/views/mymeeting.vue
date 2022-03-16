<template>
  <div>
    <div class="meetinghead">
      <p>我的会议</p>
    </div>
    <div class="test">
      <!-- <ul>
      <li v-for="item in meetings" :key="item">
        <el-card shadow="hover" class="box-card">
          我的会议{{ item.createName }}
        </el-card>
      </li>
      </ul> -->
      <div class="meetingcards">
        <div class="meetingnow">
          <div class="head">
            <p>正在进行</p>
          </div>
          <el-divider></el-divider>
          <div class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="(item,index) in meetings" :key="item"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ item.startDate }}</p>
                    <p>结束时间:{{ item.endDate }}</p>
                    <p>创建者:{{ item.createName }}</p>
                    <el-button type="text">进入会议</el-button>
                    <el-button type="text" @click="showmeetingInfo(index)"
                      >详情</el-button
                    >
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
        <div class="meetingfuture">
          <div class="head">
            <p>即将进行</p>
          </div>
          <el-divider></el-divider>
          <div class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="item in meetings" :key="item"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ item.startDate }}</p>
                    <p>结束时间:{{ item.endDate }}</p>
                    <p>创建者:{{ item.createName }}</p>
                    <el-button type="text">编辑</el-button>
                    <el-button type="text">详情</el-button>
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
        <div class="meetinghistory">
          <div class="head">
            <p>历史会议</p>
          </div>
          <el-divider></el-divider>
          <div class="meetings">
            <el-row :gutter="10">
              <el-col :span="40" v-for="item in meetings" :key="item"
                ><div class="grid-content bg-purple">
                  <el-card shadow="hover" class="box-card">
                    <p>名称:{{ item.name }}</p>
                    <p>开始时间:{{ item.startDate }}</p>
                    <p>结束时间:{{ item.endDate }}</p>
                    <p>创建者:{{ item.createName }}</p>
                    <el-button type="text">会议记录</el-button>
                    <el-button type="text">详情</el-button>
                  </el-card>
                </div></el-col
              >
            </el-row>
          </div>
        </div>
      </div>
      <el-dialog title="会议详情" :visible.sync="dialogFormVisible">
        <el-form :model="meeting">
          <el-form-item label="活动名称" :label-width="formLabelWidth" >
            <el-input v-model="meeting.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="创建者" :label-width="formLabelWidth">
            <el-input v-model="meeting.createName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="创建时间" :label-width="formLabelWidth">
            <el-input v-model="meeting.createDate" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "",
  props: [""],
  data() {
    return {
      dialogFormVisible: false,
      meetings: [
        {
          id: 1,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "das",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "dhgf",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "rte",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "ytui",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "534",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "uiy",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "lkjh",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "Thfgom",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "jggr",
        },
        {
          id: 2,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "rwewq",
        },
        {
          id: 3,
          createDate: "2020-02-03",
          name: "开题报告",
          startDate: "2020-02-04",
          endDate: "2020-02-05",
          createName: "yrt",
        },
      ],
      meeting: {
        id: 1,
        createDate: "2020-02-03",
        name: "开题报告",
        startDate: "2020-02-04",
        endDate: "2020-02-05",
        createName: "Tom",
      },
    };
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
    showmeetingInfo(index) {
      this.dialogFormVisible = !this.dialogFormVisible;
      this.meeting=JSON.parse(JSON.stringify(this.meetings[index]))
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.meetinghead {
  // background-color: rgba(177, 177, 177,0.2);
  height: 60px;
  margin-bottom: 10px;
  display: flex;
  justify-content: left;
  justify-items: left;
  p {
    font-size: 30px;
    display: flex;
    align-items: center;
    // left: 5px;
  }
}
.meetingnow {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}
.meetingfuture {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}
.meetinghistory {
  .head > p {
    // float: left;
    width: 100px;
    font-size: 20px;
  }
}

.box-card {
  width: 300px;
  //   border-radius: 20px;
}
.meetingcard {
  background-color: aquamarine;
  justify-content: center;
  justify-items: center;
}

.el-col {
  margin-bottom: 10px;
  margin-top: 10px;
  margin-left: 5px;
}
.el-card:hover {
  width: 305px;
}
</style>