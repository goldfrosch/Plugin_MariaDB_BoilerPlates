create table if not exists player_cash
(
    uuid VARCHAR(16) not null primary key,
    cash bigint default 0 not null
);