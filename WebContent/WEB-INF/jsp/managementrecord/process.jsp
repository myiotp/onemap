<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>作物生产过程回溯图表</title>
<script>
	var block = '<%=request.getParameter("blockId")%>';	
	var cropTypeId = '<%=request.getParameter("cropTypeId")%>';	
	var chartsUrl = "../managementrecord/block/"+block+"/crop/"+cropTypeId+"/charts/csv";
	$(function(){
		$("#blockPanelProcessClose").click(function(){
			$("#blockPanelProcess").parent().hide();
		});
		
		$.get(chartsUrl,{
			blockId:block,
			cropTypeId:cropTypeId
		},function(data){
			//$(".bigrect").addClass("gray");
			var all = [];
			for(var i=0; i<data.type.length;i++){
				var obj = {
					type:data.type[i],
					id:data.id[i],
					time:data.time[i],
					operationType:data.operationType[i]
				}
				all.push(obj);
			}
			all.sort(function(a,b){
				return new Date(a.time).getTime()-new Date(b.time).getTime();
			});
			var temp = $("<div style='display:none'></div>").appendTo($("body"));
			for(var i=0; i<all.length;i++){
				
				//添加间隔
				var time = new Date(all[i].time);
				if(time.getTime()<new Date().getTime()){
					temp.append($("#"+cropTypeId+"_"+block+"_"+all[i].type));
					$("#"+cropTypeId+"_"+block+"_"+all[i].type+" .processTextDate").text(new Date(all[i].time).format("yyyy-MM-dd"));
					//添加点击事件
					$("#"+cropTypeId+"_"+block+"_"+all[i].type).click({type:all[i].type,id:all[i].id},function(evt){
						onSelectBigBlock(evt.data);
					});
					//$("#"+cropTypeId+"_"+block+"_"+all[i].type).removeClass("gray");
					var img = $("#"+cropTypeId+"_"+block+"_"+all[i].type+" img");
					img.attr("src", img.attr("src").replace("gray","light"));
					if(i>0){
						var preTime = new Date(all[i-1].time);
						var days = (time-preTime)/3600/24/1000;
						if(days>0){
							buildTimeInteval(days,all[i].type);
						}
					}
				}else{
					buildTimeInteval("今",all[i].type);
					break;
				}
			}
			
			temp.children().prependTo(".blockPanelProcessLeft");
			temp.remove();
			if(all.length>0){
				onSelectBigBlock(all[0]);
			}
		});
		
		function buildTimeInteval(days,type){
			var tmplate = '<div><div class="vlineout"><div class="vline"></div></div><div class="smallrect">'+days+'天</div><div class="vlineout"><div class="vline"></div></div></div>';
			$("#"+cropTypeId+"_"+block+"_"+type).before($(tmplate));
		}
		
		function onSelectBigBlock(data){
			$(".bigrect").removeClass("bigrectSelect");
			$("#"+cropTypeId+"_"+block+"_"+data.type).addClass("bigrectSelect");
			var url = "../managementrecord/id/"+data.id;
			$.getJSON(url,function(mr){
				$("#"+cropTypeId+"_"+block+"_operationType").text(mr.operationType);
				$("#"+cropTypeId+"_"+block+"_operationTime").text(new Date(mr.operationTime).format("yyyy-MM-dd"));
				$("#"+cropTypeId+"_"+block+"_operator").text(mr.operator);
				$("#"+cropTypeId+"_"+block+"_blockNumber").text(mr.blockNumber);
				$("#"+cropTypeId+"_"+block+"_cropType").text(mr.cropType);
				$("#"+cropTypeId+"_"+block+"_mgtrecordmemo").html(mr.mgtrecordmemo);
				$("#"+cropTypeId+"_"+block+"_mgtrecordmemo"+" .MsoNormal span").attr("style","")
			});
		}
		
	})
</script>
<style>
	#blockPanelProcess{
		 background-color: #fff;
	}
	
	#blockPanelProcess .blockPanelTitle{
		padding: 10px 10px 10px 30px;
  		border-bottom: solid #b5b5b6 1px;
	}
	
	#blockPanelProcess .blockPanelTitle span{
		color: #898989;
  		font-size: 14px;
	}
	
	#blockPanelProcess .blockPanelTitle img{
		float: right;
	}
	
	#blockPanelProcess .blockPanelTitle img:hover{
		cursor: pointer;
	}
	
	#blockPanelProcess .blockPanelContent{
		 float: left;
	}
	
	#blockPanelProcess .blockbolder{
	     padding: 10px 5px 5px 10px;
		  height:570px;
	      overflow-y: scroll;
	      overflow-x:hidden;
	}
	
	#blockPanelProcess .blockPanelProcessLeft{
		float: left;
		text-align: center;
		margin: 0px 30px 0px 20px;
	} 
	
	#blockPanelProcess .blockPanelProcessRight{
		float: left;
	}
	
	#blockPanelProcess .bigrect
	{
		  width: 120px;
		  height: 60px;
		  background-color: #f7f8f8;
		  border: solid 1px #dcdddd;
		  border-radius: 4px;
	}
	
	#blockPanelProcess .bigrect:hover
	{
		  border: solid 2px #8fc31f;
	}
	
	#blockPanelProcess .bigrectSelect
	{
		  border: solid 2px #8fc31f;
	}
	
	#blockPanelProcess .bigrect:hover
	{
		  cursor:pointer;
	}
	
	#blockPanelProcess .processIcon
	{
		 float:left;
		 margin: 7px 5px 7px 7px;
		 width:35px;
	}
	
	#blockPanelProcess .processText
	{
		 float:left;
		 margin-top: 13px;
	}
	
	#blockPanelProcess .smallrect
	{
		  width: 64px;
		  height: 19px;
		  line-height:17px;
		  background-color: #f7f8f8;
		  border: solid 1px #dcdddd;
		  border-radius: 8px;
		  text-align: center;
		  display: inline-block;
	}
	
	#blockPanelProcess .vline{
		height:10px;
		width:1px;
		background-color:#dcdddd;
		display: inline-block;
	}
	
	.vlineout{
		height: 10px;
	    text-align: center;
	    line-height: 10px;
    }
    
    .blockPanelProcessRight div{
		height: 25px;
	}

	.blockPanelProcessRight .lable{
		width: 100px;
		color: #898989;
		float: left;
		line-height:25px;
		display:inline;
	}
	.blockPanelProcessRight .value{
		padding-right:20px;
		text-align: left;
		float: left;
		line-height:25px;
		display:inline;
		color: #898989;
	}
	.blockPanelProcessRight img{
	 	max-width:250px;
	 	padding-top: 5px;
	}
	.description{
		width:240px;
	}
	
	.gray { 
	  -webkit-filter: grayscale(100%);
	  -ms-filter: grayscale(100%);
	  -o-filter: grayscale(100%);
	  filter: url("data:image/svg+xml;utf8,<svg xmlns=\'http://www.w3.org/2000/svg\'><filter id=\'grayscale\'><fecolormatrix type=\'matrix\' values=\'0.3333 0.3333 0.3333 0 0 0.3333 0.3333 0.3333 0 0 0.3333 0.3333 0.3333 0 0 0 0 0 1 0\'></fecolormatrix></filter></svg>#grayscale"); /* Firefox 10+, Firefox on Android */
	  filter: grayscale(100%);
	  filter: gray;
	}
	
	
</style>
	
</head>
<body>
	<div id="blockPanelProcess">
		<div class="blockPanelTitle">
			<span>作物生产过程回溯</span>
			<img id="blockPanelProcessClose" alt="关闭" src="../images/close.png">
		</div>
		<div class="blockbolder">
		<div class="blockPanelContent">
			<div class="blockPanelProcessLeft">
				<div class="bigrect" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_1">
					<img class="processIcon" src="../images/lifecycle/gray/cetu.png"></img>
					<div class="processText">
						<div class="processTextValue">测土配方</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				
				<div class="bigrect" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_2">
					<img class="processIcon" src="../images/lifecycle/gray/gengdi.png"></img>
					<div class="processText">
						<div class="processTextValue">耕地</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_3">
					<img class="processIcon" src="../images/lifecycle/gray/shifei.png"></img>
					<div class="processText">
						<div class="processTextValue">施肥</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_4">
					<img class="processIcon" src="../images/lifecycle/gray/bozhong.png"></img>
					<div class="processText">
						<div class="processTextValue">播种</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_5">
					<img class="processIcon" src="../images/lifecycle/gray/yizhi.png"></img>
					<div class="processText">
						<div class="processTextValue">移植</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_6">
					<img class="processIcon" src="../images/lifecycle/gray/binghai.png"></img>
					<div class="processText">
						<div class="processTextValue">病害防治</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_7">
					<img class="processIcon" src="../images/lifecycle/gray/chonghai.png"></img>
					<div class="processText">
						<div class="processTextValue">虫害防治</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_8">
					<img class="processIcon" src="../images/lifecycle/gray/caohai.png"></img>
					<div class="processText">
						<div class="processTextValue">草害防治</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_9">
					<img class="processIcon" src="../images/lifecycle/gray/guangai.png"></img>
					<div class="processText">
						<div class="processTextValue">灌溉</div>
						<div class="processTextDate"></div>
					</div>
				</div>
				<div class="bigrect"  id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_10">
					<img class="processIcon" src="../images/lifecycle/gray/shouhuo.png"></img>
					<div class="processText">
						<div class="processTextValue">收获</div>
						<div class="processTextDate"></div>
					</div>
				</div>
			</div>
			<div class="blockPanelProcessRight">
			 	<div>
		   			<div class="lable">操作类型:</div>
		   			<div class="value" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_operationType"></div>
		   		</div>
		   		<div>
		   			<div class="lable">操作时间:</div>
		   			<div class="value" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_operationTime"></div>
		   		</div>
		   		<div>
		   			<div class="lable">操作人员:</div>
		   			<div class="value" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_operator"></div>
		   		</div>
		   		<div>
		   			<div class="lable">操作编号:</div>
		   			<div class="value" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_blockNumber"></div>
		   		</div>
		   		<div>
		   			<div class="lable">作物类型:</div>
		   			<div class="value" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_cropType"></div>
		   		</div>
		   		<div>
		   			<div>描述:</div><br>
		   			<div class="description" id="<%=request.getParameter("cropTypeId")%>_<%=request.getParameter("blockId")%>_mgtrecordmemo"></div>
		   		</div>
		   				
			</div>
		</div>
	</div>
</div>
</body>
</html>
