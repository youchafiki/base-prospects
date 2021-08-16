CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS prospect (
    id SERIAL PRIMARY KEY,
    identifier TEXT NOT NULL,
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    activity TEXT NOT NULL,
    address TEXT NOT NULL,
    city TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    postal_code TEXT NOT NULL

);
