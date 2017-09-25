/*
  Relatório Mensal de Vendas, ordenados por produtos mais vendidos.
*/

SELECT  INF.cd_item,
		P.cd_produto,		
		P.ds_produto,
		P.vl_unitario,
		qt_estoque + ( 
			SELECT INF.qt_vendida   -- Faz a somatoria de quantidade vendida com estoque
			FROM T_VFC_ITEM_NOTA_FISCAL INF)"TOTAL VENDIDO" --Apelida select
FROM T_VFC_PRODUTO P INNER JOIN T_VFC_ITEM_NOTA_FISCAL INF
	ON (P.cd_item = INF.cd_item)  --Inner join para a busca de chave estrangeira e a subconsulta
ORDER BY "TOTAL VENDIDO" DESC; --Faz select de ordem decrescente do select apelidado
