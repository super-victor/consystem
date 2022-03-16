const path = require('path');
const ip="localhos"
module.exports={
    configureWebpack:{
        resolve:{
            alias:{
                '@':path.join(__dirname,'./src'),
                'assets':"@/assets",
                'components':'@/components',
                'views':'@/views'
            }
        }
    },
    devServer: {
        open: true,
        host: 'localhost',
        port: 8081,
        https: false,
        proxy: {
            '/api': {//代理字段设置
                // target: `http://${ip}:8082/`,//填写后台接口
                target: `http://localhost:8082/`,
                ws: true,
                changOrigin: true,//设置允许跨域
                pathRewrite: {
                    '^/api': ''//除去代理字段
                }
            }
        }
    },
}