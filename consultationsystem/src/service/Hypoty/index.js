import { NetworkRequest } from "../api";


export default{
    async addHypoty(data){
        const res=await NetworkRequest({
            url:'/AddHypoty',
            method:'post',
            data
        })
        return res.data
    },

    async getAllHypoth(){
        const res=await NetworkRequest({
            url:'/getHypoth',
            method:'get',
        })
        return res.data
    },

    async updateHypoth(data){
        const res=await NetworkRequest({
            url:'/updateHypoth',
            method:'post',
            data
        })
        return res.data
    },
    
    async deleteHypoth(data){
        const res=await NetworkRequest({
            url:'/deleteHypoth',
            method:'post',
            data
        })
        return res.data
    },

    async giveHypothAnswer(data){
        const res=new NetworkRequest({
            url:'/giveHypothAnswer',
            method:'post',
            data
        })
        return res.data
    }
}