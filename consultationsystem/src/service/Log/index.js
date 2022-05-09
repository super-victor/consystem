import { NetworkRequest} from "../api";

export default{

    async getAllTypes(){
        const res=await NetworkRequest({
            url:'/getAllTypes',
            method:'get',
            throttle:true
        })
        return res.data
    },

    async getAllLogs(){
        const res=await NetworkRequest({
            url:'/getAllLogs',
            method:'get',
            throttle:true
        })
        return res.data
    },

    async deleteLog(data){
        const res=await NetworkRequest({
            url:'/logDelete',
            method:'delete',
            throttle:true,
            data
        })
        return res.data
    }
}