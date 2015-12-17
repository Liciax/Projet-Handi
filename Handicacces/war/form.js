function affichage(){
	var app = angular.module('app.search',['ngSanitize' ]);
	app.controller("ResultController", ['$scope','$http', '$sce' ,function($scope,$http,$sce){
	$scope.layouts = [];
		 
	$scope.renseigner = function(website,inflayout) {
		var newLayouts = inflayout;
		var websiteAcreer = true;
		if(website.resp.layouts.length > 0){
			websiteAcreer = false;
		}
		var websiteAlancer = '{ "url" : "' + website.displayLink + '" , "layouts" : [';
		angular.forEach(newLayouts, function(layout) {
			if (layout.checked){
				if(website.resp.layouts.length == 0) {

					websiteAlancer = websiteAlancer + '{ "id": "' + layout.id +'", "name": "' + layout.name +'", "description": "' + layout.description +'", "checked": false},'
					website.resp.layouts.push(layout);
				}
				else {
					var pastrouve = true;
					for(var nb = 0; nb < website.resp.layouts.length; nb++) {
						if (layout.id == website.resp.layouts[nb].id) {
							pastrouve = false;
						}
					}
					if(pastrouve) {
						websiteAlancer = websiteAlancer + '{ "id": "' + layout.id +'", "name": "' + layout.name +'", "description": "' + layout.description +'", "checked": false},'
						website.resp.layouts.push(layout);
					}
				}
			} 
		});
		if(websiteAlancer.substr(websiteAlancer.length - 1) == ',') {
			websiteAlancer = websiteAlancer.substring(0, websiteAlancer.length - 1);
		}
		websiteAlancer = websiteAlancer + ']}';
		var jsonAenvoyer = JSON.parse(websiteAlancer);
		if(jsonAenvoyer.layouts.length > 0) {
			if(websiteAcreer) {
				$http.post("https://handicacces.appspot.com/_ah/api/handicacces/v1/create?fields=layouts%2Curl", jsonAenvoyer)
				.success(function(data) {
					console.log("ok creation");
				});
			} else {
				$http.put("https://handicacces.appspot.com/_ah/api/handicacces/v1/website?fields=layouts%2Curl", jsonAenvoyer)
				.success(function(data) {
					console.log("ok");
				});
			}
		}
		
	};
		
		

//Code pour afficher le plug-in
		this.tab = 1;

		this.selectTab = function(setTab){
			if(this.tab == 1) {
				this.tab = setTab;
			}
			else {
				this.tab = 1;
			}
			for (i = 0; i < $scope.layouts.length; i++) { 
				$scope.layouts[i].checked = false;
			}
		};
		
		this.isSelected = function(checkTab){
		return this.tab === checkTab;
		};
		
		
		$scope.affresult = function(){
			if($scope.layouts.length == 0 ){
				var url = 'https://handicacces.appspot.com/_ah/api/handicacces/v1/layout'
			    	$http.get(url)
								.success(function(datas){
									$scope.layouts = datas.items;
			      				})
								.error(function(){
									console.log("je n'arrive pas à charger les layouts"); 
				  				}); 
				
			}
			
			
			
			var temp = $scope.p1;
			$http.get('http://handicacces.appspot.com/requete?p1=' + temp + '&start=1' ).success(function(response) {
				var json = response; 
				$scope.item = json.items;
				$scope.next = json.queries;
			});
		};
		
		$scope.nextpage = function(){
			var temp = $scope.p1;
			var page = $scope.next.nextPage[0].startIndex;
			$http.get('http://handicacces.appspot.com/requete?p1=' + temp + '&start=' + page).success(function(response) {
				var json = response; 
				$scope.item = json.items;
				$scope.next = json.queries;
				
			});
		};
		
		$scope.lastpage = function(){
			if($scope.next.nextPage[0].startIndex >= 11) {
				var temp = $scope.p1;
				var page = $scope.next.previousPage[0].startIndex;
				$http.get('http://handicacces.appspot.com/requete?p1=' + temp + '&start=' + page).success(function(response) {
					var json = response; 
					$scope.item = json.items;
					$scope.next = json.queries;
					
				});
			}

		};
		
		
	}]);
};


affichage();
