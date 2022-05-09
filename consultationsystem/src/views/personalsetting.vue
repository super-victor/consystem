<template>
  <div>
    <!-- <quill-editor
      v-model="content"
      :options="editorOption"
      style="height: 320px; width: 96%"
    ></quill-editor> -->

    <div id="pie1" class="pie">
      <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
      <!-- <div id="main1" style="float: left; width: 100%; height: 450px" /> -->
      <div class="mychart" style=" width: 25%; height: 150px"></div>
      <div class="mychart" style=" width: 25%; height: 150px"></div>
    </div>

    <div class="option">
      <el-button type="primary" @click="test">点击</el-button>
    </div>
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

const echarts = require("echarts/lib/echarts");
// 引入饼状图组件
require("echarts/lib/chart/pie");
require("echarts/lib/component/tooltip");
export default {
  name: "",
  props: ["content"],
  data() {
    return {
      noticeForm: {
        content: "",
      },
      disable: false,
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
      phoneEditor: "",
      data: "dsa",
      charts:[
        [1,0,3,4],
        [0,2,3,2]
      ]
    };
  },

  components: {
    quillEditor,
  },

  computed: {},

  beforeMount() {},

  mounted() {
    this.initData()
  },

  methods: {
    test() {
      this.disable = !this.disable;
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

  watch: {},
};
</script>
<style lang='less' scoped>
.option {
  margin-top: 41px;
}
.shou {
  background-color: white;
}
</style>