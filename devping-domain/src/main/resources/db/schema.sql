CREATE TABLE `user_accounts` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`email` varchar(100) NOT NULL,
`first_name` varchar(100) NOT NULL,
`last_name` varchar(100) NOT NULL,
`password` varchar(255),
`sign_in_provider` varchar(20),
`tag` varchar(2000),
`version` bigint(20) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;dd


create table UserConnection (userId varchar(255) not null,
providerId varchar(255) not null,
providerUserId varchar(255),
rank int not null,
displayName varchar(255),
profileUrl varchar(512),
imageUrl varchar(512),
accessToken varchar(255) not null,
secret varchar(255),
refreshToken varchar(255),
expireTime bigint,
primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);