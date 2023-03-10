CREATE TABLE "customers" (
                             "id" bigserial primary key,
                             "username" varchar(255)   NOT NULL,
                             "first_name" varchar(255)   NULL,
                             "middle_name" varchar(255)   NULL,
                             "last_name" varchar(255)   NULL,
                             "email" varchar(150)   NOT NULL,
                             "country" varchar(100)   NULL,
                             "region" varchar(255)   NULL,
                             "district" varchar(255)   NULL,
                             "city" varchar(150)   NULL,
                             "address" varchar(255)   NULL,
                             "phone" varchar(15)   NULL
);

-- CREATE TABLE "users" (
--                          "id" bigserial primary key,
--                          "username" varchar(255)   NOT NULL,
--                          "password" varchar(255)   NOT NULL,
--                          "customer_id" bigint   NOT NULL references customers (id)
-- );
--
-- CREATE TABLE "roles" (
--                          "id" bigserial primary key,
--                          "name" varchar(100)   NOT NULL
-- );
--
-- CREATE TABLE "users_roles" (
--                                "user_id" bigint   NOT NULL references users (id),
--                                "role_id" bigint   NOT NULL references roles (id)
-- );