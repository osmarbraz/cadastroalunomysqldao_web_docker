# Define a imagem base utilizada para executar a aplicação Web Java.
# A imagem contém o Apache Tomcat 9.0 com JDK 8.
FROM tomcat:9.0-jdk8

# Copia o arquivo WAR da aplicação, previamente gerado pelo Maven,
# do computador para o diretório de aplicações Web do Tomcat
# dentro do container.
#
# Arquivo de origem:
# ./target/cadastroalunomysqldao_web_docker-0.0.1.war
#
# Arquivo de destino dentro do container:
# /usr/local/tomcat/webapps/ROOT.war
#
# O nome ROOT.war faz com que o Tomcat disponibilize a aplicação
# diretamente na raiz do servidor Web.
#
# Dessa forma, a aplicação poderá ser acessada utilizando:
# http://localhost:8080
COPY ./target/cadastroalunomysqldao_web_docker-0.0.1.war /usr/local/tomcat/webapps/ROOT.war