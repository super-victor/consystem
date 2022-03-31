drop table if exists chatrecord;
drop table if exists attendee;
drop table if exists User;
drop table if exists  log;
drop table if exists file;
# create table Department (
#     departmentId int auto_increment comment '部门id',
#     name varchar(50) not null comment '部门名称',
#     phone char(11) not null comment '部门电话',
#     createTime timestamp default current_timestamp comment '字段创建时间',
#     updateTime timestamp on update current_timestamp comment '字段修改时间',
#     constraint pk_Department_departmentId primary key(departmentId),
#     constraint unique_Department_name unique key(name)
# );
#用户表
create table User(
    uid int auto_increment not null comment '用户ID',
    account varchar(255) comment '账号',
    username varchar(155) comment '用户名',
    password varchar(255) comment '密码',
    phone varchar(20) comment '电话',
    email varchar(50) comment '邮箱',
    isforbid  boolean comment '是否禁用',
    isadmin boolean comment '是否是管理员',
    avatarurl varchar(255) comment '头像地址',
    constraint User_userid primary key (uid)
);
insert into user(account,username, password, phone, email,isforbid) values ('zhangsan','zhangsan','123456','12345678998','12346@136.com',0);
insert into user(account,username, password, phone, email,isforbid) values ('lisi','lisi','66666','56565645615','5456446@qq.com',1);

#会议表
drop table if exists meeting;
create table meeting(
  mid int auto_increment not null comment '会议Id',
  name varchar(255) not null comment '会议名称',
  theme varchar(255) not null comment '会议主题',
  remark varchar(255)  comment '备注',
  starttime timestamp not null comment '会议开始时间',
  endtime timestamp not null comment '会议结束时间',
  creattime timestamp default current_timestamp comment '会议创建时间',
  constraint pk_meeting_mid primary key (mid)
);

#参会人员表
create table attendee(
    aid int auto_increment not null comment '参会人员表Id',
    uid int not null comment '参会人员Id',
    mid int not null  comment '会议Id',
    constraint pk_attendee_aid primary key (aid),
    constraint attendee_fk_user_uid foreign key (uid) references User(uid),
    constraint attendee_fk_meeting_mid foreign key (mid) references meeting(mid)
);

#日志表
drop table if exists log;
create table log(
    lid int not null auto_increment comment '日志id',
    level varchar(45) not null,
    type varchar(20) comment '类型',
    content text comment '内容',
    creattime timestamp default current_timestamp comment '创建时间',
    constraint pk_log_lid primary key(lid)
);
#文件表
create table file(
    fid int not null auto_increment comment '文件Id',
    fname varchar(255) not null comment '文件名称',
    furl varchar(255) not null comment '保存路径',
    creattime timestamp default current_timestamp comment '创建时间',
    constraint pk_file_fid primary key(fid)
);

#聊天记录
create table chatrecord(
    cid int not null auto_increment comment '聊天记录Id',
    mid int not null comment '会议Id',
    uid int not null  comment '使用者',
    creattime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_chartrecord_cid primary key(cid),
    constraint chatrecord_fk_meeting_mid foreign key(mid) references meeting(mid),
    constraint chatrecord_fk_user_uid foreign key(uid) references user(uid)
);