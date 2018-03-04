var app = angular.module('main', [ 'ngTable', 'ngResource' ]).controller(
		'TableCtrl',
		function($scope, $http, $filter, $q, $timeout, $resource, ngTableParams) {
			var Api = $resource('./ngtable?type=100');

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
		    
			$scope.showDetailDialog = function(imageurl) {	
				$('#seedType').html("<image src='"+imageurl+"'/>");
				
//				var url = encodeURI('./id/' + id);
		
			// 	// Get the CSV and create the chart
//				$
//					.getJSON(
//								url,
//								function(responsedata) {
								//(responsedata);
					
//          			$('#purchaseTime').html("采购时间："+getDefaultFormatDateByLong(responsedata.purchaseTime));
//					$('#name').html("名称："+responsedata.name);
//					$('#purchaseAmount').html("采购数量："+responsedata.purchaseAmount);
//					$('#purchaser').html("采购人："+responsedata.purchaser);
//					$('#serialNumber').html("成分含量："+responsedata.serialNumber);
//					$('#level').html("登记证号："+responsedata.level);
//					$('#producer').html("生产单位："+responsedata.producer);
//					$('#producerTel').html("联系电话："+responsedata.producerTel);
//					$('#seller').html("经销商："+responsedata.seller);
//					$('#sellerTel').html("联系电话："+responsedata.sellerTel);
//					$('#brief').html("品种简介：<br>"+responsedata.brief);
					$('#dialog-showDetail').dialog('open');
				
					return false;
								
//				});
				
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
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