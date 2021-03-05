import {getParameterByName} from '../../js/dom_utils.js';
import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    const editForm = document.getElementById('editForm');
    editForm.addEventListener('submit', event => updateInfoAction(event));

    const editButton = document.getElementById('editButton');
    editButton.addEventListener('click', () => redirectToListTypes())
    fetchAndDisplayType();
});

function fetchAndDisplayType() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type'),true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayType();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/types/' + getParameterByName('type'), true);

    const request = {
        'avgSpeed': parseFloat(document.getElementById('avgSpeed').value),
        'maxWeight': document.getElementById('maxWeight').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function redirectToListTypes(){
    window.location.href="../list_types/list_types.html";
}