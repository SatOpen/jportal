'use strict';

describe('Controller Tests', function() {

    describe('UserOrder Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockUserOrder, MockUser, MockPackageTv;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockUserOrder = jasmine.createSpy('MockUserOrder');
            MockUser = jasmine.createSpy('MockUser');
            MockPackageTv = jasmine.createSpy('MockPackageTv');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'UserOrder': MockUserOrder,
                'User': MockUser,
                'PackageTv': MockPackageTv
            };
            createController = function() {
                $injector.get('$controller')("UserOrderDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jhipsterSampleApplicationApp:userOrderUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
