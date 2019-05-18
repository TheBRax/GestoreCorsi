/*
Elenco degli iscritti ai corsi di un certo periodo didattico
*/
select c.codins, c.pd, c.nome, count(*) as tot
from iscritticorsi.corso as c, iscritticorsi.iscrizione as i
where c.codins = i.codins and c.pd = 1
group by c.codins; /*,c.nome;