# Projeto para disciplina de Sistemas de Informação I (2015.2) - Carona-me

O projeto em tela visa oferecer a possibilidade de um sistema de caronas solidárias para a UFCG. A nossa equipe é composta por : Jobson Lucas, Rafael Albuquerque, João Victor Mafra e Gileade Kelvin. 

Link para o 
  <a href = "https://docs.google.com/document/d/1ssz063ME3spiFODu3CDAIzaU5S8Ody05DdcUnM95z2Y/edit?usp=sharing">Drive</a>

Tutorial de como proceder para fazer a aplicação funcionar:
- Clonar o repositório;
- Mudar para o diretório cliente(cd cliente);
- Digite no terminal sudo npm install(caso seja linux) ou npm install(caso seja windows);
- Ainda em cliente, digite no terminal bower install;
- Mude para pasta server;
- Estando lá, digite activator¹ “run 9090”(no linux basta fazer ./activator "run 9090");
- Abra outra janela do terminal (não feche a que já está aberta) e vá para o diretório cliente. Uma vez lá, digite grunt serve.
- Em um navegador acesse: http://localhost:9000

¹ Para o comando activator funcionar no windows é preciso adicionar ele ao path nas variáveis de ambiente.
