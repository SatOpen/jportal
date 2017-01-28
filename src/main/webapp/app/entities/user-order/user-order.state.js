(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-order', {
            parent: 'entity',
            url: '/user-order?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterSampleApplicationApp.userOrder.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-order/user-orders.html',
                    controller: 'UserOrderController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userOrder');
                    $translatePartialLoader.addPart('orderStatusEnum');
                    $translatePartialLoader.addPart('orderTypeEnum');
                    $translatePartialLoader.addPart('paySystemEnum');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-order-detail', {
            parent: 'entity',
            url: '/user-order/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterSampleApplicationApp.userOrder.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-order/user-order-detail.html',
                    controller: 'UserOrderDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userOrder');
                    $translatePartialLoader.addPart('orderStatusEnum');
                    $translatePartialLoader.addPart('orderTypeEnum');
                    $translatePartialLoader.addPart('paySystemEnum');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserOrder', function($stateParams, UserOrder) {
                    return UserOrder.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-order',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-order-detail.edit', {
            parent: 'user-order-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-order/user-order-dialog.html',
                    controller: 'UserOrderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserOrder', function(UserOrder) {
                            return UserOrder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-order.new', {
            parent: 'user-order',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-order/user-order-dialog.html',
                    controller: 'UserOrderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                validated: null,
                                magDevice: null,
                                creationDate: null,
                                orderStatus: null,
                                orderType: null,
                                paySystem: null,
                                usernameRenewal: null,
                                passwordRenewal: null,
                                note: null,
                                magAddress: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-order', null, { reload: 'user-order' });
                }, function() {
                    $state.go('user-order');
                });
            }]
        })
        .state('user-order.edit', {
            parent: 'user-order',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-order/user-order-dialog.html',
                    controller: 'UserOrderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserOrder', function(UserOrder) {
                            return UserOrder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-order', null, { reload: 'user-order' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-order.delete', {
            parent: 'user-order',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-order/user-order-delete-dialog.html',
                    controller: 'UserOrderDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserOrder', function(UserOrder) {
                            return UserOrder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-order', null, { reload: 'user-order' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
