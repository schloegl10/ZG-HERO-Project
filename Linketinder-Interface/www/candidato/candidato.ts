import { URLSearchParams } from "url"
let todos = [];
let uriCriacao = 'http://localhost:8080/criacandidato?'

function validaResposta(data) {
  if (data.match('erros: ')) {
    exibeErro(data);
  } else {
    exibe(data);
  }
}

function exibe(data) {
  (document.getElementById('resultados')  as HTMLInputElement).innerText = data;
  (document.getElementById('resultado')  as HTMLInputElement).style.display = 'block';
  (document.getElementById('erroDiv')  as HTMLInputElement).style.display = 'none';
}

function exibeErro(data) {
  (document.getElementById('erro')  as HTMLInputElement).innerText = data;
  (document.getElementById('resultados')  as HTMLInputElement).style.display = 'none';
  (document.getElementById('erroDiv')  as HTMLInputElement).style.display = 'block';
}
function cria() {
    let nome = (document.getElementById('nome')  as HTMLInputElement).value.trim();
    let descricao = (document.getElementById('descricao')  as HTMLInputElement).value.trim();
    let email = (document.getElementById('email')  as HTMLInputElement).value.trim();
    let pais = (document.getElementById('pais')  as HTMLInputElement).value.trim();
    let estado = (document.getElementById('estado')  as HTMLInputElement).value.trim();
    let cep = (document.getElementById('cep')  as HTMLInputElement).value.trim();
    let CPF = (document.getElementById('CPF')  as HTMLInputElement).value.trim();
    let idade = (document.getElementById('idade')  as HTMLInputElement).value.trim();
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


  var url = uriCriacao + new URLSearchParams(dados).toString();

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