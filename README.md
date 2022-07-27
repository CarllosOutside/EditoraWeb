# Editora
Api com Thymeleaf e Spring Boot.

A Api utiliza a ferramenta de autenticação Auth0. 

----

### HTTP MÉTODOS (<a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/java/dw/editora/control/ArtigoController.java" >ArtigoController</a>)

| Método        | Descrição                    |
| ------------- | ------------------------------ |
| `@ModelAttributes listaPecas()`      | Cria uma lista tipo <a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/java/dw/editora/model/Artigo.java">Artigo</a> para impressão. Cria um objeto <a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/java/dw/editora/ListaArtigos.java">ListaArtigo</a> de nome listaDelecao com atributo List<Long> para armazenar id's de Artigos a serem deletados. Cria um objeto vazio Artigo de nome novoArtigo para ser preenchido e inserido no banco.       |
| `@GetMapping`<br> `getAllArtigos()`<br> `createNovo()`<br> `updateArtigo()`    | Três métodos Get para acesso às três <a href="https://github.com/CarllosOutside/EditoraWeb/tree/main/dw-main_editora/src/main/resources/templates">views</a> de listagem, update e criação de artigos. O método @GetUpdate Artigo envia uma queryparam com o id do artigo a ser modificado, e cria um objeto Artigo de nome ArtigoUpdate que recebe os dados do artigo selecionado  |
|`@Post createArtigo()`| Método chamado pela view <a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/resources/templates/newArtigoForm.html">NewArtigoForm.html</a> que permite preencher um objeto vazio chamado novoArtigo, e salvar no banco de dados|
|`@Put updateArtigo()`| Método chamado pela view <a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/resources/templates/updateArtigoForm.html">updateArtigoForm</a> que permite alterar os dados de um objeto Artigo de nome ArtigoUpdate para salvar no banco e sobrescrever o original |
|`@Delete deleteAllArtigos()`| Este método é chamado por <a href="https://github.com/CarllosOutside/EditoraWeb/blob/main/dw-main_editora/src/main/resources/templates/artigosForm.html">ArtigosForm</a> que preenche uma lista de Artigos selecionados pelo usuário e envia esta lista para o Controller efetuar a deleção|
