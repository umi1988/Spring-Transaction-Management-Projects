

Before and after read should match
Transaction B: First read stock as 60
Transaction A: Stock updated to 5
Transaction B: Second read stock as 60



2026-01-02T18:02:23.563+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.fetchStock]: PROPAGATION_REQUIRED,ISOLATION_REPEATABLE_READ
2026-01-02T18:02:23.563+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.updateStock]: PROPAGATION_REQUIRED,ISOLATION_REPEATABLE_READ
2026-01-02T18:02:23.565+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1122328020<open>)] for JPA transaction
2026-01-02T18:02:23.565+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1591277642<open>)] for JPA transaction
2026-01-02T18:02:23.574+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@501f0de1]
2026-01-02T18:02:23.574+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@481d36a4]
2026-01-02T18:02:23.575+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1591277642<open>)] for JPA transaction
2026-01-02T18:02:23.575+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1122328020<open>)] for JPA transaction
2026-01-02T18:02:23.575+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T18:02:23.575+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction B: First read stock as 60
2026-01-02T18:02:23.585+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1122328020<open>)] for JPA transaction
2026-01-02T18:02:23.585+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction A: Stock updated to 5
2026-01-02T18:02:26.588+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1591277642<open>)] for JPA transaction
2026-01-02T18:02:26.589+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction B: Second read stock as 60
2026-01-02T18:02:26.590+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T18:02:26.590+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1591277642<open>)]
2026-01-02T18:02:26.597+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-4] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
Transaction A: Committed the update
2026-01-02T18:02:28.623+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T18:02:28.624+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1122328020<open>)]
2026-01-02T18:02:28.631+05:30 DEBUG 38787 --- [Spring-Boot-Transaction-Example] [       Thread-3] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
