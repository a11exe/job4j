## Hibernate

+ [1. Что такое ORM?](#1-Что-такое-ORM)
+ [2. Опиши, как конфигурируется Hibernate. Рассказать про hibernate.cfg.xml и про mapping.](#2-Опиши-как-конфигурируется-Hibernate-Рассказать-про-hibernatecfgxml-и-про-mapping)
+ [3. Жизненный цикл Entiity.](#3-Жизненный-цикл-Entiity)
+ [4. Зачем нужен класс SessionFactory? Является ли он потокобезопасным?](#4-Зачем-нужен-класс-SessionFactory-Является-ли-он-потокобезопасным)
+ [5. Зачем нужен класс Session? Является ли он потокобезопасным?](#5-Зачем-нужен-класс-Session-Является-ли-он-потокобезопасным)
+ [6. В чем отличие методов Session.get Session.load?](#6-В-чем-отличие-методов-Sessionget-Sessionload)
+ [7. Расскажите про методы flush commit.](#7-Расскажите-про-методы-flush-commit)
+ [8. В чем отличие методы save от saveOrUpdate и merge?](#8-В-чем-отличие-методы-save-от-saveOrUpdate-и-merge)
+ [9. Расскажите процесс создания, редактирования, чтения и удаления данных через Hibernate.](#9-Расскажите-процесс-создания-редактирования-чтения-и-удаления-данных-через-Hibernate)
+ [10. Как осуществляется иерархия наследования в Hibernate? Рассказать про три стратегии наследования.](#10-Как-осуществляется-иерархия-наследования-в-Hibernate-Рассказать-про-три-стратегии-наследования)
+ [11. Можно ли создать собственный тип данных?](#11-Можно-ли-создать-собственный-тип-данных)
+ [12. Какие коллекции поддерживаются на уровне mapping?](#12-Какие-коллекции-поддерживаются-на-уровне-mapping)
+ [13. Зачем нужен класс Transactional?](#13-Зачем-нужен-класс-Transactional)
+ [14. Расскажите про уровни изоляции? Какие уровни поддерживаются в hibernate? Как их устанавливать?](#14-Расскажите-про-уровни-изоляции-Какие-уровни-поддерживаются-в-hibernate-Как-их-устанавливать)
+ [15. Что такое OplimisticLock? Расскажите стратегии создания через version, timestamp.](#15-Что-такое-OplimisticLock-Расскажите-стратегии-создания-через-version-timestamp)
+ [16. Расскажите про стратегии извлечения данных urgy, lazy?](#16-Расскажите-про-стратегии-извлечения-данных-urgy-lazy)
+ [17. Что такое объект Proxy? С чем связана ошибка LazyInitializationException? Как ее избежать?](#17-Что-такое-объект-Proxy-С-чем-связана-ошибка-LazyInitializationException-Как-ее-избежать)
+ [18. HQL. Расскажи основные элементы синтаксиса HQL? Простой запрос, запрос join? Создания объекта через конструтор.](#18-HQL-Расскажи-основные-элементы-синтаксиса-HQL-Простой-запрос-запрос-join-Создания-объекта-через-конструтор)
+ [19. Расскажите про уровни кешей в hibernate?](#19-Расскажите-про-уровни-кешей-в-hibernate)
+ [20. Что такое StatelessSessionFactory? Зачем он нужен, где он используется?](#20-Что-такое-StatelessSessionFactory-Зачем-он-нужен-где-он-используется)
+ [21. Зачем нужен решим read-only?](#21-Зачем-нужен-решим-read-only)
+ [Назовите некоторые важные аннотации, используемые для отображения в Hibernate](#Назовите-некоторые-важные-аннотации-используемые-для-отображения-в-Hibernate)
+ [Как реализованы Join’ы Hibernate?](#Как-реализованы-Join’ы-Hibernate)
+ [Почему мы не должны делать Entity class как final?](#Почему-мы-не-должны-делать-Entity-class-как-final)
+ [Что такое Query Cache в Hibernate?](#Что-такое-Query-Cache-в-Hibernate)
+ [Что такое Named SQL Query?](#Что-такое-Named-SQL-Query)
+ [Каковы преимущества Named SQL Query?](#Каковы-преимущества-Named-SQL-Query)
+ [Расскажите о преимуществах использования Hibernate Criteria API.](#Расскажите-о-преимуществах-использования-Hibernate-Criteria-API)
+ [Как логировать созданные Hibernate SQL запросы в лог-файлы?](#Как-логировать-созданные-Hibernate-SQL-запросы-в-лог-файлы)
+ [Как реализованы отношения в Hibernate?](#Как-реализованы-отношения-в-Hibernate)
+ [Как управлять транзакциями с помощью Hibernate?](#Как-управлять-транзакциями-с-помощью-Hibernate)
+ [Что такое каскадные связи обновления и какие каскадные типы есть в Hibernate?](#Что-такое-каскадные-связи-обновления-и-какие-каскадные-типы-есть-в-Hibernate)
+ [Какие паттерны применяются в Hibernate?](#Какие-паттерны-применяются-в-Hibernate)
+ [Расскажите о Hibernate Validator Framework.](#Расскажите-о-Hibernate-Validator-Framework)
+ [Best Practices в Hibernate.](#Best-Practices-в-Hibernate)

## 1 Что такое ORM

ORM (англ. Object-Relational Mapping, рус. объектно-реляционное отображение, или преобразование) — технология программирования, 
которая связывает базы данных с концепциями объектно-ориентированных языков программирования, создавая «виртуальную объектную базу данных»

[к оглавлению](#Multithreading)

## 2 Опиши как конфигурируется Hibernate Рассказать про hibernatecfgxml и про mapping

Существует четыре способа конфигурации работы с Hibernate :
+ используя аннотации;
+ hibernate.cfg.xml;
+ hibernate.properties;
+ persistence.xml.
Самый частый способ конфигурации : через аннотации и файл persistence.xml, 
что касается файлов hibernate.properties и hibernate.cfg.xml, то hibernate.cfg.xml главнее (если в приложение есть оба файла, 
то принимаются настройки из файла hibernate.cfg.xml). Конфигурация аннотациями, 
хоть и удобна, но не всегда возможна, к примеру, если для разных баз данных или для разных ситуаций 
вы хотите иметь разные конфигурацию сущностей, то следует использовать xml файлы конфигураций.

Файл конфигурации Hibernate содержит в себе данные о базе данных и необходим для инициализации SessionFactory. 
В .xml файле необходимо указать вендора базы данных или JNDI ресурсы, а так же информацию об используемом диалекте, 
что поможет hibernate выбрать режим работы с конкретной базой данных.

Файл отображения (mapping file) используется для связи entity бинов и колонок в таблице базы данных. 
В случаях, когда не используются аннотации JPA, файл отображения .xml может быть полезен (например при использовании сторонних библиотек).

[к оглавлению](#Multithreading)

## 3 Жизненный цикл Entiity

+ *Transient:* состояние, при котором объект никогда не был связан с какой-либо сессией и не является персистентностью. Этот объект находится во временном состоянии. Объект в этом состоянии может стать персистентным при вызове метода save(), persist() или saveOrUpdate(). Объект персистентности может перейти в transient состоянии после вызова метода delete().
+ *Persistent:* когда объект связан с уникальной сессией он находится в состоянии persistent (персистентности). Любой экземпляр, возвращаемый методами get() или load() находится в состоянии persistent.
+ *Detached:* если объект был персистентным, но сейчас не связан с какой-либо сессией, то он находится в отвязанном (detached) состоянии. Такой объект можно сделать персистентным используя методы update(), saveOrUpdate(), lock() или replicate(). Состояния transient или detached так же могут перейти в состояние persistent как новый объект персистентности после вызова метода merge().

[к оглавлению](#Multithreading)

## 4 Зачем нужен класс SessionFactory Является ли он потокобезопасным

Именно из объекта SessionFactory мы получаем объекты типа Session. На все приложение существует только одна SessionFactory и она инициализируеться вместе со стартом приложения. SessionFactory кэширует мета-дату и SQL запросы которые часто используются приложением во время работы. Так же оно кэширует информацию которая была получена в одной из транзакций и может быть использована и в других транзакциях.
Обьект SessionFactory можно получить следующим обращением:
```java
SessionFactory sessionFactory = configuration.buildSessionFactory();
```

Т.к. объект SessionFactory immutable (неизменяемый), то да, он потокобезопасный. 
Множество потоков может обращаться к одному объекту одновременно.

[к оглавлению](#Multithreading)

## 5 Зачем нужен класс Session Является ли он потокобезопасным

Session — это основной интерфейс, который отвечает за связь с базой данных. 
Так же, он помогает создавать объекты запросов для получение персистентных объектов. 
(персистентный объект — объект который уже находится в базе данных; 
объект запроса — объект который получается когда мы получаем результат запроса в базу данных, 
именно с ним работает приложение). Обьект Session можно получить из SessionFactory:
```java
Session session = sessionFactory.openSession();
```

Роль интерфейса Session:
+ является оберткой для jdbc подключения к базе данных; (https://ru.wikipedia.org/wiki/Java_Database_Connectiv..)
+ является фабрикой для транзакций (согласно официальной документации transaction — аllows the application to define units of work, что , по сути, означает что транзакция определяет границы операций связанных с базой данных).
+ является хранителем обязательного кэша первого уровня.

Жизненный цикл объекта session связан с началом и окончанием транзакции. 
Этот объект предоставляет методы для CRUD (create, read, update, delete) операций для объекта персистентности. 
С помощью этого экземпляра можно выполнять HQL, SQL запросы и задавать критерии выборки.

Объект Hibernate Session не является потокобезопасным. 
Каждый поток должен иметь свой собственный объект Session и закрывать его по окончанию.

[к оглавлению](#Multithreading)

## 6 В чем отличие методов Sessionget Sessionload

Hibernate session обладает различными методами для загрузки данных из базы данных. 
Наиболее часто используемые методы для этого — get() и load().

+ get() загружает данные сразу при вызове, в то время как load() использует прокси объект и загружает данные только тогда, 
когда это требуется на самом деле. В этом плане load() имеет преимущество в плане ленивой загрузки данных.
+ load() бросает исключение, когда данные не найдены. Поэтому его нужно использовать только при уверенности в существовании данных.
+ Нужно использовать метод get(), если необходимо удостовериться в наличии данных в БД.
+ В случае обращение к несуществующему объекту, метод get(); вернет null. В случае нахождения объект, метод get(); вернет сам объект и запрос в базу данных будет произведен немедленно.

[к оглавлению](#Multithreading)

## 7 Расскажите про методы flush commit

+ flush() синхронизирует вашу базу данных с текущим состоянием объекта/объектов, хранящихся в памяти, но не совершает транзакцию. 
+ если вы получите какое-либо исключение после вызова flush(), то транзакция будет отменена. 
+ Вы можете синхронизировать свою базу данных с небольшими фрагментами данных, используя flush(), вместо того, чтобы делать большие данные одновременно с помощью commit() и столкнуться с риском получить Исключение из памяти.
+ commit() сделает данные, хранящиеся в базе данных постоянными. Вы не можете отменить свою транзакцию после успешного завершения commit()

[к оглавлению](#Multithreading)

## 8 В чем отличие методы save от saveOrUpdate и merge

+ Hibernate save() используется для сохранения сущности в базу данных. 
Проблема с использованием метода save() заключается в том, что он может быть вызван без транзакции. 
А следовательно если у нас имеется отображение нескольких объектов, то только первичный объект будет сохранен и мы получим несогласованные данные. 
Также save() немедленно возвращает сгенерированный идентификатор.

+ Hibernate persist() аналогичен save() с транзакцией. persist() не возвращает сгенерированный идентификатор сразу.

+ Hibernate saveOrUpdate() использует запрос для вставки или обновления, основываясь на предоставленных данных. 
Если данные уже присутствуют в базе данных, то будет выполнен запрос обновления. 
Метод saveOrUpdate() можно применять без транзакции, но это может привести к аналогичным проблемам, как и в случае с методом save().

+ Hibernate merge() может быть использован для обновления существующих значений, 
однако этот метод создает копию из переданного объекта сущности и возвращает его. 
Возвращаемый объект является частью контекста персистентности и отслеживает любые изменения, а переданный объект не отслеживается.

[к оглавлению](#Multithreading)

## 9 Расскажите процесс создания редактирования чтения и удаления данных через Hibernate



[к оглавлению](#Multithreading)

## 10 Как осуществляется иерархия наследования в Hibernate Рассказать про три стратегии наследования

SQL не понимает наследование типов и не поддерживает его.

Всего таких стратегий 4:

+ Использовать одну таблицу для каждого класса и полиморфное поведение по умолчанию.
+ Одна таблица для каждого конкретного класса, с полным исключением полиморфизма и отношений 
наследования из схемы SQL (для полиморфного поведения во время выполнения будут использоваться UNION-запросы)
+ Единая таблица для всей иерархии классов. Возможна только за счет денормализации схемы SQL. 
Определять суперкласс и подклассы будет возможно посредством различия строк.
+ Одна таблица для каждого подкласса, где отношение “is a” представлено в виде «has a», 
т.е. – связь по внешнему ключу с использованием JOIN.

[https://habr.com/ru/post/337488/](https://habr.com/ru/post/337488/)

[к оглавлению](#Multithreading)

## 11 Можно ли создать собственный тип данных

[Пользовательские типы в Hibernate](https://easyjava.ru/data/hibernate/polzovatelskie-tipy-v-hibernate/)

[к оглавлению](#Multithreading)

## 12 Какие коллекции поддерживаются на уровне mapping

+ Bag
+ Set
+ List
+ Array
+ Map

Своей реализации тип коллекции Bag очень напоминает Set, разница состоит в том, что Bag может хранить повторяющиеся значения. 
Bag хранит непроиндексированный список элементов. Большинство таблиц в базе данных имеют индексы отображающие положение элемента данных один относительно другого, 
данные индексы имеют представление в таблице в виде отдельной колонки. 
При объектно-реляционном маппинге, значения колонки индексов мапится на индекс в Array, на индекс в List или на key в Map. 
Если вам надо получить коллекцию объектов не содержащих данные индексы, то вы можете воспользоваться коллекциями типа Bag или Set 
(коллекции содержат данные в неотсортированном виде, но могут быть отсортированы согласно запросу).

[к оглавлению](#Multithreading)

## 13 Зачем нужен класс Transactional

*Transaction (org.hibernate.Transaction)* — однопоточный короткоживущий объект, используемый для атомарных операций. 
Это абстракция приложения от основных JDBC или JTA транзакций. org.hibernate.Session может занимать несколько org.hibernate.Transaction в определенных случаях.

Вместо вызовов session.openTransaction() и session.commit() используется аннотация @Transactional
 
[Hibernate. Основные принципы работы с сессиями и транзакциями](https://habr.com/ru/post/271115/)

[к оглавлению](#Multithreading)

## 14 Расскажите про уровни изоляции Какие уровни поддерживаются в hibernate Как их устанавливать

They're 4 mains transaction's isolation levels:

+ read uncommited - imagine two transactions, 'A' and 'B'. 
First, 'A' writes a data into one table without commiting the transaction. 
After, 'B' reads the uncommited data and work on it. 
But some error occurs on commiting the 'A' transaction and all changes are rollbacked. 
In this case, 'B' continues to work on uncommited data by the 'A' transaction. This mode is very fast but can introduce a lot of data consistency problems.
+ read commited - we still use the same scenario as for read uncommited, but commited data is locked. 
It means that 'B' can't see uncommited data from the 'A' transaction. 'B' can see it only when 'A' will commit its transaction.
+ repeatable read - this isolation level promotes the same data read, even if the data was changed meanwhile. 
We continue to work with our 'A' and 'B' transactions. First, 'B' makes a SELECT query and lock selected rows. 
After, 'A' makes an INSERT query. 'B' executes a new SELECT query with the same conditions as the first one. 
'B' will now see the same results as previously (the second SELECT must be made under the same transaction as the first one).
+ serializable - this level occurs when our 'B' transaction reads the data and lock whole data's table.
 It means that another transaction can't modify the data on this table. 
 Unlike read uncommited, this way is the most secure. But in the other hand, it's also the slowest solution.

Hibernate starts the transactions by calling getTransaction() (JPA's implementation) or beginTransaction() (Hibernate's Session) methods. 
According to used persistence mechanism, a transaction can be an instance of javax.persistence.EntityTransaction (for JPA) or org.hibernate.Transaction (for Hibernate's Session). 
Both transaction are begun with begin() method, rollbacked thanks to rollback() one and commited through commit() invocation.

To configure transactions isolation level in Hibernate, we need to change the property called *hibernate.connection.isolation*. 
This property can take one from following entries: 
+ 1 (read uncommited) 
+ 2 (read commited) 
+ 4 (repeatable read) 
+ 8 (serializable)

Normally, the isolation level is set at java.sql.Connection level, through setTransactionIsolation(int level) method. 
Level passed in parameter should be one from Connection's constants:
 
+ Connection.TRANSACTION_READ_UNCOMMITTED, 
+ Connection.TRANSACTION_READ_COMMITTED, 
+ Connection.TRANSACTION_REPEATABLE_READ 
+ Connection.TRANSACTION_SERIALIZABLE.

[к оглавлению](#Multithreading)

## 15 Что такое OplimisticLock Расскажите стратегии создания через version timestamp

it's crucial to manage concurrent access to a database properly. 
We should be able to handle multiple transactions in an effective and most importantly, error-proof way.
To achieve that we can use optimistic locking mechanism.

In order to use optimistic locking, *we need to have an entity including a property with @Version annotation.* 
While using it, each transaction that reads data holds the value of the version property.
Before the transaction wants to make an update, it checks the version property again.
If the value has changed in the meantime an OptimisticLockException is thrown. 
Otherwise, the transaction commits the update and increments a value version property.

As we've said before, *optimistic locking is based on detecting changes on entities by checking their version attribute*. 
If any concurrent update takes place, OptmisticLockException occurs. After that, we can retry updating the data.

We can imagine that this mechanism is suitable for applications which do much more reads than updates or deletes. 
What is more, it's useful in situations where entities must be detached for some time and locks cannot be held.

*On the contrary, pessimistic locking mechanism involves locking entities on the database level.*

Each transaction can acquire a lock on data. As long as it holds the lock, no transaction can read, 
delete or make any updates on the locked data. We can presume that using pessimistic locking may result in deadlocks. 
However, it ensures greater integrity of data than optimistic locking.

Version attributes are properties with @Version annotation. *They are necessary for enabling optimistic locking.*

[https://www.baeldung.com/jpa-optimistic-locking](https://www.baeldung.com/jpa-optimistic-locking) 

[к оглавлению](#Multithreading)

## 16 Расскажите про стратегии извлечения данных urgy lazy

+ Eager Loading is a design pattern in which data initialization occurs on the spot. Загружаются все данные по цепочке.
+ Lazy Loading is a design pattern which is used to defer initialization of an object as long as it's possible. Данные подгружаются при обращении.

[к оглавлению](#Multithreading)

## 17 Что такое объект Proxy С чем связана ошибка LazyInitializationException Как ее избежать

Hibernate использует прокси объект для поддержки отложенной загрузки. 
Обычно при загрузке данных из таблицы Hibernate не загружает все отображенные (замаппинные) объекты. 
Как только вы ссылаетесь на дочерний объект или ищите объект с помощью геттера, если связанная сущность не находиться в кэше сессии, 
то прокси код перейдет к базе данных для загрузки связанной сущности. 
Для этого используется javassist, чтобы эффективно и динамически создавать реализации подклассов ваших entity объектов.

Hibernate поддерживает ленивую инициализацию используя proxy объекты и выполняет запросы к базе данных только по необходимости.

fetch = FetchType.LAZY это значит, что хибернейт не будет инициализировать эти поля пока вы к ним не обратитесь. 
Но т.к. вы обращаетесь к этим полям за пределами транзакционных методов, он не может это сделать и выкидывает ошибку.
Чтобы этого избежать надо, что метод, который обращается к этим полям был с аннотацей Transactional

Или как предложили в комментариях: Hibernate.initialize(owner.getBooks());

Это хак, но он заставит хибернейт инициировать коллекцию. НО! 
Возможно это не всегда надо и тогда надо выбрать первый вариант и отталкиваться от здравого смысла, смотреть, 
где надо навешивать аннотацию, а где нет.

[к оглавлению](#Multithreading)

## 18 HQL Расскажи основные элементы синтаксиса HQL Простой запрос запрос join Создания объекта через конструтор

Hibernate Framework поставляется с мощным объектно-ориентированным языком запросов — Hibernate Query Language (HQL). 
Он очень похож на SQL, за исключением, что в нем используются объекты вместо имен таблиц, что делает язык ближе к объектно-ориентированному программированию.

HQL является регистронезависимым, кроме использования в запросах имен java переменных и классов, где он подчиняется правилам Java. 
Например, SelECt то же самое, что и select, но ru.javastudy.MyClass отличен от  ru.javastudy.MyCLASS. Запросы HQL кэшируются (это как плюс так и минус).

For example, if we have these two queries
```java
FROM Employee emp
JOIN emp.department dep

and

FROM Employee emp
JOIN FETCH emp.department dep
```

In this two queries, you are using JOIN to query all employees that have at least one department associated.
But, the difference is: in the first query you are returning only the Employes for the Hibernate. 
In the second query, you are returning the Employes and all Departments associated.
So, if you use the second query, you will not need to do a new query to hit the database again to see the Departments of each Employee.
You can use the second query when you are sure that you will need the Department of each Employee. 
If you not need the Department, use the first query.

[к оглавлению](#Multithreading)

## 19 Расскажите про уровни кешей в hibernate

Hibernate использует кэширование, чтобы сделать наше приложение быстрее. 
Кэш Hibernate может быть очень полезным в получении высокой производительности приложения при правильном использовании. 
Идея кэширования заключается в сокращении количества запросов к базе данных.

Кэш первого уровня Hibernate связан с объектом Session. 
Кэш первого уровня у Hibernate  включен по умолчанию и не существует никакого способа, чтобы его отключить. 
Однако Hibernate предоставляет методы, с помощью которых мы можем удалить выбранные объекты из кэша или полностью очистить кэш.
Любой объект закэшированный в session не будет виден другим объектам session. После закрытия объекта сессии все кэшированные объекты будут потеряны.

[к оглавлению](#Multithreading)

## 20 Что такое StatelessSessionFactory Зачем он нужен где он используется

StatelessSession – командно-ориентированный API, предоставляемый Hibernate. 
Используйте его для потоковой передачи данных в базу и из нее в форме отсоединенных (detached) объектов. 
StatelessSession не имеет ассоциированного persistence-контекста и не предоставляет большую часть высокоуровневой семантики. 

```java
StatelessSession session = sessionFactory.openStatelessSession();
Transaction tx = session.beginTransaction();
   
ScrollableResults customers = session.getNamedQuery("GetCustomers")
    .scroll(ScrollMode.FORWARD_ONLY);
while ( customers.next() ) {
    Customer customer = (Customer) customers.get(0);
    customer.updateStuff(...);
    session.update(customer);
}
   
tx.commit();
session.close();
```

[к оглавлению](#Multithreading)

## 21 Зачем нужен решим read only

You might actually have reasons to mark transactions as read-only.

+ Transactions for reading might look indeed strange and often people don't mark methods for transactions in this case. But JDBC will create transaction anyway, it's just it will be working in autocommit=true if different option wasn't set explicitly.
+ But there is no guarantee that your method doesn't write into the database. If you mark method as @Transactional(readonly=true), Spring will set the JDBC transaction into a read-only mode, thus you'll dictate whether it's actually possible to write into DB in scope of this transaction. If your architecture is cumbersome and some team members may choose to put modification query where it's not expected, this flag would point you to the problematic place.
+ Also read-only transactions can be optimized by DBs, but this of course is DB specific. E.g. MySQL added support for this only in InnoDB starting from 5.6.4 version.
+ If you're not using JDBC directly, but rather an ORM, that might be problematic. For instance Hibernate community says that working outside of transaction might cause unpredictable behavior. This is because Hibernate will open transaction, but it won't close it on its own, thus connection will be returned to the Connection Pool with transaction being not committed. What happens then? JDBC keeps silence, thus this is implementation specific (MySQL rolls back transaction, Oracle afair commits it). This also can be configured on Connection Pool level (e.g. C3P0 gives you such an option, rollback by default).
+ Another thing when it comes to Hibernate, Spring sets the FlushMode to MANUAL in case of read-only transactions, which leads to other optimizations like no need for dirty checks.
+ You may want to override or set explicitly the transaction isolation level. This impacts read-transactions as well since you do or don't want to read uncommitted changes, be exposed to phantom reads, etc.

[к оглавлению](#Multithreading)

## Назовите некоторые важные аннотации используемые для отображения в Hibernate

Hibernate поддерживает как аннотации из JPA, так и свои собственные, которые находятся в пакете org.hibernate.annotations. Наиболее важные аннотации JPA и Hibernate:

+ *javax.persistence.Entity*: используется для указания класса как entity bean.
+ *javax.persistence.Table*: используется для определения имени таблицы из БД, которая будет отображаться на entity bean.
+ *javax.persistence.Access*: определяет тип доступа, поле или свойство. Поле — является значением по умолчанию и если нужно, чтобы hibernate использовал методы getter/setter, то их необходимо задать для нужного свойства.
+ *javax.persistence.Id*: определяет primary key в entity bean.
+ *javax.persistence.EmbeddedId*: используется для определения составного ключа в бине.
+ *javax.persistence.Column*: определяет имя колонки из таблицы в базе данных.
+ *javax.persistence.GeneratedValue*: задает стратегию создания основных ключей. Используется в сочетании с javax.persistence.GenerationType enum.
+ *javax.persistence.OneToOne*: задает связь один-к-одному между двумя сущностными бинами. Соответственно есть другие аннотации OneToMany, ManyToOne и ManyToMany.
+ *org.hibernate.annotations.Cascade*: определяет каскадную связь между двумя entity бинами. Используется в связке с org.hibernate.annotations.CascadeType.
+ *javax.persistence.PrimaryKeyJoinColumnм: определяет внешний ключ для свойства. Используется вместе с org.hibernate.annotations.GenericGenerator и org.hibernate.annotations.Parameter.

[к оглавлению](#Multithreading)

## Как реализованы Join’ы Hibernate

Существует несколько способов реализовать связи в Hibernate.

+ Использовать ассоциации, такие как one-to-one, one-to-many, many-to-many.
+ Использовать в HQL запросе команду JOIN. Существует другая форма «join fetch«, позволяющая загружать данные немедленно (не lazy).
+ Использовать чистый SQL запрос с командой join.

[к оглавлению](#Multithreading)

## Почему мы не должны делать Entity class как final

Хибернейт использует прокси классы для ленивой загрузки данных (т.е. по необходимости, а не сразу). 
Это достигается с помощью расширения entity bean и, следовательно, если бы он был final, то это было бы невозможно. 
Ленивая загрузка данных во многих случаях повышает производительность, а следовательно важна.

[к оглавлению](#Multithreading)

## Что такое Query Cache в Hibernate

Hibernate реализует область кэша для запросов resultset, который тесно взаимодействует с кэшем второго уровня Hibernate. Для подключения этой дополнительной функции требуется несколько дополнительных шагов в коде. Query Cache полезны только для часто выполняющихся запросов с повторяющимися параметрами. Для начала необходимо добавить эту запись в файле конфигурации Hibernate:
```java
<property name="hibernate.cache.use_query_cache">true</property>
```
Уже внутри кода приложения для запроса применяется метод setCacheable(true), как показано ниже:
```java
Query query = session.createQuery("from Employee");
query.setCacheable(true);
query.setCacheRegion("ALL_EMP");
```

[к оглавлению](#Multithreading)

## Что такое Named SQL Query

Hibernate поддерживает именованный запрос, который мы можем задать в каком-либо центральном месте и потом использовать его в любом месте в коде. 
Именованные запросы поддерживают как HQL, так и Native SQL. 
Создать именованный запрос можно с помощью JPA аннотаций @NamedQuery, @NamedNativeQuery или в конфигурационном файле отображения (mapping files).

[Hibernate — примеры именованных запросов NamedQuery](http://javastudy.ru/hibernate/hibernate-namedquery/)

[к оглавлению](#Multithreading)

## Каковы преимущества Named SQL Query

Именованный запрос Hibernate позволяет собрать множество запросов в одном месте, а затем вызывать их в любом классе. 
Синтаксис Named Query проверяется при создании session factory, что позволяет заметить ошибку на раннем этапе,
а не при запущенном приложении и выполнении запроса. Named Query глобальные, т.е. заданные однажды, могут быть использованы в любом месте.

Однако одним из основных недостатков именованного запроса является то, что его очень трудно отлаживать 
(могут быть сложности с поиском места определения запроса).

[к оглавлению](#Multithreading)

## Расскажите о преимуществах использования Hibernate Criteria API

Hibernate Criteria API является более объектно-ориентированным для запросов, которые получают результат из базы данных. Для операций update, delete или других DDL манипуляций использовать Criteria API нельзя. Критерии используются только для выборки из базы данных в более объектно-ориентированном стиле.

Вот некоторые области применения Criteria API:

+ Criteria API поддерживает проекцию, которую мы можем использовать для агрегатных функций вроде sum(), min(), max() и т.д.
+ Criteria API может использовать ProjectionList для извлечения данных только из выбранных колонок.
+ Criteria API может быть использована для join запросов с помощью соединения нескольких таблиц, используя методы createAlias(), setFetchMode() и setProjection().
+ Criteria API поддерживает выборку результатов согласно условиям (ограничениям). Для этого используется метод add() с помощью которого добавляются ограничения (Restrictions).
+ Criteria API позволяет добавлять порядок (сортировку) к результату с помощью метода addOrder().

[к оглавлению](#Multithreading)

## Как логировать созданные Hibernate SQL запросы в лог файлы

Для логирования запросов SQL добавьте в файл конфигурации Hibernate строчку:
```java
<property name="hibernate.show_sql">true</property>
```

Отметьте, что это необходимо использовать на уровне Development или Testing и должно быть отключено в продакшн.

[к оглавлению](#Multithreading)

## Как реализованы отношения в Hibernate

Реализовать отношение one-to-one, one-to-many, many-to-many можно с помощью JPA аннотаций или конфигурирования xml файла. 
За примерами посетите раздел Hibernate.

[к оглавлению](#Multithreading)

## Как управлять транзакциями с помощью Hibernate

Hibernate вообще не допускает большинство операций без использования транзакций. 
Поэтому после получения экземпляра session от SessionFactory необходимо выполнить beginTransaction() для начала транзакции. 
Метод вернет ссылку, которую мы можем использовать для подтверждения или отката транзакции.

В целом, управление транзакциями в фреймворке выполнено гораздо лучше, чем в JDBC, т.к. 
мы не должны полагаться на возникновение исключения для отката транзакции. 
Любое исключение автоматически вызовет rollback.

[к оглавлению](#Multithreading)

## Что такое каскадные связи обновления и какие каскадные типы есть в Hibernate

Если у нас имеются зависимости между сущностями (entities), то нам необходимо определить как различные операции будут влиять на другую сущность. 
Это реализуется с помощью каскадных связей (или обновлений). Вот пример кода с использованием аннотации @Cascade:
```java
import org.hibernate.annotations.Cascade;
 
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
 
@OneToOne(mappedBy = "employee")
@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
private Address address;
 
}
```
Обратите внимание, что есть некоторые различия между enum CascadeType в Hibernate и в JPA. Поэтому обращайте внимание какой пакет вы импортируете при использовании аннотации и константы типа. Наиболее часто используемые CascadeType перечисления описаны ниже.

None: без Cascading. Формально это не тип, но если мы не указали каскадной связи, то никакая операция для родителя не будет иметь эффекта для ребенка.
+ ALL: Cascades save, delete, update, evict, lock, replicate, merge, persist. В общем — всё.
+ SAVE_UPDATE: Cascades save и update. Доступно только для hibernate.
+ DELETE: передает в Hibernate native DELETE действие. Только для hibernate.
+ DETATCH, MERGE, PERSIST, REFRESH и REMOVE – для простых операций.
+ LOCK: передает в Hibernate native LOCK действие.
+ REPLICATE: передает в Hibernate native REPLICATE действие.

[к оглавлению](#Multithreading)

## Какие паттерны применяются в Hibernate

+ Domain Model Pattern – объектная модель предметной области, включающая в себя как поведение так и данные.
+ Data Mapper – слой мапперов (Mappers), который передает данные между объектами и базой данных, сохраняя их независимыми друг от друга и себя.
+ Proxy Pattern — применяется для ленивой загрузки.
+ Factory pattern — используется в SessionFactory

[к оглавлению](#Multithreading)

## Расскажите о Hibernate Validator Framework

Проверка данных является неотъемлемой частью любого приложения. Hibernate Validator обеспечивает эталонную реализацию двух спецификаций JSR-303 и JSR-349 применяемых в Java. Для настройки валидации в Hibernate необходимо сделать следующие шаги.

Добавить hibernate validation зависимости в проект.
```java
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>1.1.0.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>5.1.1.Final</version>
</dependency>
```

Так же требуются зависимости из JSR 341, 
реализующие Unified Expression Language для обработки динамических выражений и сообщений о нарушении ограничений.
```java
<dependency>
    <groupId>javax.el</groupId>
    <artifactId>javax.el-api</artifactId>
    <version>2.2.4</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>javax.el</artifactId>
    <version>2.2.4</version>
</dependency>
```
Использовать необходимые аннотации в бинах.
```java
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
 
public class Employee {
 
    @Min(value=1, groups=EmpIdCheck.class)
    private int id;
     
    @NotNull(message="Name cannot be null")
    @Size(min=5, max=30)
    private String name;
     
    @Email
    private String email;
     
    @CreditCardNumber
    private String creditCardNumber;
```
        
[к оглавлению](#Multithreading)

## Best Practices в Hibernate

При использовании фреймворка Hibernate рекомендуется придерживаться некоторых правил.

+ Всегда проверяйте доступ к primary key. Если он создается базой данных, то вы не должны иметь сеттера.
+ По умолчанию hibernate устанавливает значения в поля напрямую без использования сеттеров. Если необходимо заставить хибернейт их применять, то проверьте использование аннотации @Access(value=AccessType.PROPERTY) над свойством.
+ Если тип доступа — property, то удостоверьтесь, что аннотация используется с геттером. Избегайте смешивания использования аннотации над обоими полями и геттером.
+ Используйте нативный sql запрос только там, где нельзя использовать HQL.
+ Используйте ordered list вместо сортированного списка из Collection API, если вам необходимо получить отсортированные данные.
+ Применяйте именованные запросы разумно — держите их в одном месте и используйте только для часто применяющихся запросов. Для специфичных запросов пишите их внутри конкретного бина.
+ В веб приложениях используйте JNDI DataSource вместо файла конфигурации для соединения с БД.
+ Избегайте отношений многие-ко-многим, т.к. это можно заменить двунаправленной One-to-Many и Many-to-One связью.
+ Для collections попробуйте использовать Lists, maps и sets. Избегайте массивов (array), т.к. они не дают преимуществ ленивой загрузки.
+ Не обрабатывайте исключения, которые могут откатить транзакцию и закрыть сессию. Если это проигнорировать, то Hibernate не сможет гарантировать, что состояние в памяти соответствует состоянию персистентности (могут быть коллизии данных).
+ Применяйте шаблон DAO для методов, которые могут использоваться в entity бинах.
+ Предпочитайте ленивую выборку для ассоциаций.

[к оглавлению](#Multithreading)