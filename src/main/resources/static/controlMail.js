/**
 * Created by formation on 06/12/2016.
 */
var app = angular.module("webmail", ["ngRoute"]);

//Routes
app.config(function ($routeProvider) {

    console.log($routeProvider);
    $routeProvider
        .when('/recep', {
            templateUrl : 'reception.html'
        })
        .when('/add', {
            templateUrl : 'addUser.html'
        })
        .when('/write', {
            templateUrl : 'writeMail.html'
        })
        .when('/sent', {
            templateUrl : 'sent.html'
        });
});

//Directive
app.directive("cartemail", function() {

    return {
        template : "<div class='panel panel-success'>" +
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
        "<button class='btn btn-warning' ng-click='hide()'>Hide</button>"
    };
});

//Function
    //Init
var init = function(scope, http) {

    var url = 'http://172.28.11.18:8080/mail/log/';
    if (scope.connected)
        url += scope.usermail.id;
    else
        url += -1;

    http.get(url).then(function(resp) {
        scope.mails = resp.data;
        if (!scope.connected)
            scope.usermail = {};
        console.log(scope.connected);
    })
}
    //Remove
var deleteMail = function (id, http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + id;

    http.delete(url).then(function() {
        init(scope, http);
    })
}

    //Save Mail
var saveMail = function (http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + scope.usermail.id;

    console.log(scope.mailToSave);

    http.post(url, scope.mailToSave).then(function() {
        init(scope, http);
    })
}

    //Save User
var saveUser = function (http, scope) {

    var url = 'http://172.28.11.18:8080/user/';

    console.log('To save!');
    console.log(scope.usermail);

    http.post(url, scope.usermail).then(function() {
        console.log('To save! hey!');
        init(scope, http);
    })
}

    //Display
var getMail = function (id, http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + id;

    http.get(url).then(function(resp) {
        scope.mail = resp.data;
        scope.showok = true;
    })
}

    //Log In
var userVerify = function (http, scope) {

    var url = 'http://172.28.11.18:8080/mail/log';
    console.log('chemin fonction userVerify :')

    http.post(url, scope.usermail).then(function(resp) {
        console.log('chemin fonction requete :')
        // console.log(scope.usermail.id);
        if ((scope.usermail.id = resp.data) != -1)
            scope.connected = true;
        // console.log(scope.usermail.id);
        init(scope, http);
    })
}

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
    }

    $scope.saveU = function(usermail) {
        $scope.usermail = usermail;
        saveUser($http, $scope);
    }

    $scope.userlog = function(usermail) {
        $scope.usermail = usermail;
        console.log('chemin début :')
        console.log(usermail);
        userVerify($http, $scope);

    }

    $scope.userlogout = function () {
        $scope.connected = false;
        init($scope, $http);
    }

    $scope.display = function(id) {
        getMail(id, $http, $scope);
    };

    $scope.hide = function() {
        $scope.showok = false;
    }

    $scope.order = function(prop) {
        $scope.propMail = prop;
    }

})