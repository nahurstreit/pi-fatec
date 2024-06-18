/*================================================== LEIA AQUI ====================================================
Este é um arquivo complementar que tem por objetivo adicionar registros aleatórios às tabelas do banco de dados
QuestNutri e, portanto, não terá utilidade alguma caso ainda não tenha sido executado o arquivo de criação das
tabelas, Script_QuestNutriDB--CreateDB.sql e também o arquivo que adiciona as informações dos alimentos padrões do
sistema, Script_QuestNutriDB--InsertStdAliments.sql;

EXECUTE OS SCRIPTS: Script_QuestNutriDB--CreateDB.sql e Script_QuestNutriDB--InsertStdAliments.sql ANTES DE TENTAR
EXECUTAR ESTE SCRIPT.

As consultas deste arquivo podem ser realizadas simultaneamente, portanto, é possível executar o arquivo inteiro,
sem a necessidade de selecionar quaisquer linhas.

Vale destacar que no corpo deste arquivo, existem linhas comentadas que tentam referenciar o contexto do que
está sendo adicionado.

Ao final da execução, serão adicionadas às tabelas registros para:
Addresses: 7 registros de endereços das cidades de Indaiatuba, Campinas e Salto;
Customers: 5 clientes aleatórios, com informações fictícias;
Weights: No mínimo 1 registro de peso para cada um dos clientes;
MedicalExams: Alguns registros de solicitação de exame para alguns dos clientes - Nem todos os clientes precisam ter
exames médicos;
Meals: No mínimo 5 registros de refeições para CADA um dos clientes, e nessas samples todas estão ativas;
Foods: No mínimo 3 registros de alimentos em CADA um dos registros de Meals
SubFoods: 10 registros aleatórios de Alimentos de substituição.
Admins: 1 registro (admin, admin) para logar na aplicação.
====================================================================================================================*/

USE QuestNutriDB;

-- Criãção do login de usuário: 'adminQuestNutri'
CREATE LOGIN adminQuestNutri WITH PASSWORD = '123';
-- Associar usuário ao banco de dados
CREATE USER adminQuestNutri FOR LOGIN adminQuestNutri;
-- Conceder permissões para todas as tabelas
GRANT SELECT, INSERT, UPDATE, DELETE ON Addresses TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Users TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Customers TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Aliments TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Weights TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Meals TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON Foods TO adminQuestNutri;
GRANT SELECT, INSERT, UPDATE, DELETE ON SubFoods TO adminQuestNutri;

-- Endereços registrados em sistema, ainda que não tenham mais clientes vínculados.
INSERT INTO Addresses (addr_street, addr_num, addr_comp, addr_cep, addr_neighborhood, addr_city, addr_state)
VALUES 
    ('Rua das Flores', 123, 'Apto 101', '13330000', 'Jardim Primavera', 'Indaiatuba', 'SP'),
	('Rua dos Ipês', 321, NULL, '13330100', 'Jardim das Flores', 'Indaiatuba', 'SP'),
    ('Avenida Central', 456, NULL, '13000000', 'Centro', 'Campinas', 'SP'),
	('Avenida das Árvores', 555, 'Bloco C', '13020000', 'Jardim Botânico', 'Campinas', 'SP'),
    ('Avenida Principal', 654, NULL, '13010000', 'Centro', 'Campinas', 'SP'),
    ('Rua do Lago', 987, 'Casa 5', '13320100', 'Bairro do Lago', 'Salto', 'SP'),
	('Rua da Praia', 789, 'Casa 2', '13320000', 'Bairro do Mar', 'Salto', 'SP');


-- Clientes/Pacientes do profissional nutricionista.
INSERT INTO Customers (cust_name, cust_email, cpf, cust_phoneNumber, cust_activityStatus, cust_setKcal, cust_height, cust_birth, cust_gender, idAddress)
VALUES 
    ('José da Silva', 'jose.silva@mail.com', '12345678910', '11912345678', 1, 2000, 170, '1990-01-15', 'M', 1),
    ('Ana Souza', 'ana.souza@mail.com', '23456789021', '19987654321', 2, 1800, 160, '1985-07-20', 'F', 2),
    ('Carlos Oliveira', 'carlos.oliveira@mail.com', '34567890132', '15998765432', 3, 2200, 180, '1982-03-10', 'M', 7),
    ('Mariana Santos', 'mariana.santos@mail.com', '45678901243', '11987651234', 4, 1900, 165, '1988-11-25', 'F', 4),
    ('Fernanda Lima', 'fernanda.lima@mail.com', '56789012354', '19912345678', 5, 2100, 175, '1995-04-05', 'F', 5);

INSERT INTO Users (user_login, user_password, user_personalName, systemLevel) VALUES 
	('admin', 'admin', 'Admin', 3),
	('camila', 'nutri', 'Camila', 2),
	('recepcao', '123', 'Recepção', 1);

-- Registros de diferentes pesos para cada um dos clientes. Sendo no mínimo um registro para cada.
INSERT INTO Weights (idCustomer, wgt_value)
VALUES
	-- Cliente: José da Silva
    (1, 75.5),
    (1, 73.2),
    (1, 72.8),

	-- Cliente: Ana Souza
    (2, 65.0), 
    (2, 64.2),

	-- Cliente: Carlos Oliveira
    (3, 85.3), 

	-- Cliente: Mariana Santos
    (4, 60.7), 
    (4, 61.0),
    (4, 60.5),

	-- Cliente: Fernanda Lima
    (5, 70.0), 
    (5, 69.5);


-- Registros de Refeições (Meals) para cada um dos clientes, sendo no mínimo 5 refeições por cliente.
INSERT INTO Meals (idCustomer, meal_name, meal_daysOfWeek, meal_hour, meal_createdAt)
VALUES
	-- Cliente: José da Silva
    (1, 'Café da Manhã', 65, '08:00:00', GETDATE()),
	(1, 'Café da Manhã', 62, '08:00:00', GETDATE()),
    (1, 'Almoço', 65, '12:00:00', GETDATE()),
	(1, 'Almoço', 62, '12:00:00', GETDATE()),
    (1, 'Jantar', 65, '18:00:00', GETDATE()),
	(1, 'Jantar', 62, '18:00:00', GETDATE()),

	-- Cliente: Ana Souza
    (2, 'Café da Manhã', 127, '07:30:00', GETDATE()),
	(2, 'Lanche da Manhã', 127, '10:00:00', GETDATE()),
    (2, 'Almoço', 127, '12:30:00', GETDATE()),
    (2, 'Lanche da Tarde', 127, '15:30:00', GETDATE()),
    (2, 'Jantar', 127, '19:00:00', GETDATE()),
	(2, 'Lanche da Noite', 127, '21:00:00', GETDATE()),

	-- Cliente: Carlos Oliveira
    (3, 'Café da Manhã', 127, '06:30:00', GETDATE()),
	(3, 'Lanche da Manhã', 85, '10:00:00', GETDATE()),
	(3, 'Lanche da Manhã', 42, '10:00:00',GETDATE()),
    (3, 'Almoço', 127, '13:00:00', GETDATE()),
	(3, 'Lanche da Tarde', 85, '17:30:00', GETDATE()),
	(3, 'Lanche da Tarde', 42, '17:30:00', GETDATE()),
    (3, 'Jantar', 127, '20:00:00', GETDATE()),


	-- Cliente: Mariana Santos
	(4, 'Café da Manhã', 127, '07:00:00', GETDATE()),
    (4, 'Almoço', 127, '12:00:00', GETDATE()),
    (4, 'Lanche da Tarde', 127, '15:30:00', GETDATE()),
    (4, 'Jantar', 127, '19:30:00', GETDATE()),
    (4, 'Lanche da Noite', 127, '22:30:00', GETDATE()),

	-- Cliente: Fernanda Lima
    (5, 'Café da Manhã', 64, '07:00:00', GETDATE()),
	(5, 'Café da Manhã', 32, '07:00:00', GETDATE()),
	(5, 'Café da Manhã', 16, '07:00:00', GETDATE()),
	(5, 'Café da Manhã', 8, '07:00:00', GETDATE()),
	(5, 'Café da Manhã', 4, '07:00:00', GETDATE()),
	(5, 'Café da Manhã', 2, '05:00:00', GETDATE()),
	(5, 'Café da Manhã', 1, '07:00:00', GETDATE()),
	(5, 'Lanche da manhã', 2, '09:00:00', GETDATE()),
	(5, 'Almoço', 64, '12:30:00', GETDATE()),
	(5, 'Almoço', 32, '12:30:00', GETDATE()),
	(5, 'Almoço', 16, '12:30:00', GETDATE()),
	(5, 'Almoço', 8, '12:30:00', GETDATE()),
	(5, 'Almoço', 4, '12:30:00', GETDATE()),
	(5, 'Almoço', 2, '12:30:00', GETDATE()),
	(5, 'Almoço', 1, '12:30:00', GETDATE()),
    (5, 'Jantar', 64, '19:00:00', GETDATE()),
	(5, 'Jantar', 32, '19:00:00', GETDATE()),
	(5, 'Jantar', 16, '19:00:00', GETDATE()),
	(5, 'Jantar', 8, '19:00:00', GETDATE()),
	(5, 'Jantar', 4, '18:00:00', GETDATE()),
	(5, 'Jantar', 2, '19:00:00', GETDATE()),
	(5, 'Jantar', 1, '19:00:00', GETDATE()),
	(5, 'Lanche da noite', 4, '20:30:00', GETDATE());


-- Registros de Foods (ou alimentos de uma refeição) para CADA um dos registros de Meal anteriormente adicionados.
-- O mínimo de alimentos em nosso exemplo é 3.
INSERT INTO Foods (idMeal, idAliment, food_quantity, food_unityQt)
VALUES
	-- Cliente: José da Silva
		-- Café da Manhã (1)
			(1, 222, 150, 'gramas'),
			(1, 489, 250, 'gramas'),
			(1, 451, 100, 'gramas'),

		-- Café da Manhã (2)
			(2, 52, 100, 'gramas'),
			(2, 468, 30, 'gramas'),
			(2, 215, 250, 'gramas'),

		-- Almoço (1)
			(3, 410, 200, 'gramas'),
			(3, 1, 150, 'gramas'),
			(3, 567, 100, 'gramas'),
			(3, 77, 100, 'gramas'),

		-- Almoço (2)
			(4, 326, 200, 'gramas'),
			(4, 1, 150, 'gramas'),
			(4, 561, 100, 'gramas'),
			(4, 142, 100, 'gramas'),

		-- Jantar (1)
			(5, 315, 150, 'gramas'),
			(5, 41, 200, 'gramas'),
			(5, 161, 100, 'gramas'),

		-- Jantar (2)
			(6, 272, 2, 'gramas'),
			(6, 188, 190, 'gramas'),
			(6, 589, 30, 'gramas'),
		
	-- Cliente: Ana Souza
		-- Café da Manhã
			(7, 26,30,'gramas'),
			(7, 458,200, 'gramas'),
			(7, 63,300,'gramas'),

		-- Lanche da Manhã
			(8, 182, 70 , 'gramas'),
			(8, 448, 100,'gramas'),
			(8, 189, 127,'gramas'),

		-- Almoço
			(9, 331,100,'gramas'),
			(9, 539,100,'gramas'),
			(9, 1,200,'gramas'),

		-- Lanche da Tarde
			(10, 261,7, 'gramas'),
			(10, 51,150,'gramas'),
			(10, 476,150,'gramas'),

		-- Jantar
			(11, 3,120,'gramas'),
			(11, 406,70,'gramas'),
			(11, 480,200,'gramas'),

		-- Lanche da Noite
			(12, 468,10,'gramas'),
			(12, 51,150,'gramas'),
			(12, 18,50,'gramas'),

	-- Cliente: Carlos Oliveira
		-- Café da Manhã
			(13, 46,30,'gramas'),
			(13, 99,5,'unidades'),
			(13, 588,5,'gramas'),

		-- Lanche da Manhã 1
			(14, 199,10,'gramas'),
			(14, 452,100,'gramas'),
			(14, 214,5,'gramas'),

		-- Lanche da Manhã 2
			(15, 478,100,'gramas'),
			(15, 226,30,'gramas'),
			(15, 243,150,'gramas'),

		-- Almoço
			(16, 527,200,'gramas'),
			(16, 131,20,'gramas'),
			(16, 41,70,'gramas'),

		-- Lanche da Tarde 1
			(17, 77,50,'gramas'),
			(17, 64,30,'gramas'),
			(17, 88,30,'gramas'),

		-- Lanche da Tarde 2
			(18, 471,100,'gramas'),
			(18, 9,20,'gramas'),
			(18, 15,100,'gramas'),

		-- Jantar
			(19, 132,50,'gramas'),
			(19, 542,100,'gramas'),
			(19, 209,250,'gramas'),

	-- Cliente: Mariana Santos
		-- Café da Manhã
			(20, 140,30,'gramas'),
			(20, 484,50,'gramas'),
			(20, 219,150,'gramas'),

		-- Almoço
			(21, 3,150,'gramas'),
			(21, 561,120,'gramas'),
			(21, 411,220,'gramas'),
			(21, 161, 100,'gramas'),

		-- Lanche da Tarde
			(22, 8,50,'gramas'),
			(22, 455,220,'gramas'),
			(22, 242, 250,'gramas'),

		-- Jantar
			(23, 3,150,'gramas'),
			(23, 561, 120,'gramas'),
			(23, 488, 120,'gramas'),

		-- Lanche da Noite
			(24, 475,220,'gramas'),
			(24, 63,400,'gramas'),
			(24, 263,10,'gramas'),

	-- Cliente: Fernanda Lima
		-- Café da Manhã 1
			(25, 488,126,'gramas'),
			(25, 7,10,'gramas'),
			(25, 484,200,'gramas'),
			(25, 21, 45, 'gramas'),
			(25, 484, 300, 'gramas'),


		-- Café da Manhã 2
			(26, 7,8,'gramas'),
			(26, 175,50,'gramas'),
			(26, 52,84,'gramas'),
			(26, 239, 60, 'gramas'),
			(26, 140, 150, 'gramas'),

		-- Café da Manhã 3
			(27, 226,141,'gramas'),
			(27, 507,4,'gramas'),
			(27, 209,200,'gramas'),
			(27, 458, 200, 'gramas'),
			(27, 222, 60, 'gramas'),


		-- Café da Manhã 4
			(28, 458,100,'gramas'),
			(28, 460,80,'gramas'),
			(28, 449,170,'gramas'),
			(28, 594, 30, 'gramas'),
			(28, 448, 170, 'gramas'),

		-- Café da Manhã 5
			(29, 461,60,'gramas'),
			(29, 557,3,'gramas'),
			(29, 594,3,'gramas'),
			(29, 236, 150, 'gramas'),
			(29, 507, 2.5, 'gramas'),

		-- Café da Manhã 6
			(30, 52,50,'gramas'),
			(30, 488,63,'gramas'),
			(30, 243,250,'gramas'),
			(30, 485, 100, 'gramas'),
			(30, 218, 150, 'gramas'),

		-- Café da Manhã 7
			(31, 182,70,'gramas'),
			(31, 48,53,'gramas'),
			(31, 507,5,'gramas'),
			(31, 502, 15, 'gramas'),
			(31, 278, 90, 'gramas'),

		-- Lanche da Manhã
			(32, 451,170,'gramas'),
			(32, 221,205,'gramas'),
			(32, 244,35,'gramas'),
			(32, 182, 70, 'gramas'),
			(32, 257, 80, 'gramas'),


		-- Almoço 1
			(33, 410,150,'gramas'),
			(33, 328,170,'gramas'),
			(33, 340,160,'gramas'),
			(33, 401, 120, 'gramas'),
			(33, 337, 100, 'gramas'),

		-- Almoço 2
			(34, 88,355,'gramas'),
			(34, 88,355,'gramas'),
			(34, 3,90,'gramas'),
			(34, 575, 190, 'gramas'),
			(34, 358, 150, 'gramas'),

		-- Almoço 3
			(35, 100,240,'gramas'),
			(35, 100,190,'gramas'),
			(35, 110,50,'gramas'),
			(35, 260, 15, 'gramas'),
			(35, 545, 200, 'gramas'),

		-- Almoço 4
			(36, 1, 82.5,'gramas'),
			(36, 260, 2.5,'gramas'),
			(36, 259, 3,'gramas'),
			(36, 316, 130, 'gramas'),
			(36, 260, 2, 'gramas'),

		-- Almoço 5
			(37, 539, 80, 'gramas'),
			(37, 79, 40, 'gramas'),
			(37, 161, 99, 'gramas'),
			(37, 142, 150, 'gramas'),
			(37, 152, 20, 'gramas'),

		-- Almoço 6
			(38, 546, 170, 'gramas'),
			(38, 161, 99, 'gramas'),
			(38, 129, 70, 'gramas'),
			(38, 161, 99, 'gramas'),
			(38, 75, 18, 'gramas'),

		-- Almoço 7
			(39, 478, 190, 'gramas'),
			(39, 5, 82.5, 'gramas'),
			(39, 138, 90, 'gramas'),
			(39, 209, 200, 'gramas'),
			(39, 83, 12, 'gramas'),

		-- Jantar 1
			(40, 315, 150, 'gramas'),
			(40, 311, 150, 'gramas'),
			(40, 298, 170, 'gramas'),
			(40, 584, 150, 'gramas'),
			(40, 440, 150, 'gramas'),

		-- Jantar 2
			(41, 588, 76, 'gramas'),
			(41, 597, 9, 'gramas'),
			(41, 91, 190, 'gramas'),
			(41, 1, 190, 'gramas'),
			(41, 88, 70, 'gramas'),

		-- Jantar 3
			(42, 68, 60, 'gramas'),
			(42, 72, 90, 'gramas'),
			(42, 101, 160, 'gramas'),
			(42, 95, 100, 'gramas'),
			(42, 120, 100, 'gramas'),

		-- Jantar 4
			(43, 118, 200, 'gramas'),
			(43, 584, 100, 'gramas'),
			(43, 272, 2, 'gramas'),
			(43, 272, 1.5, 'gramas'),
			(43, 252, 190, 'gramas'),

		-- Jantar 5
			(44, 567, 16, 'gramas'),
			(44, 252, 190, 'gramas'),
			(44, 165, 190, 'gramas'),
			(44, 188, 190, 'gramas'),
			(44, 82, 10, 'gramas'),

		-- Jantar 6
			(45, 588, 76, 'gramas'),
			(45, 515, 30, 'gramas'),
			(45, 235, 148, 'gramas'),
			(45, 164, 150, 'gramas'),
			(45, 235, 148, 'gramas'),

		-- Jantar 7
			(46, 234, 190, 'gramas'),
			(46, 249, 300, 'gramas'),
			(46, 443, 7, 'gramas'),
			(46, 228, 180, 'gramas'),
			(46, 589, 30, 'gramas'),

		-- Lanche da Noite
			(47, 218, 190, 'gramas'),
			(47, 587, 12, 'gramas'),
			(47, 452, 170, 'gramas'),
			(47, 597, 30, 'gramas'),
			(47, 450, 170, 'gramas');


-- Registros de opções de substituição para alimentos aleatórios de uma dada refeição anteriormente adicionados.
INSERT INTO SubFoods (idFood, idAliment, subFood_quantity, subFood_unityQt)
VALUES
	-- Almoço (1) José da Silva
	(8, 542, 100, 'gramas'),

	-- Almoço (2) José da Silva
	(12, 542, 100, 'gramas'),
	
	-- Jantar (1) Ana Souza
	(33, 542, 100, 'gramas'),

	-- Café da manha (4) Fernanda Lima
	(96, 463, 50, 'gramas'),

	-- Café da manha (7) Fernanda Lima
	(106, 209, 70, 'gramas'),
	(106, 222, 40, 'gramas'),
	(106, 45, 40, 'gramas'),

	-- Almoço (2) Fernanda Lima
	(121, 91, 200, 'gramas'),

	-- Jantar (1) Fernanda Lima
	(151, 313, 150, 'gramas'),

	-- Jantar (7) Fernanda Lima
	(183, 439, 70, 'gramas');