# Flyway数据库迁移使用规范

## 当前配置

项目已在 `zzyl-admin` 启动模块引入 Flyway，迁移目录为：
```text
zzyl-admin/src/main/resources/db/migration
```

应用启动时，Spring Boot 会自动扫描 `classpath:db/migration` 并执行尚未执行过的迁移脚本。执行记录保存在数据库表 `flyway_schema_history`。

本项目当前使用已有数据库，因此配置了：

```yaml
spring:
  flyway:
    baseline-on-migrate: true
    baseline-version: "0"
```

含义：

- 对已经存在且没有 Flyway 记录表的数据库，首次启动会把当前库标记为基线版本 `0`。
- 后续新增的 `V1`、`V20260422.001` 等版本脚本会继续正常执行。
- 不要把已经在线上或共享库手工执行过的历史 SQL 再作为下一个版本脚本直接放入迁移目录，否则可能重复建表或重复改字段。

## 脚本命名

优先使用日期加流水号：

```text
VYYYYMMDD.NNN__description.sql
```

示例：

```text
V20260422.001__add_elder_health_index.sql
V20260422.002__create_device_alarm_table.sql
V20260423.001__alter_check_in_contract_no.sql
```

命名规则：

- `V` 表示版本化迁移。
- 版本号必须递增，不要复用已经提交或执行过的版本号。
- 版本号和描述之间必须是两个下划线 `__`。
- 描述使用英文小写和下划线，便于跨平台和日志检索。
- 一个脚本只处理一个明确的业务变化。

## 版本化迁移

版本化迁移用于表结构、索引、字典数据、必要初始化数据等变更。

常见内容：

```sql
ALTER TABLE elder ADD COLUMN health_status varchar(32) DEFAULT NULL COMMENT '健康状态';

CREATE INDEX idx_elder_health_status ON elder (health_status);
```

规范：

- 已经在任何共享环境执行过的 `V` 脚本禁止修改、重命名、删除。
- 如果脚本写错但已经执行过，新增一个更高版本脚本修正。
- 不直接手工修改数据库结构，所有结构变化都通过迁移脚本进入代码仓库。
- MySQL DDL 通常会隐式提交，提交前必须在本地或测试库验证脚本可执行。
- 涉及大表、锁表、数据修复的脚本，需要提前评估执行时间和回滚方案。

## Repeatable迁移

Repeatable 脚本命名：

```text
R__description.sql
```

适合内容：

- 视图
- 存储过程
- 函数
- 可重复重建的报表对象

注意：

- `R` 脚本内容变更后会再次执行。
- 不要用 `R` 脚本做普通表结构升级或一次性数据修复。

## 提交流程

1. 在 `zzyl-admin/src/main/resources/db/migration` 新增迁移脚本。
2. 本地启动 `zzyl-admin`，确认 Flyway 日志显示迁移成功。
3. 检查数据库中的 `flyway_schema_history`，确认版本号、描述、状态正确。
4. 将业务代码和迁移脚本一起提交，避免代码依赖的字段还没有进入数据库。
5. 生产环境发布前先备份数据库，确认迁移脚本已经在测试环境跑过。

## 常用启动方式

开发环境可以直接启动应用触发迁移：

```bash
mvn -pl zzyl-admin -am spring-boot:run
```

或打包后启动：

```bash
mvn -pl zzyl-admin -am clean package -DskipTests
java -jar zzyl-admin/target/zzyl-admin.jar
```

## 故障处理

- `Validate failed`：通常是已经执行过的脚本被修改了。优先恢复脚本原内容，然后新增更高版本脚本修正。
- `Found non-empty schema without schema history table`：说明已有库未被 Flyway 接管。本项目已启用 `baseline-on-migrate`，正常首次启动会自动建立基线。
- 迁移执行失败：修复 SQL 后重新启动应用。若失败脚本已经部分执行，需要先人工确认数据库实际状态，再决定修正脚本或手工回滚。
- 不要随意执行 `repair` 或删除 `flyway_schema_history`，除非明确知道每个环境的数据库状态。
