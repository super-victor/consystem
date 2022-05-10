import {NetworkRequest} from '../api'

export default{
    async addSwot(data){
        const res=await NetworkRequest({
            url:'/addSwot',
            method:'post',
            data
        })
        return res.data
    },
    async getUsersAllSwot(){
        const res=await NetworkRequest({
            url:'/getUsersAllSwot',
            method:'get'
        })
        return res.data
    },
    async useSwot(data){
        const res=await NetworkRequest({
            url:'/useSwot',
            method:'post',
            data
        })
        return res.data
    },
    async giveSwotAnswer(data){
        const res=await NetworkRequest({
            url:"/giveSwotAnswer",
            method:"post",
            data
        })
        return res.data
    },
    async getSwotAnswer(data){
        const res=await NetworkRequest({
            url:"/getSwotAnswer",
            method:'post',
            data
        })
        return res.data
    }
}