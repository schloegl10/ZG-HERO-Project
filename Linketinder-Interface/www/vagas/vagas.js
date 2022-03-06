"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const url_1 = require("url");
let todos = [];
let uriCriacao = 'http://localhost:8080/empresas?';
function validaResposta(data) {
    if (data.match('erros: ')) {
        exibeErro(data);
    }
    else {
        exibe(data);
    }
}
function exibe(data) {
    document.getElementById('resultados').innerText = data;
    document.getElementById('resultado').style.display = 'block';
    document.getElementById('erroDiv').style.display = 'none';
}
function exibeErro(data) {
    document.getElementById('erro').innerText = data;
    document.getElementById('resultados').style.display = 'none';
    document.getElementById('erroDiv').style.display = 'block';
}
function carrega() {
    var url = uriCriacao + new url_1.URLSearchParams(dados).toString();
    fetch(url, {
        method: 'GET',
        mode: 'no-cors',
        headers: {
            'Accept': '*/*',
            'Content-Type': 'text/plain'
        }
    })
        .then(response => response.json())
        .then(data => {
        validaResposta(data);
    })
        .catch(error => console.error("", error));
}
carrega();
