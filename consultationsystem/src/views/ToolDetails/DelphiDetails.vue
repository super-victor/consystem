<template>
  <div>
    <div class="body">
      <el-collapse v-model="activeName" accordion @change="handlechange" >
        <el-collapse-item
          :title="item.dpname"
          :name="index"
          v-for="(item, index) in QuestionNaires"
          :key="index"
          
        >
          <div v-for="(item1, index1) in item.delphis" :key="index1">
            <div>{{ index1 + 1 }},{{ item1.dname }}</div>
            <div v-if="checkType(item1.type, '1')">
              <div class="chcekboxs">
                <div v-for="(item2, index2) in item1.anwser" :key="index2">
                  <el-radio :label="item.delphis[index1].anwser[index2]"
                    >{{ String.fromCharCode(0x41 + index2) + "."
                    }}{{ item2 }}</el-radio
                  >
                </div>
              </div>
                <div class="mychart" style=" width: 100%; height: 250px">
                </div>
            </div>
            <div v-if="checkType(item1.type, '2')">
              <div class="chcekboxs">
                <div v-for="(item2, index2) in item1.anwser" :key="index2">
                  <el-checkbox :label="item.delphis[index1].anwser[index2]">
                    {{ String.fromCharCode(0x41 + index2) + "." }} {{ item2 }}
                  </el-checkbox>
                </div>
              </div>
             <div class="mychart" style="width: 100%; height: 250px"></div>
            </div>
            <div v-if="checkType(item1.type, '3')">
                <div v-for="(Item,Index) in anwserObj[index][index1] " :key="Index">
                    {{Index+1}}.{{Item}}
                </div>
                <!-- das -->
              <!-- <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="textarea"
              >
              </el-input> -->
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
        
    </div>
  </div>
</template>

<script>
import DelphiApi from "../../service/Delphi";
import utils from "../../Utils/util";
const echarts = require("echarts/lib/echarts");
// 引入饼状图组件
require("echarts/lib/chart/pie");
require("echarts/lib/component/tooltip");
export default {
  name: "",
  props: ["mid"],
  data() {
    return {
      QuestionNaires: [],
      activeName: '1',
      charts:[],
      anwserObj:[],
    };
  },

  created() {
    //   this.getAllQuestionNaire()
    this.DelphiDataDetails();
  },
  components: {},

  computed: {},

  beforeMount() {},

  mounted() {
      this.initData()
  },

  methods: {
    checkType(s, m) {
      if (s == m) {
        return true;
      } else {
        return false;
      }
    },
    getAllQuestionNaire() {
      DelphiApi.getAllQuestionNaire().then((res) => {
        if (res.status == 200) {
          this.QuestionNaires = res.object;
          this.QuestionNaires.forEach((element) => {
            element.createtime = utils.getYMDHMS(element.createtime);
            element.delphis.forEach((e) => {
              e.anwser = [];
              e.anwser.push(e.delphia);
              e.anwser.push(e.delphib);
              e.anwser.push(e.delphic);
              e.anwser.push(e.delphid);
            });
          });
          console.log(this.QuestionNaires);
        }
      });
    },
    DelphiDataDetails() {
      DelphiApi.DelphiDataDetails({
        mid: this.mid,
      }).then((res) => {
        console.log(res)
        this.QuestionNaires=[]
        res.object.forEach((e) => {
          // console.log(e)
          this.QuestionNaires.push(e[0]);
          this.anwserObj.push(e[1])
          var k=e[1]
          this.QuestionNaires.forEach((element) => {
            element.createtime = utils.getYMDHMS(element.createtime);
            // element.delphis.anwser=[]
            element.delphis.forEach((e) => {
              e.anwser = [];
              e.anwser.push(e.delphia);
              e.anwser.push(e.delphib);
              e.anwser.push(e.delphic);
              e.anwser.push(e.delphid);
            });
          });

            for(var i=0;i<e[0].delphis.length;i++){
                if(e[0].delphis[i].type==1||e[0].delphis[i].type==2){
                    this.charts.push(k[i])
                }
            }

        });
        console.log("QY")
        console.log(this.charts)
        console.log(this.QuestionNaires);
      });
    },
    handlechange(){
        // alert(this.activeName)
        this.initData()
    },
    initData() {
      // 基于准备好的dom，初始化echarts实例
      // const myChart = echarts.init(document.getElementById("main1"));
      var m=document.getElementsByClassName("mychart")
    
      for(var i=0;i<m.length;i++){
        var myChart=echarts.init(m[i])
        myChart.setOption({
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)", // 具体a b c d 代表的属性看下面注释啦
          },
          series: [
            {
              name: "访问来源", // formatter 中的a
              type: "pie",
              radius: "50%",
              center: ["50%", "60%"],
              data: [
                {
                  value: this.charts[i][0],
                  name: "A",
                  itemStyle: { color: "#D8ECFF" },
                  label: { color: "#666666" },
                  labelLine: { lineStyle: { color: "#666666" } },
                },
                { value: this.charts[i][1], name: "B", itemStyle: { color: "#F5A623" } },
                { value: this.charts[i][2], name: "C", itemStyle: { color: "#339DFF" } },
                { value: this.charts[i][3], name: "D", itemStyle: { color: "#389DCF" } },
              ], // data 中的name为formatter中的 b；data中的value呢就是formatter中c；至于d就是Echarts计算出来的百分比啦；itemStyle:为饼图每个扇形的颜色；label为指示线后面的文字的样式，labelLine为指示线的颜色
              label: {
                normal: {
                  show: true,
                  position:'outer',
                //   margin:10,
                  alignTo:'edge',
                  textStyle: {
                    fontWeight: 400,
                    fontSize: 12, // 文字的字体大小
                  },
                  formatter: "{b} \n {c}人", // 这里为指示线后面的提示信息，这里的换行要用\n 与上面tooltips中的formatter换行不同滴
                },
              },
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: "rgba(0, 0, 0, 0.5)",
                },
              },
            },
          ],
        });
      window.onresize = myChart.resize; //自适应浏览器窗口的大小
      }
    },
  },

  watch: {
    mid:{
      handler(newMid,oldMid){
          if(newMid!=oldMid){
            this.mid=newMid
            this.DelphiDataDetails()
          }
      },
      immediate:true,
      deep:true
    }
  },
};
</script>
<style lang='less' scoped>
.body {
  width: 100%;
  height: 500px;
  overflow-y: scroll;
}
 .chcekboxs > div {
    display: flex;
    justify-content: left;
    justify-items: left;
  }
</style>