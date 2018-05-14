
# A
SELECT * FROM act_evt_log;

# 资源文件表
# 存储流程定义相关的部署信息，即流程定义文档的存放地。每部署一次就会增加两条记录，一条是关于bpmn规则文件的，
# 一条是图片的（如果部署时只指定了bpmn一个文件，activiti会在部署时解析bpmn文件内容自动生成流程图）。
# 两个文件不是很大，都是以二进制形式存储在数据库中
SELECT * FROM act_ge_bytearray;

# 主键生成策略表
SELECT * FROM act_ge_property;

# 所有活动节点的历史表（包括UserTask和开始、结束节点等，所有活动的节点）
# 所有活动节点的
SELECT * FROM act_hi_actinst;

# A
SELECT * FROM act_hi_attachment;

# A
SELECT * FROM act_hi_comment;

# A
SELECT * FROM act_hi_detail;

# A
SELECT * FROM act_hi_identitylink;

# 流程实例历史表
SELECT * FROM act_hi_procinst;

# 任务历史表
# 只有节点是UserTask的时候，该表中存在数据
SELECT * FROM act_hi_taskinst;

# 历史的流程变量表
SELECT * FROM act_hi_varinst;

# A
SELECT * FROM act_id_group;

# A
SELECT * FROM act_id_info;

# A
SELECT * FROM act_id_membership;

# A
SELECT * FROM act_id_user;

# A
SELECT * FROM act_procdef_info;

# 部署对象表
# 存放流程定义的显示名和部署时间，每部署一次增加一条记录
SELECT * FROM act_re_deployment;

# A
SELECT * FROM act_re_model;

# 流程定义表
# 流程定义的属性信息，部署每个新的流程定义都会在这线表中增加一条记录。注意：当流程定义的key相同的情况下，使用的是版本升级
SELECT * FROM act_re_procdef;

# A
SELECT * FROM act_ru_event_subscr;

# 正在执行的执行对象表
SELECT * FROM act_ru_execution;

# A
SELECT * FROM act_ru_identitylink;

# A
SELECT * FROM act_ru_job;

# 正在执行的任务表
# 只有节点是UserTask的时候，该表中存在数据
SELECT * FROM act_ru_task;

# 正在执行的流程变量表
SELECT * FROM act_ru_variable;
