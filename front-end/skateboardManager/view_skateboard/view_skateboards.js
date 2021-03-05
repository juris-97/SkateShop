import {
    getParameterByName,
    setTextNode
} from '../../js/dom_utils.js';
import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySkateboard();
});

function fetchAndDisplaySkateboard() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySkateboard(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET",
        getBackendUrl() + '/api/types/' +
        getParameterByName('type') + '/skateboards/' +
        getParameterByName('skateboard'), true);
    xhttp.send();
}

function displaySkateboard(skateboard) {
    setTextNode('name', skateboard.name);
    setTextNode('producer', skateboard.producer);
    setTextNode('price', skateboard.price);
    setTextNode('type', skateboard.type);
}