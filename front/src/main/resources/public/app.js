
function loadGetMsg() {
    let header = document.getElementById('header');
    header.setAttribute('isloggin', 'true');

    let body = document.getElementById('body');


    // let message = document.getElementById("message").value;
    // const xhttp = new XMLHttpRequest();
    // xhttp.onload = function () {
    // }
    // xhttp.open("POST", "/message");
    // xhttp.setRequestHeader("content-type", "text/plain")
    // xhttp.send(message);

}


function signInButton(){
    var authenticationData = {
        Username: document.getElementById("username").value,
        Password: document.getElementById("password").value,
        };
    var authenticationDetails = new AmazonCognitoIdentity.AuthenticationDetails(authenticationData);
    var poolData = {
        UserPoolId: _config.cognito.userPoolId, // Your user pool id here
        ClientId: _config.cognito.userPoolClientId, // Your client id here
    };

    var userPool = new AmazonCognitoIdentity.CognitoUserPool(poolData);

    var userData = {
        Username: document.getElementById("username").value,
        Pool: userPool,
    };

    var cognitoUser = new AmazonCognitoIdentity.CognitoUser(userData);

    cognitoUser.authenticateUser(authenticationDetails, {
        onSuccess: function(result) {
            var accessToken = result.getAccessToken().getJwtToken();
            console.log(accessToken);
        },

        onFailure: function(err) {
            alert(err.message || JSON.stringify(err));
        },
    });
}