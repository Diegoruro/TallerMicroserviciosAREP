import loadHTML from '../loadHTML.js';

class custom_header extends HTMLElement{
    constructor(){
        super();
        this.isLoggin;

    }

    static get observedAttributes() {
        return ['isloggin']
    }

    attributeChangedCallback(nameAtr, oldValue, newValue) {
        switch (nameAtr) {
            case 'isloggin':
                this.isloggin = newValue;
                this.updateButton();
                break;
        
            default:
                break;
        }
    }

    connectedCallback(){
        async function load () {
            const html = await loadHTML("./header.html", import.meta.url);
            return html;
        }
        load().then((html) => {
            this.innerHTML = html;
        })
    }

    updateButton() {
        let button = document.getElementById('sign-out');
        if(this.isloggin != 'false') {
            button.style.display = '';
        }
        else if (button) {
            button.style.display = 'none';
        };
    }

}


window.customElements.define('custom-header', custom_header);