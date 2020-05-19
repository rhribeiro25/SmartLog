# SmartLog

OBJETIVO
--------
Escrever uma aplicação Java para fazer upload de arquivo de log e popular o banco de dados,
uma interface para listar/consultar/pesquisar os logs, e uma ultima interface com formulário
para inserção manual de log. Implemente o back-end com (Spring ou JavaEE) e front-end (JSP
ou SPA) (front-end em Angular será considerado um diferencial).
DETALHES DO BACK-END:
- Definir o modelo de dados no PostgreSQL;
- Definir serviços para a inserção em batch (usando o arquivo de logs fornecido,
usando JPA);
- Definir serviços para a inserção de logs manuais (CRUD);
- Implementar filtros ou pesquisas de logs;
- (BÔNUS) Testes automatizados;
DETALHES DO FRONT-END:
- Tela para inserção de logs manuais (CRUD);
- Tela para inserção de logs usando o arquivo modelo;
- Tela para buscar logs feitos por um determinado IP e por um intervalo de tempo;
- (BÔNUS) Dashboard para exibir o número de requests feitos por um determinado IP,
por hora, user-agent (agregação);

FORMATO LOG
-----------
Data, IP, Request, Status, User Agent (delimitado por aspas duplas);
O delimitador do arquivo de log é o caracter pipe (|);
Formato de data: "yyyy-MM-dd HH:mm:ss.SSS";

ENTREGAS
--------
(1) Aplicação JavaWEB (Spring ou JavaEE).
(2) Código Fonte da aplicação (back-end e front-end).
(3) Esquema do PostgreSQL usado para os dados de log.
Importante: Tempo para realização do teste é de 7 dias corridos, teste completo vale mais
pontos, mas não se preocupe caso não consiga terminar todos os itens, visto que o foco se
consiste nas premissas abaixo.
Qualidade do código, Lógica e Organização;
Ao finalizar a entrega do desafio, use um repositório privado no GitHub e compartilhe com os
usuários: mhbpcuiaba e geovannefduarte.
