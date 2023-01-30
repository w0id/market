angular.module('jwtApp')
    .controller('LoginController', function ($http, $scope, $state, AuthService, $rootScope) {
        $scope.login = function () {
            $http({
                url: 'http://localhost:9100/auth',
                method: "POST",
                data: {
                    username: $scope.username,
                    password: $scope.password
                }
            }).success(function (res) {
                $scope.password = null;
                if (res.token) {
                    $scope.message = '';
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;

                    AuthService.user = res.user;
                    $rootScope.$broadcast('LoginSuccessful');
                    localStorage.setItem('user', JSON.stringify(res.user));
                    localStorage.setItem('token', res.token);
                    $state.go('home');
                } else {
                    $scope.message = 'Авторизация не прошла!';
                }
            }).error(function (error) {
                $scope.message = 'Авторизация не прошла!';
            });
        };
    });