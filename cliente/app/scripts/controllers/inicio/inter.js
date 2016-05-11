$app.config([‘$translateProvider’, function ($translateProvider) {

  $translateProvider.translations(‘en’, {
  	//Signup
    ‘cadastro.title’: ‘Sign Up!’,
    ‘cadastro.nome’: ‘Name’,
    ‘cadastro.email’: ‘Email’,
    ‘cadastro.email.check’: ‘You must enter a valid email address. Example caroname@caroname.com’,
    ‘cadastro.senha’: ‘Password’,
    ‘cadastro.senha.check’: ‘At least 8 characters’,
    ‘cadastro.matricula’: ‘Number of enrollment’,
    ‘cadastro.matricula.check’: ‘Type the 9 numbers of enrollment in UFCG’,
    ‘cadastro.rua’: ‘Address’,
    ‘cadastro.telefone’: ‘Phone number’,
    ‘cadastro.telefone.ddd’: ‘Phone number without DDD’,
    ‘cadastro.telefone.check’: ‘Type your phone number’,    

    ‘main.email’: ‘Email’,
    ‘main.email.type’: ‘Type your email’,
    ‘main.senha’: ‘Password’,
    ‘main.senha.type’: ‘Type your password’,
    ‘main.cadastro’: ‘Sign up’,


    ‘label.title’: ‘Vote in Movie?’,   
  });

  $translateProvider.translations(‘pt’, {
  	//Cadastro
    ‘cadastro.title’: ‘Cadastro!’,
    ‘cadastro.nome’: ‘Nome’,
    ‘cadastro.email’: ‘Email’,
    ‘cadastro.email.check’: ‘Preencha com um email válido. Exemplo caroname@caroname.com’,
    ‘cadastro.senha’: ‘Senha’,
    ‘cadastro.senha.check’: ‘Pelo menos 8 caracteres’,
    ‘cadastro.matricula’: ‘Nº Matricula’,
    ‘cadastro.matricula.check’: ‘Digite os 9 números da sua matrícula da UFCG’,
    ‘cadastro.rua’: ‘Rua’,
    ‘cadastro.telefone’: ‘Telefone’,
	‘cadastro.telefone.ddd’: ‘Telefone sem DDD’,
	‘cadastro.telefone.check’: ‘Digite os números do seu telefone’,


    ‘main.email’: ‘Email’,
    ‘main.email.type’: ‘Digite seu email’,
    ‘main.senha’: ‘Senha’,
    ‘main.senha.type’: ‘Digite a senha’,
    ‘main.cadastro’: ‘Cadastrar’,


    ‘label.title’: ‘Vote no Filme’   
  });

  $translateProvider.preferredLanguage(‘pt’); //linguagem padrao

}]);