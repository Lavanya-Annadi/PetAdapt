var app = angular.module('myApp', []);

app.controller('postcontroller', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var url = $location.absUrl() + "user";
		
		var config = {
                headers : {
                	'Content-Type' : 'application/json; charset=UTF-8'
                }
		'Accept' : 'application/json'
		}
		var data = {
            username: $scope.username,
            firstname: $scope.firstname,
            lastname:$scope.lastname,
            email:$scope.email,
            phone:$cope.phone,
            userstatus:$scope.userstatus,
            password:$scope.password,
                    
            
        };
		
		$http.post(url, data, config).then(function (response) {
			$scope.postResultMessage = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
		
		$scope.firstname = "";
		$scope.lastname = "";
	}
});


app.controller('getcontroller', function($scope, $http, $location) {
	$scope.getfunction = function(){
		var url = $location.absUrl() + "user" +id;
		
		$http.get(url).then(function (response) {
			$scope.response = response.data
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
	}
});
		














angular.module('demo', []).controller(
		'postcontroller',
		function($scope, $http) {
			$http.get('http://localhost:9090/pet/').then(
					function(response) {
						$scope.students = response.data;
					});
			$scope.edit = true;
			$scope.error = false;
			$scope.incomplete = false;
			$scope.hideform = true;
			$scope.editStudent = function(id) {
				$scope.hideform = false;
				if (id == 'new') {
					$scope.edit = true;
					$scope.incomplete = true;
					$scope.name = '';
					$scope.breed = '';
					$scope.age = '';
					$scope.origin = '';
					$scope.color = '';
					$scope.hieght = '';
					$scope.life_span = '';
				} else {
					$scope.edit = true;
					$scope.name = $scope.pet[id].name;
					$scope.breed = $scope.pet[id].breed;
					$scope.age = $scope.pet[id].age;
					$scope.origin = $scope.pet[id].origin;
					$scope.color = $scope.pet[id].color;
					$scope.hieght = $scope.pet[id].hieght;
					$scope.life_span = $scope.pet[id].life_span;
				}
			};

			$scope.saveStudent = function() {
				var obj = {
						name : $scope.name,
						breed : $scope.breed,
						age : $scope.age,
						origin : $scope.origin,
						color : $scope.color,
						hieght : $scope.hieght,
						life_span : $scope.life_span,
						
				};
				$scope.hideform = true;
				
				
				if($scope.name=='')
				$http.post('http://localhost:9090/pet', obj, {
					headers : {
						'Content-Type' : 'application/json; charset=UTF-8'
					},
					'Accept' : 'application/json'
				}).then(function onSuccess(data) {
					window.alert(JSON.stringify(data.data));
					$http.get('http://localhost:9090/pet').then(
							function(response) {
								$scope.students = response.data;
							});
					$route.reload();
				}, function onError(err) {
					// do something on error
				});
				else
				$http.put('http://localhost:9090/pet', obj, {
					headers : {
						'Content-Type' : 'application/json; charset=UTF-8'
					},
					'Accept' : 'application/json'
				}).then(function onSuccess(data) {
					window.alert(JSON.stringify(data.data));
					$http.get('http://localhost:9090/pet').then(
							function(response) {
								$scope.students = response.data;
							});
					$route.reload();
				}, function onError(err) {
					// do something on error
				});
				$scope.rollNo=''
			};

			$scope.$watch('name', function() {
				$scope.test();
			});
			$scope.$watch('breed', function() {
				$scope.test();
			});
			$scope.$watch('age', function() {
				$scope.test();
			});

			$scope.test = function() {
				$scope.incomplete = false;
				if ($scope.edit
						&& (!$scope.name.length || !$scope.breed.length)) {
					$scope.incomplete = true;
				}
			};
		});