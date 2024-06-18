/*========================================= !!! ATENÇÃO - LEIA AQUI !!! ===========================================
Este é um arquivo complementar que tem por objetivo fazer a EXCLUSÃO COMPLETA DO BANCO DE DADOS QuestNutri e, portanto, 
não terá utilidade alguma caso ainda não tenha sido executado o arquivo de criação das 
tabelas, Script_QuestNutriDB--CreateDB.sql;

EXECUTE O SCRIPT: Script_QuestNutriDB--CreateDB.sql ANTES DE TENTAR EXECUTAR ESTE SCRIPT.

O RESULTADO DA EXECUÇÃO DESSES COMANDOS É IRREVERSÍVEL, NÃO SERÁ POSSÍVEL RECUPERAR OS DADOS.
EXECUTE APENAS EM TESTES E QUANDO O MOTIVO FOR VIÁVEL. A intenção é facilitar o processo de exclusão para TESTES DE 
IMPLEMENTAÇÃO DO SISTEMA e NÃO DEVERÁ SER UTILIZADO EM AMBIENTES DE PRODUÇÃO.

As linhas a seguir devem ser executadas em uma ordem específica para ter o comportamento esperado.
Você deverá executar em ordem os seguintes comandos abaixo:
	- COMANDO [1]: feito para DELETAR as TABELAS associadas ao Banco de Dados em uma ordem livre de erros;
	- COMANDO [2] (OPCIONAL): feito para DELETAR o usuário adminQuestNutri, criado ao executar o arquivo de inserção de exemplos
	- COMANDO [3]: feito para DELETAR o BANCO DE DADOS.
Para isso será indicado o 'INÍCIO DO COMANDO [X]' e o 'FIM DO COMANDO [X]'. Selecione TODAS AS LINHAS entre
esses dois indicadores e execute. Executar estes comandos fora da ordem resultará em ERRO.
Executar todo o arquivo NÃO FUNCIONARÁ! Execute os dois comandos indicados.

====================================================================================================================*/

--COMANDO [1]
--INÍCIO DO COMANDO [1]
USE QuestNutriDB;
DROP TABLE dbo.SubFoods;
DROP TABLE dbo.Foods;
DROP TABLE dbo.Meals;
DROP TABLE dbo.Aliments;
DROP TABLE dbo.Weights;
DROP TABLE dbo.Customers;
DROP TABLE dbo.Addresses;
DROP TABLE dbo.Users;
--FIM DO COMANDO [1]

--COMANDO 2 (Opcional): Execute apenas se você tiver criado o usuário adminQuestNutri.
--INICIO DO COMANDO [2]
USE QuestNutriDB;
-- Remover permissões do usuário
REVOKE SELECT, INSERT, UPDATE, DELETE ON Addresses FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Customers FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Aliments FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Weights FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Meals FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Foods FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON SubFoods FROM adminQuestNutri;
-- Remover usuário do banco de dados
DROP USER adminQuestNutri;
-- Remover login
DROP LOGIN adminQuestNutri;
--FIM DO COMANDO [2]


--COMANDO [3]
--INÍCIO DO COMANDO [3]
USE master;
DROP DATABASE QuestNutriDB;
--FIM DO COMANDO [3]