var app1 = angular.module('app1', []);

app1.controller('average',function($scope){
    
    var sum = 0; 
    for(var i = 0; i < movie.length; i++){
        sum += parseInt(movie.averageRating[i], 10); //don't forget to add the base 
    }

    var avg = sum/movie.length;

    return avg;
     
    
    
    
});

function AVl($scope) {
    $scope.MyData = [{averageRating:1000}, {averageRating:2000}];

     $scope.avgAverageRating = function() {
         return alasql('SELECT VALUE AVG(income) FROM ?',[$scope.MyData]);
     };
}; 

app1.controller('mListCtrl', function ($scope) {
    
    $scope.movies = [{
			"id" : "tt3222784",
			"item" : "Minimum Viable Product",
			"averageRating" :7.8,
			"releaseYear" : 2014-04-06,
			"url" : "http://www.netflix.com/Movie/Bag_It/70153545",
			"rating" : "NR"
		}, {
			"id" : "tt3668816",
			"item" : "The Cap Table",
			"averageRating" : 7.9,
			"releaseYear" : 2014-04-13,
			"url" : "http://www.netflix.com/Movie/Lost_Boy_The_Next_Chapter/70171826",
			"rating" : "NR"
		 },
    {
        "id": "tt3380250",
        "item": "Articles of Incorporation",
        "averageRating": 8.2,
        "releaseYear": 2014-04-20,
        "url": "http://www.netflix.com/Movie/Archer_Season_2_Disc_1/70217944",
        "rating": "TV-MA"
    },
    {
        "id": "tt3668330",
        "item": "Fiduciary Duties",
        "averageRating": 7.8,
        "releaseYear": 2014-04-27,
        "url": "http://www.netflix.com/Movie/Archer_Season_2_Disc_2/70217945",
        "rating": "TV-MA"
    },
    {
        "id": "tt3467892",
        "item": "Signaling Risk",
        "averageRating": 8.4,
        "releaseYear": 2014-05-04,
        "url": "http://www.netflix.com/Movie/Big_Time_Rush_Season_1_Vol._2/70171491",
        "rating": "NR"
    },
    {
        "id": "tt3557356",
        "item": "Third Party Insourcing",
        "averageRating": 8.5,
        "releaseYear": 2014-05-11,
        "url": "http://www.netflix.com/Movie/Taylor_Swift_Taylor_Swift/70085495",
        "rating": "NR"
    },
    {
        "id": "tt3388032",
        "item": "Proof of Concept",
        "averageRating": 8.6,
        "releaseYear": 2014-05-18,
        "url": "http://www.netflix.com/Movie/Hopkins_24_7_Season_2/70100701",
        "rating": "NR"
    },
    {
        "id": "tt3557358",
        "item": "Optimal Tip-To-Tip Efficiency",
        "averageRating": 9.4,
        "releaseYear": 2014-06-01,
        "url": "http://www.netflix.com/Movie/The_Shawshank_Redemption/70005379",
        "rating": "R"
    }
	];

    
});

 
//Do configuration and routing here
exam.config(function($routeProvider){
  console.log($routeProvider);
  $routeProvider
.when("/",{
  controller: "MoviesCtrl",
  templateUrl: "js/views/moviesView.html"
});
 
$routeProvider.otherwise({"redirectTo": "/"}); //.otherwise("/"); //does not work
});

