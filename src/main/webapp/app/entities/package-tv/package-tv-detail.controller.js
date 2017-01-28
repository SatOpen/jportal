(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('PackageTvDetailController', PackageTvDetailController);

    PackageTvDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PackageTv', 'UserOrder'];

    function PackageTvDetailController($scope, $rootScope, $stateParams, previousState, entity, PackageTv, UserOrder) {
        var vm = this;

        vm.packageTv = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApplicationApp:packageTvUpdate', function(event, result) {
            vm.packageTv = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
