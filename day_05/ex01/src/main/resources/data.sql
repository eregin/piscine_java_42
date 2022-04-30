insert into chat.users (login, password) VALUES ('Mike', 'qwerty');
insert into chat.users (login, password) VALUES ('Tom', '12345');
insert into chat.users (login, password) VALUES ('Lisa', 'password');
insert into chat.users (login, password) VALUES ('Hanck', '1234');
insert into chat.users (login, password) VALUES ('John', 'q1w2e3r4t5');

insert into chat.rooms (chat_name, chat_owner) VALUES ('chat 1', 1);
insert into chat.rooms (chat_name, chat_owner) VALUES ('chat 2', 2);
insert into chat.rooms (chat_name, chat_owner) VALUES ('chat 3', 3);
insert into chat.rooms (chat_name, chat_owner) VALUES ('chat 4', 4);
insert into chat.rooms (chat_name, chat_owner) VALUES ('chat 5', 5);

insert into chat.messages (author, room_id, message, time) VALUES (1, 2, 'Hi', '1970-01-01 00:00:01');
insert into chat.messages (author, room_id, message, time) VALUES (3, 2, 'Hello', '1971-02-03 00:00:01');
insert into chat.messages (author, room_id, message, time) VALUES (4, 3, 'Gracias', '1973-03-02 00:00:01');
insert into chat.messages (author, room_id, message, time) VALUES (5, 1, 'Bonjorno', '1971-02-08 00:00:01');
insert into chat.messages (author, room_id, message, time) VALUES (2, 3, 'Nihao', '1970-01-01 00:00:01');

