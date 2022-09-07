# language: pt
@CadastroTeste
Funcionalidade: Teste de cadastro de usuarioExemplo
	o sistema deve cadastrar de forma correta.
	Seguindo as seguintes restricoes:
	1-) o email deve ser valido seguindo o regex ".@.\\..*"
	2-) o usuario nao pode ter um email que ja foi cadastrado (duplicado)

	Cenario: Criacao de um usuario sucesso
		Dado que o usuario "luis" "brienze" de email "luis_sabadoto@hotmail.com" com a senha "12345" 
		Quando ele tentar criar um usuario pelo endpoint create e for retornado um id de resposta
		Então deve ter um usuario com o id criado na base com "luis_sabadoto@hotmail.com" "luis" e "brienze"

	Cenario: Criacao de um usuario erro usuairo duplicado
		Dado que o usuario "luis" "brienze" de email "luis_sabadoto@hotmail.com" com a senha "12345" ja tenha sido cadastrado
		Quando ele tentar criar um usuario novamente
		Então deve ser retornado uma mensagem de erro "User luis_sabadoto@hotmail.com already exists"
	
