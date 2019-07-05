
$(function (){
    load();

})

function load(){
    $("#bootTap").bootstrapTable({
        url:"queryvip",

        method:"post",

        striped: true,  	// 斑马线效果     默认false
        //只允许选中一行
        /* 	 singleSelect:true, */
        //选中行是不选中复选框或者单选按钮
        clickToSelect:true,
        showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        uniqueId: "vipId",                 //每一行的唯一标识，一般为主键列

        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,     //  最少留两列
        detailView: false,                  //是否显示父子表
        //发送到服务器的数据编码类型
        contentType:'application/x-www-form-urlencoded;charset=UTF-8',   //数据编码纯文本  offset=0&limit=5
        toolbar:'#toolbar',   //  工具定义位置
        columns:[
            //{checkbox: true},
            {field:'vipId',title:"卡号",width:100,
                 formatter:function(value,row,index){   //  格式化  当前单元格内容
                     return "<input type='checkbox'  value=" + value + " name='chk'/>";
                  }
            },
            {field:'vipName',title:'会员名',width:100},      //  列    field   代表是 后台  传过来的值
            /*  {field:'gameType',title:'游戏类型',width:100},  */
            {field:'vipLevel',title:'会员等级',width:100,
                formatter:function (value,row,index) {
                    if(value==1){return "普通";};
                    if(value==2){return "白银";};
                    if(value==3){return "黄金";};
                    if(value==4){return "钻石";};
                }},

            {field:'vipPhone',title:'电话',width:100},
            {field:'vipScore',title:'积分',width:100},
            {field:'costMoney',title:'消费金额',width:100},
            {field:'vipBalance',title:'余额',width:100},
            {field:'list',title:'list',width:100},
            //  列    field   代表是 后台  传过来的值
            {field:'startDate',title:'生效时间',width:100,sortable:true
                /*  formatter: function (value,row,index){
                     var simfomat = new Date();
                      simfomat.setTime(value);   //转换时间
                      return  simfomat.toLocaleDateString();
              } */},


            {field:'crud',title:'操作',width:100,
                formatter: function (value,row,index){
                    var str=''
                   /* var upd="<a href='javascript:upd(&quot;"+row.contractId+"&quot;)'>修改</a>"
                    var del="&nbsp;<a href='javascript:del(&quot;"+row.contractId+"&quot;)'>删除</a>"
                    str=upd+del*/
                    str="&nbsp;<a href='javascript:duanxin(&quot;"+row.vipPhone+"&quot;)'>短信</a>"
                    return str;
                }
            }
        ],
        //传递参数（*）
        queryParams: function(params) {
            var whereParams = {
                /*
                    分页  自定义的参数         默认传 limit（展示几条）    offset（从第几条开始    起始条数）
                */
                "pageSize":params.limit,
                "start":params.offset,
                "gameName":params.search,//搜索产品名称
                "vipName":$("#vipName").val(),
                "vipLevel":$("#vipLevel").val(),
                "sTime":$("#minTime").val(),
                "eTime":$("#maxTime").val(),
                "vipOrder":$("#vipOrder").val(),
                "orderud":$("#orderud").val()
            }
            return whereParams;
        },
        //前台--排序字段
        //sortName:'proPrice',
        //sortOrder:'desc',
        //前台--搜索框
        search:true,
        //启动回车键做搜索功能
        searchOnEnterKey:true,
        //分页方式   后台请求的分页方式
        sidePagination:'server',
        pagination: true,                   //是否显示分页（*）
        pageNum: 1,                       //每页的记录行数（*）
        pageSize: 3,                       //每页的记录行数（*）
        pageList: [3, 6, 9,12],        //可供选择的每页的行数（*）
    });

}

//条查
function  search(){

    $("#bootTap").bootstrapTable("refresh")
}




function getJspHtml(urlStr){
    var  jspHtml = "";
// 	 async  (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
    //注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
    $.ajax({
        url:urlStr,
        type:'post',
        //同步的ajax
        async:false,
        success:function(data){
            //alert(data);//data--addProduct.jsp页面内容
            jspHtml = data;
        },
        error:function(){
            bootbox.alert("ajax失败");
        }
    });
    return jspHtml;
}


function  dialog(HTMLurl,submitUrl,title){

    var dialog = bootbox.dialog({
        title: title,
        message:getJspHtml(HTMLurl),   //调用方法
        buttons:{
            "save":{

                label: '保存',
                //自定义样式
                className: "btn-success",
                callback: function() {

                    $.ajax({
                        url:submitUrl,
                        type:'post',
                        data:$("#ModiyDialogForm").serialize(),
                        success:function(data){
                            bootbox.alert("保存成功");
                            $("#bootTap").bootstrapTable("refresh")
                        },
                        error:function(){
                            bootbox.alert("ajax失败");
                        }
                    });
                }
            },
            "unSave":{
                label: '取消',
                //自定义样式
                className: "btn-info",
                callback: function() {
                    // return false;  //dialog不关闭
                }
            }
        }
    });
}
function add(){
    dialog("toaddvip"
        ,"saveadd",
        "新增")
}

function getVipid(){
    var vipid=""
    var ran=Math.floor(Math.random()*100)
    var time=new Date().getTime()
    var vipid=ran.toString()+time.toString()
    $("#vipId").val(vipid)
}


    function upd(){

        var count = 0;
        $("[name='chk']:checked").each(function(){
            count++;
        })
        if(count!=1){
            alert("请选择一行修改");
        }else{
            var memderId = $("[name='chk']:checked").val();
            dialog("toupdvip?id="+memderId,"saveupd","修改");
        }

    }


    function message() {
        dialog("addmessage","savemessage","短信");

    }






    function  del () {

    var temp = "";
    var count=0;
    $("[name='chk']:checked").each(function(){
        temp+=","+$(this).val();
        count++;                               });
    var ids = temp.substr(1);
    alert(temp)


    if(confirm("您确定删除这"+count+"信息吗?")){
        $.ajax({
            url:"delvip",
            type:'post',
            data:{ids:ids},
            success:function(data){
                bootbox.alert("删除成功");
                $("#bootTap").bootstrapTable("refresh");
            },
            error:function(){

            }
        });
    }

}
    function up(){

        $("#orderud").val(1)
    }
    function down(){

        $("#orderud").val(2)
    }

    function duanxin(phone){
    alert(phone)

        $.ajax({
            url:'gainMEssgerCode',
            type:'post',
            data:{
                phoneNumber:phone
            },
            dataType:'json',
            success:function(data){
                alert(data.msg);
                if (data.code != 0) {
                    $.messager.alert('提示',data.msg,'error')
                }else{
                    $.messager.alert('提示',data.msg,'error')
                }
            }


        })
    }

