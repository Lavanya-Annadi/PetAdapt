var app = angular.module('myApp', ['ngRoute']);
//var app = angular.module('myapp', ['ngRoute']);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	template:'<h1>Welcome to my home page templte</h1>'
    })
    .when("/user", {
        templateUrl : "view/users.html"
    })
    .when("/user/login", {
        templateUrl : "view/roles.html"
    })
    .when("/petinfo", {
    	//template:'<h1>Welcome to my petinfo page templte</h1>'
    	templateUrl : "view/petinfo.html"
    })
    .when("/statistics", {
    	//template:'<h1>User Statistics</h1>'
    	templateUrl : "view/statistics.html",
    	controller : "statisticscontroller"
    })
    .when("/user/dashboard", {
    	resolve :{
    		 "check":function($location,$rootScope){
    	  		 if (!$rootScope.loggedIn){
    	  			 $location.path("view/roles.html");
    	  		 }
    	  	 }
    	},
        templateUrl : "view/dashboard.html"
        
    })
    .otherwise("/user/login", {
        templateUrl : "view/roles.html"
    });
});
app.controller('logincontroller',function($scope,$rootScope,$location,$http){
	
	$scope.login=function()
	{
		var Indata = {'email':$scope.loginform.email, 'password':$scope.loginform.password };
		    $http({
		    url: "http://localhost:9095/petadapt/user/login/",
		    method: "POST",
		    params: Indata
		    }).then(function(response){
				console.log(response.data);
		    	$rootScope.loggedIn=true;
				$scope.Message = $location.path("user/dashboard");
				$rootScope.logindetails=response.data;
			} ,
			function onError(err) {
				// do something on error
				$scope.Message="username or passowrd specified incorrectly"
			});
	}  
	
});	

app.controller('dashboardcontroller',function($scope,$location,$http){
	
	$scope.logout=function(){
	    $http({
		    url: "http://localhost:9095/petadapt/user/logout",
		    method: "GET",
		    }).then(function(response){
				//console.log(response.data);
				$scope.Message = $location.path("user/login");
			});
		
	}

});
app.controller('statisticscontroller',function($scope,$http){
	
	$http.get('http://localhost:9095/petadapt/statistics').then(
			function(response) {
			$scope.statistics = response.data;
//				$scope.msg="inside controller"

//	    $http({
//		    url: "http://localhost:9095/petadapt/statistics",
//		    method: "GET",
//		    }).then(function(response){
//				//console.log(response.data);
//				$scope.Message = response.data;
//			});
		
	
});

});


app.controller('PersonController',function($scope,$http){
	$scope.create = function() {
		
		
		var user = {};
		
		user.id = $scope.personForm.id;
		
		user.username = $scope.personForm.username;
		user.firstName =$scope.personForm.firstname;
		user.lastName = $scope.personForm.lastname;
		user.email=$scope.personForm.email;
		user.phone = $scope.personForm.phone;
		user.password = $scope.personForm.password;
		//user.userstatus=$scope.personForm.userstatus;
		user.confirmPassword = "";
//			
//		$scope.Message = User.save(user);
		
		$http({
					url:"http://localhost:9095/petadapt/user",
					method:"POST",
					data:user
				}).then(function(response){
					
					$scope.loginconfirmation=true;
					$scope.loginconfirmed="user created successfully"
						console.log($scope.loginconfirmed);
//					window.alert("user created successfully");
//					$scope.Message="user created successfully"
					//$scope.successMessagebool = true;
				});
				
				
		
			
		};
	
});






//old code

//var app = angular.module('myApp', ['ngRoute','ngResource']);
//
////app.config(function($routeProvider){
////  $routeProvider
////  .when('/', {
////  	  template:'<h1>Welcome to my home page templte</h1>',
////  	  
////  	  })
////      .when('users',{
////          templateUrl: '/view/users.html',
////          
////      })
////      .when('pet',{
////          templateUrl: '/view/roles.html',
////          
////      })
////      .otherwise(
////          { redirectTo: '/'}
////      );
////});
//
//
//app.config(function($routeProvider) {
//  $routeProvider
//  .when("/", {
//  	template:'<h1>Welcome to my home page templte</h1>',
//  })
//  .when("/user", {
//      templateUrl : "view/users.html"
//  })
////  .when("/blue", {
////      templateUrl : "view/roles.html"
////  })
//  .when("/user/login", {
//      templateUrl : "view/roles.html"
//  });
////   .when("/user/dashboard", {
////  	 "check":function($location,$rootScope){
////  		 if (!$rootScope.loggedIn){
////  			 $location.path("view/roles.html");
////  		 }
////  	 },
////      templateUrl : "view/dashboard.html"
////  });
//});
//
//
app.controller('HomeController', function($scope) {
  $scope.headingTitle = "Welcome to Home Page";
});

app.controller('PetController', function($scope) {
  $scope.headingTitle = "Welcome to Petinfo Page";
});
//
//
////app.
////service('POST', ['$resource','$scope', function($resource,$scope){
////  var LoginResource = $resource('http://localhost:9095/petadapt/user/login/');
////  var serviceObject = {loginUser: function (email:'@email', password:'@password'){
////      return LoginResource.save({}, {email:$scope.loginform.email, password: $scope.loginform.password}).$promise; //this promise will be fulfilled when the response is retrieved for this call
////  }};
////  return serviceObject;
////}]);
////app.controller("logincontroller", ['$scope', 'POST',     
////	   function($scope,yourService){
////	yourService.loginUser("lav", "123").then(
////	        function(loginResult){
////	           console.log(loginResult);
////	        },
////	        function(err){
////	           console.error(err);
////	        }
////	    )
////	  }]);
//
////syat
//
//app.controller('logincontroller', ['$scope', '$resource','$location',
//	function($scope,$resource,$location)
//	{
//	$scope.login=function(){
//		Users=$resource("http://localhost:9095/petadapt/user/login/",
//				{},
//				{save: {method:'POST',params:{email: '@email',password:'@password'},isArray:false}});
//	
//  Users.save({email:$scope.loginform.email,password:$scope.loginform.password});
//	if(Users.email==$scope.loginform.email && Users.password==$scope.loginform.password)
//	{
//		Users.save({email:$scope.loginform.email,password:$scope.loginform.password});
//		$scope.Message=$location.path("view/dashboard.html");
//	}else{
//		$scope.Message=$location.path("view/login.html");
//	}
//	
//	
////	then(function successCallback(response) {
////		$rootScope.loggedIn=true;
//////		$location.path("view/dashboard.html");		
////	$scope.Message = $location.path("view/dashboard.html");
////	}
////	
////	,function errorCallback(response) {
////	    
////	}
////	 
////	);
//	};	 
//	
//	}]);
////emd
//
//
//app.controller('PersonController', ['$scope', '$resource',function($scope,$resource) {
//   
//  $scope.create = function(){
//  	User = $resource(
//  		    "http://localhost:9095/petadapt/user",
//  		    {},
//  		    {save: {method:'POST',isArray:false}}
//  	);
//  	
//  	var user = {};
//		
//		user.id = $scope.personForm.id;
//		
//		user.username = $scope.personForm.username;
//		user.firstname = $scope.personForm.firstName;
//		user.lastname = $scope.personForm.lastName;
//		user.email=$scope.personForm.email;
//		user.phone = $scope.personForm.phone;
//		user.password = $scope.personForm.password;
//		//user.userstatus=$scope.personForm.userstatus;
//		user.confirmPassword = "";
//			
//		$scope.Message = User.save(user);
//				
//		$scope.personForm.id = "";
//		$scope.personForm.username="";
//		$scope.personForm.firstname="";
//		$scope.personForm.lastname="";
//		$scope.personForm.email="";
//		$scope.personForm.phone="";
//		//$scope.personForm.userstatus="";
//		$scope.personForm.password="";
//		
//  };
//  
//  $scope.deleteRec = function(){
//  	User = $resource(
//  		    "http://localhost:9095/petadapt/user/:id",
//  		    {},
//  		    {save: {method:'DELETE', params: {id: '@id'}}}
//  	);
//  	
//			
//		User.delete({id: $scope.personForm.id}).then(function successCallback(response) {
//			$scope.Message = response;
//		}, function errorCallback(response) {
//		    
//		});
//				
//		$scope.personForm.id = "";
//		$scope.personForm.username="";
//		$scope.personForm.firstname="";
//		$scope.personForm.lastname="";
//		$scope.personForm.email="";
//		$scope.personForm.phone="";
//		//$scope.personForm.userstatus="";
//		$scope.personForm.password="";
//  };
//  
//  
//  $scope.update = function(){
//  		
//  	User = $resource(
//  		    "http://localhost:9095/petadapt/user",
//  		    {},
//  		    {save: {method:'PUT', params: {id: '@id'}}}
//  	);
//  	
//		var user = {};
//		
//		user.id = $scope.personForm.id;
//		user.username = $scope.personForm.username;
//		user.firstname = $scope.personForm.firstName;
//		user.lastname = $scope.personForm.lastName;
//		user.email=$scope.personForm.email;
//		user.phone = $scope.personForm.phone;
//		user.password = $scope.personForm.password;
//		//user.userstatus=$scope.personForm.userstatus;
//		user.confirmPassword = "";
//		
//		$scope.Message = User.save({id: $scope.personForm.id}, user);
//				
//		$scope.personForm.id = "";
//		$scope.personForm.username="";
//		$scope.personForm.firstname="";
//		$scope.personForm.lastname="";
//		$scope.personForm.email="";
//		$scope.personForm.phone="";
//		//$scope.personForm.userstatus="";
//		$scope.personForm.password="";
//  };
//  
//  
//  
//  
//  
//  function fetchAllusers(){
//      $scope.persons = $resource('http://localhost:9095/petadapt/user/All'
//      ).query(function(data)
//      		{return data;});
//  };
//  fetchAllusers();
//  
//  $scope.refresh = function(){
//  	fetchAllusers();
//  };
//  
//        
//
//  
//}]);
//
////
////
////
////
////
////
////
////		    
