,/*  

	ATRIBUTOS DE PRODUTO
    CREATE TABLE T_VFC_PRODUTO
  (
    cd_produto          NUMBER (5) NOT NULL ,
    ds_produto          VARCHAR2 (20) NOT NULL ,
    ds_completa_produto VARCHAR2 (128) NOT NULL ,
    qt_estoque          NUMBER (4) NOT NULL ,
    vl_unitario         NUMBER (6,2) NOT NULL ,
    vl_volume           NUMBER NOT NULL ,
    vl_peso             NUMBER NOT NULL ,
    ds_tempo_producao   DATE NOT NULL
  ) ;

	ATRIBUTOS DE PEDIDO VENDA
    cd_ordem_venda      NUMBER (10) NOT NULL ,
    dt_venda            DATE NOT NULL ,
    dt_previsao_entrega DATE NOT NULL ,
    ds_forma_pagamento  VARCHAR2 (30) NOT NULL ,
    vl_pedido           NUMBER (6,2) NOT NULL

  TIREI CD_NOTA_FISCAL DEVIDO AO ERRO DE FK
  CRIEI CONSTRAINT DE FK DE CD_PRODUTO PARA PEDIDO_VENDA
*/
--POPULANDO
INSERT INTO T_VFC_PRODUTO
(
	cd_produto ,
	ds_produto ,
	ds_completa_produto,
	qt_estoque,
	vl_unitario,
	vl_volume,
	vl_peso,
	ds_tempo_producao
)
VALUES
(
	3265, 'BEACON', 'BEANCO NAO EH BACON',
	3, 26.50, 4, 2, TO_DATE('11/10/2017','DD/MM/YYYY')
);
INSERT INTO T_VFC_PRODUTO
(
	cd_produto ,
	ds_produto ,
	ds_completa_produto,
	qt_estoque,
	vl_unitario,
	vl_volume,
	vl_peso,
	ds_tempo_producao
)
VALUES
(
	7854, 'BACON', 'AGORA EH BACON',
	1, 9.50, 2, 3, TO_DATE('13/10/2017','DD/MM/YYYY')
);
--POPULANDO EM PEDIDO_VENDA
INSERT INTO T_VFC_PEDIDO_VENDA (
    cd_ordem_venda,
    nr_nota_fiscal,
    dt_venda,
    dt_previsao_entrega,
    ds_forma_pagamento,
    vl_pedido
)
VALUES(
    11, TO_DATE('23/09/2017','DD/MM/YYYY'), 
    TO_DATE('10/10/2017','DD/MM/YYYY'),'CARTAO', 245.90
);

INSERT INTO T_VFC_PEDIDO_VENDA (
    cd_ordem_venda,
    nr_nota_fiscal,
    dt_venda,
    dt_previsao_entrega,
    ds_forma_pagamento,
    vl_pedido
)
VALUES(
    22, TO_DATE('30/09/2017','DD/MM/YYYY'), 
    TO_DATE('25/10/2017','DD/MM/YYYY'),'BOLETO', 300.70
);

--SELECTS BASICOS
--SELECIONANDO VALORES DE PRODUTO
SELECT  P.cd_produto 			"CODIGO PRODUTO" ,
		P.ds_produto 			"DESCRICAO PRODUTO",
		P.ds_completa_produto 	"DS. COMPLETA PRODUTO"
		P.vl_unitario			"VALOR UNITARIO"
		P.qt_estoque			"QUANTIDADE EM ESTOQUE"
FROM T_VFC_PRODUTO P;

--SELECIONANDO VALORES DE PEDIDO VENDA
SELECT  PV.cd_produto 		"COD. PRODUTO",
		PV.ds_produto 			"DESC. PRODUTO",
		PV.ds_completa_produto "DESC. COMP. PRODUTO",
		PV.qt_estoque 			"QT. ESTOQUE",
		PV.vl_unitario 		"VALOR UNITARIO",
		PV.vl_volume 			"VALOR VOLUME",
		PV.vl_peso 			"VALOR DO PESO", 
		PV.ds_tempo_producao 	"DESC. DURACAO DE PRODUCAO"
FROM T_VFC_PEDIDO_VENDA PV;

--SELECT MENOR VALOR UNITARIO DE PRODUTO
SELECT  P.cd_produto 			"CODIGO PRODUTO" ,
		P.ds_produto 			"DESCRICAO PRODUTO",
		P.ds_completa_produto 	"DS. COMPLETA PRODUTO"
		P.vl_unitario			"VALOR UNITARIO"
		P.qt_estoque			"QUANTIDADE EM ESTOQUE"
FROM T_VFC_PRODUTO P
ORDER BY P.vl_unitario ASC; --Pode utilizar DESC para ordenar para maiores valores

--Consutla os maioers valores do valor de produto unitario e o valor de pedido venda
SELECT  P.cd_produto		"CODIGO PRODUTO" ,
		P.ds_produto 		"DESC. PRODUTO" ,
		P.vl_unitario		"QUANT. ESTOQUE" ,
		PV.cd_ordem_venda 	"COD. ORDEM VENDA",
		PV.vl_pedido		"VALOR DO PEDIDO",
		PV.dt_venda			"DATA DE VENDA"
FROM T_VFC_PRODUTO P INNER JOIN T_VFC_PEDIDO_VENDA PV 
	ON (P.CD_PRODUTO = PV.CD_PRODUTO)
ORDER BY P.vl_unitario, PV.vl_pedido DESC;

--Consulta os produtos com estoque menor que 3
SELECT  P.cd_produto "CODIGO PRODUTO",
		P.ds_produto "DESC. PRODUTO" ,
		P.vl_unitario "VALOR UNITARIO",
		P.qt_estoque  "QUANT. EM ESTOQUE"
FROM T_VFC_PRODUTO P
WHERE P.qt_estoque < 3;

