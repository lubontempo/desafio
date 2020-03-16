# desafio BB (Luciana Bontempo)
# Histórias de Usuário
HU #1 Consultar cotação do dólar por data
 Disponibilizar a cotação do dólar através de consulta de dados ao WebService do BACEN. Passar como parâmetro data específica em formato DD-MM-YYYY. Armazenar o resultado da consulta em banco de dados local para evitar novo acesso ao WebService em caso de existência da informação localmente.
 
HU #2 Consultar cotação do dólar por mês
 Disponibilizar lista de cotações do dólar através de consulta de dados ao WebService do BACEN. Passar como parâmetro número do mês desejado. 
 
# Critérios de aceitação
1. As datas informadas (data específica ou mês) devem ser válidas.
2. As cotações devem ser exibidas em formato JSON
3. Exibir a cotação existente em base local antes de consultar o WebService do BACEN

# Cenários de Teste
1. Validar data para pesquisa por data específica (Formato esperado e data inválida)
2. Validar mês para pesquisa por mês (Número deve estar compreendido entre 1 e 12)