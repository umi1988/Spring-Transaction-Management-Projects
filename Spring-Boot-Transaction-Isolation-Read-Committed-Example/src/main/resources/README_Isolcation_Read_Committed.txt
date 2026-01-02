Spring-Boot-Transaction-Isolation-Read-Committed-Example-:


No DB updated as we are checking only committed values

2026-01-02T17:37:20.458+05:30  INFO 37559 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T17:37:20.458+05:30  INFO 37559 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T17:37:20.460+05:30  INFO 37559 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
2026-01-02T17:37:20.518+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.updateStock]: PROPAGATION_REQUIRED,ISOLATION_READ_COMMITTED
2026-01-02T17:37:20.519+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1774576047<open>)] for JPA transaction
2026-01-02T17:37:20.526+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@1b1b4de9]
2026-01-02T17:37:20.528+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1774576047<open>)] for JPA transaction
2026-01-02T17:37:20.528+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T17:37:20.568+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1774576047<open>)] for JPA transaction
2026-01-02T17:37:20.568+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction A: Stock updated to 5
2026-01-02T17:37:22.521+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.ProductService.checkStock]: PROPAGATION_REQUIRED,ISOLATION_READ_COMMITTED
2026-01-02T17:37:22.522+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(41097422<open>)] for JPA transaction
2026-01-02T17:37:22.526+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@cb4e6f0]
2026-01-02T17:37:22.528+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(41097422<open>)] for JPA transaction
2026-01-02T17:37:22.529+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Transaction B: Read stock as 50
2026-01-02T17:37:22.534+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T17:37:22.535+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(41097422<open>)]
2026-01-02T17:37:22.543+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-2] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
Stock read by Transaction B: 50
Transaction A: Committed the update
2026-01-02T17:37:25.595+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T17:37:25.595+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1774576047<open>)]
2026-01-02T17:37:25.600+05:30 DEBUG 37559 --- [Spring-Boot-Transaction-Example] [       Thread-1] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
