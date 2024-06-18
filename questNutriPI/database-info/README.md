Para um tutorial mais detalhado veja o arquivo "Tutorial-de-instalação-e-configuração-do-Banco-de-Dados-no-SQLServer.pdf".
Caso não tenha dificuldades em configurar o SQL Server, execute a query dos arquivos presentes na pasta de Scripts exatamente nesta ordem:
(1) Script_QuestNutriDB--CreateDB.sql -> Cria as tabelas
(2) Script_QuestNutriDB--InsertStdAliments.sql -> Adiciona os alimentos
(3) Script_QuestNutriDB--InsertExamples.sql -> Adiciona exemplos gerais, é preciso ter os alimentos em sistema para que funcione, portanto execute o arquivo em (2)!

E quando quiser excluir o banco de dados:
(O) Script_CAUTION_QuestNutri--DeleteDB