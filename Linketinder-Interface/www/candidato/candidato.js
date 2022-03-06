"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const url_1 = require("url");
let todos = [];
let uriCriacao = 'http://localhost:8080/criacandidato?';
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
function cria() {
    let nome = document.getElementById('nome').value.trim();
    let descricao = document.getElementById('descricao').value.trim();
    let email = document.getElementById('email').value.trim();
    let pais = document.getElementById('pais').value.trim();
    let estado = document.getElementById('estado').value.trim();
    let cep = document.getElementById('cep').value.trim();
    let CPF = document.getElementById('CPF').value.trim();
    let idade = document.getElementById('idade').value.trim();
    let dados = {
        nome: nome,
        descricao: descricao,
        email: email,
        estado: estado,
        cep: cep,
        CPF: CPF,
        idade: idade,
        competencias: []
    };
    var url = uriCriacao + new url_1.URLSearchParams(dados).toString();
    fetch(url, {
        method: 'POST',
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
