function loadGetMsg() {
    let message = document.getElementById("message").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
    }
    xhttp.open("POST", "/message");
    xhttp.setRequestHeader("content-type", "text/plain")
    xhttp.send(message);
}