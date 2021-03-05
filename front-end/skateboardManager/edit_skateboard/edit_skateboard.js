import {getParameterByName} from '../../js/dom_utils.js';
import {getBackendUrl, getFrontendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));

    const editButton = document.getElementById('editButton');
    editButton.addEventListener('click', () => redirectToTypeView())

    fetchAndDisplaySkateboard();
});

function fetchAndDisplaySkateboard() {
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
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/skateboards/'
        + getParameterByName('skateboard'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("PUT", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/skateboards/'
        + getParameterByName('skateboard'), true);

    const request = {
        'name': document.getElementById('name').value,
        'producer': document.getElementById('producer').value,
        'price': parseFloat(document.getElementById('price').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function redirectToTypeView(){
    window.location.href= getFrontendUrl() + "/typeManager/view_type/view_type.html?type=" + getParameterByName('type');
}