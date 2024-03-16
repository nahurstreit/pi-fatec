# Sobre o repositório
Este repositório compartilhado tem como principal finalidade armazenar o histórico de realizações relacionadas à programação para o Projeto Integrador do curso de Análise e Desenvolvimento de Sistemas (ADS) da Faculdade de Tecnologia (FATEC) da unidade de Indaiatuba.

## Tópicos neste README
- [O que é o Projeto Integrador?](#projeto-integrador)
- [Nosso Projeto](#nosso-projeto)
- Proposta do Semestre (TBD)
- O que foi feito no Semestre (TBD)
- [Integrantes](#integrantes-desse-semestre)

# Projeto Integrador
Nos cursos oferecidos pela FATEC, a cada semestre os alunos devem desenvolver um projeto conhecido como "Projeto Integrador", abreviado como "PI". O objetivo deste projeto é integrar os conhecimentos adquiridos nas disciplinas do semestre em um projeto único.

Os alunos selecionam um projeto que será desenvolvido ao longo do curso inteiro, realizando melhorias conforme adquirem novos conhecimentos.

A cada semestre, um conjunto selecionado de disciplinas compõe as "matérias do PI", que estabelecem requisitos mínimos de cada uma dessas disciplina a serem incorporados e estudados para o desenvolvimento do projeto daquele semestre. Essa seleção de disciplinas muda a cada novo semestre, refletindo as matérias lecionadas no período, mas o projeto deverá permanecer o mesmo.

A ideia central é capacitar os alunos a aprender todos os aspectos do desenvolvimento de um projeto real na área de seu curso.

# Nosso Projeto
Especificamente em nosso caso, no curso de ADS, os alunos devem criar um solução em software.

O objetivo escolhido pelo nosso grupo foi o desenvolvimento de uma aplicação de acompanhamento nutricional com gamificação chamado de "QuestNutri".

<div align="center">
    <img src="/img/QuestNutri.png" width="400" height="400">
</div>

A função do software será simplificar a prescrição de dietas pelos profissionais nutricionistas, enquanto promove a fidelização dos pacientes por meio de uma plataforma interativa de acompanhamento nutricional com um sistema de gamificação.

- Para os Nutricionistas, o sistema ajudará na realização de cálculos nutricionais padrões, controle de exames e evolução de peso dos clientes, além de um sistema escalável e altamente personalizável de dietas individualizadas.

- Para os Clientes, o sistema oferecerá suporte no monitoramento individualizado das dietas e implementará um sistema gamificado para fomentar o envolvimento com o plano alimentar, concedendo recompensas por marcos na plataforma e aumentando a percepção de valor em seguir com a dieta.

# Integrantes desse Semestre
- [@nahurstreit](https://github.com/nahurstreit)
Ficou responsável por projetar a estrutura e o funcionamento geral da API, bem como pela modelagem do banco de dados, além da correção e refatoração do projeto.

- [@BrunoSoares21](https://github.com/BrunoSoares21)
Ficou responsável pela criação da classe Meal, implementação do CRUD na classe Customer através do customerModel, criação dos arquivos de schema utilizados pelos outros integrantes nos arquivos de path do Swagger, também dividiu as funções e instruiu os outros integrantes sobre como fazer os objetos de configuração dos métodos.

- [@Migu3l-Prado](https://github.com/Migu3l-Prado)
Ficou responsável pela criação de algumas classes menores, auxílio na execução de testes unitários da API, criação de relatórios de erros e soluções, e pela criação dos objetos de configuração dos métodos para as rotas de Customers do Swagger.

- [@SammyOAS](https://github.com/SammyOAS)
Ficou responsável pela criação das classes Foods e Aliments, criação dos objetos de configuração de métodos para as rotas de Foods e SubFoods do Swagger, implementação e definição das rotas da API, criação do arquivo de inicialização do servidor e ajustes menores de conflito de biblioteca.

- [@mathvalim](https://github.com/mathvalim)
Ficou responsável pela criação da classe SubFood, criação dos objetos de configuração dos métodos para as rotas de Meals do Swagger, e criou os schemas de validação para serem utilizados nos métodos POST e UPDATE nas chamadas da API.