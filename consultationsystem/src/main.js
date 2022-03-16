import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/base.css';

Vue.use(ElementUI)
import{
  Container,
  Header,
  Aside,
  Main,
  Footer
} from'element-ui';
Vue.use(Container);
Vue.use(Header);
Vue.use(Main);
Vue.use(Footer);
Vue.use(Aside);
Vue.config.productionTip = false
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
