package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Aluno;

public class AlunoExcluir extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html><html><head><title>Cadastro de Aluno - Excluir</title>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + request.getContextPath() + "/estilo.css\" /></head><body>");
                out.println("<h1>Cadastro de Aluno - Excluir</h1>");

                Aluno objetoaluno = new Aluno();
                int id = Integer.parseInt(request.getParameter("id"));

                if (objetoaluno.deleteAlunoBD(id)) {
                    out.print("<span class='mensagemExcluir'>Exclus&atilde;o realizada com sucesso.</span><br>");
                } else {
                    out.print("<span class='mensagemExcluir'>Exclus&atilde;o n&atilde;o realizada.</span><br>");
                }
                out.print("<br><a href=\"" + request.getContextPath() + "/FrmGerenciarAluno.jsp\">Gerenciar Aluno</a> - <a href=\"" + request.getContextPath() + "/index.jsp\"> Menu </a><br>");

                out.println("</body></html>");
            }
        } catch (IOException e) {
            System.out.println("Problema E/S :" + e.toString());
        }
    }
}
