CREATE TABLE ref_permissions (
  id INTEGER PRIMARY KEY,
  description TEXT NOT NULL
);

INSERT INTO ref_permissions
  (id, description)
VALUES
  (1 , 'Edit'),
  (2 , 'View'),
  (3 , 'Restricted')
;

CREATE TABLE user_details (
  user_name TEXT PRIMARY KEY,
  user_password TEXT NOT NULL,
  user_permission INTEGER references ref_permissions(id)
);

CREATE TABLE quiz (
  id SERIAL PRIMARY KEY,
  title TEXT NOT NULL
);

CREATE TABLE question (
  id SERIAL UNIQUE,
  quiz_id INTEGER references quiz(id),
  question_index INTEGER NOT NULL,
  question_text TEXT NOT NULL,
  PRIMARY KEY(quiz_id, question_index)
);

CREATE TABLE answer (
  id SERIAL UNIQUE,
  question_id INTEGER references question(id),
  answer_index CHAR(1) NOT NULL,
  answer_text TEXT NOT NULL,
  PRIMARY KEY(question_id, answer_index)
);
