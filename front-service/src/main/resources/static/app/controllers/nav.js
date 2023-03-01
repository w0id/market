angular.module('jwtApp')
    .controller('NavController', function ($http, $scope, AuthService, $state, $rootScope, $cookies) {
        if (localStorage['user']) {
            $scope.user = AuthService.user;
        }
        $scope.$on('LoginSuccessful', function () {
            $scope.user = AuthService.user;
            $scope.mergeCarts();
        });
        $scope.logout = function () {
            AuthService.user = null;
            $scope.user = null;
            localStorage.removeItem('token');
            localStorage.removeItem('refreshToken');
            $cookies.remove('X-Authorization-Token');
            $http.defaults.headers.common['Authorization'] = '';
            $rootScope.$broadcast('LogoutSuccessful');
            $state.go('login');
        };
        $scope.mergeCarts = function () {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items/merge',
                method: 'GET',
                params: {
                    'uuid': localStorage.marketGuestId
                }
            }).then(function (response) {
            });
        }
    });