(function() {
    'use strict';
    angular
        .module('jhipsterSampleApplicationApp')
        .factory('PackageTv', PackageTv);

    PackageTv.$inject = ['$resource'];

    function PackageTv ($resource) {
        var resourceUrl =  'api/package-tvs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
