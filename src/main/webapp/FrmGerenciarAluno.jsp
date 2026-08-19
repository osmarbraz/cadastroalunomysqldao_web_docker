<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Aluno"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar Alunos</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Cadastro de Aluno - Listar</h1>                        
        <table border="1">    
            <td><b>Id</b></td><td><b>Nome</b></td><td><b>Idade</b></td><td><b>Curso</b></td><td><b>Fase</b></td><td><b>Editar</b></td><td><b>Excluir</b></td>
            <% 	Aluno objetoaluno = new Aluno();
                ArrayList<Aluno> alunos = objetoaluno.getMinhaLista();
                for (Aluno aluno : alunos) {%>                    			
            <tr>
                <td><%=aluno.getId()%></td>
                <td><%=aluno.getNome()%></td>
                <td><%=aluno.getIdade()%></td>
                <td><%=aluno.getCurso()%></td>
                <td><%=aluno.getFase()%></td>
                <td><a href='FrmCadastroAluno.jsp?id=<%=aluno.getId()%>'>Editar</a> </td>
                <td><a href='servlet/AlunoExcluir?id=<%=aluno.getId()%>'>Excluir</a> </td>
            </tr>  
            <% }%>
        </table>
        <br>
        <a href='index.jsp'>Voltar ao menu</a>
    </body>
</html>
