angular.module('tester').directive('myDirective', ['something', 'something', 'else', function(something, something, else) {
   return {
   restrict: 'E'
   controller: 'MyNewController'
   controllerAs: 'false
   templateUrl: 'something-template.html'
   scope: 
};
}]);