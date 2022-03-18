package Linketinder.utils

class SqlUtils {

    public static String SELECT_PESSOA_FISICA = 'select * from pessoafisica'
    public static String SELECT_PESSOA_JURIDICA = 'select * from pessoajuridica'
    public static String SELECT_COMPETENCIA = 'select * from competencia'
    public static String SELECT_VAGA = 'select * from vaga'
    public static String INSERT_PESSOA_FISICA = 'INSERT INTO pessoafisica (nome, sobrenome, email, senha, pais, estado, cep, descricao, cpf, formacao, idade) VALUES '
    public static String INSERT_PESSOA_JURIDICA = 'INSERT INTO pessoafisica (nome, email, senha, pais, estado, cep, descricao, cnpj) VALUES '
    public static String INSERT_COMPETENCIA = 'INSERT INTO competencia (descricao, nivel) VALUES '
    public static String INSERT_RELACAO_COMPETENCIA_PESSOA = 'INSERT INTO relacaocompetenciapessoa (competenciaid, pessoafisicaid) VALUES '
    public static String INSERT_VAGA = 'INSERT INTO vaga (nome, descrição, estado, cidade, pessoajuridicaid) VALUES'
    public static String INSERT_RELACAO_COMPETENCIA_VAGA = 'INSERT INTO relacaocompetenciavaga (competenciaid, vagaid) VALUES '

}
