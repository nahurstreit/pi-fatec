/*========================================= !!! ATEN��O - LEIA AQUI !!! ===========================================
Este � um arquivo complementar que tem por objetivo fazer a EXCLUS�O COMPLETA DO BANCO DE DADOS QuestNutri e, portanto, 
n�o ter� utilidade alguma caso ainda n�o tenha sido executado o arquivo de cria��o das 
tabelas, Script_QuestNutriDB--CreateDB.sql;

EXECUTE O SCRIPT: Script_QuestNutriDB--CreateDB.sql ANTES DE TENTAR EXECUTAR ESTE SCRIPT.

O RESULTADO DA EXECU��O DESSES COMANDOS � IRREVERS�VEL, N�O SER� POSS�VEL RECUPERAR OS DADOS.
EXECUTE APENAS EM TESTES E QUANDO O MOTIVO FOR VI�VEL. A inten��o � facilitar o processo de exclus�o para TESTES DE 
IMPLEMENTA��O DO SISTEMA e N�O DEVER� SER UTILIZADO EM AMBIENTES DE PRODU��O.

As linhas a seguir devem ser executadas em uma ordem espec�fica para ter o comportamento esperado.
Voc� dever� executar em ordem os seguintes comandos abaixo:
	- COMANDO [1]: feito para DELETAR as TABELAS associadas ao Banco de Dados em uma ordem livre de erros;
	- COMANDO [2] (OPCIONAL): feito para DELETAR o usu�rio adminQuestNutri, criado ao executar o arquivo de inser��o de exemplos
	- COMANDO [3]: feito para DELETAR o BANCO DE DADOS.
Para isso ser� indicado o 'IN�CIO DO COMANDO [X]' e o 'FIM DO COMANDO [X]'. Selecione TODAS AS LINHAS entre
esses dois indicadores e execute. Executar estes comandos fora da ordem resultar� em ERRO.
Executar todo o arquivo N�O FUNCIONAR�! Execute os dois comandos indicados.

====================================================================================================================*/

--COMANDO [1]
--IN�CIO DO COMANDO [1]
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

--COMANDO 2 (Opcional): Execute apenas se voc� tiver criado o usu�rio adminQuestNutri.
--INICIO DO COMANDO [2]
USE QuestNutriDB;
-- Remover permiss�es do usu�rio
REVOKE SELECT, INSERT, UPDATE, DELETE ON Addresses FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Customers FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Aliments FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Weights FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Meals FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON Foods FROM adminQuestNutri;
REVOKE SELECT, INSERT, UPDATE, DELETE ON SubFoods FROM adminQuestNutri;
-- Remover usu�rio do banco de dados
DROP USER adminQuestNutri;
-- Remover login
DROP LOGIN adminQuestNutri;
--FIM DO COMANDO [2]


--COMANDO [3]
--IN�CIO DO COMANDO [3]
USE master;
DROP DATABASE QuestNutriDB;
--FIM DO COMANDO [3]