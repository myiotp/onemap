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
					$('#title').html(responsedata.title);
					$('#source').html(responsedata.source);
					$('#time').html(responsedata.time);
					$('#readamount').html(responsedata.readamount);
					$('#content').html(responsedata.content);
					$('#dialog-showTrail').dialog('open');
					return false;
								
				});
				
			};
			
			$scope.deleteDialog = function(id){
				$('#id').val(id);
				$('#dialog-Delete').dialog('open');
				return false;
			};
		});