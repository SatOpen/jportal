(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('PackageTvDialogController', PackageTvDialogController);

    PackageTvDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PackageTv', 'UserOrder'];

    function PackageTvDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PackageTv, UserOrder) {
        var vm = this;

        vm.packageTv = entity;
        vm.clear = clear;
        vm.save = save;
        vm.userorders = UserOrder.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.packageTv.id !== null) {
                PackageTv.update(vm.packageTv, onSaveSuccess, onSaveError);
            } else {
                PackageTv.save(vm.packageTv, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterSampleApplicationApp:packageTvUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
