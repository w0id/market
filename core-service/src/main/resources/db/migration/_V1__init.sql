CREATE TABLE "products" (
                            "id" bigserial primary key,
                            "name" varchar(255)   NOT NULL,
                            "description" varchar(255)   NULL,
                            "cost" decimal   NOT NULL
);