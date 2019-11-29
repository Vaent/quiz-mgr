DROP FUNCTION IF EXISTS fn_create_answer;
DROP FUNCTION IF EXISTS fn_util_increment_answer_index;

CREATE FUNCTION fn_create_answer(par_question_id INTEGER, par_answer_text TEXT)
RETURNS BOOLEAN
AS $$
DECLARE
  var_current_max_index CHAR(1);
  var_answer_index CHAR(1);
BEGIN
  SELECT INTO var_current_max_index MAX(answer_index) FROM answer WHERE question_id=par_question_id;
  IF var_current_max_index IS NULL THEN
    var_answer_index := 'A';
  ELSE
    var_answer_index := fn_util_increment_answer_index(var_current_max_index);
  END IF;
  INSERT INTO answer(question_id, answer_index, answer_text)
    VALUES (par_question_id, var_answer_index, par_answer_text);
  RETURN true;
EXCEPTION
  WHEN invalid_parameter_value THEN RETURN false;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION fn_util_increment_answer_index(par_char CHAR(1))
RETURNS CHAR(1)
AS $$
BEGIN
  CASE
    WHEN par_char='A' THEN RETURN 'B';
    WHEN par_char='B' THEN RETURN 'C';
    WHEN par_char='C' THEN RETURN 'D';
    WHEN par_char='D' THEN RETURN 'E';
    WHEN par_char='E' THEN RAISE invalid_parameter_value USING MESSAGE='Cannot have an index greater than "E"';
	ELSE RAISE invalid_parameter_value USING MESSAGE='Parameter is not a valid answer index';
  END CASE;
END;
$$ LANGUAGE plpgsql;