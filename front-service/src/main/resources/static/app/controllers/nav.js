angular.module('jwtApp')
    .controller('NavController', function ($http, $scope, AuthService, $state, $rootScope) {
        if (localStorage['user']) {
            $scope.user = AuthService.user;
        }
        $scope.$on('LoginSuccessful', function () {
            $scope.user = AuthService.user;
            $scope.mergeCarts();
        });
        $scope.$on('LogoutSuccessful', function () {
            $scope.user = null;
        });
        $scope.logout = function () {
            AuthService.user = null;
            localStorage.removeItem('user');
            localStorage.removeItem('token');
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