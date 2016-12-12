/**
 * Created by formation on 12/12/2016.
 */
//Function
    //Init
var init = function(scope, http) {

        var url = 'http://172.28.11.18:8080/mail/log/';
        if (scope.connected)
            url += scope.usermail.id;
        else
            url += -1;

        http.get(url).then(function(resp) {
            scope.mails = resp.data[0];
            scope.sentmails = resp.data[1];
            scope.userconnected = resp.data[2];
            if (!scope.connected)
                scope.usermail = {};
            console.log(scope.userconnected);
            scope.showok = false;
        })
    };
//Remove
var deleteMail = function (id, http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + id;

    http.delete(url).then(function() {
        init(scope, http);
    });
};

//Save Mail
var saveMail = function (http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + scope.usermail.id;

    console.log(scope.mailToSave);

    http.post(url, scope.mailToSave).then(function() {
        init(scope, http);
    })
};

//Save User
var saveUser = function (http, scope) {

    var url = 'http://172.28.11.18:8080/user/';

    console.log('To save!');
    console.log(scope.usermail);

    http.post(url, scope.usermail).then(function() {
        console.log('To save! hey!');
        init(scope, http);
    })
};

//Display Inbox
var getMail = function (id, http, scope) {

    var url = 'http://172.28.11.18:8080/mail/' + id;

    http.get(url).then(function(resp) {
        scope.mail = resp.data;
        scope.showok = true;
    })
};

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
};

//Log Out
var userLogout = function (http, scope) {

    var url = 'http://172.28.11.18:8080/mail/logout';
    console.log('chemin fonction logout :')

    http.post(url, scope.usermail).then(function(resp) {
        console.log('chemin fonction requete :')
        scope.connected = false;
        init(scope, http);
    })
};