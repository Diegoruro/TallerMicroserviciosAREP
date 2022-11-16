import { login, logout } from "../../../app.js";

const events = {
    CLICK: 'click',
}

const selectors = {
    LOGIN_BUTTON: '.main-button.login-button',
}

class Login {
    constructor(element) {
        this.element = element;

        this.loginButton = null;

        this.init();
    }

    init() {
        this.setElements();
        this.addEventListeners();
    }

    setElements() {
        this.loginButton = document.querySelector(selectors.LOGIN_BUTTON, this.element);
    }

    addEventListeners() {
        this.loginButton.addEventListener(events.CLICK, () => {
            login();
        });
    }
}

export default {
    init: () => {
        return [...document.querySelectorAll('.login-box')].map((element) => new Login(element));
    }
}