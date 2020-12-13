-- 信的表结构

CREATE TABLE IF NOT EXISTS tongxingmao.Problem (
	id INT auto_increment NOT NULL,
	title TEXT NOT NULL COMMENT '问题的主题',
	content TEXT NOT NULL COMMENT '问题的描述',
	CONSTRAINT Problem_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='问题：问题的主题、问题的内容、问题的选项（关系）';


CREATE TABLE IF NOT EXISTS tongxingmao.`Option` (
	id INT auto_increment NOT NULL,
	content TEXT NOT NULL COMMENT '问题选项的主题',
	CONSTRAINT Problem_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='问题选项：选项主题、TAGs（关系）';


CREATE TABLE IF NOT EXISTS tongxingmao.TAG (
	name varchar(200) NOT NULL COMMENT 'TAG的名字',
	describ TEXT NOT NULL COMMENT 'TAG的具体描述',
	CONSTRAINT TAG_PK PRIMARY KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='TAG：标签、标签描述';


CREATE TABLE IF NOT EXISTS tongxingmao.`Problem-Option` (
	problemId INT NOT NULL,
	optionId INT NOT NULL,
	CONSTRAINT Problem_Option_FK_1 FOREIGN KEY (problemId) REFERENCES tongxingmao.Problem(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT Problem_Option_FK_2 FOREIGN KEY (optionId) REFERENCES tongxingmao.`Option`(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='Problem和Option的关系表
一个Problem对应2个或多个Option
一个Option只对应一个Problem';


CREATE TABLE IF NOT EXISTS tongxingmao.`Option-TAG` (
	optionId INT NOT NULL,
	TAGName varchar(200) NOT NULL,
	CONSTRAINT Option_TAG_FK_1 FOREIGN KEY (optionId) REFERENCES tongxingmao.`Option`(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT Option_TAG_FK_2 FOREIGN KEY (TAGName) REFERENCES tongxingmao.TAG(name) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='Option和TAG的关系表
一个Option可以有一个或多个TAG
一个TAG可以供多个Option使用';
