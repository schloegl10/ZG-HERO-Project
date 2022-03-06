import { URLSearchParams } from "url"
let todos = [];
let uriCriacao = 'http://localhost:8080/criaempresa?'

function validaResposta(data: any) {
  if (data.match('erros: ')) {
    exibeErro(data);
  } else {
    exibe(data);
  }
}

function exibe(data: any) {
  (document.getElementById('resultados')  as HTMLInputElement).innerText = data;
  (document.getElementById('resultado')  as HTMLInputElement).style.display = 'block';
  (document.getElementById('erroDiv')  as HTMLInputElement).style.display = 'none';
}

function exibeErro(data: any) {
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
    let CNPJ = (document.getElementById('CNPJ')  as HTMLInputElement).value.trim();
    let dados = {
        nome: nome,
        descricao: descricao,
        email: email,
        estado: estado,
        cep: cep,
        CNPJ: CNPJ,
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