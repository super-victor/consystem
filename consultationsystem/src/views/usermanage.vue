<template>
  <div id="usermanage">
    <div class="option">
      <el-button type="primary" @click="useradd">新增用户</el-button>
      <el-dialog title="新增用户" :visible.sync="useraddfromvisible">
        <el-form :model="adduser">
          <el-row type="flex" class="row-bg" justify="space-around">
            <el-col :span="8">
              <div class="grid-content bg-purple">
                <el-form-item label="账号" :label-width="formLabelWidth">
                  <el-input
                    v-model="adduser.account"
                    autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="用户名" :label-width="formLabelWidth">
                  <el-input
                    v-model="adduser.username"
                    autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth">
                  <el-input
                    v-model="adduser.phone"
                    autocomplete="off"
                  ></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                  <el-input
                    v-model="adduser.email"
                    autocomplete="off"
                  ></el-input>
                </el-form-item></div
            ></el-col>
            <el-col :span="8">
              <el-form-item label="头像">
                <div class="grid-content bg-purple">
                  <el-avatar :size="150">
                    <el-upload
                      class="avatar-uploader"
                      action="#"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess"
                      :before-upload="beforeAvatarUpload"
                      auto-upload="false"
                      :on-change="onChangeFile"
                    >
                      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </el-avatar>
                </div>
              </el-form-item>
              <el-form-item label="角色" :label-width="formLabelWidth">
                <el-radio v-model="adduser.isadmin" label="0"
                  >普通用户</el-radio
                >
                <el-radio v-model="adduser.isadmin" label="1">管理员</el-radio>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="userAddtion()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <el-table :data="userData" style="width: 100%" max-height="580">
      <el-table-column prop="account" label="账号" width="180" align="center">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column prop="phone" label="电话" align="center">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" align="center">
      </el-table-column>
      <el-table-column prop="isadmin" label="角色" align="center">
        <!-- <div v-if="isadmin=='0'">普通用户</div> -->
        <!-- <div v-if="isadmin=='1'">管理员</div> -->
        <!-- {{isadim}} -->
        <!-- <p>{{isadmin}}</p> -->
        <template slot-scope="scope">
          <div v-if="scope.row.isadmin == '0'">普通用户</div>
          <div v-else>管理员</div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
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
    <el-dialog
      title="用户信息"
      :visible.sync="usermodifyfromvisible"
      close="userInfoClose"
    >
      <el-form :model="user">
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="8">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input
                v-model="user.username"
                autocomplete="off"
                style="width: 150px; float: left"
              ></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input
                v-model="user.phone"
                autocomplete="off"
                style="width: 150px; float: left"
              ></el-input>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input
                v-model="user.email"
                autocomplete="off"
                style="width: 150px; float: left"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="角色" :label-width="formLabelWidth">
              <el-radio v-model="user.isadmin" label="0">普通用户</el-radio>
              <el-radio v-model="user.isadmin" label="1">管理员</el-radio>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="usermodifycancel">取 消</el-button>
        <el-button type="primary" @click="usermodify">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Avatar from "../components/basic/Avatar.vue";
import UserManageApi from "../service/UserManage";
export default {
  name: "",
  props: [""],
  data() {
    return {
      radio: 1,
      imageUrl: "",
      imageFile: null,
      useraddfromvisible: false,
      usermodifyfromvisible: false,
      dialogVisible: false,
      userData: [
        {
          id: 1,
          username: "张三",
          password: null,
          phone: 18349802085,
          email: "123@136.com",
        },
        {
          id: 2,
          username: "李四",
          password: null,
          phone: 18349802085,
          email: "123@136.com",
        },
      ],
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      adduser: {
        account: "",
        email: "",
        username: "",
        isadmin: "0",
        isforbid: "",
        password: "",
        phone: "",
        uid: "",
        avatarUrl: "",
      },
      user: {
        account: "das",
        email: "",
        username: "",
        isadmin: "0",
        isforbid: "",
        password: "",
        phone: "",
        uid: 0,
      },
    };
  },

  components: { Avatar },

  computed: {},

  created() {
    this.initAllUsers();
  },

  beforeMount() {},

  mounted() {},

  methods: {
    boolCalculate(input) {
      if (input == false) {
        return "0";
      } else {
        return "1";
      }
    },
    usermodify() {
      // console.log(this.user)
      UserManageApi.userUpdate({
        account: this.user.account,
        email: this.user.email,
        username: this.user.username,
        isadmin: this.user.isadmin,
        isforbid: this.user.isforbid,
        password: this.user.password,
        phone: this.user.phone,
        uid: this.user.uid,
        // user:this.user
      })
        .then((res) => {
          this.usermodifyfromvisible = !this.usermodifyfromvisible;
          if (res.status == 200) {
            this.$message({
              message: res.msg,
              type: "success",
            });
            UserManageApi.getAllUsers().then((res) => {
              this.userData = res.object;
            });
          } else {
            this.$message({
              message: res.msg,
              type: "warning",
            });
          }
        })
        .catch((err) => {
          this.$message({
            message: res.msg,
            type: "error",
          });
        });
    },

    usermodifycancel() {
      this.usermodifyfromvisible = !this.usermodifyfromvisible;
    },
    initAllUsers() {
      UserManageApi.getAllUsers()
        .then((res) => {
          this.userData = res.object;
          this.userData.forEach(function (user) {
            /*TODO isadmin boolen 类型转 0 1 字符串 */
            // user["isadmin"]=boolCalculate(user["isadmin"])
            // user["isadin"]=boolCalculate(user["isadmin"])
            // console.log(user['isadmin']='0')
            if (user["isadmin"]) {
              user["isadmin"] == "1";
            } else {
              user["isadmin"] = "0";
            }
          });
          console.log(this.userData);
        })
        .catch((err) => {
          this.$message({
            message: "获取用户失败",
            type: "error",
          });
        });
    },

    //  关闭用户信息面板
    userInfoClose() {
      this.user = null;
    },
    onChangeFile(file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      this.imageFile = file.raw;
    },
    // 打开添加新用户的面板
    useradd() {
      this.useraddfromvisible = !this.useraddfromvisible;
    },
    // 添加新用户
    userAddtion() {
      this.useraddfromvisible = !this.useraddfromvisible;
      console.log(this.imageFile.name);
      UserManageApi.addAvatarImg({
        file: this.imageFile,
      }).then((res) => {
        // console.log(res)
        this.adduser.avatarUrl = res.object.avatarUrl;
        console.log(this.adduser.avatarUrl);
        UserManageApi.userAdd({
          account: this.adduser.account,
          email: this.adduser.email,
          username: this.adduser.username,
          isadmin: this.adduser.isadmin,
          phone: this.adduser.phone,
          avatarurl: this.adduser.avatarUrl,
          // file:this.imageFile,
        })
          .then((res) => {
            this.account = "";
            this.email = "";
            this.username = "";
            this.isadmin = "0";
            this.isforbid = "";
            this.password = "";
            this.phone = "";
            this.uid = "";
            this.avatarUrl = "";
            if (res.status == 200) {
              this.initAllUsers();
            }
          })
          .catch((err) => {});
      });
    },
    handleEdit(index, row) {
      // this.initAllUsers();
      this.usermodifyfromvisible = !this.usermodifyfromvisible;
      // this.user=this.tableData[index]
      this.user = JSON.parse(JSON.stringify(this.userData[index]));
      this.user.isadmin = this.boolCalculate(this.user.isadmin);
      this.user.isforbid = this.boolCalculate(this.user.isforbid);
      console.log(this.user);
    },
    handleDelete(index, row) {
      this.dialogVisible = !this.dialogVisible;
      this.open(index);
    },
    open(index) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          UserManageApi.deleteUser({
            uid: this.userData[index].uid,
          }).then((res) => {
            if (res.status == 200) {
              this.$message({
                message: res.msg,
                type: "success",
              });
              this.initAllUsers();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },

  watch: {},
};
</script>
<style lang='less' scoped>
.avatar-uploader {
  img {
    width: 100%;
  }
}
#usermanage {
  height: 670px;
}
.option {
  // height: 50px;
  // background-color: red;
  // display: flex;
  // justify-content: center;
  // justify-items: center;
  // float: left;
  // margin-bottom: 10px;
  // background-color: white;
  height: 60px;
  margin-bottom: 10px;
  display: flex;
  justify-content: left;
  justify-items: left;
  background-color: white;
  border-radius: 10px;
  .el-button{
    width: 100px;
    height: 40px;
    margin-left: 15px;
    margin-top: 10px;
  }
}
.el-dialog {
  height: 20px;
}

/deep/ .el-dialog_header {
  height: 30px;
}
.el-table{
  border-radius: 10px;
}
</style>