ALTER TABLE pacientes ADD COLUMN ativo boolean;
update pacientes SET ativo = true;