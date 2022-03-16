import { NetworkRequest} from "../api";

export default{
    
    async loginRequest(data){
        const res=await NetworkRequest({
            url:'/login',
            method:'post',
            postHeaderType:'multipart/form-data',
            throttle:true,  
            data
        })
        return res.data
    },
}