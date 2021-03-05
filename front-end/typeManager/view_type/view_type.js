import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../../js/dom_utils.js';
import {getBackendUrl, getFrontendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayType();
    fetchAndDisplaySkateboards();
});

function fetchAndDisplayType() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayType(JSON.parse(this.responseText))
            setAddButton();
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type'), true);
    xhttp.send();
}

function displayType(type) {
    setTextNode('type', getParameterByName('type'));
    setTextNode('avgSpeed', type.avgSpeed);
    setTextNode('maxWeight', type.maxWeight);
}

function fetchAndDisplaySkateboards() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySkateboards(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/skateboards', true);
    xhttp.send();
}

function displaySkateboards(skateboards) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    skateboards.skateboards.forEach(skateboard => {
        tableBody.appendChild(createTableRow(skateboard));
    })
}

function createTableRow(skateboard) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(skateboard.name));
    tr.appendChild(createLinkCell('view', getFrontendUrl() + '/skateboardManager/view_skateboard/view_skateboard.html?type='
        + getParameterByName('type') + '&skateboard=' + skateboard.id));
    tr.appendChild(createLinkCell('edit', getFrontendUrl() + '/skateboardManager/edit_skateboard/edit_skateboard.html?type='
        + getParameterByName('type') + '&skateboard=' + skateboard.id));
    tr.appendChild(createButtonCell('delete', () => deleteSkateboard(skateboard.id)));
    return tr;
}


function setAddButton(){
    let addSkateboard = document.getElementById('addSkateboard');
    addSkateboard.onclick = redirectToAddSkateboardView;
}

function redirectToAddSkateboardView(){
    window.location.href = getFrontendUrl() + '/skateboardManager/add_skateboard/add_skateboard.html?type=' + getParameterByName('type');
}

function deleteSkateboard(skateboard) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySkateboards();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/types/' + getParameterByName('type')
        + '/skateboards/' + skateboard, true);
    xhttp.send();
}
