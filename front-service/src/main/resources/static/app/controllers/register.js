angular.module('jwtApp')
    .controller('RegisterController', function ($http, $scope, AuthService) {
        $scope.submit = function () {
            $scope.registration_selected=true;
            $http.post('http://localhost:9100/registration', $scope.appUser).success(function (res) {
                $scope.appUser = null;
                $scope.confirmPassword = null;
                $scope.registration.$setPristine();
                $scope.message = "Успешная регистрация!";
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
    });