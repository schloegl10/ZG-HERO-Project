import com.opencsv.CSVWriter
import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import java.nio.file.Files

class WebCrawler {

    public static String DOWNLOAD_DOCUMENTACAO = 'https://www.ans.gov.br/images/stories/Plano_de_saude_e_Operadoras/tiss/Padrao_tiss/tiss3/Padr%C3%A3o_TISS_Componente_Organizacional_202103.pdf'
    public static String VESOES_ANTERIORES = 'https://www.ans.gov.br/prestadores/tiss-troca-de-informacao-de-saude-suplementar/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss'
    public static String DOWNLOAD_TABELAS = 'https://www.ans.gov.br/images/stories/Plano_de_saude_e_Operadoras/tiss/Padrao_tiss/tiss3/padrao-tiss-tabela-erros-envio-para-ans-padrao-tiss-08022019.xlsx'


    static void main(String[] args) {
        downloadDocumentacao()
        obtemDadosTabela()
        downloadTabelas()

    }

    private static void downloadDocumentacao() {
        realizaRequisicaoBaixaArquivo(DOWNLOAD_DOCUMENTACAO, "./doc.pdf")
    }

    private static void obtemDadosTabela() {
        Document resultado = realizaRequisicao(VESOES_ANTERIORES)
        List<Element> linhas =  resultado.getElementsByTag('tbody').first().getElementsByTag("tr")
        List<Map> resultados = []
        for(Element linha in linhas) {
            List<Element> campos = linha.getElementsByTag("td")
            String competencia = campos.get(0)
            String publicacao = campos.get(1)
            String vigencia = campos.get(2)
            Map res = [
                    'competencia' : competencia,
                    'publicacao'  : publicacao,
                    'vigencia'    : vigencia
            ]
            resultados.add(res)
            if(competencia == 'jan/2016') {
                break
            }
        }
        println(resultados.toString())
    }

    private static void downloadTabelas() {
        realizaRequisicaoBaixaArquivo(DOWNLOAD_TABELAS, "./padrao.xlsx")
    }

    private static void realizaRequisicaoBaixaArquivo(String link, String localArquivo) {
        URL url = new URL(link)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection()
        connection.connect()
        connection
        byte[] data = new byte[4096]
        InputStream input = connection.getInputStream()
        OutputStream output = new FileOutputStream(localArquivo)
        int count
        while ((count = input.read(data)) != -1) {
            output.write(data, 0, count)
        }
        try {
            if (output != null) {
                output.close()
            }
            if (input != null) {
                input.close()
            }
        } catch (IOException ignored) {}
        if (connection != null) {
            connection.disconnect()
        }
    }

    private static Document realizaRequisicao(String url) {
        return Jsoup.connect(url).get()
    }
}
