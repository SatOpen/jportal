(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('package-tv', {
            parent: 'entity',
            url: '/package-tv?page&sort&search',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'jhipsterSampleApplicationApp.packageTv.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/package-tv/package-tvs.html',
                    controller: 'PackageTvController',
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
                    $translatePartialLoader.addPart('packageTv');
                    $translatePartialLoader.addPart('packageNameEnum');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('package-tv-detail', {
            parent: 'entity',
            url: '/package-tv/{id}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'jhipsterSampleApplicationApp.packageTv.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/package-tv/package-tv-detail.html',
                    controller: 'PackageTvDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('packageTv');
                    $translatePartialLoader.addPart('packageNameEnum');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PackageTv', function($stateParams, PackageTv) {
                    return PackageTv.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'package-tv',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('package-tv-detail.edit', {
            parent: 'package-tv-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/package-tv/package-tv-dialog.html',
                    controller: 'PackageTvDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PackageTv', function(PackageTv) {
                            return PackageTv.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('package-tv.new', {
            parent: 'package-tv',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/package-tv/package-tv-dialog.html',
                    controller: 'PackageTvDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                packageName: null,
                                priceSkrill: null,
                                priceNeteller: null,
                                priceBitcoins: null,
                                note: null,
                                priceMyCommerce: null,
                                descriptionMyCommerce: null,
                                descriptionSkrill: null,
                                descriptionNeteller: null,
                                descriptionBitcoins: null,
                                pricePaySafeCard: null,
                                descriptionPaySafeCard: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('package-tv', null, { reload: 'package-tv' });
                }, function() {
                    $state.go('package-tv');
                });
            }]
        })
        .state('package-tv.edit', {
            parent: 'package-tv',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/package-tv/package-tv-dialog.html',
                    controller: 'PackageTvDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PackageTv', function(PackageTv) {
                            return PackageTv.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('package-tv', null, { reload: 'package-tv' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('package-tv.delete', {
            parent: 'package-tv',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/package-tv/package-tv-delete-dialog.html',
                    controller: 'PackageTvDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PackageTv', function(PackageTv) {
                            return PackageTv.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('package-tv', null, { reload: 'package-tv' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
