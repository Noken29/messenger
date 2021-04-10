var app = angular.module('SpringChat', ['AngularStomp']);

app.controller('ChatCtrl', function($scope, ngstomp) {
    $scope.messages = [];
    var chatId = document.getElementById("chatId").value;
    $.getJSON('http://localhost:8080/hello/' + chatId, function(data) {
        for(var i = 0; i < data.length; ++i)
            $scope.messages.push(data[i]);
    });
    $scope.client = ngstomp('/chat');
    $scope.client.connect('', '', function() {
        $scope.client.subscribe('/topic/chat/' + chatId, function(message) {
            $scope.messages.push(JSON.parse(message.body));
        });
    });

    $scope.msg = {};
    $scope.msg.sender = document.getElementById("user").value;
    $scope.msg.text = '';
    $scope.msg.chatId = chatId;

    $scope.sendMsg = function() {

        if (this.msg.text) {
            this.client.send('/app/chat', {}, JSON.stringify(this.msg));
            this.msg.text = '';
        }
    };

})
