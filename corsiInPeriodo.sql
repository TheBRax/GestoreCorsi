/*
Elenco dei corsi in certo periodo didattico
*/
select c.codins, c.crediti, c.nome, c.pd
from iscritticorsi.corso as c
where c.pd = 1;