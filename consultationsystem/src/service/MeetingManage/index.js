import { NetworkRequest } from "../api";

export default{
    async addMeeting(data){
        const res=await NetworkRequest({
            url:'/addmeeting',
            method:'post',
            data
        })
        return res.data
    },

    async addSunmmary(data){
        const res=await NetworkRequest({
            url:'/addSunmmary',
            method:'post',
            data
        })
        return res.data
    },

    async getMyMeeting(){
        const res=await NetworkRequest({
            url:'/getmymeeting',
            method:'get',
        })
        return res.data
    },

    async getAllUsersByMid(data){
        const res=await NetworkRequest({
            url:'/getAllUsersByMid',
            method:'get',
            data
        })
        return res.data
    },

    async findSunmmary(data){
        const res=await NetworkRequest({
            url:'/findSunmmary',
            method:'get',
            data
        })

        return res.data
    },

    async getAllReminder(){
        const res=await NetworkRequest({
            url:'/getAllReminder',
            method:'get'
        })
        return res.data
    }
}