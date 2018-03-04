<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%> 
<!DOCTYPE html>
<html>
<head>
<title>智慧物流平台系统</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	 <link rel="stylesheet" href="../jquery-ui-1.11.1.custom/jquery-ui.min.css"> 
	 <link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/manage.css" rel="stylesheet" type="text/css" />
<link href="../css/welcome2_files/css.css" rel="stylesheet" type="text/css">
<link href="../css/welcome2_files/jingjinji.css" rel="stylesheet" type="text/css">
<script src="../css/welcome2_files/jquery.min.js" type="text/javascript"></script>


<script type="text/javascript" src="../jquery-1.10.2/jquery.js"></script>
<script src="../jquery-ui-1.11.1.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../bootstrap-3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/ahover.js"></script>
</head>
<style type="text/css">
.lm {
    margin: 0px auto 0;
    overflow: hidden;
    width: 100%;
}

</style>
<body>
	

	<c:import url="../common/top.jsp">
	<c:param name="memuid" value="3"></c:param></c:import>
	<span class="blank18"></span>
	<div class="page">
		<div class="lm">
            <div style="overflow: hidden; float: left;width:100%">
                <div class="title y_j_ttl">
                    <div class="fl bold white">
                        <a href="#" style="color: #fff">实时车源</a></div>
                    <div class="ser" style="padding-left: 17px">
                       </div>
                  
                   
                   
                    <div class="fr" style="float: right;">
                        <a href="editlist" target="_blank">更多>>>>>&gt;&gt;</a></div>
                </div>
                <div style="clear: both">
                </div>
                <div style="width: 100%; overflow: hidden; border: 1px solid #dbdbdb; border-top: none; margin-bottom: 5px;">
                    <div id="con_huoyuan_1" style="display: block;" width="100%">
                        <table id="con_huoyuan_1" width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td class="bghui" width="8%"> 来源城市 </td>
                                    <td class="bghui" width="14%">始发地</td>
                                    <td class="bghui" width="13%"> 目的地 </td>
                                    <td class="bghui" width="8%">车牌号</td>
                                    <td class="bghui" width="10%">总载重(吨)</td>
                                    <td class="bghui" width="8%">车型 </td>
                                    <td class="bghui" width="8%">车长 </td>
                                    <td class="bghui" width="8%">车辆品牌</td>
                                    <td class="bghui" width="8%">发车时间</td>
                                    <td class="bghui" width="10%">联系方式</td>
                                    <td class="bghui" width="5%">有效天数</td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="demo" style="overflow: hidden; width: 100%; height: 480px">
                            <ul id="demo3" style="margin-top: -244px;">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                    <tbody>
                                    
             <c:forEach items="${resultList}" var="result"  varStatus="varStatus">                            
                                        <tr onmouseout="Kkdown(this);" onmouseover="Kkover(this);">
                                            <td class="bghuiw" width="8%"> ${result.fromCityName}</td>
                                            <td class="bghuiw" width="14%" style="color: #8E4904;">${result.fromname}</td>
                                            <td class="bghuiw" width="13%" style="color: #8E4904;"> ${result.toname}</td>
                                            <td class="bghuiw" width="8%">${result.licenseplate}</td>
                                            <td class="bghuiw" width="10%">${result.truckWeight}</td>
                                            <td class="bghuiw" width="8%">${result.carType}</td>
                                            <td class="bghuiw" width="8%">${result.carLength}</td>
                                            <td class="bghuiw" width="8%">${result.truckBarnd}</td>
                                            <td class="bghuiw" width="8%">${result.shipTimestamp}</td>
                                            <td class="bghuiw" width="10%">${result.ownerCellphone}</td>
                                            <td class="bghuiw" width="5%">${result.validDays}</td>
                                        </tr>
             </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

	</div>	
	<c:import url="../common/footer.jsp"></c:import>	
<script type="text/javascript">
   $(document).ready(function() {
            if ("Safari" == mb || "FF" == mb || "UA" == mb) {

                if ("Safari" == mb) {
                    $("#dibawuliuhui").empty();
                    }
              }
       });

    var BeginProId = $("#BeginProId").val();
    var BeginCityId = $("#BeginCityId").val();
    var BeginCountyId = $("#BeginCountyId").val();
    var EndProId = $("#EndProId").val();
    var EndCityId = $("#EndCityId").val();
    var EndCountyId = $("#EndCountyId").val();
    var mb = myBrowser();

    $(function() {
        $('.z_weixin').each(function() {
            var distance = 10;
            var time = 250;
            var hideDelay = 500;
            var hideDelayTimer = null;
            var beingShown = false;
            var shown = false;
            var trigger = $('.trigger', this);
            var info = $('.z_ewm', this).css('opacity', 0);
            $([trigger.get(0), info.get(0)]).mouseover(function() {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;
                    info.css({
                        top: 32,
                        left: 70,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }
                return false;
            }).mouseout(function() {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                hideDelayTimer = setTimeout(function() {
                    hideDelayTimer = null;
                    info.animate({
                        top: '-=' + distance + 'px',
                        opacity: 0
                    }, time, 'swing', function() {
                        shown = false;
                        info.css('display', 'none');
                    });
                }, hideDelay);
                return false;
            });
            $('#TopK1').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(1); }
            });
            $('#TopK2').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(2); }
            });
            $('#TopK3').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(3); }
            });
            $('#TopK5').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(5); }
            });
            $('#TopK8').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(8); }
            });
            $('#TopK9').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(9); }
            });
            $('#TopK6').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(6); }
            });
            $('#TopK10').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(10); }
            });
            $('#TopK11').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(11); }
            });
            $('#TopK12').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(12); }
            });
            $('#TopK13').keydown(function(e) {
                if (e.keyCode == 13) { SearchTop(13); }
            });

            $(window).scroll(function() {
                if ($(window).scrollTop() > 300) {
                    $('#jump li:eq(0)').fadeIn(800);
                } else {
                    $('#jump li:eq(0)').fadeOut(800);
                }
            });
            $("#top").click(function() {
                $('body,html').animate({
                    scrollTop: 0
                },
                1000);
                return false;
            });
        });
        initHeadAddress("wlBeginPlace1");
        initHeadAddress("goodsBeginPlace1");
        initHeadEndAddress("goodsEndPlace1");
        initHeadAddress("veBeginPlace1");
        initHeadEndAddress("veEndPlace1");
        initHeadAddress("priceBeginPlace1");
        initHeadEndAddress("priceEndPlace1");
        initHeadAddress("railwayBeginPlace1");
        initHeadEndAddress("railwayEndPlace1");
        initHeadAddress("menkehuBeginPlace1");
        initEndAddress("veEndPlace2");
        initAddress("veBeginPlace2");
        initEndAddress("goodsEndPlace2");
        initAddress("goodsBeginPlace2");

        initAddress("railwayBeginPlace2");
        initEndAddress("railwayEndPlace2");

        //startmarquee(0, 50, 150, "demo5");
        startmarquee(0, 50, 150, "demo3");
    });
    function initAddress(id) {
        var settingBegin = {
            id: id, //要使用弹出层选择省市区功能的input文本框ID
            countySync: true, //true:根据城市ID查询区县; false: 一次性查询所有区县
            isShowProvince: true, //选择省份后是否立即显示到文本框
            isShowCity: true, //选择城市后是否立即显示到文本框
            proId: $("#BeginProId"), //要设置省份ID的文本框
            cityId: $("#BeginCityId"), //要设置城市ID的文本框
            countyId: $("#BeginCountyId"), //要设置区县ID的文本框
            proName: $("#BeginProName"), //要设置省份名称的文本框
            cityName: $("#BeginCityName"), //要设置城市名称的文本框
            countyName: $("#BeginCountyName"), //要设置区县名称的文本框
            proUrl: "/FeedBack/getProvinces", //获取省份数据的访问地址
            cityUrl: "/FeedBack/getCity", //获取城市数据的访问地址
            countyUrl: "/FeedBack/getDistrict", //获取区县数据的访问地址
            countyByCityUrl: "/FeedBack/getDistrictByCity", //根据城市ID获取区县的访问地址
            hotCityUrl: "/FeedBack/gethotCity"
        }
       // new $.district(settingBegin).init(); //创建对象并初始化
    }
    function initEndAddress(id) {
        var settingBegin = {
            id: id, //要使用弹出层选择省市区功能的input文本框ID
            countySync: true, //true:根据城市ID查询区县; false: 一次性查询所有区县
            isShowProvince: true, //选择省份后是否立即显示到文本框
            isShowCity: true, //选择城市后是否立即显示到文本框
            proId: $("#EndProId"), //要设置省份ID的文本框
            cityId: $("#EndCityId"), //要设置城市ID的文本框
            countyId: $("#EndCountyId"), //要设置区县ID的文本框
            proName: $("#EndProName"), //要设置省份名称的文本框
            cityName: $("#EndCityName"), //要设置城市名称的文本框
            countyName: $("#EndCountyName"), //要设置区县名称的文本框
            proUrl: "/FeedBack/getProvinces", //获取省份数据的访问地址
            cityUrl: "/FeedBack/getCity", //获取城市数据的访问地址
            countyUrl: "/FeedBack/getDistrict", //获取区县数据的访问地址
            countyByCityUrl: "/FeedBack/getDistrictByCity", //根据城市ID获取区县的访问地址
            hotCityUrl: "/FeedBack/gethotCity"
        }

        //new $.district(settingBegin).init(); //创建对象并初始化
    }
    function initHeadAddress(id) {
        var settingBegin = {
            id: id, //要使用弹出层选择省市区功能的input文本框ID
            countySync: true, //true:根据城市ID查询区县; false: 一次性查询所有区县
            isShowProvince: true, //选择省份后是否立即显示到文本框
            isShowCity: true, //选择城市后是否立即显示到文本框
            proId: $("#" + id + "Pro"), //要设置省份ID的文本框
            cityId: $("#" + id + "City"), //要设置城市ID的文本框
            countyId: $("#" + id + "County"), //要设置区县ID的文本框
            proName: $("#" + id + "ProN"), //要设置省份名称的文本框
            cityName: $("#" + id + "CityN"), //要设置城市名称的文本框
            countyName: $("#" + id + "CountyN"), //要设置区县名称的文本框
            proUrl: "/FeedBack/getProvinces", //获取省份数据的访问地址
            cityUrl: "/FeedBack/getCity", //获取城市数据的访问地址
            countyUrl: "/FeedBack/getDistrict", //获取区县数据的访问地址
            countyByCityUrl: "/FeedBack/getDistrictByCity", //根据城市ID获取区县的访问地址
            hotCityUrl: "/FeedBack/gethotCity"
        }
        //new $.headdistrict(settingBegin).init(); //创建对象并初始化
    }
    function initHeadEndAddress(id) {
        var settingBegin = {
            id: id, //要使用弹出层选择省市区功能的input文本框ID
            countySync: true, //true:根据城市ID查询区县; false: 一次性查询所有区县
            isShowProvince: true, //选择省份后是否立即显示到文本框
            isShowCity: true, //选择城市后是否立即显示到文本框
            proId: $("#" + id + "Pro"), //要设置省份ID的文本框
            cityId: $("#" + id + "City"), //要设置城市ID的文本框
            countyId: $("#" + id + "County"), //要设置区县ID的文本框
            proName: $("#" + id + "ProN"), //要设置省份名称的文本框
            cityName: $("#" + id + "CityN"), //要设置城市名称的文本框
            countyName: $("#" + id + "CountyN"), //要设置区县名称的文本框
            proUrl: "/FeedBack/getProvinces", //获取省份数据的访问地址
            cityUrl: "/FeedBack/getCity", //获取城市数据的访问地址
            countyUrl: "/FeedBack/getDistrict", //获取区县数据的访问地址
            countyByCityUrl: "/FeedBack/getDistrictByCity", //根据城市ID获取区县的访问地址
            hotCityUrl: "/FeedBack/gethotCity"
        }

        //new $.headdistrict(settingBegin).init(); //创建对象并初始化
    } 
    function startmarquee(lh, speed, delay, id) {
        var t;
        var o = document.getElementById(id);
        o.innerHTML += o.innerHTML;
        o.style.marginTop = 0;
        o.onmouseover = function() { clearInterval(t); }
        o.onmouseout = function() { t = setInterval(scrolling, speed); }

        function start() {
            t = setInterval(scrolling, speed);
        }
        function scrolling() {
            if (parseInt(o.style.marginTop) % lh != 0) {
                o.style.marginTop = parseInt(o.style.marginTop) - 1 + "px";
                if (Math.abs(parseInt(o.style.marginTop)) >= o.scrollHeight / 2) o.style.marginTop = 0;
            } else {
                clearInterval(t);
                setTimeout(start, delay);
            }
        }
        setTimeout(start, delay);
    }
    function sendmsg() {
        var rname = document.getElementById("Name").value;
        var rtelphone = document.getElementById("Telphone").value;
        var rcontent = document.getElementById("Content").value;
        if (rname == "") {
            alert("请填写姓名");
        }
        else {
            if (rtelphone == "") {
                alert("请填写联系方式");
            }
            else {
                if (rcontent != "" && rcontent != "请务必留下有效联系方式，我们将第一时间给予回复！") {
                    $.ajax({
                        type: "POST",
                        url: "/FeedBack/SaveFeedBack",
                        data: {
                            "Content": rcontent,
                            "Name": rname,
                            "Telphone": rtelphone
                        },
                        dataType: "json",
                        success: function(data) {
                            var data = eval("(" + data + ")");
                            if (data.succ == true) {
                                alert('保存成功!我们将第一时间予以回复!');
                                $("#Name").val("");
                                $("#Telphone").val("");
                                $("#Content").val("请务必留下有效联系方式，我们将第一时间给予回复！");
                            }
                            else {
                                alert('添加失败：' + data.mesg);
                            }
                        }
                    });
                }
                else {
                    alert("没有填写内容");
                }
            }
        }
    }
    function SetHome() {
        if (document.all) {
            document.body.style.behavior = 'url(#default#homepage)';
            document.body.setHomePage("http://" + window.location.host);
        } else {
            alert("您使用浏览器不支持自动设置首页。\n\n加入设置首页失败，请手动设置！");
        }
    }
    function AddFavorite(title, url) {
        try {
            window.external.addFavorite(url, title);
        }
        catch (e) {
            try {
                window.sidebar.addPanel(title, url, "");
            }
            catch (e) {
                alert("您使用的浏览器不支持自动收藏。\n\n加入收藏失败，请使用Ctrl+D进行添加");
            }
        }
    }
    function feedback_Content_sj_onclick() {
        var neetConteny = document.getElementById("Content").value;
        if (neetConteny = "请务必留下有效联系方式，我们将第一时间给予回复！") {
            document.getElementById("Content").value = "";
        }
    }
    function setTab(name, cursel, n) {
        for (i = 1; i <= n; i++) {
            var menu = document.getElementById(name + i);
            var con = document.getElementById("con_" + name + "_" + i);
            menu.className = i == cursel ? "hover" : "";
            con.style.display = i == cursel ? "block" : "none";
        }
    }
    function myBrowser() {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        if (isOpera) {
            return "Opera"
        }; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "FF";
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1) {
            return "Chrome";
        }
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } //判断是否Safari浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }; //判断是否IE浏览器
        if (userAgent.indexOf("UA") > -1 || userAgent.indexOf("iPhone") > -1) {
            return "UA";
        }; //判断是否UA浏览器
    }
    function SearchTop(val) {
        var t1 = "";
        var t2 = "";
        var t3 = "";
        var islong = $("#islong").val();
        switch (val) {
            case 1:
                BeginProId = $("#goodsBeginPlace1Pro").val();
                BeginCityId = $("#goodsBeginPlace1City").val();
                BeginCountyId = $("#goodsBeginPlace1County").val();
                EndProId = $("#goodsEndPlace1Pro").val();
                EndCityId = $("#goodsEndPlace1City").val();
                EndCountyId = $("#goodsEndPlace1County").val();

                t3 = $("#TopK1").val();
                if (t1 == "请输入始发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/GoodsSourceList2?IsLong=" + islong + "&ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
           
            case 2:
                BeginProId = $("#veBeginPlace1Pro").val();
                BeginCityId = $("#veBeginPlace1City").val();
                BeginCountyId = $("#veBeginPlace1County").val();
                EndProId = $("#veEndPlace1Pro").val();
                EndCityId = $("#veEndPlace1City").val();
                EndCountyId = $("#veEndPlace1County").val();
                t3 = $("#TopK2").val();
                if (t1 == "请输入始发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/VehicleSourceList2?ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
            case 3:
                t1 = $("#TopB3").val();
                t3 = $("#TopK3").val();
                if (t3 == "请输入关键字")
                    t3 = "";
                BeginProId = $("#BeginProId").val();
                BeginCityId = $("#BeginCityId").val();
                BeginCountyId = $("#BeginCountyId").val();
                window.open("/WL/CompanyList2?proId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&Keys=" + t3);
                break;
            case 5:
                t1 = $("#TopB5").val();
                t3 = $("#TopK5").val();
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/WlshebeiList1?Keys=" + t3);
                break;
            case 4:
                t1 = $("#TopB4").val();
                t3 = $("#TopK4").val();
                if (t1 == "0")
                    t1 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                if (t1 == "") {
                    t1 = "1";
                    return;
                }
                window.open("/SearchMap/Index?Types=" + t1 + "&Keys=" + t3);
                break;
            case 6:
                BeginProId = $("#wlBeginPlace1Pro").val();
                BeginCityId = $("#wlBeginPlace1City").val();
                BeginCountyId = $("#wlBeginPlace1County").val();
                t1 = $("#TopB6").val();
                t3 = $("#TopK6").val();
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/WarehouseList2?pro=" + BeginProId + "&city=" + BeginCityId + "&county=" + BeginCountyId + "&Keys=" + t3 + "&type=WLWarehouse");
                break;
            case 7:
                BeginProId = $("#priceBeginPlace1Pro").val();
                BeginCityId = $("#priceBeginPlace1City").val();
                BeginCountyId = $("#priceBeginPlace1County").val();
                EndProId = $("#priceEndPlace1Pro").val();
                EndCityId = $("#priceEndPlace1City").val();
                EndCountyId = $("#priceEndPlace1County").val();
                t3 = $("#TopK7").val();
                t3 = "";
                window.open("/WL/LookForPrice?ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
            case 8:
                BeginProId = $("#railwayBeginPlace1Pro").val();
                BeginCityId = $("#railwayBeginPlace1City").val();
                BeginCountyId = $("#railwayBeginPlace1County").val();
                EndProId = $("#railwayEndPlace1Pro").val();
                EndCityId = $("#railwayEndPlace1City").val();
                EndCountyId = $("#railwayEndPlace1County").val();
                t3 = $("#TopK8").val();
                if (t1 == "请输入出发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/LogisticsRailwayList2?ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
            case 9:
                t3 = $("#TopK9").val();
                if (t3 == "请输入您想要查看的资讯标题或关键字")
                    t3 = "";
                window.open("/WL/InformationList2?Keys=" + t3);
                break;
            case 10:
                t3 = $("#TopK10").val();
                if (t3 == "请输入仓储企业名称")
                    t3 = "";
                window.open("/WL/CompanyList2?type=1&Keys=" + t3);
                break;
            case 11:
                t3 = $("#TopK11").val();
                if (t3 == "请输入物流企业名称")
                    t3 = "";
                window.open("/WL/CompanyList2?type=111&Keys=" + t3);
                break;
            case 12:
                t3 = $("#TopK12").val();
                if (t3 == "请输入配货站点名称")
                    t3 = "";
                window.open("/WL/CompanyList2?type=9&Keys=" + t3);
                break;
            case 13:
                t3 = $("#TopK13").val();
                if (t3 == "请输入物流园区名称")
                    t3 = "";
                window.open("/WL/LogisticsParkList2?keys=" + t3);
                break;
            case 14:
                BeginProId = $("#BeginProId").val();
                BeginCityId = $("#BeginCityId").val();
                BeginCountyId = $("#BeginCountyId").val();
                EndProId = $("#EndProId").val();
                EndCityId = $("#EndCityId").val();
                EndCountyId = $("#EndCountyId").val();

                if (t1 == "请输入出发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/LogisticsRailwayList2?ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId);
                break;
            case 15:
                BeginProId = $("#BeginProId").val();
                BeginCityId = $("#BeginCityId").val();
                BeginCountyId = $("#BeginCountyId").val();
                EndProId = $("#EndProId").val();
                EndCityId = $("#EndCityId").val();
                EndCountyId = $("#EndCountyId").val();

                t3 = $("#GoodsSourceKey").val();
                if (t1 == "请输入始发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/GoodsSourceList2?IsLong=" + islong + "&ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
            case 16:
                BeginProId = $("#BeginProId").val();
                BeginCityId = $("#BeginCityId").val();
                BeginCountyId = $("#BeginCountyId").val();
                EndProId = $("#EndProId").val();
                EndCityId = $("#EndCityId").val();
                EndCountyId = $("#EndCountyId").val();
                t3 = $("#VehicleSourceKey").val();
                if (t1 == "请输入始发地")
                    t1 = "";
                if (t2 == "请输入目的地")
                    t2 = "";
                if (t3 == "请输入关键字")
                    t3 = "";
                window.open("/WL/VehicleSourceList2?ProvinceId=" + BeginProId + "&CityId=" + BeginCityId + "&CountyId=" + BeginCountyId + "&ProvinceId1=" + EndProId + "&CityId1=" + EndCityId + "&CountyId1=" + EndCountyId + "&Keys=" + t3);
                break;
        }
    }
</script>
<script type="text/javascript">
$(function(){
var memuid=3;
var objLink=$("a[href*='memuid="+memuid+"']");
objLink.parent().attr('class',"current");
$('li.group').click(function(){
    if($(this).hasClass('open')){
        $(this).nextAll().hide();
        $(this).removeClass('open');
    }else{
        $(this).nextAll().show();
        $(this).addClass('open');
    }
});
$('li.group').each(function(){
    var iscurrent=false;
    $(this).nextAll().each(function(){
        if($(this).html().indexOf(curl)>0){
            iscurrent=true;
        }
    });
    !iscurrent&&$(this).click();
    
});

});
</script>
</body>
</html>
