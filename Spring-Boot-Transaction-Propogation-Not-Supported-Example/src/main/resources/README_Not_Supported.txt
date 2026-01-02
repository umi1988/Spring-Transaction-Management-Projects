Spring-Boot-Transaction-Propogation-Not-Supported-Example-:


Happy scenario-

{
  "id": 108,
  "productId": 3,
  "quantity": 1
}

2026-01-02T14:49:48.281+05:30  INFO 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-01-02T14:49:48.282+05:30  INFO 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-01-02T14:49:48.283+05:30  INFO 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2026-01-02T14:49:48.379+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.379+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T14:49:48.387+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@5814b217]
2026-01-02T14:49:48.389+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.389+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.428+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.428+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.428+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.428+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.438+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T14:49:48.439+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(581511625<open>)] for JPA transaction
2026-01-02T14:49:48.441+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@5290804a]
2026-01-02T14:49:48.442+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(581511625<open>)] for JPA transaction
2026-01-02T14:49:48.442+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:49:48.457+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:49:48.457+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(581511625<open>)]
2026-01-02T14:49:48.469+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T14:49:48.469+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T14:49:48.470+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1741833007<open>)] for JPA transaction
2026-01-02T14:49:48.470+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction
Recommendations fetched for customer 
2026-01-02T14:49:48.470+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T14:49:48.470+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:49:48.470+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1741833007<open>)]
2026-01-02T14:49:48.481+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-1] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction



failed scenario-

{
  "id": 109,
  "productId": 1,
  "quantity": 1
}


2026-01-02T14:51:24.381+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.382+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.starttohkar.service.OrderProcessingService.placeAnOrder]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-01-02T14:51:24.388+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@5399e32f]
2026-01-02T14:51:24.389+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.389+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:51:24.400+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.400+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:51:24.400+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.400+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:51:24.405+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.406+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:51:24.408+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating transaction failed - marking existing transaction as rollback-only
2026-01-02T14:51:24.408+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Setting JPA transaction on EntityManager [SessionImpl(1566443252<open>)] rollback-only
2026-01-02T14:51:24.409+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.409+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction, creating new transaction with name [com.starttohkar.handler.AuditLogHandler.logAuditDetails]
2026-01-02T14:51:24.409+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(1282768044<open>)] for JPA transaction
2026-01-02T14:51:24.412+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@e09b390]
2026-01-02T14:51:24.413+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1282768044<open>)] for JPA transaction
2026-01-02T14:51:24.413+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-01-02T14:51:24.419+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:51:24.419+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1282768044<open>)]
2026-01-02T14:51:24.424+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(1566443252<open>)] for JPA transaction
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Suspending current transaction
Recommendations fetched for customer 
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Resuming suspended transaction after completion of inner transaction
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2026-01-02T14:51:24.425+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [SessionImpl(1566443252<open>)]
2026-01-02T14:51:24.432+05:30 DEBUG 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
2026-01-02T14:51:24.436+05:30 ERROR 29852 --- [Spring-Boot-Transaction-Example] [nio-9191-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only] with root cause

org.springframework.transaction.UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:803) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:757) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:684) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:406) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118) ~[spring-tx-7.0.2.jar:7.0.2]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-7.0.2.jar:7.0.2]
	at org.spring