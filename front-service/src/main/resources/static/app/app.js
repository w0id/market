angular.module('jwtApp', ['ngCookies', 'ui.router'])

    .run(function (AuthService, $rootScope, $state, $http, $interval, $cookies, sharedProps) {
        function stop($rootScope) {
            $interval.cancel($rootScope.updateTokenInterval);
        };
        function parseJwt(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            return JSON.parse(decodeURIComponent(escape(atob(base64))));
          };
        function initializeKeycloak(callback) {
            var keycloak = sharedProps.dataObj;
            keycloak.init({
            onLoad: 'cheack-sso', checkLoginIframe: false,
            token: localStorage.getItem('token'),
            refreshToken: localStorage.getItem('refreshToken'),
            timeSkew: 0
            }).success(function () {
                keycloak.loadUserInfo().success(function (userInfo) {
                    AuthService.user = userInfo;
                    $rootScope.$broadcast('LoginSuccessful');
                    callback('Success');
                });
            });
        }
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, init) {
            if (!AuthService.user) {
                if (localStorage['token']) {
                    try {
                        let jwt = localStorage['token'];
                        let payload = parseJwt(jwt);
                        let currentTime = parseInt(new Date().getTime() / 1000);
                        if (currentTime > payload.exp) {
                            console.log("Token is expired!");
                            $cookies.remove('X-Authorization-Token');
                            localStorage.removeItem('user');
                            localStorage.removeItem('token');
                            localStorage.removeItem('refreshToken');
                            $http.defaults.headers.common['Authorization'] = '';
                            event.preventDefault();
                            $state.go('login');
                        } else {
                            $http.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
                            $rootScope.$broadcast("InitMethod");
                        }
                    } catch (e) {
                    }
                } else {
                    if (toState.name != 'login' && toState.name != 'registration') {
                        $rootScope.selected_registration = true;
                        $rootScope.selected_login = true;
                    } else if (toState.name == 'registration') {
                        $rootScope.selected_registration = true;
                        $rootScope.selected_login = false;
                    } else if (toState.name == 'login') {
                        $rootScope.selected_registration = false;
                        $rootScope.selected_login = true;
                    }
                }
            } else {
                if (toState.data) {
                    var hasAccess = false;
                    angular.forEach(toState.data, function (value) {
                        for (var i = 0; i < AuthService.user.realm_access.roles.length; i++) {
                            var role = AuthService.user.realm_access.roles[i];
                            if (value.role == role || value == role) {
                                hasAccess = true;
                                break;
                            }
                            if (hasAccess) {
                                break;
                            }
                        }
                    })
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('access-denied');
                    }
                }
            }
        });
        $rootScope.$on('InitMethod', function(){
            initializeKeycloak(function(res){
				if(res == 'Success'){
                    stop($rootScope);
                    $rootScope.updateTokenInterval = $interval(function () {
                        var keycloak = sharedProps.dataObj;
                        if (typeof keycloak.timeSkew == 'undefined') {
                            keycloak.init({
                                onLoad: 'cheack-sso', checkLoginIframe: false,
                                token: localStorage.getItem('token'),
                                refreshToken: localStorage.getItem('refreshToken'),
                                timeSkew: 0
                                });
                        }
                        keycloak.updateToken(15)
                        .success(function (refreshed) {
                            if (refreshed) {
                            $cookies.put('X-Authorization-Token', keycloak.token);
                            $http.defaults.headers.common['Authorization'] = 'Bearer ' + keycloak.token;
                            localStorage.setItem('token', keycloak.token);
                            }
                        });
                    }, 10000);
					$state.go('home');
				}
			});	
        });
        $rootScope.$on('LogoutSuccessful', function (updateTokenInterval) {
            stop($rootScope);
        });
    });