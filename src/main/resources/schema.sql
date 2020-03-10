CREATE TABLE IF NOT EXISTS USERS (
  id BIGINT AUTO_INCREMENT,
  login VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  user_password VARCHAR(250) NOT NULL,
  user_role INTEGER NOT NULL,
  primary key(id)
 );

CREATE TABLE IF NOT EXISTS TELEPHONE_COMPANY (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  primary key(id)
);

CREATE TABLE IF NOT EXISTS TELEPHONE_NUMBER (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  user_id BIGINT,
  telephone_company_id BIGINT,
  primary key(id),
  foreign key (user_id) references USERS(id),
  foreign key (telephone_company_id) references TELEPHONE_COMPANY(id)
);

CREATE TABLE IF NOT EXISTS USER_ACCOUNT (
  id BIGINT AUTO_INCREMENT,
  money DECIMAL,
  user_id BIGINT,
  primary key(id),
  foreign key (user_id) references users(id)
);

insert into users values (1,'superAdmin', 'superAdmin', 'superAdmin', '$2a$10$VsH99.9rnhSRhr7JwNLbZOqKLaoQ5Bg7UD65VyrPxAGkxYjNp9lG6', 1);

