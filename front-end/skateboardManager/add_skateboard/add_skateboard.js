import {
    getParameterByName, setTextNode,
} from '../../js/dom_utils.js';
import {getBackendUrl, getFrontendUrl} from '../../js/configuration.js';


window.addEventListener('load', () => {
    const addForm = document.getElementById('addForm');
    addForm.addEventListener('submit', event => addNewSkateboardAction(event));

    setTextNode('type', getParameterByName('type'));

    const addButton = document.getElementById('addButton');
    addButton.addEventListener('click', () => redirectToTypeView())
});

function addNewSkateboardAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/skateboards', true);

    const request = {
        name: document.getElementById('name').value,
        producer: document.getElementById('producer').value,
        price: parseFloat(document.getElementById('price').value),
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function redirectToTypeView() {
    window.location.href = getFrontendUrl() + "/typeManager/view_type/view_type.html?type=" + getParameterByName('type');
}