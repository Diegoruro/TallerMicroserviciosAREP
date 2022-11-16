import loadHTML from '../loadHTML.js';
import sendTweet from '../../app.js';

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
                this.updateView(newValue);
                break;
        
            default:
                break;
        }
    }

    async updateView(value){
        if(value == 'home') {
            this.currentview = "./home/home.html";
            const html = await loadHTML(this.currentview, import.meta.url);
            this.innerHTML = html;

            
            let stream = document.getElementById('stream');

            fetch('http://localhost:4567/posts')
            .then(response => response.json())
            .then(data => {
                data.forEach(element => {
                    let tweetTemplate = `<article class="tweet">
                        <img class="prof-img" width="50px" height="50px" src="https://pbs.twimg.com/profile_images/1509033228874694659/KjCCiVZI_400x400.jpg" alt="">
                        <div class="tweet-content">
                            <div class="tweet-user">
                                <h3 class="tweet-name">${element.userName}</h3><p class="tweet-nickname">@${element.userName}</p>
                            </div>
                            <p class="tweet-msg">${element.text}</p>
                        </div>
                        </article>`;

                    stream.innerHTML = tweetTemplate + stream.innerHTML;
                });
            })
            .catch( err => console.log(err));

            document.getElementById("sendButton").addEventListener ("click", () => {
                sendTweet();
            });
        }
        else if (value == 'login') {
            this.currentview = "./login/login.html";            
            const html = await loadHTML(this.currentview, import.meta.url);
            this.innerHTML = html;
        }
    }
}

window.customElements.define('custom-body', custom_body);