angular.module('jwtApp')
    .controller('OrderController', function ($http, $scope, AuthService) {

        var init = function () {
            $http.get(
                'http://localhost:9100/order/api/v1/order',
                // {
                //     params: { username: AuthService.user.preferred_username }
                // }
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
            }).error(function (error) {
                $scope.message = error.message;
            });
        }

        init();
        loadCartItems();
        loadDeliveryTypes();
        loadPickUpPoints();
    });