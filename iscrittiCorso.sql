/*
Elenco degli iscritti ad un certo corso
*/
select s.matricola, s.cognome, s.nome, s.CDS
from iscritticorsi.studente as s, iscritticorsi.iscrizione as i
where s.matricola = i.matricola and i.codins = "01KSUPG"