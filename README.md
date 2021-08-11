# Crawler Java IMDB

Essa aplicação consiste em um pequeno crawler em forma de API Rest em Java para obtenção de informações do site IMDB.

---
## Objetivo

O objetivo desta API é acessar o site do IMDB através do link https://www.imdb.com/chart/bottom e trazer as seguintes informações:

- Lista com os 10 piores filmes, em ordem da melhor nota para a pior.
- Nome do Filme (Em inglês).
- Nota (Com uma casa decimal).
- Diretor(es).
- Elenco principal
- Pelo menos um comentário positivo sobre o filme.

---
## Tecnologias utilizadas

Para a criação dessa API, as seguintes frameworks foram utilizadas, por possuir mais familiaridade:

- Spring Boot: O Spring é uma das melhores ferramentas existentes no momento para a criação de API´s Rest. De fácil utilização com suas várias ferramentas para criação de códigos mais limpos através de annotation e muito intuitiva.
 - Jsoup: Apesar de não possuir muita familiaridade com essa ferramenta, foi uma das melhores escolhas. Com ele foi possível acessar os vários elementos do DOM HTML para trazer somente as informações necessáias para o uso.

---
## Executando a API

Para executar a API, pode-se abrir a mesma na IDE de preferência e utilizar a opção de RUN, ou através do Maven, com o comando ```mvn spring-boot:run```.

A API irá funcionar na porta 8080, através do endereço http://localhost:8080.

Ao utilizar o endereço com o endpoint ```/movies```, será possível visualizar uma lista em formato JSON, contendo todas as informações solicitadas.