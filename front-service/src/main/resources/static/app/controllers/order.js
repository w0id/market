angular.module('jwtApp')
    .controller('OrderController', function ($http, $scope, AuthService) {

        var init = function () {
            $http.get(
                'http://localhost:9100/order/api/v1/order',
                ).success(function (res) {
                $scope.customer = res;

                $scope.orderForm.$setPristine();
                $scope.message = '';

            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        var loadCartItems = function () {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items',
                method: 'GET',
                params: {
                    uuid: localStorage.marketGuestId
                }
            })
                .then(function (res) {
                    $scope.cart = res.data;
                });
        };

        var loadDeliveryTypes = function () {
            $http.get('http://localhost:9100/order/api/v1/order/delivery_types')
                .then(function (res) {
                    $scope.types = res.data;
                });
        };

        var loadPickUpPoints = function () {
            $http.get('http://localhost:9100/order/api/v1/order/pickup_points')
                .then(function (res) {
                    $scope.points = res.data;
                });
        };

        $scope.changeType = function () {
            if ($scope.appOrder.deliveryType.id != "2") {
                $("#point").val("");
                $("#point").attr('disabled', true);
                $scope.appOrder.pickUpPoint = {};
            } else {
                $("#point").attr('disabled', false);
            }
            if ((($scope.appOrder.deliveryType.id == "2" && $scope.appOrder.pickUpPoint.id != null) || $scope.appOrder.deliveryType.id == "1") && $scope.cart.items.length != 0) {
                $("#send").attr('disabled', false);
            } else {
                $("#send").attr('disabled', true);
            }
        }

        $scope.clearCart = function () {
            $http({
                url: 'http://localhost:9100/cart/api/v1/cart_items/clear',
                method: 'GET',
                params: {
                    'uuid': localStorage.marketGuestId
                }
            }).then(function (response) {
                loadCartItems();
            });
        }

        $scope.sendOrder = function () {
            $scope.appOrder.customer = $scope.customer;
            $scope.appOrder.cart = $scope.cart;
            if ( $scope.appOrder.pickUpPoint == null ) {
                $scope.appOrder.pickUpPoint = {};
            }
            $http.post('http://localhost:9100/order/api/v1/order', $scope.appOrder).success(function (res) {
                $scope.orderForm.$setPristine();
                $scope.message = "Заказ отправлен";
                $scope.clearCart();
                $("#send").attr('disabled', true);
            }).error(function (error) {
                $scope.message = error.message;
            });
        }

        init();
        loadCartItems();
        loadDeliveryTypes();
        loadPickUpPoints();
    });