# Projeto para disciplina de Sistemas de Informação I (2015.2) - Carona-me

O projeto em tela visa oferecer a possibilidade de um sistema de caronas solidárias para a UFCG. A nossa equipe é composta por : Jobson Lucas, Rafael Albuquerque, João Victor Mafra e Gileade Kelvin. 

Link para o 
  <a href = "https://docs.google.com/document/d/1ssz063ME3spiFODu3CDAIzaU5S8Ody05DdcUnM95z2Y/edit?usp=sharing">Drive com as decisões de projeto.</a>

Tutorial de como proceder para fazer a aplicação funcionar:
- Clonar o repositório;
- É necessário ter o PostgreSQL 9.4 instalado localmente e criar um banco de dados nele. As informações do banco de dados constam em server/conf/application.conf [linhas 42-45]. Neste, consta o nome do database criado, bem como o usuário e senha locais devem estar corretamente preenchidos.
- Mudar para o diretório cliente(cd cliente);
- Digite no terminal sudo npm install(caso seja linux) ou npm install(caso seja windows);
- Ainda em cliente, digite no terminal bower install;
- Abra outra janela do terminal e nesta vá para a pasta server;
- Estando lá, digite activator¹ “run 9090”(no linux basta fazer ./activator "run 9090"). Além disso, é necessário abrir o navegador em localhost:9090 após ele iniciar o server e clicar em apply this script now;
- Após o passo anterior, vá na janela do terminal que está na pasta cliente e digite grunt serve.
- Em um navegador acesse: http://localhost:9000

¹ Para o comando activator funcionar no windows é preciso adicionar ele ao path nas variáveis de ambiente.
