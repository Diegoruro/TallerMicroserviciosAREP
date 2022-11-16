import './node_modules/amazon-cognito-identity-js/dist/amazon-cognito-identity.js';
let user = '';

export function login() {

    //JWT then ->
    var authenticationData = {
        Username: document.getElementById("username").value,
        Password: document.getElementById("password").value,
    };
    var authenticationDetails = new AmazonCognitoIdentity.AuthenticationDetails(authenticationData);
    var poolData = {
        UserPoolId: 'us-east-1_wh8JAy3C3', // Your user pool id here
        ClientId: '4do7bfv7s15a07itimburb63l9', // Your client id here
    };
    var userPool = new AmazonCognitoIdentity.CognitoUserPool(poolData);
    var userData = {
        Username: document.getElementById("username").value,
        Pool: userPool,
    };
    user = authenticationData.Username;
    var cognitoUser = new AmazonCognitoIdentity.CognitoUser(userData);
    cognitoUser.authenticateUser(authenticationDetails, {
        onSuccess: function(result) {
            var accessToken = result.getAccessToken().getJwtToken();
            let header = document.getElementById('header');
            header.setAttribute('isloggin', 'true');

            let body = document.getElementById('body');
            body.setAttribute('currentview', 'home');
        },
    
        onFailure: function(err) {
            alert(err.message || JSON.stringify(err));
        },
    });
}

export function logout() {
    // ESCRIBIR FUNCIÓN LOG OUT
    console.log('logout()');
}

export default function sendTweet() {
    let tweet = document.getElementById('tweet').value;
    let stream = document.getElementById('stream');

    let tweetTemplate = `<article class="tweet">
    <img class="prof-img" width="50px" height="50px" src="https://pbs.twimg.com/profile_images/1509033228874694659/KjCCiVZI_400x400.jpg" alt="">
    <div class="tweet-content">
        <div class="tweet-user">
            <h3 class="tweet-name">${user}</h3><p class="tweet-nickname">@${user}</p>
        </div>
        <p class="tweet-msg">${tweet}</p>
    </div>
    </article>`;
    
    stream.innerHTML = tweetTemplate + stream.innerHTML;

    let data = {
        text: tweet,
        date: new Date().toLocaleDateString(),
        userName: user
    }
    fetch('http://localhost:4567/post', {
        method: "POST",
        body: JSON.stringify(data)
    })


    document.getElementById('tweet').value = '';
}
