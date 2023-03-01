angular.module('jwtApp').controller('LoginController', function($scope, $http, $state, $cookies, $rootScope, sharedProps, AuthService) {
	$scope.login = function () {
		$http({
			url: 'http://localhost:9500/realms/market/protocol/openid-connect/token',
			method: "POST",
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			data: $.param(
				{
					client_id: 'js-client',
					grant_type: 'password',
					username: $scope.username,
					password: $scope.password
				}
			)
		}).success(function (res) {
			$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.access_token;
			localStorage.setItem('token', res.access_token);
			localStorage.setItem('refreshToken', res.refresh_token);
			$cookies.put('X-Authorization-Token', res.access_token);
			$rootScope.$broadcast('InitMethod');
		}).error(function (error) {
			$scope.message = 'Авторизация не прошла!';
		});
	};
});