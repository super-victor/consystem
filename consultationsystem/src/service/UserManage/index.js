import { NetworkRequest } from "../api";


export default{
    async getAllUsers(){
        const res=await NetworkRequest({
            url:'/getAllUsers',
            method:'get',
            throttle:true
        })
        return res.data
    },
    
    async userUpdate(data){
        const res = await NetworkRequest({
            url:'/updateUser',
            method:'post',
            // throttle:true,
            data
        })
        return res.data
    },

    async userAdd(data){
        const res=await NetworkRequest({
            url:'/userAdd',
            method:'post',
            ContentType:'multipart/form-data',
            data,
            // Headers:{
            //     'Content-Type':'multipart/form-data',
            // }
        })
        return res.data
    },
    
    async addAvatarImg(data){
        const res=await NetworkRequest({
            url:'addAvatarImg',
            method:'post',
            postHeaderType:'multipart/form-data',
            fileType:true,
            data
        })
        return res.data
    },
    async deleteUser(data){
        const res=await NetworkRequest({
            url:'/deleteUser',
            method:'delete',
            data
        })
        return res.data
    }

}