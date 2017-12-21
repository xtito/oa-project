SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键ID',
  login_name varchar(50) DEFAULT NULL COMMENT '登录名',
  nickname varchar(100) DEFAULT NULL COMMENT '用户昵称',
  email varchar(100) DEFAULT NULL COMMENT 'Email地址',
  password_ varchar(50) DEFAULT NULL COMMENT '用户密码',
  phone varchar(20) DEFAULT NULL COMMENT '手机号',
  department_id varchar(20) DEFAULT NULL COMMENT '所属部门ID',
  status_ int(10) DEFAULT '0' COMMENT '用户状态（0禁止登录，1正常，2锁定）',
  lock_time datetime DEFAULT NULL COMMENT '锁定时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  last_login_time datetime DEFAULT NULL COMMENT '最后登录时间',
  user_type varchar(20) DEFAULT NULL COMMENT '用户类型ID',
  description varchar(300) DEFAULT NULL COMMENT '用户描述',
  def_identify int(10) DEFAULT '0' COMMENT '默认标识（0可删1不可删除）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键ID',
  name_ varchar(64) DEFAULT NULL COMMENT '角色名称',
  description varchar(255) DEFAULT NULL COMMENT '角色描述',
  role_type varchar(20) DEFAULT NULL COMMENT '角色类型',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  def_identify int(10) DEFAULT '0' COMMENT '默认标识（0可删1不可删除）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限主键ID',
  name_ varchar(64) DEFAULT NULL COMMENT '权限名称',
  url varchar(255) DEFAULT NULL COMMENT '权限URL',
  description varchar(255) DEFAULT NULL COMMENT '权限描述',
  uuid varchar(64) DEFAULT NULL COMMENT 'UUID用于树分级',
  pms_level int(10) DEFAULT NULL COMMENT '权限级别',
  pid varchar(64) DEFAULT NULL COMMENT '父级权限ID，也就是父级权限的UUID',
  icon varchar(255) DEFAULT NULL COMMENT '权限树图标路径',
  order_by_id int(10) DEFAULT NULL COMMENT '排序ID',
  pms_type int(10) DEFAULT NULL COMMENT '权限类型',
  def_identify int(10) DEFAULT '0' COMMENT '默认标识（0可删1不可删除）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联主键ID',
  user_id varchar(64) DEFAULT NULL COMMENT '用户主键ID',
  role_id varchar(64) DEFAULT NULL COMMENT '角色主键ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关联关系表';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关联主键ID',
  role_id varchar(64) DEFAULT NULL COMMENT '角色主键ID',
  pms_id varchar(64) DEFAULT NULL COMMENT '权限主键ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与系统权限关联关系表';


DROP TABLE IF EXISTS sys_department;
CREATE TABLE sys_department (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门主键ID',
  name_ varchar(50) DEFAULT NULL COMMENT '部门名称',
  level_ int(10) DEFAULT NULL COMMENT '部门级别',
  parent_id bigint(20) DEFAULT NULL COMMENT '上级所属部门ID',
  description varchar(300) DEFAULT NULL COMMENT '部门描述',
  correlation_code varchar(800) DEFAULT NULL COMMENT '部门关联关系描述ID拼接',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  def_identify int(10) DEFAULT '0' COMMENT '默认标识（0可删1不可删除）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

