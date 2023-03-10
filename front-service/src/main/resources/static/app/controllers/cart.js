angular.module('jwtApp')

    .controller('CartController', function ($scope, $document, $http, AuthService) {
        // $scope.user = AuthService.user;

        if (!localStorage.marketGuestId) {
            $http.get('http://localhost:9100/cart/api/v1/cart_items/generate_uuid')
                .then(function successCallback(response) {
                    localStorage.marketGuestId = response.data.value;
                });
        };

        $scope.goToOrder = function () {
            if(AuthService.user){
                document.location.href='#/order';
            } else {
                document.location.href='#/login';
            }
        }

        $scope.onChange = function () {
            if ($scope.cart.items.length != 0) {
                $("#clear").attr('disabled', false);
                $("#order").attr('disabled', false);
            } else {
                $("#clear").attr('disabled', true);
                $("#order").attr('disabled', true);
            }
        }

        $scope.loadCartItems = function () {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items',
                method: 'GET',
                params: {
                    uuid: localStorage.marketGuestId
                }
            })
                .then(function (response) {
                    $scope.cart = response.data;
                    $scope.onChange();
                });

        };

        $scope.clearCart = function () {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items/clear',
                method: 'GET',
                params: {
                    'uuid': localStorage.marketGuestId
                }
            }).then(function (response) {
                $scope.loadCartItems();
            });
        }

        // $scope.addToCart = function (id) {
        //     $http({
        //         url: 'http://localhost:9100/cart/api/v1/cart_items',
        //         method: 'POST',
        //         params: {
        //             uuid: localStorage.marketGuestId,
        //             id: id
        //         }
        //     }).then(function (response) {
        //         $scope.loadCartItems();
        //     });
        // };

        $scope.changeQuantity = function (id, quantity) {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items',
                method: 'PUT',
                params: {
                    uuid: localStorage.marketGuestId,
                    id: id,
                    quantity: quantity
                }
            }).then(function (response) {
                $scope.loadCartItems();
            })
        }

        $scope.deleteFromCart = function (id) {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items',
                method: 'DELETE',
                params: {
                    uuid: localStorage.marketGuestId,
                    id: id
                }
            })
                .then(function (response) {
                    $scope.loadCartItems();
                });
        };
        $scope.loadCartItems();
    });