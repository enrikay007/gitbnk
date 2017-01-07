var app1 = angular.module('app1', []);

app1.controller('mListCtrl', function ($scope) {
    
    $scope.movies = [
        {item: "Pirates of the carribean", purchased: false}, {item: "Silicon Valley", purchased: false},{item: "How I met your mom", purchased: false}, {item: "startups", purchased: false}

          ];   
    
});


