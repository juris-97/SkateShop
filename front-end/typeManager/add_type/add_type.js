import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    const addForm = document.getElementById('addForm');
    addForm.addEventListener('submit', event => addNewTypeAction(event));

    const addButton = document.getElementById('addButton');
    addButton.addEventListener('click', () => redirectToListTypes())
});

function addNewTypeAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/types', true);

    const request = {
        typeName: document.getElementById('type').value,
        avgSpeed: document.getElementById('avgSpeed').value,
        maxWeight: document.getElementById('maxWeight').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function redirectToListTypes() {
    window.location.href = "../list_types/list_types.html";
}