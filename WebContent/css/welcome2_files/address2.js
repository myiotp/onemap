/**
* 用法：
* var setting = {
* 		id : "inputId", //要使用弹出层选择省市区功能的input文本框ID
* 		countySync : true, //true:根据城市ID查询区县; false: 一次性查询所有区县
* 		isShowProvince : true, //选择省份后是否立即显示到文本框
* 		isShowCity : true, //选择城市后是否立即显示到文本框
* 		proId : $("#proId"), //要设置省份ID的文本框
*		proName : $("#proName"), //要设置省份名称的文本框
*		cityId : $("#cityId"), //要设置城市ID的文本框
*		cityName : $("#cityName"), //要设置城市名称的文本框
*		countyId : $("#countyId"), //要设置区县ID的文本框
*		countyName : $("#countyName"), //要设置区县名称的文本框
* 		proUrl : "../common/queryProvince.action", //获取省份数据的访问地址
* 		cityUrl : "../common/queryCities.action", //获取城市数据的访问地址
* 		countyUrl : "../common/queryCounties.action", //获取区县数据的访问地址
*		countyByCityUrl : "../common/queryCountyByCity.action" //根据城市ID获取区县的访问地址
* }
* new $.district(setting).init(); //创建对象并初始化
* 
* 
* 
* @version 1.0
*/

(function($) {

    $.district = function(setting) {
        //数据集合
        var provinces = [];
        var cities = [];
        var counties = [];
        var hotCities = [];
        var currentObj = null;
        var proCurPage = 1;
        var cityCurPage = 1;
        var countyCurPage = 1;
        var pageSize = 12;
        //选中的省市区
        var selectedPro;
        var selectedCity;
        var selectedCounty;
        var selectedProId;
        var selectedCityId;
        var selectedCountyId;
        //省市区访问地址
        var proUrl;
        var cityUrl;
        var countyUrl;
        var countyByCityUrl;
        var hotCityUrl;

        var _dom_prefix = "<div class='district provinceCity ";
        var _dom_suffix =
			  "' style='display: none; z-index: 6666;'>"
				+ "<div class='tabs clearfix'>"
					+ "<ul class=''>"
						+ "<li><a href='javascript:' tb='hotCity' id='hotCity' class='current'>热门城市</a></li>"
						+ "<li><a href='javascript:' tb='province' id='province'>省份</a></li>"
						+ "<li><a href='javascript:' tb='city' id='city'>城市</a></li>"
						+ "<li><a href='javascript:' tb='county' id='county'>区县</a></li>"
					+ "</ul>"
				+ "</div>"
				+ "<div class='conu'>"
					+ "<div class='hotCity'>"
						+ "<div class='list'>"
							+ "<ul>"
							+ "</ul>"
						+ "</div>"
					+ "</div>"

					+ "<div class='province'>"
						+ "<div tb='pre' class='pre pageu'><a tb='province' style='cursor:pointer'></a></div>"
						+ "<div class='list'>"
							+ "<ul>"
							+ "</ul>"
						+ "</div>	"
						+ "<div tb='next' class='next pageu'><a tb='province' class='can'  style='cursor:pointer'></a></div>"
					+ "</div>"

					+ "<div class='city'>"
						+ "<div tb='pre' class='pre pageu'><a tb='city'  style='cursor:pointer'></a></div>"
						+ "<div class='list'>"
							+ "<ul>"
							+ "</ul>"
						+ "</div>"
						+ "<div tb='next' class='next pageu'><a tb='city' class='can'  style='cursor:pointer'></a></div>"
					+ "</div>"

					+ "<div class='county'>"
						+ "<div tb='pre' class='pre pageu'><a tb='county' style='cursor:pointer'></a></div>"
						+ "<div class='list'>"
							+ "<ul>"
							+ "</ul>"
						+ "</div>"
						+ "<div tb='next' class='next pageu'><a tb='county' class='can'  style='cursor:pointer'></a></div>"
					+ "</div>"

				+ "</div>"
			+ "</div>";

        /**
        * 查询省份
        * @returns
        */
        var queryProvinces = function() {
            if ($("body").data("provinces")) {
                provinces = $("body").data("provinces");
            } else {
                $.ajax({
                    type: "get",
                    url: proUrl,
                    async: false,
                    dataType: "json",
                    success: function(data) {
                        provinces = data;
                        $("body").data("provinces", data);
                    },
                    error: function(data) {
                        //alert("查询省份出错！");
                    }
                });
            }
        };

        /**
        * 查询热门城市（从城市列表里获取）
        * @returns
        */
        var queryHotCities = function() {
            if ($("body").data("hotcities")) {
                hotCities = $("body").data("hotcities");
            } else {
                $.ajax({
                    type: "get",
                    url: hotCityUrl,
                    async: false,
                    dataType: "json",
                    success: function(data) {
                        hotCities = data;
                        $("body").data("hotcities", data);
                    },
                    error: function(data) {
                        alert(data);
                    }
                });
            }
        };

        /**
        * 查询城市
        * @returns
        */
        var queryCities = function(proId) {
            if ($("body").data("cities")) {
                cities = $("body").data("cities");
            } else {
                $.ajax({
                    type: "get",
                    url: cityUrl,
                    data: { "provinceId": proId },
                    async: false,
                    dataType: "json",
                    success: function(data) {
                        cities = data;
//                        $("body").data("cities", data);
                    },
                    error: function(data) {
                        alert(data);
                    }
                });
            }
        };

        /**
        * 根据省份ID查询城市
        * @param proId 省份ID
        * @returns
        */
        var queryCityByPro = function(proId) {
            if (!$("body").data("cities")) {
                queryCities(proId);
            }
            queryCities(proId);
            //            var list = $("body").data("cities");
            //            var index = 0;
            //            cities = [];
            //            $.each(list, function (i, city) {
            //                if (city.Code == proId) {
            //                    cities[index++] = city;
            //                }
            //            });
        };

        /**
        * 查询区县
        * @returns
        */
        var queryCounties = function() {
            if ($("body").data("counties")) {
                counties = $("body").data("counties");
            } else {
                $.ajax({
                    type: "get",
                    url: countyUrl,
                    async: false,
                    dataType: "json",
                    success: function(data) {
                        counties = data.counties;
                        $("body").data("counties", counties);
                    },
                    error: function(data) {
                        alert(data);
                    }
                });
            }
        };

        /**
        * 根据城市ID查询区县
        * @param cityId 城市ID
        * @returns
        */
        var queryCountyByCity = function(cityId) {
            if (setting.countySync) {
                $.ajax({
                    type: "get",
                    url: countyByCityUrl,
                    async: false,
                    dataType: "json",
                    data: { "cityId": cityId },
                    success: function(data) {
                        counties = data;
                        $("body").data(counties);
                    },
                    error: function(data) {
                        alert("查询区县出错！");
                    }
                });
            } else {
                if (!$("body").data("counties")) {
                    queryCounties();
                }
                list = $("body").data("counties");
                var index = 0;
                counties = [];
                $.each(list, function(i, county) {
                    if (cityId == county.cityId) {
                        counties[index++] = county;
                    }
                });
            }
        };

        /**
        * 显示数据
        * 参数：
        * currentObj: 添加到HTML中的当前弹出层对象
        * list: 要添加的数据
        * pageNum: 页数
        * totalPage: 总页数
        * type: 数据分类：省份、城市、区县
        */
        var viewData = function(currentObj, list, pageNum, totalPage, type) {
            //控制样式
            if (pageNum == 1) {
                currentObj.find(".conu" + " ." + type + " .pre a").removeClass("can");
            } else {
                currentObj.find(".conu" + " ." + type + " .pre a").addClass("can");
            }
            if (pageNum == totalPage) {
                currentObj.find(".conu" + " ." + type + " .next a").removeClass("can");
            } else {
                currentObj.find(".conu" + " ." + type + " .next a").addClass("can");
            }
            //加载数据
            currentObj.find(".conu" + " ." + type + " .list ul").empty();
            if (!list || list.length < 1) {
                currentObj.hide();
                return false;
            }
            $.each(list, function(i, obj) {
                var li = "<li title='" + obj.name + "' type='" + type + "'><a style='font-size:12px;' href='javascript:void(0);'";
                //如果是热门城市，把省份名称设置进去，方便显示
                if (type == "hotCity") {
                    li = li + " pro='" + obj.name + "' proId='" + obj.provinceId + "'";
                }

                var objName = obj.name;
                if ("province" == type) {
                    if (obj.name == '内蒙古自治区') {
                        objName = '内蒙古';
                    } else if (obj.name == '黑龙江省') {
                        objName = '黑龙江';
                    } else {
                        objName = obj.name.substring(0, 2);
                    }
                } else {
                    if (obj.name.length > 3) {
                        objName = obj.name.substring(0, 3);
                    }
                }

                li = li + " id='" + obj.id + "'>" + objName + "</a></li>";
                currentObj.find(".conu" + " ." + type + " .list ul").append(li);
            });

            if ("province" == type) {
                proCurPage = pageNum;
            } else if ("city" == type) {
                cityCurPage = pageNum;
            } else if ("county" == type) {
                countyCurPage = pageNum;
            }
            //切换
            changeTab(currentObj, type);
        };

        /**
        * 切换
        * @param currentObj: 添加到HTML中的当前弹出层对象
        * @param tab: 要切换到的TAB
        * @returns
        */
        var changeTab = function(currentObj, tab) {
            currentObj.find(".tabs").find("li a").removeClass("current");
            currentObj.find(".tabs").find("#" + tab).addClass("current");
            currentObj.find(".conu").children().hide();
            currentObj.find(".conu").find("div." + tab).show();
        };

        /**
        * 获取指定页数的数据
        * @param list 数据集合
        * @param pageSize 每页数量大小
        * @param pageNum 页数
        * @returns
        */
        var getPageData = function(list, pageSize, pageNum) {
            var obj = [];
            var start = (pageNum - 1) * pageSize;
            var end = pageNum * pageSize
            var index = 0;
            $.each(list, function(i, o) {
                if (i >= start && i < end) {
                    obj[index++] = list[i];
                }
            });
            return obj;
        };

        //仿时间控件获取CSS路径
        var districtX = window,
		districtM = "document",
		districtC = "getElementsByTagName";
        var districtK = function(A, $, B) {
            var districtD = districtX[districtM][districtC]("HEAD").item(0),
	        _ = districtX[districtM].createElement("link");
            if (districtD) {
                _.href = A;
                _.rel = "stylesheet";
                _.type = "text/css";
                districtD.appendChild(_)
            }
        }

        /**
        * 初始化
        */
        this.init = function() {
            //添加弹出层DOM到页面
            $("body").append(_dom_prefix + setting.id + _dom_suffix);


            $("script").each(function() {
                var flag = false;
                var path = $(this).attr("src");
                if (path && path.indexOf("district.js") > -1) {
                    $("link").each(function() {
                        var cssPath = $(this).attr("href");
                        if (cssPath.indexOf("master.css") > -1) {
                            flag = true;
                            return false;
                        }
                    });
                    if (!flag) {
                        path = path.substring(0, path.lastIndexOf("/"));
                        districtK(path + "/theme/master.css");
                        return false;
                    }
                }
            });
            //设置查询链接
            proUrl = setting.proUrl ? setting.proUrl : "/FeedBack/getProvinces";
            cityUrl = setting.cityUrl ? setting.cityUrl : "/FeedBack/getCity";
            countyUrl = setting.countyUrl ? setting.countyUrl : "/FeedBack/getDistrict";
            countyByCityUrl = setting.countyByCityUrl ? setting.countyByCityUrl : "/FeedBack/getDistrictByCity";
            hotCityUrl = setting.hotCityUrl ? setting.hotCityUrl : "/FeedBack/gethotCity";
            //获取当前弹出层对象
            currentObj = $("." + setting.id);
            //显示省份数据
            queryProvinces();
            var totalPage = Math.ceil(provinces.length / pageSize);
            var list = getPageData(provinces, pageSize, 1);
            viewData(currentObj, list, 1, totalPage, "province");

            //初始化城市、热门城市数据
            //     queryCities();
            queryHotCities();
            //区县数据可以配置为全部加载或者根据城市ID查询
            if (!setting.countySync) {
                queryCounties();
            }
            //显示热门城市
            viewData(currentObj, hotCities, 1, 1, "hotCity");
            //给文本框绑定事件，点击事弹出选择层
            $("#" + setting.id).live("click", function(event) {
                $(".district").hide();
                $(this).select();
                var o = $(this).offset();
                var l = o.left;
                var t = o.top;
                var h = $(this).height();
                currentObj.css("top", t + h - 1).css("left", l).toggle();

                changeTab(currentObj, "hotCity");       //显示当前的
                //  changeTab(currentObj, "province");
                event.stopPropagation();
            });

            //添加按键事件，当前文本框为readonly的时候清除文本框里的内容
            $("#" + setting.id).live("keydown", function(event) {
                if ($(this).attr("readonly") && event.keyCode == 8) {
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
                event.stopPropagation();
            });

            //文本框失去焦点时如果内容为空则清除隐藏文本框的内容
            $("#" + setting.id).live("blur", function(event) {
                if (!$.trim($("#" + setting.id).val())) {

                    if (setting.proId) {
                        setting.proId.val("");
                    }
                    if (setting.proName) {
                        setting.proName.val("");
                    }
                    if (setting.cityId) {
                        setting.cityId.val("");
                    }
                    if (setting.cityName) {
                        setting.cityName.val("");
                    }
                    if (setting.countyId) {
                        setting.countyId.val("");
                    }
                    if (setting.countyName) {
                        setting.countyName.val("");
                    }
                }

                event.stopPropagation();
            });

            //TAB切换
            currentObj.find(".tabs li").live("click", function(event) {
                var tb = $(this).find("a").attr("tb")
                var lis = currentObj.find(".conu").find("." + tb).find(".list li");
                if (lis.length > 0) {
                    changeTab(currentObj, tb);
                }
                event.stopPropagation();
            });

            //点击其他地方时隐藏弹出层
            $("html").live("click", function(event) {
            $(".district").hide();
            event.stopPropagation();
                /*if (event.srcElement.tagName == "div" || event.srcElement.tagName == "A") {
                    $(".district").hide();
                    event.stopPropagation();
                }
                else {
                    $(".district").hide();
                    event.stopPropagation();
                }*/
            });

            /**
            * 热门城市点击事件
            */
            currentObj.find(".conu .hotCity .list ul li").live("click", function(event) {
                selectedProId = $(this).find("a").attr("proId");
                selectedCityId = $(this).find("a").attr("id");
                $(this).parent().find("a").removeClass("current");
                $(this).find("a").addClass("current");
                selectedPro = $(this).find("a").attr("pro");
                selectedCity = $(this).find("a").text();
                if (setting.proId) {
                    setting.proId.val(selectedProId);
                }
                if (setting.proName) {
                    setting.proName.val(selectedPro);
                }
                if (setting.cityId) {
                    setting.cityId.val(selectedCityId);
                }
                if (setting.cityName) {
                    setting.cityName.val(selectedCity);
                }

                //清楚区县的数据
                if (setting.countyId) {
                    setting.countyId.val("");
                }
                if (setting.countyName) {
                    setting.countyName.val("");
                }

                queryCountyByCity(selectedCityId);
                var totalPage = Math.ceil(counties.length / pageSize);
                var list = getPageData(counties, pageSize, 1);
                viewData(currentObj, list, 1, totalPage, "county");
                if (setting.isShowCity) {
                    if (selectedPro == selectedCity) {
                        $("#" + setting.id).attr("value", selectedPro);
                    } else {
                        $("#" + setting.id).attr("value", selectedPro + "-" + selectedCity);
                    }
                }
                //热门城市选择回调函数
                if (setting.hotCityBack) {
                    setting.hotCityBack({
                        proId: selectedProId,
                        proName: selectedPro,
                        cityId: selectedCityId,
                        cityName: selectedCity
                    });
                }
                $("#" + setting.id).focus();
                event.stopPropagation();
                getjieguo();
            });

            /**
            * 省份点击事件
            */
            currentObj.find(".conu .province .list ul li").live("click", function(event) {
                selectedProId = $(this).find("a").attr("id");
                $(this).parent().find("a").removeClass("current");
                $(this).find("a").addClass("current");
                //				selectedPro = $(this).find("a").text();
                selectedPro = $.trim($(this).attr("title"));
                if (setting.proId) {
                    setting.proId.val(selectedProId);
                }
                if (setting.proName) {
                    setting.proName.val(selectedPro);
                }

                //清楚城市和区县的数据
                if (setting.cityId) {
                    setting.cityId.val("");
                }
                if (setting.cityName) {
                    setting.cityName.val("");
                }
                if (setting.countyId) {
                    setting.countyId.val("");
                }
                if (setting.countyName) {
                    setting.countyName.val("");
                }

                queryCityByPro(selectedProId);
                var totalPage = Math.ceil(cities.length / pageSize);
                var list = getPageData(cities, pageSize, 1);
                viewData(currentObj, list, 1, totalPage, "city");
                if (setting.isShowProvince) {
                    $("#" + setting.id).attr("value", selectedPro);
                    $("#" + setting.id).attr("title", selectedPro);
                }
                //省份选择回调函数
                if (setting.proBack) {
                    setting.proBack({
                        proId: selectedProId,
                        proName: selectedPro
                    });
                }
                $("#" + setting.id).focus();
                event.stopPropagation();
                getjieguo();
            });

            /**
            * 城市点击事件
            */
            currentObj.find(".conu .city .list ul li").live("click", function(event) {
                selectedCityId = $(this).find("a").attr("id");
                $(this).parent().find("a").removeClass("current");
                $(this).find("a").addClass("current");
                //				selectedCity = $(this).find("a").text();
                selectedCity = $(this).attr("title");
                if (setting.cityId) {
                    setting.cityId.val(selectedCityId);
                }
                if (setting.cityName) {
                    setting.cityName.val(selectedCity);
                }
                //清楚区县的数据
                if (setting.countyId) {
                    setting.countyId.val("");
                }
                if (setting.countyName) {
                    setting.countyName.val("");
                }

                queryCountyByCity(selectedCityId);
                var totalPage = Math.ceil(counties.length / pageSize);
                var list = getPageData(counties, pageSize, 1);
                viewData(currentObj, list, 1, totalPage, "county");
                if (setting.isShowCity) {
                    if (selectedPro == selectedCity) {
                        $("#" + setting.id).attr("value", selectedPro);
                        $("#" + setting.id).attr("title", selectedPro);
                    } else {
                        $("#" + setting.id).attr("value", selectedPro + "-" + selectedCity);
                        $("#" + setting.id).attr("title", selectedPro + "-" + selectedCity);
                    }
                }
                //城市选择回调函数
                if (setting.cityBack) {
                    setting.cityBack({
                        proId: selectedProId,
                        proName: selectedPro,
                        cityId: selectedCityId,
                        cityName: selectedCity
                    });
                }
                $("#" + setting.id).focus();
                event.stopPropagation();
                getjieguo();
            });

            /**
            * 区县点击事件
            */
            currentObj.find(".conu .county .list ul li").live("click", function(event) {
                selectedCountyId = $(this).find("a").attr("id");
                $(this).parent().find("a").removeClass("current");
                $(this).find("a").addClass("current");
                //				selectedCounty = $(this).find("a").text();
                selectedCounty = $(this).attr("title");
                if (setting.countyId) {
                    setting.countyId.val(selectedCountyId);
                }
                if (setting.countyName) {
                    setting.countyName.val(selectedCounty);
                }
                if (selectedPro == selectedCity) {
                    $("#" + setting.id).attr("value", selectedPro + "-" + selectedCounty);
                    $("#" + setting.id).attr("title", selectedPro + "-" + selectedCounty);
                } else {
                    $("#" + setting.id).attr("value", selectedPro + "-" + selectedCity + "-" + selectedCounty);
                    $("#" + setting.id).attr("title", selectedPro + "-" + selectedCity + "-" + selectedCounty);
                }
                currentObj.hide();
                //区县选择回调函数
                if (setting.countyBack) {
                    setting.countyBack({
                        proId: selectedProId,
                        proName: selectedPro,
                        cityId: selectedCityId,
                        cityName: selectedCity,
                        countyId: selectedCountyId,
                        countyName: selectedCounty
                    });
                }
                $("#" + setting.id).focus();
                event.stopPropagation();
                getjieguo();
            });

            /**
            * 翻页点击事件
            */
            currentObj.find(".conu .pageu a").live("click", function(event) {
                if (!$(this).hasClass("can")) {
                    event.stopPropagation();
                    return false;
                }
                var clickPage = $(this).parent().attr("tb");
                var totalPage;
                var list;
                var pageNum;
                var clazz = $(this).attr("tb");
                if (clazz == "province") {
                    pageNum = clickPage == "pre" ? (proCurPage - 1) : (proCurPage + 1);
                    totalPage = Math.ceil(provinces.length / pageSize);
                    list = getPageData(provinces, pageSize, pageNum);
                } else if (clazz == "city") {
                    pageNum = clickPage == "pre" ? (cityCurPage - 1) : (cityCurPage + 1);
                    totalPage = Math.ceil(cities.length / pageSize);
                    list = getPageData(cities, pageSize, pageNum);
                } else if (clazz == "county") {
                    pageNum = clickPage == "pre" ? (countyCurPage - 1) : (countyCurPage + 1);
                    totalPage = Math.ceil(counties.length / pageSize);
                    list = getPageData(counties, pageSize, pageNum);
                }
                //显示数据
                viewData(currentObj, list, pageNum, totalPage, clazz);
                event.stopPropagation();
            });

        };
    };

})(jQuery);
