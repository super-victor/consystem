import { NetworkRequest } from "../api";

export default{
    async addDelphi(data){
        const res=await NetworkRequest({
            url:'/adddelphi',
            method:'post',
            data
        })
        return res.data
    },
    async getAllDelphi(){
        const res=await NetworkRequest({
            url:'/getAlldelphis',
            method:'get'
        })
        return res.data
    },
    async addQuestionNaire(data){
        const res=await NetworkRequest({
            url:'/addquestionnaire',
            method:'post',
            data
        })
        return res.data
    },
    async getAllQuestionNaire(){
        const res=await NetworkRequest({
            url:"/getAllquestionnaire",
            method:'get'
        })
        return res.data
    },
    async addQuestionnaireAnswer(data){
        const res=await NetworkRequest({
            url:"/addQuestionnaireAnswer",
            method:'post',
            data
        })
        return res.data
    },

    async DelphiDataDetails(data){
        const res=await NetworkRequest({
            url:'/DelphiDataDetails',
            method:'get',
            data
        })
        return res.data
    },
    
    async DelphiModify(data){
        const res=await NetworkRequest({
            url:'/delphimodify',
            method:'post',
            data
        })
        return res.data
    },


    async deleteDelphi(data){
        const res=await NetworkRequest({
            url:'/deleteDelphi',
            method:'post',
            data
        })
        return res.data
    },
    async findOneDelphiAnswer(data){
        const res=await NetworkRequest({
            url:'/findOneDelphiAnswer',
            method:'get',
            data
        })
        return res.data
    },

    
}