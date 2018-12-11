CREATE TABLE todoitem (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    isArchived BOOL NOT NULL
);