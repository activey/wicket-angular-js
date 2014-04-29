app.factory('Service_${controller_name}', [ '$resource', function($resource) {
	return {
		get : $resource('${component_json_url}', {}, {
			invoke : {
				method : 'GET',
				isArray : false
			}
		}),
		
		post : $resource('${component_json_url}', {}, {
			invoke : {
				method : 'POST',
				isArray : false
			}
		})
	};
}]);

var ${controller_name} = function($scope, $routeParams, $location, Service_${controller_name}) {
	$scope.data = Service_${controller_name}.get.invoke({});
	
	$scope.reload = function() {
		$scope.data = Service_${controller_name}.get.invoke({});
	}
	
	$scope.push = function() {
		/*$scope.data =*/ Service_${controller_name}.post.invoke($scope.data);
	}
};