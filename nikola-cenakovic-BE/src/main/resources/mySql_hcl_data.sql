
/*Data for the table `organization_type` */

insert ignore into `organization_type`(`id_organization_type`,`name`) values 
(1,'Hospital'),
(2,'Insurance Company'),
(3,'Educational Institute'),
(4,'Clinical Research'),
(5,'Other');

/*Data for the table `service_type` */

insert ignore into `service_type`(`id_service_type`,`name`) values 
(1,'Aged Care Assessment'),
(2,'Aged Residential Care'),
(3,'Home Care/Housekeeping Assistance'),
(4,'Acupuncture'),
(5,'Bowen Therapy'),
(6,'Blood Donation'),
(7,'Family Planning'),
(8,'Immunization'),
(9,'Optometry'),
(10,'Osteopathy'),
(11,'Physiotherapy'),
(12,'Podiatry'),
(13,'Endodontic'),
(14,'Dental'),
(15,'Oral Surgery'),
(16,'Emergency Medical'),
(17,'Psychology'),
(18,'Dermatology');

/*Data for the table `user` */

insert ignore into `user`(`username`,`password`) values 
('admin','admin');
