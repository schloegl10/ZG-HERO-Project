package Linketinder.utils

class SqlUtils {

    public static String SELECT_PESSOA_FISICA = 'select * from pessoafisica'
    public static String SELECT_PESSOA_JURIDICA = 'select * from pessoajuridica'
    public static String SELECT_COMPETENCIA = 'select * from competencia'
    public static String SELECT_VAGA = 'select * from vaga'
    public static String INSERT_PESSOA_FISICA = 'INSERT INTO pessoafisica (nome, sobrenome, email, senha, pais, estado, cep, descricao, cpf, formacao, idade) VALUES '
    public static String INSERT_PESSOA_JURIDICA = 'INSERT INTO pessoajuridica (nome, email, senha, pais, estado, cep, descricao, cnpj) VALUES '
    public static String INSERT_COMPETENCIA = 'INSERT INTO competencia (descricao, nivel) VALUES '
    public static String INSERT_RELACAO_COMPETENCIA_PESSOA = 'INSERT INTO relacaocompetenciapessoa (competenciaid, pessoafisicaid) VALUES '
    public static String INSERT_VAGA = 'INSERT INTO vaga (nome, descrição, estado, cidade, pessoajuridicaid) VALUES'
    public static String INSERT_RELACAO_COMPETENCIA_VAGA = 'INSERT INTO relacaocompetenciavaga (competenciaid, vagaid) VALUES '
    public static String UPDATE_PESSOA_FISICA = 'UPDATE pessoafisica SET '
    public static String UPDATE_PESSOA_JURIDICA = 'UPDATE pessoajuridica SET '
    public static String UPDATE_COMPETENCIA = 'UPDATE vaga SET '
    public static String UPDATE_VAGA = 'UPDATE competencia SET '
    public static String DELETE_PESSOA_FISICA = 'DELETE pessoafisica '
    public static String DELETE_PESSOA_JURIDICA = 'DELETE pessoajuridica '
    public static String DELETE_COMPETENCIA = 'DELETE vaga '
    public static String DELETE_VAGA = 'DELETE competencia '

}
