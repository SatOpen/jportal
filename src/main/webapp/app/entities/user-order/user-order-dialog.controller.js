(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('UserOrderDialogController', UserOrderDialogController);

    UserOrderDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserOrder', 'User', 'PackageTv'];

    function UserOrderDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserOrder, User, PackageTv) {
        var vm = this;

        vm.userOrder = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();
        vm.packagetvs = PackageTv.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userOrder.id !== null) {
                UserOrder.update(vm.userOrder, onSaveSuccess, onSaveError);
            } else {
                UserOrder.save(vm.userOrder, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApplicationApp:userOrderUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.creationDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
