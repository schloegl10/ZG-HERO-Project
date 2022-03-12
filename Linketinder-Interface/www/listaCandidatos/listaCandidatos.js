"use strict";
let uriCriacao = 'http://localhost:8080/candidatos';
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
    fetch(uriCriacao, {
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
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    //TODO adicionar conexão com banco posteriormente
    var data = google.visualization.arrayToDataTable([
        ["Categoria", "Quantidade"],
        ["JAVA", 8],
        ["Regex", 10]
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
