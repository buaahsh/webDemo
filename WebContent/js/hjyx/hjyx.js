
/*!
 * Control environment display
 * By:HuangShaohan  
 * Date: 2014-03-31T21:10Z
 */

$("body").on("click","#clustertime",function(){
    $("#choose").empty();
    $("#figure1").empty();
    $("#figure2").empty();
    $("#choose").append(
        "<div><p><strong> 起止日期: </strong> </p> " 
        +"<input type='text' style='width: 100px' name='startDay' id='begin' class='span2' value='05/02/2014'>&nbsp;&nbsp;&nbsp;"
        +"<input type='text' style='width: 100px' name='endDay' id='end' class='span2' value='05/05/2014'>"
        +"</div>"
        +"<div><p><strong> 服务类型: </strong> </p>"
             +"<label class='checkbox inline'><input type='checkbox' id='inlineCheckbox1' value='cluster utilization'> CPU利用率/节点利用率</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='inlineCheckbox2' value='fs workspace'> 磁盘利用</label>"
        +"</div>"
        +"<div><p><strong> 结点名称: </strong> </p>"       
        +"<div>"
        +"<label class='checkbox inline'><input type='checkbox' id='' value='cigit'>cigit</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='dicp'> dalian</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='deepcomp7000'> deepcomp7000</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='issp'> hefei</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='ibp'>ibp</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='kib'>kunming</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='casnw'> lanzhou</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='qdio'> qingdao</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='imr'> shenyang</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='siat'> shenzhen</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='ihb'> wuhan</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='jinan'> jinan</label>"
            +"</div>"
        +"</div><br>"
    );
    $('#begin').datepicker();
    $('#end').datepicker();         
});

$("body").on("click","#devops",function(){
    $("#choose").empty();
    $("#figure1").empty();
    $("#figure2").empty();
    $("#choose").append(
        "<div><p><strong> 起止日期: </strong> </p> " 
        +"<input type='text' style='width: 100px' name='startDay' id='begin' class='span2' value='05/02/2014'>&nbsp;&nbsp;&nbsp;"
        +"<input type='text' style='width: 100px' name='endDay' id='end' class='span2' value='05/05/2014'>"
        +"</div>"
        +"<div><p><strong> 服务类型: </strong> </p>"
             +"<label class='checkbox inline'><input type='checkbox' id='inlineCheckbox1' value='Current Load'>服务器负载</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='inlineCheckbox2' value='Root Partition'> 磁盘利用</label>"
        +"</div>"
        +"<br><div><p><strong> 结点名称: </strong> </p>"       
        +"<div>"           
        +"<label class='checkbox inline'><input type='checkbox' id='' value='fs-jinan'>fs-jinan</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='fs-ibp'> fs-ibp</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='fs40'> fs40</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='fs41'> fs41</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='cs30'>cs30</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='cs31'>cs31</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='cs32'> cs32</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='cs33'> cs33</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='client50'> client50</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='client51'> client51</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='portal81'> portal81</label>"
            +"</div>"
        +"</div><br>"
    );   
    $('#begin').datepicker();
    $('#end').datepicker();      
});

$("body").on("click","#clusterstatistics",function(){
    $("#choose").empty();
    $("#figure1").empty();
    $("#figure2").empty();
    $("#choose").append(
        "<div><p><strong> 起止日期: </strong> </p> " 
        +"<input type='text' style='width: 100px' name='startDay' id='begin' class='span2' value='09/01/2013'>&nbsp;&nbsp;&nbsp;"
        +"<input type='text' style='width: 100px' name='endDay' id='end' class='span2' value='03/01/2014'>"
        +"</div>"
        +"<div><p><strong> 结点名称: </strong> </p>"       
        +"<div>"
        +"<label class='checkbox inline'><input type='checkbox' id='' value='cigit'>cigit</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='dicp'> dalian</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='deepcomp7000'> deepcomp7000</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='issp'> hefei</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='ibp'>ibp</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='kib'>kunming</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='casnw'> lanzhou</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='qdio'> qingdao</label>"
            +"</div>"
            +"<div>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='imr'> shenyang</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='siat'> shenzhen</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='ihb'> wuhan</label>"
            +"<label class='checkbox inline'><input type='checkbox' id='' value='jinan'> jinan</label>"
            +"</div>"
        +"</div><br>"
    );         
    $('#begin').datepicker();
    $('#end').datepicker();
    
});

$("body").on("click", "[type='checkbox']",function(){
    display();
});
$("body").on("focusout", "input.span2",function(){
    display();
});

function getHost(src)
{
    host = src.split("&")[0].split("=")[1];
    return host;
}

function showFig(kind, serviceKind1, serviceKind2, hostArray, snow, enow)
{
    $("#figure1").empty();
    $("#figure2").empty();
    var cgiUrl = "http://www.scgrid.cn/nagiosgraph/cgi-bin/showgraph.cgi?";
    var constant1 = "&geom=530x215&rrdopts=+-snow-"+snow+"+-enow-"+enow;
    var constant2 = "&geom=530x215&rrdopts=+-snow-"+snow+"+-enow-"+enow;
    var figure1 = new Array();
    var figure2 = new Array();
    var j = 0;
    if (serviceKind1 != "")
    {
        for (var i = hostArray.length - 1; i >= 0; i--) {
            figure1[j++] = cgiUrl + "host=" + hostArray[i] +"&service=" + serviceKind1 + constant1;
        };
    }
    if (serviceKind2 != "")
    {
        j = 0;
        for (var i = hostArray.length - 1; i >= 0; i--) {
            figure2[j++] = cgiUrl + "host=" + hostArray[i] +"&service=" + serviceKind2 + constant2;
        };
    }
    if (kind == 1  || kind == 2)
    {
           for (var i = figure1.length - 1; i >= 0; i--) {
                $("#figure1").append("<div>"+getHost(figure1[i])+"</div>");
                $("#figure1").append("<div><iframe width=630 height='300' frameborder=0 scrolling='auto' src='"+figure1[i]+"'></iframe></div>");
           };
           for (var i = figure2.length - 1; i >= 0; i--) {
                $("#figure2").append("<div>"+getHost(figure2[i])+"</div>");
                $("#figure2").append("<div><iframe width=630 height='300' frameborder=0 scrolling='auto' src='"+figure2[i]+"'></iframe></div>");
           };
    }
    else if (kind == 0)
    {
        cgiUrl = "http://www.scgrid.cn/nagiosgraph/cgi-bin/showhpcutil.cgi?";
        j = 0;
        for (var i = hostArray.length - 1; i >= 0; i--) {
            figure2[j++] = cgiUrl + "host=" + hostArray[i]+"&start="+snow+"&end="+enow+"&geom=600x400";
        };
        for (var i = figure2.length - 1; i >= 0; i--) {
            $("#figure2").append("<div>"+getHost(figure2[i])+"</div>");
            $("#figure2").append("<div><iframe width=630 height='300' frameborder=0 scrolling='auto' src='"+figure2[i]+"'></iframe></div>");
       };
    }
    // for (var i = $("iframe").length - 1; i >= 0; i--) {
    //     var item = $("iframe")[i];
    //     var bHeight = item.contentWindow.document.body.scrollHeight; 
    //     var dHeight = item.contentWindow.document.documentElement.scrollHeight; 
    //     var height = Math.max(bHeight, dHeight); 
    //     item.height =  height;
    // };
    
    //var iframe = $("iframe")[0];
    //var h = iframe.offsetTop + 10;
    //iframe.height = h;
}


function display()
{
    
    var serviceKind1 = "";
    var serviceKind2 = "";
    var hostArray = new Array();
    var kind = 0;

    var i = 0;
    var j = 0;
    $.each($("[type='checkbox']"),function(idx, item){
        
        if(item.checked)
        {
            if (item.value == "fs workspace" || item.value == "cluster utilization")
            {
                if (item.value == "cluster utilization")
                    serviceKind1 = item.value;
                else
                    serviceKind2 = item.value;
                kind = 1;
            }
            else if (item.value == "Current Load" || item.value == "Root Partition")
            {
                if (item.value == "Current Load")
                    serviceKind1 = item.value;
                else
                    serviceKind2 = item.value;
                kind = 2;
            }
            else
            {
                hostArray[j++] = item.value;
            }
        }
    });
    var startDay = $("[name='startDay']")[0].value;
    var endDay = $("[name='endDay']")[0].value;
    var today = new Date();
    var start = new Date(startDay);
    var end = new Date(endDay);
    var snow = (today - start) / 1000;
    var enow = (today - end) / 1000;
    if (kind == 0)
    {
        snow = startDay.split("/")[2]+startDay.split("/")[0]
        enow = endDay.split("/")[2]+endDay.split("/")[0]
        showFig(kind, serviceKind1, serviceKind2, hostArray, snow, enow);
    }
    else
    {
        showFig(kind, serviceKind1, serviceKind2, hostArray, snow, enow);
    }
}

