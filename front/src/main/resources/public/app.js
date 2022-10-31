function loadGetMsg() {
    let header = document.getElementById('header');
    header.setAttribute('isloggin', 'true');

    let body = document.getElementById('body');
    body.setAttribute('currentview', 'home');

    // let message = document.getElementById("message").value;
    // const xhttp = new XMLHttpRequest();
    // xhttp.onload = function () {
    // }
    // xhttp.open("POST", "/message");
    // xhttp.setRequestHeader("content-type", "text/plain")
    // xhttp.send(message);
}