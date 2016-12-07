/**
 * Created by formation on 06/12/2016.
 */
var app = angular.module("webmail", ["ngRoute"]);

// var xhr = new XMLHttpRequest();

// //On définit le controller
// app.controller("controlMail", function ($scope, $http) {
//
//     init($scope, $http);
//
//     $scope.remove = function(id) {
//         deleteMail(id, $http, $scope);
//     };
//
//     $scope.save = function(mail) {
//         $scope.mail = mail;
//         saveMail($http, $scope);
//     }
//
//     $scope.saveU = function(user) {
//         $scope.user = user;
//         saveUser($http, $scope);
//     }
//
//     $scope.display = function(id) {
//         getMail(id, $http, $scope);
//     };
//
//     $scope.order = function(prop) {
//         $scope.propMail = prop;
//     }
//
// })

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
        .when('/part4', {
            templateUrl : 'partials/part4.html'
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

    var url = 'http://172.28.11.18:8080/mail/';

    http.get(url).then(function(resp) {
        scope.mails = resp.data;
        scope.usermail = {};
        console.log(resp.data);
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

    var url = 'http://172.28.11.18:8080/mail/';

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
        console.log('ici');
        console.log(scope.mail);
    })
}

//On définit le controller
app.controller("controlMail", function ($scope, $http) {

    init($scope, $http);

    $scope.remove = function(id) {
        deleteMail(id, $http, $scope);
    };

    $scope.send = function(mail) {
        $scope.mailToSave = mail;
        saveMail($http, $scope);
    }

    $scope.saveU = function(usermail) {
        console.log('coucou');
        console.log('test 1 add :');
        console.log(usermail);
        $scope.usermail = usermail;
        // console.log(('test 2 add :'))
        // console.log($scope.usermailToAdd);
        saveUser($http, $scope);
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