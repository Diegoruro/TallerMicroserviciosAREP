import loadHTML from '../loadHTML.js';

class custom_body extends HTMLElement{
    
    constructor(){
        super();
        this.currentview;
    }
    
    static get observedAttributes() {
        return ['currentview']
    }
    
    attributeChangedCallback(nameAtr, oldValue, newValue) {
        switch (nameAtr) {
            case 'currentview':
                console.log('currentview status', oldValue, newValue);
                this.updateView(newValue);
                break;
        
            default:
                break;
        }
    }

    async connectedCallback(){
        console.log('loading');
        console.log(this.currentview);
        const html = await loadHTML(this.currentview, import.meta.url);
        this.innerHTML = html;
    }

    async updateView(value){
        if(value == 'home') {
            this.currentview = "./home/home.html";
            const html = await loadHTML(this.currentview, import.meta.url);
            this.innerHTML = html;
        }
        else if (value == 'login') {
            this.currentview = "./login/login.html";
        }
    }
}

window.customElements.define('custom-body', custom_body);