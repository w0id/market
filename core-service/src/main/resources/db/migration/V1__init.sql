CREATE TABLE "products" (
                            "id" bigserial primary key,
                            "name" varchar(255)   NOT NULL,
                            "description" varchar(255)   NULL,
                            "cost" decimal   NOT NULL
);

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

CREATE TABLE "delivery_types" (
                                  "id" bigserial primary key,
                                  "title" varchar(255)   NOT NULL
);

CREATE TABLE "pickup_points" (
                                 "id" bigserial primary key,
                                 "title" varchar(255)   NOT NULL,
                                 "email" varchar(150)   NOT NULL,
                                 "country" varchar(100)   NOT NULL,
                                 "region" varchar(255)   NOT NULL,
                                 "district" varchar(255)   NULL,
                                 "city" varchar(150)   NOT NULL,
                                 "address" varchar(255)   NOT NULL,
                                 "phone" varchar(15)   NOT NULL
);

CREATE TABLE "orders" (
                          "id" bigserial primary key,
                          "customer_id" bigint   NOT NULL references customers (id),
                          "delivery_type_id" bigint   NOT NULL references delivery_types (id),
                          "pickup_point_id" bigint  null references pickup_points (id)
);

CREATE TABLE "order_items" (
                               "id" bigserial primary key,
                               "order_id" bigint not null references orders (id),
                               "product_id" bigint  NOT NULL references products (id),
                               "quantity" int not null
);
