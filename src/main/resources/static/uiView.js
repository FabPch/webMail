/**
 * Created by formation on 12/12/2016.
 */
console.log('uiView');
app.config(function($stateProvider) {


    // HOME STATES AND NESTED VIEWS ========================================
        var add = {
            name: 'add',
            url: '/addUser',
            templateUrl: 'addUser.html'
        }

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        var login = {
            name: 'login',
            url: '/login',
            templateUrl: 'login.html',
            controller: 'loginController'
        }

        // var nav = {
        //     url: '/nav',
        //     templateUrl: 'nav.html',
        //     controller: 'loginController'
        // }
        //
        // //Secondary view
        //
        // var recep = {
        //     url: '/reception',
        //     templateUrl: 'reception.html'
        // }
        //
        // var write = {
        //     url: '/write',
        //     templateUrl: 'writeMail.html'
        // }
        //
        // var outbox = {
        //     url: '/outbox',
        //     templateUrl: 'outbox.html'
        // }

        $stateProvider.state(add);
        $stateProvider.state(login);

});

app.controller("loginController", function($scope, $http, $state) {

    $scope.userlog = function(usermail) {
        $scope.usermail = usermail;
        console.log('chemin début :')
        console.log(usermail);
        userVerify($http, $scope);
        $state.go('nav');

    };

    // $state.go('recep');

});