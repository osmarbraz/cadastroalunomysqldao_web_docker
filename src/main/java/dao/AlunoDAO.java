package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Aluno;

/**
 * Realiza a persistência de dados.
 */
public class AlunoDAO {

    //Altere aqui os dados do seu banco de dados
    public static final String SERVIDOR = "mysql"; //caminho do MySQL no docker
    public static final String DATABASE = "db_alunos";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USUARIO = "root";
    public static final String SENHA = "root";

    //Utilizado para retornar uma lista de alunos.
    public ArrayList<Aluno> minhaLista = new ArrayList<>();

    public AlunoDAO() {
        //Cria o database e a tabela se não existir
        criar();
    }

    /**
     * Retorna uma conexão com o banco de dados com o database.
     */
    public Connection getConexao() {
        Connection connection = null;  //instância da conexão
        try {
            // Carregamento do JDBC Driver
            Class.forName(DRIVER);
            // Configurar a conexão
            String url = "jdbc:mysql://" + SERVIDOR + ":3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, USUARIO, SENHA);
            // Testando..
            if (connection == null) {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    /**
     * Retorna uma conexão com o banco de dados se o database.
     */
    public Connection getConexaoBD() {
        Connection connection = null;  //instância da conexão
        try {
            // Carregamento do JDBC Driver
            Class.forName(DRIVER);
            // Configurar a conexão
            String url = "jdbc:mysql://" + SERVIDOR + ":3306/";
            connection = DriverManager.getConnection(url, USUARIO, SENHA);
            // Testando..
            if (connection == null) {                
                System.out.println("Status: NÃO CONECTADO BD!");
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    /**
     * Retorna a Lista de Alunos(objetos)
     */
    public ArrayList<Aluno> getMinhaLista() {
        minhaLista.clear(); // Limpa nosso ArrayList
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("select * from tb_alunos");
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                int idade = res.getInt("idade");
                String curso = res.getString("curso");
                int fase = res.getInt("fase");
                Aluno objeto = new Aluno(id, nome, idade, curso, fase);
                minhaLista.add(objeto);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return minhaLista;
    }

    /**
     * Modifica a lista de alunos
     */
    public void setMinhaLista(ArrayList<Aluno> minhaLista) {
        this.minhaLista = minhaLista;
    }

    /**
     * Cadastra um novo aluno.
     */
    public boolean insertAlunoBD(Aluno objeto) {
        String sql = "insert into tb_alunos(id,nome,idade,curso,fase) values(?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCurso());
            stmt.setInt(5, objeto.getFase());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Deleta um aluno específico pelo seu campo ID
     */
    public boolean deleteAlunoBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("delete from tb_alunos where id = " + id);
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    /**
     * Edita um aluno específico pelo seu campo ID
     */
    public boolean updateAlunoBD(Aluno objeto) {
        String sql = "update tb_alunos set nome = ? ,idade = ? ,curso = ? ,fase = ? where id = ?";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCurso());
            stmt.setInt(4, objeto.getFase());
            stmt.setInt(5, objeto.getId());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega um aluno pelo ID
     */
    public Aluno carregaAluno(int id) {
        Aluno objeto = new Aluno();
        objeto.setId(id);
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("select * from tb_alunos where id = " + id);
            res.next();
            objeto.setNome(res.getString("nome"));
            objeto.setIdade(res.getInt("idade"));
            objeto.setCurso(res.getString("curso"));
            objeto.setFase(res.getInt("fase"));
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }

    /**
     * Retorna o maior id de um aluno.
     */
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("select max(id) id from tb_alunos");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

    /**
     * Cria o database e a tabela.
     */
    private void criar() {
        criarBancoDeDados();
        criarTabela();
    }

    /**
     * Cria o database.
     */
    private void criarBancoDeDados() {
        try {
            Connection con = getConexaoBD();
            Statement stmt = con.createStatement();
            //Cria a tabela senão existir
            int retorno = stmt.executeUpdate("create database if not exists " + DATABASE + ";"); 
            //System.out.println("Database criado:" + retorno);
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    /**
     * Cria as tabelas do banco de dados.
     */
    private void criarTabela() {
        try {
            Connection con = getConexao();
            Statement stmt = con.createStatement();
            //Cria a tabela senão existir
            int retorno = stmt.executeUpdate("create table if not exists tb_alunos (id integer not null, nome varchar(100), idade integer, curso varchar(50), fase integer, constraint pk_tb_alunos primary key(id));");
            //System.out.println("Tabela criada:" + retorno);
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }
}
