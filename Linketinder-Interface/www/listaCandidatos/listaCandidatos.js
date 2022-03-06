"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const url_1 = require("url");
let todos = [];
let uriCriacao = 'http://localhost:8080/candidatos?';
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
    let nome = document.getElementById('nome').value.trim();
    let descricao = document.getElementById('descricao').value.trim();
    let email = document.getElementById('email').value.trim();
    let pais = document.getElementById('pais').value.trim();
    let estado = document.getElementById('estado').value.trim();
    let cep = document.getElementById('cep').value.trim();
    let CNPJ = document.getElementById('CNPJ').value.trim();
    let dados = {
        nome: nome,
        descricao: descricao,
        email: email,
        estado: estado,
        cep: cep,
        CNPJ: CNPJ,
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
carrega();
function drawSort() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Name');
    data.addColumn('number', 'Salary');
    data.addColumn('boolean', 'Full Time');
    data.addRows(5);
    data.setCell(0, 0, 'John');
    data.setCell(0, 1, 10000);
    data.setCell(0, 2, true);
    data.setCell(1, 0, 'Mary');
    data.setCell(1, 1, 25000);
    data.setCell(1, 2, true);
    data.setCell(2, 0, 'Steve');
    data.setCell(2, 1, 8000);
    data.setCell(2, 2, false);
    data.setCell(3, 0, 'Ellen');
    data.setCell(3, 1, 20000);
    data.setCell(3, 2, true);
    data.setCell(4, 0, 'Mike');
    data.setCell(4, 1, 12000);
    data.setCell(4, 2, false);
    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1]);
    var formatter = new google.visualization.NumberFormat({ prefix: '$' });
    formatter.format(data, 1); // Apply formatter to second column
    var table = new google.visualization.Table(document.getElementById('table_sort_div'));
    table.draw(data, { width: '100%', height: '100%' });
    var chart = new google.visualization.BarChart(document.getElementById('chart_sort_div'));
    chart.draw(view);
    google.visualization.events.addListener(table, 'sort', function (event) {
        data.sort([{ column: event.column, desc: !event.ascending }]);
        chart.draw(view);
    });
}
