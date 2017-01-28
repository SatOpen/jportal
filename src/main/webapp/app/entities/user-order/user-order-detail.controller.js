(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('UserOrderDetailController', UserOrderDetailController);

    UserOrderDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserOrder', 'User', 'PackageTv'];

    function UserOrderDetailController($scope, $rootScope, $stateParams, previousState, entity, UserOrder, User, PackageTv) {
        var vm = this;

        vm.userOrder = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterSampleApplicationApp:userOrderUpdate', function(event, result) {
            vm.userOrder = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
