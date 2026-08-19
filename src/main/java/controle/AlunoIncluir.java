package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Aluno;

public class AlunoIncluir extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("text/html");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html><html><head><title>Cadastro de Aluno - Incluir</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + request.getContextPath() + "/estilo.css\" /></head><body>");
                out.println("<h1>Cadastro de Aluno - Incluir</h1>");

                String nome = request.getParameter("nome");
                int idade = Integer.parseInt(request.getParameter("idade"));
                String curso = request.getParameter("curso");
                int fase = Integer.parseInt(request.getParameter("fase"));

                Aluno objetoaluno = new Aluno();

                if (objetoaluno.insertAlunoBD(nome, idade, curso, fase)) {
                    out.print("<span class='mensagemIncluir'>Inclus&atilde;o realizada com sucesso.</span><br>");
                } else {
                    out.print("<span class='mensagemIncluir'>Inclus&atilde;o n&atilde;o realizada.</span><br>");
                }

                out.print("<br><a href=\"" + request.getContextPath() + "/FrmCadastroAluno.jsp\">Cadastrar Aluno</a> - <a href=\"" + request.getContextPath() + "/index.jsp\"> Menu </a> <br>");

                out.println("</body></html>");
            }
        } catch (IOException e) {
            System.out.println("Problema E/S: " + e.toString());
        }
    }
}
