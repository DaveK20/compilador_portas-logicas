grammar Loga;

GATE: 'GATE';
INPUT: 'IN';
OUTPUT: 'OUT';
PARTS: 'PARTS';
CIRCUIT: 'CIRCUIT';
BUILTIN_GATE: 'Nand';
GATE_IN1: 'a';
GATE_IN2: 'b';
GATE_OUT: 'out';

FUNCAO: ('A' ..'Z') ('a' ..'z' | '0' ..'9')*;
VARIAVEL: (('a' ..'z') ( 'a' ..'z' | 'A' ..'Z' | '0' ..'9')*);
var_a: VARIAVEL;
var_b: VARIAVEL;
var_out: VARIAVEL;

COMENTARIO: '#' ~('\n')* '\n' -> skip;
WS: (' ' | '\t' | '\r' | '\n') -> skip;

ABRE_CHAVE: '{';
FECHA_CHAVE: '}'; 
ABRE_PAR: '(';
FECHA_PAR: ')';
OP_REL: '=';

declaracoes: (BUILTIN_GATE | FUNCAO) ABRE_PAR GATE_IN1 OP_REL simpleGateName+=var_a ',' GATE_IN2 OP_REL var_b
		',' GATE_OUT OP_REL var_out FECHA_PAR ';';

declaracao_final: (BUILTIN_GATE | FUNCAO) ABRE_PAR GATE_IN1 OP_REL var_a ',' GATE_IN2 OP_REL
		var_b ',' GATE_OUT OP_REL GATE_OUT FECHA_PAR ';';

circuitos:
	CIRCUIT FUNCAO ABRE_PAR GATE_IN1 OP_REL var_a ',' GATE_IN2 OP_REL var_b ',' GATE_OUT
		OP_REL var_out FECHA_PAR ABRE_CHAVE declaracoes+ FECHA_CHAVE;

entrada_estilo1: INPUT ':' var_a ',' var_b;
entrada_estilo2: INPUT ':' var_a ';' INPUT ':' var_b;

estrutura:
	GATE ABRE_CHAVE (entrada_estilo1 | entrada_estilo2) ';' OUTPUT ':' GATE_OUT ';'
		countCircuits+=circuitos* PARTS ':' countGate+=declaracoes* endGate=declaracao_final FECHA_CHAVE;
