# language: pt
@BuscaTeste
Funcionalidade: Teste de busca de usuarioExemplo
	o sistema deve buscar de forma correta.
	Seguindo as seguintes restricoes:
	1-) quando for chamado o metodo findAll trazer todos os usuarios criados
	2-) quanto for chamado o metodo findById trazer somente o usuario com Id selecionado
	3-) caso nao aja usuarios cadastrados trazer uma lista vazia
	4-) caso nao exista usuario com id selecionada devolver uma mensagem de erro

	Cenario: Busca de todos usuarios sucesso
		Dado que os usuarios abaixo existem
		  | firstName   | 	lastName	| email              | password        | usr |
 		  | Aslak  		| 	Hellesoy	| aslak@cucumber.io  | @aslak_hellesoy | 1   |
  		  | Julien 		| 	Pros		| julien@cucumber.io | @jbpros         | 2	 |
  	  	  | Matt   		| 	Twynne		| matt@cucumber.io   | @mattwynne      | 3	 |
		Quando for selecionado a busca por todos os usuarios
		Então deve ser devolvido uma lista com todos os usuarios criados e o status "200"
		
	Cenario: Busca por id sucesso
		Dado que os usuarios abaixo existem na base
		  | firstName   | 	lastName	| email              | password        | usr |
 		  | Aslak  		| 	Hellesoy	| aslak@cucumber.io  | @aslak_hellesoy | 1   |
  		  | Julien 		| 	Pros		| julien@cucumber.io | @jbpros         | 2	 |
  	  	  | Matt   		| 	Twynne		| matt@cucumber.io   | @mattwynne      | 3	 |
		Quando for selecionado a busca pelo id do usuario "1"
		Então deve ser devolvido o usuario com os seguintes dados abaixo e o status "200"
		  | firstName   | 	lastName	| email              | password        |
 		  | Aslak  		| 	Hellesoy	| aslak@cucumber.io  | @aslak_hellesoy |
 		  
 	Cenario: Busca por id nao existente
		Dado que os usuarios abaixo existem na base de dados
		  | firstName   | 	lastName	| email              | password        | usr |
 		  | Aslak  		| 	Hellesoy	| aslak@cucumber.io  | @aslak_hellesoy | 1   |
  		  | Julien 		| 	Pros		| julien@cucumber.io | @jbpros         | 2	 |
  	  	  | Matt   		| 	Twynne		| matt@cucumber.io   | @mattwynne      | 3	 |
		Quando for selecionado a busca pelo id "444"
		Então deve ser devolvido uma excecao com a mensagem "User Not Found." e o status "400"
		
	Cenario: Busca de todos usuarios vazio
		Dado que nenhum usuario esteja cadastrado
		Quando for selecionado a busca por todos os usuarios cadastrados
		Então deve ser devolvido uma lista vazia e o status "200"
