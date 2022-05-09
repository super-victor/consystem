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
    }
}