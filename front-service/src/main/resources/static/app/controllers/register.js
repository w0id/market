angular.module('jwtApp')
    .controller('RegisterController', function ($http, $scope, AuthService) {
        // $scope.submit = function () {
        //     $scope.registration_selected=true;
        //     $http.post('http://localhost:9100/registration', $scope.appUser).success(function (res) {
        //         $scope.appUser = null;
        //         $scope.confirmPassword = null;
        //         $scope.registration.$setPristine();
        //         $scope.message = "Успешная регистрация!";
        //     }).error(function (error) {
        //         $scope.message = error.message;
        //     });
        // };
        $scope.submit = function () {
            var keycloakConfig = {
                "url": "http://localhost:8500",
                "realm": "master",
                "clientId": "register-cli",
                "credentials": {
                  "secret": "Z0uFYGlnk8iMWxJvmWE0iCu5BVBWGf2o"
                }
              };
            var keycloak = Keycloak(keycloakConfig);
            keycloak.init({
            onLoad: 'cheack-sso', checkLoginIframe: false
            }).success(function () {
                // keycloak.loadUserInfo().success(function (userInfo) {
                //     AuthService.user = userInfo;
                //     $rootScope.$broadcast('LoginSuccessful');
                //     localStorage.setItem('user', JSON.stringify(userInfo));
                //     $state.go('home');
                // });
                alert('OK');
            });
        };
        $scope.submit();
    });