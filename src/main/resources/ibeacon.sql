create database ibeacon;
use ibeacon;

/*用户表*/
create table t_user (
	id integer unsigned not null auto_increment,
	subscribe integer unsigned default 0, /*用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。*/
	openid varchar(255) not null, /*用户的标识，对当前公众号唯一*/
	nickname varchar(255), /*用户的昵称*/
	sex integer unsigned default 0,/*用户的性别，值为1时是男性，值为2时是女性，值为0时是未知*/
	language varchar(10),/*用户的语言，简体中文为zh_cn*/
	city varchar(255),/*用户所在城市*/
	country varchar(255),/*用户所在国家*/
	province varchar(255),/*用户所在省份*/
	headimgurl varchar(255),/*用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像url将失效。*/
	subscribe_time integer unsigned,/*用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间*/
	unionid varchar(255), /*只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。*/
	remark varchar(255), /*公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注*/
	groupid varchar(255), /*用户所在的分组id（兼容旧的用户分组接口）*/
	tagid_list varchar(255),/*用户被打上的标签id列表*/
	amount float not null default 0,/*账户余额*/
	primary key (id)
) engine=innodb default charset=utf8;

/*设备表*/
create table t_device (
	id integer unsigned not null auto_increment,
	device_id integer unsigned,/*设备编号，若填了uuid、major、minor，则可不填设备编号，若二者都填，则以设备编号为优先*/
	uuid varchar(255),/*uuid、major、minor，三个信息需填写完整，若填了设备编号，则可不填此信息*/
	major integer unsigned,
	minor integer unsigned,
	last_active_time integer unsigned,/*设备最近一次被摇到的日期（最早只能获取前一天的数据）；新申请的设备该字段值为0*/
	poi_appid varchar(255),/*若配置了设备与其他公众账号门店关联关系，则返回配置门店归属的公众账号appid。*/
	poi_id integer unsigned,/*设备关联的门店id，关联门店后，在门店1km的范围内有优先摇出信息的机会。当值为0时，将清除设备已关联的门店id。*/
	comment varchar(255),/*设备的备注信息*/
	status integer unsigned not null default 0,/*激活状态，0：未激活，1：已激活*/
	primary key (id)
) engine=innodb default charset=utf8;

/*红包表*/
create table t_packet (
	id integer unsigned not null auto_increment,
	amount_total float not null,/*红包总金额，最低0.01*/
	amount_left float not null,/*红包剩余金额*/
	num_total integer unsigned not null,/*红包总个数*/
	num_left integer unsigned not null,/*红包剩余个数*/
	type integer unsigned not null,/*红包类型，1：固定金额红包，2：随机金额红包*/
	title varchar(255) not null,/*活动标题*/
	description varchar(255) not null,/*活动描述*/
	pic_url varchar(255),/*活动图片，可选*/
	start_time integer unsigned,/*活动开始时间*/
	end_time integer unsigned,/*活动结束时间*/
	create_time integer unsigned,/*活动创建时间*/
	status integer unsigned not null default 1,/*活动状态，0：结束，1：可用*/
	primary key (id)
) engine=innodb default charset=utf8;

/*红包和设备的绑定关系*/
create table bind_packet_device (
	id integer unsigned not null auto_increment,
	packet_id integer unsigned not null,
	device_id  integer unsigned not null,
	time integer unsigned,
	primary key (id)
) engine=innodb default charset=utf8;

/*用户和红包的绑定关系，即抢红包表*/
create table bind_user_packet (
	id integer unsigned not null auto_increment,
	user_id  integer unsigned not null,
	packet_id integer unsigned not null,
	amount float not null,/*红包金额，最低0.01*/
	time integer unsigned,
	primary key (id)
) engine=innodb default charset=utf8;