app1.controller("MoviesCtrl", function ($scope, $http) {
	$http.get("/js/data/movies.json")
	.then(function (results) {
		//Success;
		console.log("Succss: " + results.status);
		$scope.movies = results.data;
	}, function (results) {
		//error
		console.log("Error: " + results.data + "; "
			 + results.status);
	})
});