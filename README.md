# README #
Repositório com exemplo pratico sobre como corrigir o problema de CORS Origin em API REST.

### API REST com Spring e Spring Security ###
* API REST com Spring;
* Segurança com Spring Security em REST API;
* Cors Origin Filter;
* Spring Data JPA;
* MySQL;
* Tomcat;

Sistema todo desaclopado, sendo apenas uma API REST onde a View pode ser em outra linguagem como por exemplo (PHP, HTML, etc), totalmente indepemdente do back-end;

### View em simples HTML com jQuery ###
* HTML;
* jQuery;

### Exemplo de consumir essa API utilizando jQuery AJAX ###

Abaixo deixo o código do html utilizado para testes dessa API.

<code>
	<html>
	<head>
		<title>Teste</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script type="text/javascript">
			var settings = {
			  "async": true,
			  "crossDomain": true,
			  "url": "http://localhost:8080/spring-rest-api/api/v1/regra-acesso",
			  "method": "POST",
			  "headers": {
			    "content-type": "application/json",
			    "authorization": "Basic YWRtaW46c2FsbW9zODk=",
			    "cache-control": "no-cache"
			  },
			  "xhrFields": {
			       "withCredentials": true
			    },
			  "processData": false,
			  "data": "{\n\t\"nome\": \"CLIENTE4\"\n}"
			}

			$.ajax(settings).done(function (response) {
			  console.log(response);
			});
		</script>
	</head>
	<body>

	</body>
</html>

</code>
