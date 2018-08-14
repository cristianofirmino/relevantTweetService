# Revelant Tweet Service


## Recursos usados no Projeto
### Linguagem de Programação
Java com recursos da versão 8

### SpringBoot
O Spring Boot é um projeto da Spring que facilita o processo de configuração e publicação de aplicações. 
Ele favorece a convenção sobre a configuração.
Basta que seja informado quais módulos a que se utilizar deseja utilizar (WEB, Template, Persistência, Segurança, etc.) que ele reconhece e configura.

O maior benefício do Spring Boot é que ele permite o desenvolvedor focar mais nas regras de negócio da aplicação.

O start do projeto foi feito usando o SPRING INITIALIZR https://start.spring.io/.
O módulo usandos foram Web, Lombok e DevTools;

### Lombok
O Lombok é um Framework criado sob licença MIT, podendo ser usado livremente em qualquer projeto Java. 
Sua vantagem é evitar a repetição de código "clichê", como a criação de gets e sets para todos os atributos, 
métodos equals e hashCode, toString, Construtores entre outros. Dessa forma, o código fica mais limpo e claro.
Compatível somente com as IDEs Eclipse, IntelliJ IDEA e Netbeans.

### DevTools

DevTools é um módulo do Spring Boot que adiciona algumas ferramentas que são interessantes em tempo de desenvolvimento. 
Oferece configuração de propriedades úteis para desenvolvimento, reinicialização automática do servidor,
 e o LiveReload que envia um aviso para o navegador dizendo que os arquivos estáticos ou os de templates foram alterados.

### Docker Maven Plugin

É um plugin desenvolvido pela Spotify que por meio do Maven facilita a dockerização de uma aplicação Java / SpringBoot.


## Instruções para executar a aplicação

### Acesso

Por padrão a aplicação irá rodar na porta 80 (pode ser parametrizavel e uma próxima versão).
A url de acesso ao serviço será http://localhost/api.
Para consumir da api os tweets mais mencionados acesse:
http://localhost/api/most_mentions o sistema retornará um JSON com as informações, e o mesmo para os tweets mais relevantes, só que o endpoint será /most_relevants.

### Inicialização


#### Diretórios do projeto

  /relevantTweetService/src
  
  /relevantTweetService/target/
  
  /relevantTweetService/target/docker/
            
#### Windows e Linux

##### Start Script
Nevegue pelo terminal do sistema operacional ou explorer até o local onde se encontram os arquivos (/relevantTweetService/target).

No Linux execute o script: ./run.sh

No Windows execute o script: ./run.bat

A url de acesso da api será http://localhost/api/most_relevants e http://localhost/api/most_mentions

##### Start Manual
Para inicializar a aplicação no Windows ou Linux, é necessário ter o Java 8 Runtime instalado no sistema operacional. 
Está sendo disponibilizado o arquivo JAR em /target/relevantTweetService-1.0.0.jar. É necessário abrir o prompt de comandos  
do windows ou linux (bash) e navegar até onde o arquivo será salvo (sua escolha onde salvá-los).

Executar o comando: "java -jar -DHTTP_USERNAME=seu-email@seu-dominio.com relevantTweetService-1.0.0.jar"

Feito isso a aplicação será inicializada e todas as requisições enviará um header Username com o seu e-mail
com o valor para autorização na mock api http://localhost:9999/api/mock (minha mock api de testes).


#### Docker (Avançado)

##### Deploy Script
Nevegue pelo terminal do Docker até o local onde se encontra os arquivos (/relevantTweetService/target/docker).
Execute o script make.sh: ./make.sh

##### Deploy Manual
Estão sendo disponibilizados em /target/docker os arquivos Dockerfile e relevantTweetService-1.0.0.jar
para deploy no seu ambiente Docker.

Nevegue pelo terminal do Docker até o local onde se encontra os arquivos (sua escolha onde salvá-los).
 
Execute o comando: "docker image build . --tag relevant-tservice:1.0.0"

Feito isso o docker irá subir para seu servidor a imagem enviada.
Também irá fazer o download da imagem openjdk:8-jdk-alpine necessária para o container.

Se tudo ocorrer bem um log de sucesso como essa irá surgirá ao final:


Sending build context to Docker daemon   19.9MB
Step 1/3 : FROM openjdk:8-jdk-alpine
 ---> 5801f7d008e5
 Step 2/3 : ADD /relevantTweetService-1.0.0.jar //
  ---> 09f1aa31a4cd
  Step 3/3 : ENTRYPOINT ["java", "-DHTTP_USERNAME=cristianofirmino@gmail.com", "-jar", "/relevantTweetService-1.0.0.jar"]
   ---> Running in def452f0c698
   Removing intermediate container def452f0c698
    ---> 636a33867689
    Successfully built 636a33867689
    Successfully tagged relevant-tservice:1.0.0
    

Em seguida execute o comando: docker run -it -p 9999:80 relevant-tservice:1.0.0

Obs.: A porta 9999 no comando anterior é uma opção para expor a aplicação numa porta
diferente da porta 80 caso isso seja necessário e para melhor exemplificar nesse documento.

Em meu ambiente Docker a url de acesso da api ficou assim: http://192.168.99.100:9999/api/most_relevants e http://192.168.99.100:9999/api/most_mentions