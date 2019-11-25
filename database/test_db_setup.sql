INSERT INTO quiz
  (title)
VALUES
  ('First quiz')
;
--SELECT * FROM quiz;

INSERT INTO question
  (quiz_id, question_index, question_text)
VALUES
  (1, 1, 'Question text?')
;
--SELECT * FROM question;

INSERT INTO answer
  (question_id, answer_index, answer_text)
VALUES
  (1, 'A', 'Answer text.')
;
--SELECT * FROM question;

TRUNCATE quiz CASCADE;
TRUNCATE question CASCADE;
TRUNCATE answer CASCADE;

ALTER SEQUENCE quiz_id_seq RESTART;
ALTER SEQUENCE question_id_seq RESTART;
ALTER SEQUENCE answer_id_seq RESTART;
