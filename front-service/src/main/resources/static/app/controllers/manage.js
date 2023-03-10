angular.module('jwtApp')
    .controller('ManageController', function ($scope, $document, $http) {
    $scope.filter = {}

    $scope.deleteProduct = function (productId) {
        $http.delete('http://localhost:9100/core/api/v1/products/' + productId)
            .then(function (response) {
                $scope.setActive(true);
                $scope.loadProducts(1);
            });
    }

    $scope.changeCost = function (product, delta) {
        body = JSON.stringify(
            {
                id: product.id,
                name: product.name,
                cost: product.cost + delta
            }
        );
        $http.put('http://localhost:9100/core/api/v1/products', body)
            .then(function (response) {
                $scope.loadProducts($scope.page);
                $scope.data = {};
            });
    }

    $scope.addProduct = function (form) {
        body = JSON.stringify(
            {
                name: $scope.data.Name,
                cost: $scope.data.Cost
            }
        );
        $http.post('http://localhost:9100/core/api/v1/products', body)
            .then(function (response) {
                $scope.loadProducts($scope.page);
                $scope.data = {};
            });
    }

    $scope.loadProducts = function (page) {
        $scope.page = page;
        $http({
            url: 'http://localhost:9100/core/api/v1/products',
            method: 'GET',
            params: {
                min: $scope.filter ? $scope.filter.min : null,
                max: $scope.filter ? $scope.filter.max : null,
                p: $scope.filter ? $scope.page : null,
                cache: false
            }
        })
            .then(function (response) {
                $scope.productsList = response.data.content;
                $scope.page = response.data.pageable.pageNumber + 1;
                $scope.totalPages = response.data.totalPages;
                if ($scope.page > $scope.totalPages) {
                    $scope.page = $scope.totalPages -1;
                }
                $scope.count = response.data.content.length;
            });
    };

    $scope.setActive = function (static) {
        if (static == null) {
            var header = $document[0].getElementById("pagination");
            var btns = header.getElementsByClassName("page-item");
            for (var i = 0; i < btns.length; i++) {
                btns[i].addEventListener("click", function () {
                    var current = $document[0].getElementsByClassName("active");
                    current[0].className = current[0].className.replace(" active", "");
                    this.className += " active";
                });
            }
        } else {
            document.getElementsByClassName("active")[0].classList.toggle("active");
            document.getElementsByClassName("static")[0].classList.toggle("active");
        }
    }

    $scope.changePage = function (page) {
        $scope.setActive();
        $scope.loadProducts(page);
    }

    $scope.loadProducts(1);
}).controller('range', function ($scope, $document) {
    $scope.$parent.filter = $scope;
    $(".js-range-slider").ionRangeSlider({
        onFinish: function (data) {
            $scope.min = data.from
            $scope.max = data.to
            $scope.$parent.setActive(true);
            $scope.$parent.loadProducts(1);
        }
    });
})