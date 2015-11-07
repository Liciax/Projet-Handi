// JavaScript Document
(function(){
    var app = angular.module('plugin-directives', []);

    app.directive("amenagement", function() {
      return {
        restrict: 'E',
        templateUrl: "amenagement.html",
		controller: function() {
        	this.description = false;

          	this.setDescription = function() {
				if(this.description == true)
					this.description = false;
				else
					this.description = true;
          	};
        },
        controllerAs: "formulaire"
      };
    });

    
  })();
