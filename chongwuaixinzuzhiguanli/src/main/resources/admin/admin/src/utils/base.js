const base = {
    get() {
        return {
            url : "http://localhost:8080/chongwuaixinzuzhiguanli/",
            name: "chongwuaixinzuzhiguanli",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/chongwuaixinzuzhiguanli/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "宠物爱心组织管理系统"
        } 
    }
}
export default base
