$app.config([‘$translateProvider’, function ($translateProvider) {

  $translateProvider.translations(‘en’, {
  	//Signup
    /*'cadastro.title': "Sign Up!",
    'cadastro.nome': "Name",
    'cadastro.email': "Email",
    'cadastro.email.check': "You must enter a valid email address. Example caroname@caroname.com",
    'cadastro.senha': "Password",
    'cadastro.senha.check': "At least 8 characters",
    'cadastro.matricula': "Number of enrollment",
    'cadastro.matricula.check': "Type the 9 numbers of enrollment in UFCG",
    'cadastro.rua': "Address",
    'cadastro.telefone': "Phone number",
    'cadastro.telefone.ddd': "Phone number without DDD",
    'cadastro.telefone.check': "Type your phone number",    

    'main.email': "Email",
    'main.email.type': "Type your email",
    'main.senha': "Password",
    'main.senha.type': "Type your password",
    'main.cadastro': "Sign up",

    ‘home.mudarFoto’: ‘Change your profile picture’,

    ‘busca.paginaAnterior’: ‘Previous page’,
    ‘busca.paginaPosterior’: ‘Next page’,
    ‘busca.diaDaSemana’: ‘Day of the week’,
    ‘busca.aula’: ‘Class’,
    ‘busca.tipoDeCarona’: ‘Kind of ride’,
    ‘busca.tipoDeCarona.ida’: ‘One-way trip’,
    ‘busca.tipoDeCarona.volta’: ‘Back trip’,
    ‘busca.endereco’: ‘Address’,
    ‘busca.buscar’: ‘Search’,

    ‘carona.diaDaSemana’: ‘Day of the week’,
    ‘carona.aula’: ‘Class’,
    ‘carona.vagas’: ‘Ride spaces’,
    ‘carona.tipoDeCarona’: ‘Kind of ride’,
    ‘carona.tipoDeCarona.ida’: ‘One-way trip’,
    ‘carona.tipoDeCarona.volta’: ‘Back trip’,
    ‘carona.endereco’: ‘Address’,
    ‘carona.rotas’: 'Routes',
    ‘carona.salvar’: ‘Save’,
    ‘carona.cancelar’: ‘Cancel’,

    'horario.bemvindo': "Welcome",
    'horario.saudacao': "Here you will find informations and can ask a ride or create a ride",
    'horario.oferecer': "Offer a ride",
    'horario.buscar': "Search a ride",
    'horario.motorista': "Driver",
    'horario.passageiro': "Passenger",    


    ‘label.title’: ‘Vote in Movie?’,   */
  });

  $translateProvider.translations(‘pt’, {
  	//Cadastro
    /*‘cadastro.title’: ‘Cadastro!’,
    'cadastro.nome': "Nome",
    'cadastro.email': "Email",
    'cadastro.email.check': "Preencha com um email válido. Exemplo caroname@caroname.com",
    'cadastro.senha': "Senha",
    'cadastro.senha.check': "Pelo menos 8 caracteres",
    'cadastro.matricula': "Nº Matricula",
    'cadastro.matricula.check': "Digite os 9 números da sua matrícula da UFCG",
    'cadastro.rua': "Rua",
    'cadastro.telefone': "Telefone",
	'cadastro.telefone.ddd': "Telefone sem DDD",
	'cadastro.telefone.check': "Digite os números do seu telefone",

    'main.email': "Email",
    'main.email.type': "Digite seu email",
    'main.senha': "Senha",
    'main.senha.type': "Digite a senha",
    'main.cadastro': "Cadastrar",

    ‘home.mudarFoto’: ‘Mudar foto de perfil’,

    ‘busca.paginaAnterior’: ‘Página anterior’,
    ‘busca.paginaPosterior’: ‘Próxima página’,
    ‘busca.diaDaSemana’: ‘Dia da semana’,
    ‘busca.aula’: ‘Aula’,
    ‘busca.tipoDeCarona’: ‘Tipo de Carona’,
    ‘busca.tipoDeCarona.ida’: ‘IDA’,
    ‘busca.tipoDeCarona.volta’: ‘VOLTA’,
    ‘busca.endereco’: ‘Endereço’,
    ‘busca.buscar’: ‘Buscar’,

    ‘carona.diaDaSemana’: ‘Dia da Semana’,
    ‘carona.aula’: ‘Aula’,
    ‘carona.vagas’: ‘Vagas’,
    ‘carona.tipoDeCarona’: ‘Tipo de carona’,
    ‘carona.tipoDeCarona.ida’: ‘IDA’,
    ‘carona.tipoDeCarona.volta’: ‘VOLTA’,
    ‘carona.endereco’: ‘Endereço’,
    ‘carona.rotas’: ‘Rotas’,
    ‘carona.salvar’: ‘Salvar’,
    ‘carona.cancelar’: ‘Cancelar’,


    'horario.bemvindo': "Bem vindo",
    'horario.saudacao': "Aqui você encontra informações, bem como pode pedir ou criar uma carona",
    'horario.oferecer': "Oferecer Carona",
    'horario.buscar': "Buscar carona",
    'horario.motorista': "Motorista",
    'horario.passageiro': "Passageiro",
    


    ‘label.title’: ‘Vote no Filme’   */
  });

  $translateProvider.preferredLanguage(‘pt’); //linguagem padrao

}]);



//só pra lembrar.
/*<div class="almostJumbotron"
 ng-controller="Ctrl">
      <button ng-click="changeLanguage('pt')" translate="BUTTON_TEXT_PT"></button>
      <button ng-click="changeLanguage('en')" translate="BUTTON_TEXT_EN"></button>
    </div>*/

