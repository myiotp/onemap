var app = angular.module('main', [ 'ngTable', 'ngResource' ]).controller(
		'TableCtrl',
		function($scope, $http, $filter, $q, $timeout, $resource, ngTableParams) {
			var Api = $resource('./ngtable');

			$scope.tableParams = new ngTableParams({
				page : 1, // show first page
				count : 10, // count per page
				sorting : {
					'id' : 'asc' // initial sorting
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
			
			var inArray = Array.prototype.indexOf ?
            function (val, arr) {
                return arr.indexOf(val)
            } :
            function (val, arr) {
                var i = arr.length;
                while (i--) {
                    if (arr[i] === val) return i;
                }
                return -1;
            };
            
		    $scope.names = function(column) {
		        var def = $q.defer(),
		            arr = [],
		            names = [];
		        angular.forEach(data, function(item){
		            if (inArray(item.name, arr) === -1) {
		                arr.push(item.name);
		                names.push({
		                    'id': item.name,
		                    'title': item.name
		                });
		            }
		        });
		        def.resolve(names);
		        return def;
		    };
		    
			$scope.showDetailDialog2 = function(id) {	
				//("id:" + id);
				var url = encodeURI('./id/' + id);
		
			// 	// Get the CSV and create the chart
				$
					.getJSON(
								url,
								function(responsedata) {
								//(responsedata);
					$('#seedType').html("车牌号："+responsedata.licenseplate);
          			$('#purchaseTime').html("发动机号："+(responsedata.enginenumber));
					$('#name').html("登记地(市区)："+responsedata.fromname);
					$('#purchaseAmount').html("登记地(详细)："+responsedata.fromAddress);
					$('#purchaser').html("车辆品牌："+responsedata.vehiclebrand);
					$('#serialNumber').html("车辆类型："+responsedata.vehicletype);
					$('#level').html("货箱长度："+responsedata.cargolength);
					$('#producer').html("车辆载重(吨)："+responsedata.vehicleweight);
					$('#producerTel').html("年检截止日期："+responsedata.checkdeadline);
					$('#seller').html("保险截止日期："+responsedata.insurancedeadline);
					$('#sellerTel').html("证件：<br><image src='"+responsedata.certimage+"'/>");
					//$('#brief').html("品种简介：<br>"+responsedata.brief);
					$('#dialog-showDetail').dialog('open');
					return false;
								
				});
				
			};
			
			$scope.approveDialog = function(id){
				$('#id').val(id);
				$('#dialog-approve').dialog('open');
				return false;
			};
			$scope.rejectDialog = function(id){
				$('#id2').val(id);
				$('#dialog-reject').dialog('open');
				return false;
			};
		});