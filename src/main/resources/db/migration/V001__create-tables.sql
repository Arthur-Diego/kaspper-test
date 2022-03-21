CREATE TABLE tb_job (
  id bigint NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  salary double DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE tb_candidate (
  id bigint NOT NULL AUTO_INCREMENT,
  curriculum longblob,
  name varchar(255) DEFAULT NULL,
  profile varchar(255) DEFAULT NULL,
  salary_expectation varchar(255) DEFAULT NULL,
  cpf varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_bj7ebo4k5v0ucsafjeceluns4 (cpf)
) engine=InnoDB default charset=utf8;

CREATE TABLE tb_job_candidate (
  id bigint NOT NULL AUTO_INCREMENT,
  id_candidate bigint NOT NULL,
  id_job bigint NOT NULL,
  PRIMARY KEY (id),
  KEY id_candidate (id_candidate),
  KEY id_job (id_job),
  CONSTRAINT id_candidate FOREIGN KEY (id_candidate) REFERENCES tb_candidate (id),
  CONSTRAINT id_job FOREIGN KEY (id_job) REFERENCES tb_job (id)
) engine=InnoDB default charset=utf8;