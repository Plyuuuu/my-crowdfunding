
//生成页面效果,如何时时候调用这个函数都会重新加载页面
function generatePage() {
    // 1、获取分页数据
    var pageInfo = getPageInfoRemote();
    //console.log("generatePage 中的 pageInfo"+pageInfo.toString())
    // 2.填充表格
    fillTableBody(pageInfo);
}

// 远程访问服务器端获取pageInfo数据
function getPageInfoRemote() {

   console.log("getPageInfoRemote方法中访问window.pageNum:"+window.pageNum)
   var ajaxResult = $.ajax({
       "url":"role/get/page/info.json",
       "type":"post",
       "data":{
           "pageNum":window.pageNum,
           "pageSize":window.pageSize,
           "keyword":window.keyword
       },
       "async":false,
       "dataType":"json",

   });

   //console.log("getPageInfoRemote方法中的window.pageNum:"+this.data.pageNum)
   console.log(ajaxResult);

   //判断响应状态码是否为200
    var statusCode = ajaxResult.status;
    //如果当前响应状态码不是200，让函数停止
    if (statusCode != 200){
        layer.msg("服务器端程调用失败! 响应状态码:"+statusCode+"  说明信息:"+ajaxResult.statusText);
        return null;
    }

    //如果响应状态码是200，说明处理成功,获取pageInfo
    var resultEntity = ajaxResult.responseJSON;

    // 从resultEntity中获取result属性
    var result = resultEntity.result;

    //判断result是否成功
    if (result == "FAILED"){
        layer.msg(resultEntity.message);
        return  null;
    }

    // 确认result成功获取pageInfo后
    var pageInfo = resultEntity.data;

    //返回pageInfo
    return  pageInfo;


}

//  填充表格
function fillTableBody(pageInfo) {

    //清除tbody中旧的内容
    $("#rolePageBody").empty();
    // 为了搜索没有结果时不显示页码
   // $("#Pagination").empty();

    //判断pageInfo对象是否有效
    if(pageInfo==null || pageInfo==undefined || pageInfo.list == null || pageInfo.list.length==0){
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉！没有查询到你想要的数据！</td></tr>");
        return;

    }
    // 使用pageInfo的List属性填充tbody
    for (var i = 0;i<pageInfo.list.length;i++){
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;

        var numberTd = "<td>"+(i+1)+"</td>";
        var checkboxTd = "<td><input type='checkbox'></td>";
        var roleNameTd = "<td>"+roleName+"</td>";

        // 通过button标签的id属性把roleId值传递到button按钮的单击响应函数中
        var checkBtn = "<button type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button type='button' class='btn btn-primary btn-xs pencilBtn'><i class='glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type='button' class='btn btn-danger btn-xs removeBtn'><i class='glyphicon glyphicon-remove'></i></button>";
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";

        $("#rolePageBody").append(tr);
    }

    // 生成分页导航条
    generateNavigator(pageInfo);
}

// 生成分页页码导航条
function generateNavigator(pageInfo) {
    // 获取总记录数
    var totalRecord = pageInfo.total;

    // 声明其他相关属性
    var properties = {
        "num_edge_entries": 1,
        "num_display_entries": 4,
        "callback": paginationCallback,
        "items_per_page": pageInfo.pageSize,
        "current_page": pageInfo.pageNum - 1,
        "prev_text": "上一页",
        "next_text": "下一页"

    };

    // 调用 pagination()函数
    $("#Pagination").pagination(totalRecord,properties);


}

// 翻页时的回调函数
function paginationCallback(pageIndex,jQuery) {

    console.log("paginationCallback 函数被触发");
    console.log("修改前window.pageNum:"+window.pageNum);
    // 修改window对象的pageNum属性
    window.pageNum = pageIndex+1;
    console.log("修改后window.pageNum:"+window.pageNum)


    // 调用分页函数
    generatePage();

    // 取消页码超链接的默认行为
    return false;

}