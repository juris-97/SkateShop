import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell} from '../../js/dom_utils.js';
import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTypes();
});

function fetchAndDisplayTypes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTypes(JSON.parse(this.responseText))
            setAddButton();
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types', true);
    xhttp.send();
}

function displayTypes(types) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    types.types.forEach(type => {
        tableBody.appendChild(createTableRow(type));
    })
}

function createTableRow(type) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(type));
    tr.appendChild(createLinkCell('view', '../view_type/view_type.html?type=' + type));
    tr.appendChild(createLinkCell('edit', '../edit_type/edit_type.html?type=' + type));
    tr.appendChild(createButtonCell('delete', () => deleteType(type)));
    return tr;
}

function setAddButton(){
    let addType = document.getElementById('addType');
    addType.onclick = redirectToAddTypeView;
}

function redirectToAddTypeView(){
    window.location.href = '../add_type/add_type.html';
}

function deleteType(type) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 204) {
            fetchAndDisplayTypes();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/types/' + type, true);
    xhttp.send();
}