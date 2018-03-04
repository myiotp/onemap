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
					//responsedata = responsedata.data;				
					console.log(responsedata);				
					$('#wx_avatarUrl').html("微信头像："+"<image src='"+responsedata.wx_avatarUrl+"'/>");
					$('#wx_nickName').html("微信昵称："+responsedata.wx_nickName);
					$('#username').html("唯一号："+responsedata.username);
					$('#realname').html("真实姓名："+responsedata.realname);
					$('#company').html("公司名称："+responsedata.company);
					$('#mobilephone').html("联系方式："+responsedata.mobilephone);
					$('#idcard').html("身份证号："+responsedata.idcard);
					//$('#city').html("城市："+responsedata.city);
					$('#province').html("省份："+responsedata.province);
					$('#address').html("详细地址："+responsedata.address);
					$('#emergency').html("应急联系人："+responsedata.emergency);
					$('#emergencyphone').html("应急联系电话："+responsedata.emergencyphone);
					$('#dialog-showDetail').dialog('open');
					return false;
								
				});
				
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
			};
		});