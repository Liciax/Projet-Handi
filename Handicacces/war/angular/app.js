// JavaScript Document
(function() {
	var app = angular.module('handicacces', ['plugin-directives']);

	app.controller('AmenagementsController', ['$http',function($http){
		var plugin = this;
		//var url = 'https://alma12091994.appspot.com/_ah/api/scoreentityendpoint/v1/scoreentity'
		var url = '/amenagements.json'
   		plugin.amenagements = [];
		
    	$http.get(url)
					.success(function(datas){
       					plugin.amenagements = datas;
      				})
					.error(function(){
						alert('Erreur');  
	  				}); 
		
 	 	}]);
		
})();

