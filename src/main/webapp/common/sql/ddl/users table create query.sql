CREATE TABLE `tb_users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '���̵�',
	`email` VARCHAR(100) NULL DEFAULT NULL COMMENT '�̸���',
	`password` VARCHAR(500) NULL DEFAULT NULL COMMENT '��й�ȣ',
	`nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '����',
	`sns_id` VARCHAR(255) NULL DEFAULT NULL COMMENT 'sns_���̵�',
	`sns_type` VARCHAR(10) NULL DEFAULT NULL COMMENT 'sns_Ÿ��',
	`sns_profile` VARCHAR(255) NULL DEFAULT NULL COMMENT 'sns_������',
	`create_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '���_�Ͻ�',
	`modify_date` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '����_�Ͻ�',
	PRIMARY KEY (`id`, `email`),
	UNIQUE INDEX `idx1_email` (`email`),
	INDEX `idx2_sns_id` (`sns_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
