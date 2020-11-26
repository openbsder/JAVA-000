-- t_account - 用户表
drop table if exists t_account;
create table t_account
(
    id               bigint unsigned        not null comment '编号',
    account_name     varchar(90)            not null comment '账户名称',
    account_password varchar(255)           not null comment '账户密码',
    display_name     varchar(128)           not null comment '显示名称',
    email            varchar(128)           not null comment 'Email',
    phone_number     varchar(15)            not null comment '手机号',
    country          char(2)                not null comment 'country code(iso 3166-1, 2-letters)',
    gender           char                   not null comment '性别，M-男, F-女, N-中性, U-未知',
    birth_date       bigint unsigned        not null comment '出生日期',
    is_enabled       tinyint(1) unsigned    not null comment '是否启用',
    created_at       bigint unsigned        not null comment '创建时间',
    last_modified_at bigint unsigned        not null comment '最后修改时间',
    rvn              int unsigned default 1 not null comment '版本号',
    constraint uk_t_account_account_name unique (account_name),
    constraint uk_t_account_display_name unique (display_name),
    constraint uk_t_account_id_id unique (id)
) comment '用户表';

-- t_product - 产品表
drop table if exists t_product;
create table t_product
(
    id               bigint unsigned                not null comment '产品编号',
    category_id      bigint unsigned                not null comment '产品分类id',
    title            varchar(255)                   not null comment '产品标题',
    product_type     varchar(30)                    not null comment '产品类型',
    brand            varchar(128)        default '' not null comment '产品品牌',
    product_status   char(6)                        not null comment '产品状态',
    is_deleted       tinyint(1) unsigned default 0  not null comment '是否删除',
    created_at       bigint unsigned                not null comment '创建时间',
    last_modified_at bigint unsigned                null comment '最后修改时间',
    rvn              int unsigned        default 1  not null comment '版本号',
    constraint uk_t_product_id unique (id)
) comment '产品表';

create index idx_t_product_category_id on t_product (category_id);

-- t_product_specs - 商品规格表
drop table if exists t_product_specs;
create table t_product_specs
(
    id                  bigint unsigned                not null comment '编号',
    product_id          bigint unsigned                not null comment '产品编号',
    title               varchar(255)                   not null comment '商品标题',
    sub_title           varchar(255)        default '' not null comment '商品副标题',
    image_url           varchar(255)        default '' not null comment '商品图片',
    product_stock       int unsigned        default 0  not null comment '库存量',
    product_spec_status char(6)                        not null comment '商品状态',
    is_deleted          tinyint(1) unsigned default 0  not null comment '是否删除',
    created_at          bigint unsigned                not null comment '创建时间',
    last_modified_at    bigint unsigned                null comment '最后修改时间',
    rvn                 int unsigned        default 1  not null comment '版本号',
    constraint uk_t_product_specs_id unique (id)
) comment '商品规格表';

create index idx_t_product_specs_product_id on t_product_specs (product_id);

-- t_product_specs_detail - 商品详情表
drop table if exists t_product_specs_detail;
create table t_product_specs_detail
(
    id                bigint unsigned          not null comment '编号',
    product_spec_id   bigint unsigned          not null comment '商品编号',
    attributes        varchar(5000) default '' null comment '商品属性',
    product_spec_desc varchar(5000) default '' not null comment '商品详情',
    created_at        bigint unsigned          not null comment '创建时间',
    last_modified_at  bigint unsigned          null comment '最后修改时间',
    rvn               int unsigned  default 1  not null comment '版本号',
    constraint uk_t_order_detail_id unique (id)
) comment '商品详情表';

create index idx_t_product_specs_detail_product_spec_id on t_product_specs_detail (product_spec_id);


-- t_product_specs_price - 商品价格表
drop table if exists t_product_specs_price;
create table t_product_specs_price
(
    id                 bigint unsigned        not null comment '编号',
    product_spec_id    bigint unsigned        not null comment '商品编号',
    product_spec_price int unsigned           not null comment '商品价格',
    currency           char(3)                not null comment '货币类型',
    is_deleted         tinyint(1)   default 0 not null comment '是否删除',
    created_at         bigint unsigned        not null comment '创建时间',
    last_modified_at   bigint unsigned        null comment '最后修改时间',
    rvn                int unsigned default 1 not null comment '版本号',
    constraint uk_t_product_specs_price_id unique (id)
) comment '商品价格表';

create index idx_t_product_specs_price_product_id on t_product_specs_price (product_spec_id);

-- t_order - 订单表
drop table if exists t_order;
create table t_order
(
    id                 bigint unsigned                not null comment '订单编号',
    parent_id          bigint unsigned                not null comment '父级订单编号',
    account_id         bigint unsigned                not null comment '用户编号',
    order_type_id      char(6)                        not null comment '订单类型',
    order_status       char(6)                        not null comment '订单状态',
    country            char(2)                        not null comment '下单国家',
    currency           char(3)                        not null comment '货币类型',
    total_price        int unsigned                   not null comment '总金额',
    total_tax          int unsigned                   not null comment '总税金',
    total_discounted   int unsigned                   not null comment '总折扣后金额',
    started_at         bigint unsigned                not null comment '下单时间',
    completed_at       bigint unsigned                null comment '订单完成时间',
    refund_reason_code char(6)             default '' not null comment '订单退款原因',
    comments           varchar(255)        default '' not null comment '备注',
    is_deleted         tinyint(1) unsigned default 0  not null comment '是否删除',
    created_at         bigint unsigned                not null comment '创建时间',
    last_modified_at   bigint unsigned                null comment '最后修改时间',
    rvn                int                 default 1  not null comment '版本号',
    constraint uk_t_order_id unique (id)
) comment '订单表';

create index idx_t_order_account_id on t_order (account_id);

-- t_order_snapshot - 订单快照表
drop table if exists t_order_snapshot;
create table t_order_snapshot
(
    id                      bigint unsigned               not null comment '订单编号',
    order_id                bigint unsigned               not null comment '订单编号',
    product_category        varchar(128)                  not null comment '产品分类名称',
    product_type            varchar(30)                   not null comment '产品类型',
    product_brand           varchar(128)                  not null comment '产品品牌',
    product_spec_id         bigint unsigned               not null comment '商品编号',
    product_spec_price      int unsigned                  not null comment '商品价格',
    currency                char(3)                       not null comment '货币类型',
    product_spec_title      varchar(255)                  not null comment '商品标题',
    product_spec_sub_title  varchar(255)                  not null comment '商品副标题',
    product_spec_image_url  varchar(255)                  not null comment '商品图片',
    is_deleted              tinyint(1) unsigned default 0 not null comment '是否删除',
    created_at              bigint unsigned               not null comment '创建时间',
    last_modified_at        bigint unsigned               null comment '最后修改时间',
    rvn                     int                 default 1 not null comment '版本号',
    constraint uk_t_order_snapshot_id unique (id)
) comment '订单表';

create index idx_t_order_snapshot_order_id on t_order_snapshot (order_id);

-- t_category - 分类表
drop table if exists t_category;
create table t_category
(
    id               bigint unsigned                 not null comment '编号',
    parent_id        bigint unsigned                 not null comment '父编号',
    name             varchar(128)                    not null comment '分类名称',
    category_desc    varchar(255)        default ''  not null comment '分类描述',
    image_url        varchar(255)        default ''  not null comment '分类图片',
    display_order    int unsigned        default 100 not null comment '显示顺序',
    is_display       tinyint(1) unsigned default 1   not null comment '是否显示',
    is_enabled       tinyint(1) unsigned default 1   not null comment '是否启用',
    is_deleted       tinyint(1)          default 0   not null comment '是否删除',
    created_at       bigint unsigned                 not null comment '创建时间',
    last_modified_at bigint unsigned                 null comment '最后修改时间',
    rvn              int unsigned        default 1   not null comment '版本号',
    constraint uk_t_category_id unique (id)
) comment '分类表';

-- t_attribute_key - 属性key表
drop table if exists t_attribute_key;
create table t_attribute_key
(
    id               bigint unsigned        not null comment '编号',
    category_id      bigint unsigned        not null comment '分类编号',
    name             varchar(128)           not null comment '属性名称',
    is_deleted       tinyint(1)   default 0 not null comment '是否删除',
    created_at       bigint unsigned        not null comment '创建时间',
    last_modified_at bigint unsigned        null comment '最后修改时间',
    rvn              int unsigned default 1 not null comment '版本号',
    constraint uk_t_attribute_key_id unique (id)
) comment '属性key表';

create index idx_t_attribute_key_category_id on t_attribute_key (category_id);

-- t_attribute_value - 属性value表
drop table if exists t_attribute_value;
create table t_attribute_value
(
    id               bigint unsigned        not null comment '编号',
    attribute_key_id bigint unsigned        not null comment '属性编号',
    attribute_value  varchar(255)           not null comment '属性值',
    is_deleted       tinyint(1)   default 0 not null comment '是否删除',
    created_at       bigint unsigned        not null comment '创建时间',
    last_modified_at bigint unsigned        null comment '最后修改时间',
    rvn              int unsigned default 1 not null comment '版本号',
    constraint uk_t_attribute_value_id unique (id)
) comment '属性value表';

create index idx_t_attribute_value_attribute_key_id on t_attribute_value (attribute_key_id);
