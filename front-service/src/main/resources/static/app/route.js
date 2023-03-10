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
    }).state('secure', {
        parent: 'nav',
        url: '/secure',
        views: {
            'content@': {
                templateUrl: 'app/views/secure.html',
                controller: 'SecureController'
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
    })
    .state('home', {
        parent: 'nav',
        url: '/',
        views: {
            'content@': {
                templateUrl: 'app/views/home.html',
                controller: 'HomeController'
            }
        }
    }).state('cart', {
        parent: 'nav',
        url: '/cart',
        views: {
            'content@': {
                templateUrl: 'app/views/cart.html',
                controller: 'CartController'
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
                role: "market.manager"
            },
            {
                role: "market.admin"
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