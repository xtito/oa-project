
# A
SELECT * FROM act_evt_log;

# 部署与流程定义-资源文件表
# 存储流程定义相关的部署信息，即流程定义文档的存放地。每部署一次就会增加两条记录，一条是关于bpmn规则文件的，
# 一条是图片的（如果部署时只指定了bpmn一个文件，activiti会在部署时解析bpmn文件内容自动生成流程图）。
# 两个文件不是很大，都是以二进制形式存储在数据库中
SELECT * FROM act_ge_bytearray;

# 部署与流程定义-主键生成策略表
SELECT * FROM act_ge_property;

# 流程实例、对象-所有活动节点的历史表（包括UserTask和开始、结束节点等，所有活动的节点）
# 所有活动节点的
SELECT * FROM act_hi_actinst;

# A
SELECT * FROM act_hi_attachment;

# A
SELECT * FROM act_hi_comment;

# A
SELECT * FROM act_hi_detail;

# 组任务-历史的人员表
SELECT * FROM act_hi_identitylink;

# 流程实例、对象-流程实例历史表，与act_ru_execution正好对应
SELECT * FROM act_hi_procinst;

# 流程实例、对象-任务历史表
# 只有节点是UserTask的时候，该表中存在数据
SELECT * FROM act_hi_taskinst;

# 流程变量-历史的流程变量表，与act_ru_variable正好对应
SELECT * FROM act_hi_varinst;

# 组织结构-工作流中的角色表
SELECT * FROM act_id_group;

# A
SELECT * FROM act_id_info;

# 组织结构-中间表，关联关系表
SELECT * FROM act_id_membership;

# 组织结构-工作流中的用户表
SELECT * FROM act_id_user;

# A
SELECT * FROM act_procdef_info;

# 部署与流程定义-部署对象表
# 存放流程定义的显示名和部署时间，每部署一次增加一条记录
# 部署数据表，一次部署可以添加多个资源，资源会被保存到资源表（act_ge_bytearray）中；而部署的信息，则保存到部署表中
SELECT * FROM act_re_deployment;

# A
SELECT * FROM act_re_model;

# 部署与流程定义-流程定义表
# 流程定义的属性信息，部署每个新的流程定义都会在这线表中增加一条记录。注意：当流程定义的key相同的情况下，使用的是版本升级
# 如果发布部署的文件是流程文件，除了将内容保存到资源表外，还会解析流程文件的内容，形成特定的流程定义数据，保存到此表中
SELECT * FROM act_re_procdef;

# A
SELECT * FROM act_ru_event_subscr;

# 流程实例、对象-正在执行的执行对象表
# 当流程启动后，会产生一个流程实例，同时会产生相应的执行流，那么流程实例和执行流数据均会被保存到act_ru_execution表中
SELECT * FROM act_ru_execution;

# 组任务-正在执行的组任务表
SELECT * FROM act_ru_identitylink;

# A
SELECT * FROM act_ru_job;

# 流程实例、对象-正在执行的任务表
# 只有节点是UserTask的时候，该表中存在数据
SELECT * FROM act_ru_task;

# 流程变量-正在执行的流程变量表，用来保存在整个流程执行过程中用到的变量信息
SELECT * FROM act_ru_variable;
