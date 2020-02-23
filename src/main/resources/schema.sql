
DROP TABLE IF EXISTS currency_exchange_rate;
create table currency_exchange_rate (id INT AUTO_INCREMENT  PRIMARY KEY,
from_currency varchar(20),
to_currency varchar(20),
exchange_rate DECIMAL(20,2),
exchange_rate_date date
);
insert into CURRENCY_EXCHANGE_RATE(id,from_currency,to_currency,exchange_rate,exchange_rate_date) values(1,'BTC','USD','8000.8','2020-02-19');
insert into CURRENCY_EXCHANGE_RATE(id,from_currency,to_currency,exchange_rate,exchange_rate_date) values(2,'BTC','USD','7000.8','2020-02-19');

insert into CURRENCY_EXCHANGE_RATE(id,from_currency,to_currency,exchange_rate,exchange_rate_date) values(3,'BTC','USD','9612.43','2020-02-20');
insert into CURRENCY_EXCHANGE_RATE(id,from_currency,to_currency,exchange_rate,exchange_rate_date) values(4,'BTC','USD','9610.43','2020-02-20');