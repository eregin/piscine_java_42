create schema if not exists chat;

drop table if exists chat.users, chat.rooms, chat.messages;

create table if not exists chat.users (
    id          serial primary key ,
    login       text not null ,
    password    text
);

create table if not exists chat.rooms (
    id          serial primary key ,
    chat_name   text not null,
    chat_owner  int not null references chat.users(id)
);

create table if not exists chat.messages (
    id          serial primary key ,
    author      int not null references chat.users(id),
    room_id     int not null references chat.rooms(id),
    message     text not null,
    time        timestamp
);