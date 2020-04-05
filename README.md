Translator from sql to MongoDB shell

Grammar:

query ::= fields collection where skip limit

fields ::= 'SELECT' id {',' id}* | 'SELECT *'

collection ::= 'FROM' id

where ::= EPS | 'WHERE' predicate {'AND' predicate}*

skip ::= EPS | 'OFFSET' n

limit ::= 'limit' n

predicate ::= id '=' value | id '<>' value |id '>' value | id '<' value

id ::= ('0'...'9')*('a'...'z'|'A'...'Z'|'_'|'$'){'a'...'z'|'A'...'Z'|'0'...'9'|'_'|'$'}*

n ::= '-'?'1'...'9'{'0'...'9'}*  

value ::= '"'('\\'. | '\'\'' | ~('\'' | '\\') | .)* '"' | n | 'true' | 'false' | '-'?{'0'...'9'}+'.'{'0'...'9'}+

query.res = 'db.' + collection.res + '.find({' + where.res + '}' + fields.res + ')' + skip.res + limit.res

fields.res = ', {' + id.str + ':1' + {', ' + id.str + ':1'}* | ''

collection.res = id.str

where.res = '' | predicate.res + {',' + predicate.res}*

skip.res = '' | '.skip(' + n.str + ')'

limit.res = '' | '.limit(' + n.str + ')'

predicate.res = id.str + ': ' + value.str | id.str + ': { $ne: ' + value.str + '}' | id.str + ': { $gt: ' + value.str + '}' | id.str + ': { $gt: ' + value.str + '}' id.str + ': { $lt: ' + value.str + '}'