/*
dato un corso, stampare la divisione degli studenti iscritti tra i vari Corsi di Studio (CDS)
*/
select i.codins, s.CDS, count(*) as tot
from iscritticorsi.studente as s, iscritticorsi.iscrizione as i
where i.codins = "01OVZPG"
group by s.CDS