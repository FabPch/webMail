/**
 * Created by formation on 06/12/2016.
 */
var app = angular.module("webmail", ["ui.router"]);

//Routes
// app.config(function ($routeProvider) {
//
//     console.log($routeProvider);
//     $routeProvider
//         .when('/recep', {
//             templateUrl : 'reception.html'
//         })
//         .when('/add', {
//             templateUrl : 'addUser.html'
//         })
//         .when('/write', {
//             templateUrl : 'writeMail.html'
//         })
//         .when('/outbox', {
//             templateUrl : 'outbox.html'
//         });
// });

//Directive
app.directive("cartemail", function() {

    return {
        template :
        "<div ng-if='showok'>" +
        "<div class='panel panel-success'>" +
        "<div class='panel-heading'>Super Template</div>" +
        "<div class='panel-body'>" +
        "<ul>" +
        "<li>" +
        "<p>Title : {{mail.title}}</p>" +
        "<p>Content : {{mail.content}}</p>" +
        "</li>" +
        "</ul>" +
        "</div>" +
        "</div>" +
        "<button class='btn btn-warning' ng-click='hide()'>Hide</button>" +
        "</div>"
    };
});


//On définit le controller
app.controller("controlMail", function ($scope, $http) {


    if ($scope.connected == undefined) {
        $scope.connected = false;
        console.log($scope.connected);
    }
    init($scope, $http);

    $scope.remove = function(id) {
        deleteMail(id, $http, $scope);
    };

    $scope.send = function(mail, rec) {
        $scope.mailToSave = mail;
        console.log(rec);
        $scope.mailToSave.userMails = [rec];
        console.log($scope.mailToSave.userMails);
        saveMail($http, $scope);
    };

    $scope.saveU = function(usermail) {
        $scope.usermail = usermail;
        saveUser($http, $scope);
    };

    // $scope.userlog = function(usermail) {
    //     $scope.usermail = usermail;
    //     console.log('chemin début :')
    //     console.log(usermail);
    //     userVerify($http, $scope);
    //
    // };

    $scope.userlogout = function () {
        userLogout($http, $scope);
    };

    $scope.display = function(id) {
        getMail(id, $http, $scope);
    };

    $scope.hide = function() {
        $scope.showok = false;
    };

    $scope.order = function(prop) {
        $scope.propMail = prop;
    };

});