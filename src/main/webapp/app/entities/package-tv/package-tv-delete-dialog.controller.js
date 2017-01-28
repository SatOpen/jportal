(function() {
    'use strict';

    angular
        .module('jhipsterSampleApplicationApp')
        .controller('PackageTvDeleteController',PackageTvDeleteController);

    PackageTvDeleteController.$inject = ['$uibModalInstance', 'entity', 'PackageTv'];

    function PackageTvDeleteController($uibModalInstance, entity, PackageTv) {
        var vm = this;

        vm.packageTv = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PackageTv.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
