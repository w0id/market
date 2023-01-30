angular.module('jwtApp').config(function ($stateProvider, $urlRouterProvider) {

    // $urlRouterProvider.otherwise('/page-not-found');
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('nav', {
        abstract: true,
        url: '',
        views: {
            'nav@': {
                templateUrl: 'app/views/nav.html',
                controller: 'NavController'
            }
        }
    }).state('login', {
        parent: 'nav',
        url: '/login',
        views: {
            'content@': {
                templateUrl: 'app/views/login.html',
                controller: 'LoginController'
            }
        }
    }).state('users', {
        parent: 'nav',
        url: '/users',
        data: {
            role: 'ROLE_ADMIN'
        },
        views: {
            'content@': {
                templateUrl: 'app/views/users.html',
                controller: 'UsersController',
            }
        }
    }).state('home', {
        parent: 'nav',
        url: '/',
        views: {
            'content@': {
                templateUrl: 'app/views/home.html',
                controller: 'HomeController'
            }
        }
    }).state('page-not-found', {
        parent: 'nav',
        url: '/page-not-found',
        views: {
            'content@': {
                templateUrl: 'app/views/page-not-found.html',
                controller: 'PageNotFoundController'
            }
        }
    }).state('access-denied', {
        parent: 'nav',
        url: '/access-denied',
        views: {
            'content@': {
                templateUrl: 'app/views/access-denied.html',
                controller: 'AccessDeniedController'
            }
        }
    }).state('registration', {
        parent: 'nav',
        url: '/registration',
        views: {
            'content@': {
                templateUrl: 'app/views/registration.html',
                controller: 'RegisterController'
            }
        }
    }).state('manage', {
        parent: 'nav',
        url: '/manage',
        data: [
            {
                role: "ROLE_MANAGER"
            },
            {
                role: "ROLE_ADMIN"
            }
        ],
        views: {
            'content@': {
                templateUrl: 'app/views/manage.html',
                controller: 'ManageController'
            }
        }
    }).state('order', {
        parent: 'nav',
        url: '/order',
        views: {
            'content@': {
                templateUrl: 'app/views/order.html',
                controller: 'OrderController'
            }
        }
    });
});