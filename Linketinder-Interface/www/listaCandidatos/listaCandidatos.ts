let uriCriacao = 'http://localhost:8080/candidatos?'

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
function carrega() {
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

 google.charts.load("current", {packages:["corechart"]});
 google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    //adicionar conexão com banco posteriormente
      var data = google.visualization.arrayToDataTable([
        ["Categoria", "Quantidade"],
        ["JAVA", 8],
        ["Silver", 10],
        ["Gold", 19],
        ["Platinum", 21]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1]);

      var options = {
        title: "Candidatos por Categoria",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart((document.getElementById('barchart_values')  as HTMLInputElement));
      // @ts-ignore
      chart.draw(view, options);
  }