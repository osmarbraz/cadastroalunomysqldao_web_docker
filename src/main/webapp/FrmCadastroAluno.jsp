<%@page import="modelo.Aluno"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Aluno</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <script src="biblioteca.js"></script>
    </head>
    <body>       
        <%   Aluno aluno = null;
            Aluno objetoaluno = new Aluno();

            if (request.getParameter("id") != null) {
                //Recupero o objeto                
                aluno = objetoaluno.carregaAluno(Integer.parseInt(request.getParameter("id")));
        %>
        <h1>Cadastro de Aluno - Alterar</h1>
        <form name="FrmCadastroAluno" method="post" action="servlet/AlunoAlterar">	
            <%
            } else { %>    
            <h1>Cadastro de Aluno - Incluir</h1>
            <form name="FrmCadastroAluno" method="post" action="servlet/AlunoIncluir">	
                <% }%>

                <input type=hidden name="id" value="<%=request.getParameter("id")%>">
                Nome: <input type=text name="nome" value="<%=aluno != null ? aluno.getNome() : ""%>" size=50 maxlength=100><br><br>
                Idade: <input type=text name="idade" value="<%=aluno != null ? aluno.getIdade() : ""%>" size=10 maxlength=10 onkeydown="return somenteNumero(event)"> <br><br>
                Curso: <input type=text name="curso" value="<%=aluno != null ? aluno.getCurso() : ""%>" size=25 maxlength=50> <br><br>
                Fase: <input type=text name="fase" value="<%=aluno != null ? aluno.getFase() : ""%>" size=10 maxlength=15 onkeydown="return somenteNumero(event)"> <br><br>
                <input type="reset" value="Limpar">
                <input type="submit" name="Cadastrar" value="Cadastrar" onClick="return validar(this.form)"> <br>		
            </form>
            <br>
            <a href='index.jsp'>Voltar ao menu</a>        
    </body>
</html>	
