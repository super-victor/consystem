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
insert into user(account,username, password, phone, email,isforbid,isadmin,avatarurl)
values ('zhangsan','zhangsan','123456','12345678998','12346@136.com',0,1,'D:\Learning Materials\web开发\consystem\src\main\resources\static\src=http---diy.qqjay.com-u2-2014-1012-ed029d9a21f2b0819820b809a2464e55.jpg&refer=http---diy.qqjay.jpg');
insert into user(account,username, password, phone, email,isforbid,isadmin,avatarurl)
values ('lisi','lisi','66666','56565645615','5456446@qq.com',1,'D:\Learning Materials\web开发\consystem\src\main\resources\static\src=http---diy.qqjay.com-u2-2014-1012-ed029d9a21f2b0819820b809a2464e55.jpg&refer=http---diy.qqjay.jpg');

#会议表
drop table if exists meeting;
create table meeting(
  mid int auto_increment not null comment '会议Id',
  uid int not null comment '创建者Id',
  username varchar(255) not null  comment '创建者姓名',
  name varchar(255) not null comment '会议名称',
  theme varchar(255) not null comment '会议主题',
  remark varchar(255)  comment '备注',
  starttime timestamp not null comment '会议开始时间',
  endtime timestamp not null comment '会议结束时间',
  creattime timestamp default current_timestamp comment '会议创建时间',
  constraint pk_meeting_mid primary key (mid)
);

#参会人员表
drop table if exists attendee;
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
drop table if exists file;
create table file(
    fid int not null auto_increment comment '文件Id',
    fname varchar(255) not null comment '文件名称',
    furl varchar(255) not null comment '访问路径',
    fpath varchar(255) not null comment '存储路径',
    createtime timestamp default current_timestamp comment '创建时间',
    constraint pk_file_fid primary key(fid)
);

#聊天记录
drop table if exists chatrecord;
create table chatrecord(
    cid int not null auto_increment comment '聊天记录Id',
    mid int not null comment '会议Id',
    uid int not null  comment '使用者',
    creattime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_chartrecord_cid primary key(cid),
    constraint chatrecord_fk_meeting_mid foreign key(mid) references meeting(mid),
    constraint chatrecord_fk_user_uid foreign key(uid) references user(uid)
);

drop table if exists delphi;
create table delphi(
    did int not null auto_increment comment '德尔菲Id',
    uid int not null comment '员工id',
    dname varchar(255) not null comment 'delphi名称',
    type int not null comment '类型',
    delphia varchar(255) not null  comment 'A选项',
    delphib varchar(255) not null comment 'B选项',
    delphic varchar(255) not null comment 'C选项',
    delphid varchar(255) not null comment 'D选项',
    createtime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_delphi_did primary key (did),
    constraint delphi_fk_user_id foreign key (uid) references user(uid)
);

# 德尔菲问卷表
drop table if exists delphiqa;
create table delphiqa(
    dpid int not null auto_increment comment '问卷表id',
    dpname varchar(255) not null comment '问卷名称',
    createtime timestamp not null  default current_timestamp comment '创建时间',
    constraint pk_deliphiqa_dpid primary key (dpid)
);

# 问卷内容
drop table if exists delphiqacontent;
create table delphiqacontent(
    dqcid int not null  auto_increment comment '问卷内容Id',
    did int not null comment '德尔菲Id',
    dpid int not null  comment  '问卷表id',
    constraint pk_delphiqacontent_dpcid primary key(dqcid),
    constraint fk_delphiqa_dpid foreign key(dpid) references delphiqa(dpid),
    constraint fk_delphi_did foreign key(did) references delphi(did)
);
# 问卷回答表
drop table if exists delphiqanswer;
create table  delphiqanswer(
    daid int not null auto_increment comment '德尔菲回答结果表',
    uid int not null  comment '用户id',
    mid int not null  comment '会议Id',
    dpid int not null comment '问卷id',
    answer text not null comment '回答内容',
    createtime timestamp not null  default current_timestamp comment '创建时间',
    constraint pk_delphiqanswer_daid primary key (daid),
    constraint fk_user_uid foreign key (uid) references user(uid),
    constraint fk_meeting_mid foreign key (mid) references meeting(mid),
    constraint delphiqanswer_fk_delphiqa_dpid foreign key (dpid) references delphiqa(dpid)
);

drop table if exists swot;
create table swot(
    sid int auto_increment not null  comment 'swotid',
    swotname varchar(255) not null comment 'swot名称',
    uid int not null  comment '创建用户id',
    createtime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_swot_sid primary key (sid),
    constraint fk_swot_uid foreign key (uid) references user(uid)
);

drop table if exists swotcontent;
create table swotcontent(
    scid int auto_increment not null comment 'swot内容Id',
    sid int not null  comment 'swotid',
    type varchar(255) not null comment '类型',
    content text not null comment '内容',
    createtime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_swotcontent_scid primary key(scid),
    constraint fk_swot_sid foreign key (sid) references swot(sid)
);
drop table if exists swotanswer;
create table swotanswer(
    said int auto_increment not null comment 'swot回答ID',
    sid int not null comment 'swotid',
    mid int not null comment '会议ID',
    uid int not null  comment '用户Id',
    type varchar(255) not null comment '类型',
    content text not null comment '内容',
    constraint pk_swotanswer_said primary key (said),
    constraint fk_swotanswer_sid foreign key(sid)references swot(sid),
    constraint fk_swotanswer_mid foreign key(mid)references meeting(mid),
    constraint fk_swotanswer_uid foreign key(uid)references user(uid)
);
drop table if exists msgcontent;
create table msgcontent(
    id int not null auto_increment comment '消息Id',
    fromid int not null comment '用户id',
    mid int not null comment '会议Id',
    fromname varchar(255) comment '用户名',
    fromprofile varchar(255) comment '用户头像',
    content text not null comment '聊天内容',
    messagetypeid int not null comment '聊天类型',
    createtime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_memsgcontent_id primary key (id),
    constraint  fk_fromid foreign key (fromid) references user(uid),
    constraint fk_msgconten_mid foreign key (mid) references meeting(mid)
);

drop table if exists sunmmary;
create table sunmmary(
    id int not null auto_increment comment '纪要Id',
    mid int not null  comment '会议Id',
    content MEDIUMTEXT not null comment '纪要内容',
    createtime timestamp not null  default  CURRENT_TIMESTAMP comment '创建时间',
    constraint pk_sunmmary primary key (id),
    constraint fk_sunmmary_mid foreign key (mid) references meeting(mid)
);

drop table if exists reminder;
create table reminder(
  rid int not null auto_increment comment '提醒ID',
  uid int not null  comment '用户ID',
  title varchar(255) comment '标题',
  content text not null comment '内容',
  createtime timestamp not null default current_timestamp comment '创建时间',
  constraint pk_reminder primary key (rid),
  constraint fk_reminder_uid foreign key(uid)references user(uid)
);

drop table if exists hypoth;
create table hypoth(
    hid int not null auto_increment comment '假设Id',
    uid int not null  comment '用户Id',
    hname varchar(255) not null comment '假设名称',
    hypoth text not null comment '假设内容',
    evidence text not null comment '假设证据',
    createtime timestamp not null default current_timestamp comment '创建时间',
    constraint pk_hypoth primary key (hid),
    constraint fk_hypoth_uid foreign key(uid)references user(uid)
);

drop table if exists hypothanswer;
create table hypothanswer(
    haid int not null auto_increment comment '假设回答Id',
    hid int not null comment '假设Id',
    mid int not null comment '会议Id',
    uid int not null  comment '用户Id',
    answer int not null comment '回答',
    constraint pk_hypothanswer_haid primary key (haid),
    constraint fk_hypothanswer_mid foreign key(mid) references meeting(mid),
    constraint fk_hyporhanswer_hid foreign key(hid)references hypoth(hid),
    constraint fk_hypothanswer_uid foreign key(uid)references user(uid)
);

