function login() {
    // JWT then ->

    let header = document.getElementById('header');
    header.setAttribute('isloggin', 'true');

    let body = document.getElementById('body');
    body.setAttribute('currentview', 'home');
}

function logout() {
    // ESCRIBIR FUNCIÓN LOG OUT
}

function sendTweet() {
    let tweet = document.getElementById('tweet').value;
    let stream = document.getElementById('stream');

    let tweetTemplate = `<article class="tweet">
    <img class="prof-img" width="50px" height="50px" src="https://pbs.twimg.com/profile_images/1509033228874694659/KjCCiVZI_400x400.jpg" alt="">
    <div class="tweet-content">
        <div class="tweet-user">
            <h3 class="tweet-name">Juan Rojas</h3><p class="tweet-nickname">@juanro</p>
        </div>
        <p class="tweet-msg">${tweet}</p>
    </div>
    </article>`;
    stream.innerHTML += tweetTemplate;
    document.getElementById('tweet').value = '';
}