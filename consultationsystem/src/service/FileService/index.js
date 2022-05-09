import {NetworkRequest} from '../api'

export default{
    async uploadImg(data){
        const res=await NetworkRequest({
            url:"/uploadimage",
            method:'post',
            postHeaderType:'multipart/form-data',
            fileType:true,
            data
        })
        return res.data
    },

    async uploadFile(data){
        const res=await NetworkRequest({
            url:'/uploadfile',
            method:'post',
            postHeaderType:'multipart/form-data',
            fileType:true,
            data
        })
        return res.data
    },

    async downLoadFile(data){
        const res=new NetworkRequest({
            url:'/downLoadFile',
            method:'post',
            data
        })
        return res.data
    }
}