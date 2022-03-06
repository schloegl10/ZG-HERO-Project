"use strict";
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
        competencias: ''
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
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ["Categoria", "Quantidade"],
        ["Copper", 8.94],
        ["Silver", 10.49],
        ["Gold", 19.30],
        ["Platinum", 21.45]
    ]);
    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1]);
    var options = {
        title: "Candidatos por Categoria",
        width: 600,
        height: 400,
        bar: { groupWidth: "95%" },
        legend: { position: "none" },
    };
    var chart = new google.visualization.BarChart(document.getElementById('barchart_values'));
    // @ts-ignore
    chart.draw(view, options);
}
