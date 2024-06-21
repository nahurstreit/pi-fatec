/*================================================== LEIA AQUI ====================================================
As linhas a seguir devem ser executadas em uma ordem espec�fica para ter o comportamento esperado.
Voc� dever� executar em ordem os seguintes comandos abaixo:
	- COMANDO [1]: feito para criar efetivamente o Banco de Dados;
	- COMANDO [2]: feito para criar as Tabelas do sistema;
Para isso ser� indicado o 'IN�CIO DO COMANDO [X]' e o 'FIM DO COMANDO [X]'. Selecione TODAS AS LINHAS entre
esses dois indicadores e execute. Executar estes comandos fora da ordem resultar� em ERRO.
Executar todo o arquivo N�O FUNCIONAR�! Execute os dois comandos indicados.

Ao executar nessa ordem voc� ter� criado o Banco de Dados f�sico do QuestNutri.
Tabelas presentes no sistema:
	-> Addresses: armazena os endere�os dos clientes;
	-> Customers: armazena os clientes do profissional Nutricionista;
	-> Aliments: armazena as informa��es nutricionais dos alimentos como um todo;
	-> Weights: armazena os registros de peso do cliente;
	-> MedicalExams: armazena exames solicitados pelo Nutricionista para fornecer uma melhor prescri��o da Dieta;
	-> Meals: armazena as refei��es dos clientes;
	-> Foods: surge do relacionamento (n para n) entre as tabelas 'Meals' e 'Aliments', armazena as informa��es
			  gerais de um alimento em uma dada refei��o, como exemplo, quantidade e unidade de medida;
	-> SubFoods: atua como uma tabela que registra diferentes op��es de substitui��o para um �nico alimento em uma
			  dada refei��o;
	-> Users: usu�rios dispon�veis para login no sistema

============================================ INFORMA��ES ADICIONAIS =============================================
Depois de criado o Banco de Dados e suas Tabelas siga as seguintes orienta��es para adicionar os registros.
	- (1) Inserir os alimentos padr�es: 
		Use o arquivo: Script_QuestNutriDB--InsertStdAliments.sql
	- (2) Inserir registros aleat�rios para todas as tabelas: 
		Use o arquivo: Script_QuestNutriDB--InsertExamples.sql

Para *EXCLUIR* completamente o Banco de Dados e suas tabelas:
	<!> Use o arquivo: Script_CAUTION_QuestNutri--DeleteDB.sql <!>
Lembre-se: essa a��o � IRREVERS�VEL, tome cuidado!

====================================================================================================================*/

--COMANDO [1]
--IN�CIO DO COMANDO [1]
USE master;
CREATE DATABASE QuestNutriDB;
--FIM DO COMANDO [1]


--COMANDO [2]
--IN�CIO DO COMANDO [2]
USE QuestNutriDB;
CREATE TABLE Addresses(
    idAddress INT PRIMARY KEY IDENTITY,
    addr_street VARCHAR(50) NOT NULL,
	addr_num INT NOT NULL,
	addr_comp VARCHAR(50),
	addr_cep VARCHAR(10) NOT NULL,
    addr_neighborhood VARCHAR(50) NOT NULL,
    addr_city VARCHAR(30) NOT NULL,
    addr_state VARCHAR(2) NOT NULL
);

CREATE TABLE Customers(
    idCustomer INT PRIMARY KEY IDENTITY,
	cust_createdAt DATETIME DEFAULT GETDATE(),
	cust_deletedAt DATETIME DEFAULT NULL,
    cust_name VARCHAR(50) NOT NULL,
    cust_email VARCHAR(30) UNIQUE,
    cpf VARCHAR(14) UNIQUE NOT NULL,
	cust_phoneNumber VARCHAR(20) NOT NULL,
	cust_activityStatus INT CHECK(cust_activityStatus IN (1,2,3,4, 5)),
	cust_setKcal FLOAT,
    cust_height FLOAT,
    cust_birth DATE,
    cust_gender VARCHAR(2) CHECK (cust_gender = 'M' OR cust_gender = 'F'),
	idAddress INT FOREIGN KEY REFERENCES Addresses(idAddress),
);

CREATE TABLE Aliments(
	idAliment INTEGER PRIMARY KEY IDENTITY, 
	ali_custom INTEGER CHECK(ali_custom = 1 or ali_custom = 0),
	ali_name VARCHAR(100),
	alimentGroup VARCHAR(50),
	ali_kcal VARCHAR(30),
	ali_kJ VARCHAR(30),
	ali_carb VARCHAR(30), 
	ali_protein VARCHAR(30),
	ali_fat VARCHAR(30),
	humidity VARCHAR(30),
	dietaryFiber VARCHAR(30),
	cholesterol VARCHAR(30),
	sodium VARCHAR(30),
	calcium VARCHAR(30),
	magnesium VARCHAR(30),
	manganese VARCHAR(30),
	phosphorus VARCHAR(30),
	iron VARCHAR(30),
	potassium VARCHAR(30),
	copper VARCHAR(30),
	zinc VARCHAR(30),
	retinol VARCHAR(30),
	RE VARCHAR(30),
	RAE VARCHAR(30),
	thiamine VARCHAR(30),
	riboflavin VARCHAR(30), 
	pyridoxine VARCHAR(30),
	niacin VARCHAR(30),
	vitaminC VARCHAR(30),
	ash VARCHAR(30)
);

CREATE TABLE Weights(
    idWeight INT PRIMARY KEY IDENTITY,
    idCustomer INT NOT NULL FOREIGN KEY REFERENCES Customers(idCustomer) ON DELETE CASCADE,
    wgt_value FLOAT NOT NULL,
    wgt_dateRegister DATETIME DEFAULT GETDATE(),
);

CREATE TABLE Meals(
    idMeal INT PRIMARY KEY IDENTITY,
    idCustomer INT NOT NULL FOREIGN KEY REFERENCES Customers(idCustomer) ON DELETE CASCADE,
    meal_name VARCHAR(30) NOT NULL,
	meal_daysOfWeek INT CHECK(meal_daysOfWeek > 0 AND meal_daysOfWeek <= 127) NOT NULL,
    meal_hour TIME NOT NULL,
	meal_createdAt DATETIME DEFAULT GETDATE(),
	meal_deactivatedAt DATETIME DEFAULT NULL
);

CREATE TABLE Foods(
    idFood INT PRIMARY KEY IDENTITY,
    idMeal INT NOT NULL FOREIGN KEY REFERENCES Meals(idMeal) ON DELETE CASCADE,
    idAliment INT NOT NULL FOREIGN KEY REFERENCES Aliments(idAliment) ON DELETE CASCADE,
    food_quantity FLOAT NOT NULL,
    food_unityQt VARCHAR(20) NOT NULL,
	food_createdAt DATETIME DEFAULT GETDATE(),
	food_deactivatedAt DATETIME DEFAULT NULL
);

CREATE TABLE SubFoods(
    idSubFood INT PRIMARY KEY IDENTITY,
    idFood INT NOT NULL FOREIGN KEY REFERENCES Foods(idFood) ON DELETE CASCADE,
    idAliment INT NOT NULL FOREIGN KEY REFERENCES Aliments(idAliment),
    subFood_quantity FLOAT NOT NULL,
    subFood_unityQt VARCHAR(20) NOT NULL,
	subFood_createdAt DATETIME DEFAULT GETDATE(),
	subFood_deactivatedAt DATETIME DEFAULT NULL
);

CREATE TABLE Users (
    idUser INT PRIMARY KEY IDENTITY,
	user_login VARCHAR(30) NOT NULL UNIQUE,
	user_password VARCHAR(30) NOT NULL,
	user_personalName VARCHAR(15) NOT NULL,
	user_prefLanguage INT DEFAULT 0,
	systemLevel INT CHECK (systemLevel BETWEEN 1 AND 3) DEFAULT 1
);

--FIM DO COMANDO [2]