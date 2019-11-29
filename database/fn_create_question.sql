DROP FUNCTION IF EXISTS fn_create_question;

CREATE FUNCTION fn_create_question(par_quiz_id INTEGER, par_question_text TEXT, VARIADIC par_answer_texts TEXT[] DEFAULT '{}')
RETURNS BOOLEAN
AS $$
DECLARE
  var_current_max_index INTEGER;
  var_question_index INTEGER;
  var_question_id INTEGER;
  var_answer_text TEXT;
  var_answer_created BOOLEAN;
BEGIN
  SELECT INTO var_current_max_index MAX(question_index) FROM question WHERE quiz_id=par_quiz_id;
  IF var_current_max_index IS NULL THEN
    var_question_index := 1;
  ELSIF var_current_max_index < 1 THEN
	RAISE EXCEPTION USING MESSAGE='Invalid database state - negative question index found';
  ELSE
    var_question_index := var_current_max_index + 1;
  END IF;
  INSERT INTO question(quiz_id, question_index, question_text)
    VALUES (par_quiz_id, var_question_index, par_question_text);
  SELECT INTO var_question_id id FROM question WHERE (quiz_id=par_quiz_id AND question_index=var_question_index);
  FOREACH var_answer_text IN ARRAY par_answer_texts LOOP
    SELECT INTO var_answer_created fn_create_answer(var_question_id, var_answer_text);
  END LOOP;
  RETURN true;
END;
$$ LANGUAGE plpgsql;