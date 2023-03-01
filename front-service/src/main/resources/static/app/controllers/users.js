angular.module('jwtApp')
    .controller('UsersController', function ($http, $scope, AuthService) {
        $scope.roles = [
                {
                    "id": null,
                    "name": "market.admin"
                },
                {
                    "id": null,
                    "name": "market.manager"
                },
                {
                    "id": null,
                    "name": "market.user"
                }
            ]

        var edit = false;
        $scope.buttonText = 'Создать';
        var init = function () {
            $http.get('http://localhost:9100/users/api/v1/users').success(function (res) {
                $scope.users = res;

                $scope.userForm.$setPristine();
                $scope.message = '';
                $scope.appUser = null;
                $scope.buttonText = 'Создать';

            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.initEdit = function (appUser) {
            edit = true;
            $scope.appUser = appUser;
            $scope.message = '';
            $scope.buttonText = 'Обновить';
        };
        $scope.initAddUser = function () {
            edit = false;
            $scope.appUser = null;
            $scope.userForm.$setPristine();
            $scope.message = '';
            $scope.buttonText = 'Создать';
        };
        $scope.deleteUser = function (appUser) {
            $http.delete('http://localhost:9100/users/api/v1/users/' + appUser.id).success(function (res) {
                $scope.deleteMessage = "Успешно!";
                init();
            }).error(function (error) {
                $scope.deleteMessage = error.message;
            });
        };
        var editUser = function () {
            $http.put('http://localhost:9100/users/api/v1/users', $scope.appUser).success(function (res) {
                $scope.appUser = null;
                $scope.confirmPassword = null;
                $scope.userForm.$setPristine();
                $scope.message = "Успешно обновлено";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        var addUser = function () {
            $http.post('http://localhost:9100/users/api/v1/users', $scope.appUser).success(function (res) {
                $scope.appUser = null;
                $scope.confirmPassword = null;
                $scope.userForm.$setPristine();
                $scope.message = "Пользователь создан";
                init();
            }).error(function (error) {
                $scope.message = error.message;
            });
        };
        $scope.submit = function () {
            if (edit) {
                editUser();
            } else {
                addUser();
            }
        };
        init();

    });