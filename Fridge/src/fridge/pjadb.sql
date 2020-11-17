CREATE DATABASE IF NOT EXISTS pjadb;
USE pjadb;
CREATE TABLE Ingred
(
    `ing_no`    INT            NOT NULL    AUTO_INCREMENT COMMENT '재료고유번호', 
    `ing_name`  VARCHAR(45)    NULL        COMMENT '재료이름', 
    PRIMARY KEY (ing_no)
);

ALTER TABLE Ingred COMMENT '재료';

ALTER TABLE Ingred
    ADD CONSTRAINT UC_ing_no UNIQUE (ing_no);
CREATE TABLE Fridge
(
    `fri_no`    INT    NOT NULL    AUTO_INCREMENT COMMENT '냉장고고유번호', 
    `ingre_no`  INT    NULL        COMMENT '재료번호', 
    PRIMARY KEY (fri_no)
);

ALTER TABLE Fridge COMMENT '내 냉장고';

ALTER TABLE Fridge
    ADD CONSTRAINT FK_Fridge_ingre_no_Ingred_ing_no FOREIGN KEY (ingre_no)
        REFERENCES Ingred (ing_no) ON DELETE RESTRICT ON UPDATE RESTRICT;
CREATE TABLE Food
(
    `f_no`    INT            NOT NULL    AUTO_INCREMENT COMMENT '음식고유번호', 
    `f_name`  VARCHAR(45)    NULL        COMMENT '음식이름', 
    PRIMARY KEY (f_no, f_name)
);

ALTER TABLE Food COMMENT '음식';

ALTER TABLE Food
    ADD CONSTRAINT UC_f_name UNIQUE (f_name);
CREATE TABLE Recing
(
    `recipe_no`  INT    NOT NULL    AUTO_INCREMENT COMMENT '레시피고유번호', 
    `rec_no`     INT    NULL        COMMENT '재료번호', 
    `food_no`    INT    NULL        COMMENT '음식번호', 
    PRIMARY KEY (recipe_no)
);

ALTER TABLE Recing COMMENT '레시피 재료';

ALTER TABLE Recing
    ADD CONSTRAINT FK_Recing_rec_no_Ingred_ing_no FOREIGN KEY (rec_no)
        REFERENCES Ingred (ing_no) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Recing
    ADD CONSTRAINT FK_Recing_food_no_Food_f_no FOREIGN KEY (food_no)
        REFERENCES Food (f_no) ON DELETE RESTRICT ON UPDATE RESTRICT;