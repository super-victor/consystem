import SockJS from '../Utils/sockjs'
import '../Utils/stomp'

export default {
    //websocket 连接
    connect(context,payload) {
        //连接Stomp站点
        // TODO 有alert
        // alert("连接")
        console.log("context")
        console.log(context)
        console.log(payload)
        // TODO 解析
        context.state.stomp = Stomp.over(new SockJS('/ws/ep?mid='+payload.mid+"&uid="+payload.uid));
        // alert(context.state.stomp)
        context.state.stomp.connect({
            mid:payload.mid
        }, success => {
            // console.log("连接成功")
            /**
             * 订阅系统广播通知消息
             */

            // context.state.stomp.subscribe("/topic/notification", msg => {
            //     //判断是否是系统广播通知
            //     Notification.info({
            //         title: '系统消息',
            //         message: msg.body.substr(5),
            //         position: "top-right"
            //     });
            //     //更新用户列表（的登录状态）
            //     context.commit('GET_USERS');
            // });
            /**
             * 订阅群聊消息
             */
            context.state.stomp.subscribe("/topic/greetings", msg => {
                //接收到的消息数据
                let receiveMsg = JSON.parse(msg.body);
                // console.log("收到消息" + receiveMsg);
                //当前点击的聊天界面不是群聊,默认为消息未读
                // if (context.state.currentSession.username != "群聊") {
                //     Vue.set(context.state.isDot, context.state.currentUser.username + "#群聊", true);
                // }
                //提交消息记录
                context.commit('addGroupMessage', receiveMsg);
            });
            /**
             * 订阅机器人回复消息
             */
            // context.state.stomp.subscribe("/user/queue/robot", msg => {
            //     //接收到的消息
            //     let receiveMsg = JSON.parse(msg.body);
            //     //标记为机器人回复
            //     receiveMsg.notSelf = true;
            //     receiveMsg.to = '机器人';
            //     receiveMsg.messageTypeId = 1;
            //     //添加到消息记录保存
            //     context.commit('addMessage', receiveMsg);
            // })
            /**
             * 订阅私人消息
             */
            // context.state.stomp.subscribe('/user/queue/chat', msg => {
            //     //接收到的消息数据
            //     let receiveMsg = JSON.parse(msg.body);
            //     //没有选中用户或选中用户不是发来消息的那一方
            //     if (!context.state.currentSession || receiveMsg.from != context.state.currentSession.username) {
            //         Notification.info({
            //             title: '【' + receiveMsg.fromNickname + '】发来一条消息',
            //             message: receiveMsg.content.length < 8 ? receiveMsg.content : receiveMsg.content.substring(0, 8) + "...",
            //             position: "bottom-right"
            //         });
            //         //默认为消息未读
            //         Vue.set(context.state.isDot, context.state.currentUser.username + "#" + receiveMsg.from, true);
            //     }
            //     //标识这个消息不是自己发的
            //     receiveMsg.notSelf = true;
            //     //获取发送方
            //     receiveMsg.to = receiveMsg.from;
            //     //提交消息记录
            //     context.commit('addMessage', receiveMsg);
            // })
        }, error => {
            // console.log(error)
            Notification.info({
                title: '系统消息',
                message: "无法与服务端建立连接，请尝试重新登陆系统~",
                position: "top-right"
            });
        })
    },

    // websocket 端口连接
    disconnect(context) {
        if (context.state.stomp != null) {
            context.state.stomp.disconnect();
            console.log("关闭连接~");
        }
    },
}

