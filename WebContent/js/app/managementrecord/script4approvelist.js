var app = angular.module('main', [ 'ngTable', 'ngResource' ]).controller(
		'TableCtrl',
		function($scope, $http, $filter, $timeout, $resource, ngTableParams) {
			var Api = $resource('./ngtable4approvelist');

			$scope.tableParams = new ngTableParams({
				page : 1, // show first page
				count : 10, // count per page
				filter : {
					'approvelevel' : approvelevel// level=0
				},
				sorting : {
					'ordernumber' : 'desc', // initial sorting
					'approvelevel': 'asc'
				}
			}, {
				total : 0, // length of data
				getData : function($defer, params) {
					// ajax request to api
					Api.get(params.url(), function(data) {
						// update table params
						params.total(data.total);
						// set new data
						$defer.resolve(data.result);
					});
				}
			});
			$scope.showDetailDialog0 =function(id) {	
				var url = encodeURI('../api/cargoes/' + id);
		
			// 	// Get the CSV and create the chart
				$
					.getJSON(
								url,
								function(responsedata) {
								//(responsedata);
									responsedata = responsedata.data;				
					$('#item1').html("货主姓名："+(responsedata.cargoOwner));
					$('#item2').html("联系方式："+responsedata.ownerCellphone);
					$('#item3').html("货物名称："+responsedata.cargoName);
					$('#item4').html("货物重量(吨)："+responsedata.cargoWeight);
					$('#item5').html("装货时间："+responsedata.shipTimestamp);
					$('#item6').html("装货地址："+responsedata.fromname);
					$('#item7').html("卸货地址："+responsedata.toname);
					$('#item8').html("装货地址(详细)："+responsedata.fromname + responsedata.fromAddress);
					$('#item9').html("卸货地址(详细)："+responsedata.toname + responsedata.toAddress);
					$('#item10').html("运价："+responsedata.price);
					$('#item11').html("付款方式："+responsedata.payment);
					$('#item12').html("运输时效："+responsedata.validDays);
					$('#item13').html("微信："+responsedata.wechat);
					$('#item14').html("紧急联系人："+responsedata.emergencyContact);
					$('#item15').html("紧急联系人电话："+responsedata.emergencyCellphone);
					$('#item16').html("备注："+responsedata.memo);
					$('#dialog0-showDetail').dialog('open');
					return false;
								
				});
			};
			$scope.showDetailDialog1 =function(id) {	
				var url = encodeURI('../api/truck/' + id);
		
			// 	// Get the CSV and create the chart
				$
					.getJSON(
								url,
								function(responsedata) {
								//(responsedata);
									responsedata = responsedata.data;		
									$('#aitem1').html("车主姓名："+(responsedata.owner));
									$('#aitem2').html("联系方式："+responsedata.ownerCellphone);
									$('#aitem3').html("车牌号："+responsedata.licenseplate);
									$('#aitem4').html("车辆："+responsedata.truckBarnd + responsedata.carType );
									$('#aitem41').html("货箱长度："+ responsedata.carLength);
									$('#aitem5').html("装货时间："+responsedata.shipTimestamp);
									$('#aitem6').html("出发地："+responsedata.fromname);
									$('#aitem7').html("目的地："+responsedata.toname);
									//$('#aitem8').html("出发地(详细)："+responsedata.fromname + responsedata.fromAddress);
									//$('#aitem9').html("目的地(详细)："+responsedata.toname + responsedata.toAddress);
									$('#aitem10').html("运价："+responsedata.price);
									$('#aitem11').html("付款方式："+responsedata.payment);
									$('#aitem12').html("运输时效："+responsedata.validDays);
									$('#aitem13').html("微信："+responsedata.wechat);
									$('#aitem14').html("紧急联系人："+responsedata.emergencyContact);
									$('#aitem15').html("紧急联系人电话："+responsedata.emergencyCellphone);
									$('#aitem16').html("备注："+responsedata.memo);
									$('#dialog1-showDetail').dialog('open');
					return false;
								
				});
			};
			$scope.showDetailDialog2 =function(id) {	
				var url = encodeURI('./id/' + id);
		
			// 	// Get the CSV and create the chart
				$
					.getJSON(
								url,
								function(responsedata) {
								//(responsedata);
					$('#operationTime').html("操作时间："+getDefaultFormatDateByLong(responsedata.operationTime));
					$('#operationType').html("操作类型："+responsedata.operationType);
					$('#operator').html("操作人："+responsedata.operator);
					$('#blockNumber').html("地块编号："+responsedata.blockNumber);
					$('#cropType').html("作物类型："+responsedata.cropType);
					$('#mgtrecordmemo').html(responsedata.mgtrecordmemo);
					$('#dialog-showDetail').dialog('open');
					return false;
								
				});
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
			};
			$scope.approveDialog = function(id, level){
				$('#id').val(id);
				$('#level').val(level);
				$('#dialog-approve').dialog('open');
				return false;
			};
			$scope.submit4approveDialog = function(id, approvelevel){
				$('#id2').val(id);
				$('#approvelevel').val(approvelevel);
				$('#dialog-submit4approve').dialog('open');
				return false;
			};
			
		});