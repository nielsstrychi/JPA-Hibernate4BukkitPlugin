> # JPA_Hibernate_Plugin
> Example plugin for minecraft servers in how to use JPA with Hibernate as implementation
> forum post on Spigot -> https://www.spigotmc.org/threads/setup-jpa-hibernate-for-your-minecraft-plugin.397782/
> 
> Setup of JPA with Hibernate for a Minecraft plugin can be a struggle. 
> Below i made a small tutorial on how to setup Hibernate ORM.
> The [source code](https://github.com/nielsstrychi/JPA_Hibernate_Plugin) is included on GitHub.
> 
> First start with adding the dependency of Hibernate in your Maven projects [pom.xml](https://github.com/nielsstrychi/JPA_Hibernate_Plugin/blob/master/pom.xml).
> 
   `
   <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.2.12.Final</version>
        <scope>compile</scope>
   </dependency>
> `
> Then lets configure our [persistence.xml](https://github.com/nielsstrychi/JPA_Hibernate_Plugin/blob/master/src/main/resources/META-INF/persistence.xml) and place it in the **src/main/recources/META-INF/** directory.
> 
> `<?xml version="1.0" encoding="UTF-8" ?>
> <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
>              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
>                     http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
>              version="2.1">
>     <persistence-unit name="persistence-unit" transaction-type="RESOURCE_LOCAL">
> 
>         <class>com.nielsstrychi.Entity</class>
>         <exclude-unlisted-classes>true</exclude-unlisted-classes>
>         <properties>
>             <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
>             <property name="javax.persistence.jdbc.url" value="jdbc:mysql://yourdatabase.com:3306" />
>             <property name="javax.persistence.jdbc.user" value="yourusername" />
>             <property name="javax.persistence.jdbc.password" value="yourpassword" />
>             <property name="javax.persistence.schema-generation.database.action" value="create" />
>             <!-- Hibernate Specific -->
>             <property name="hibernate.show_sql" value="true" />
>         </properties>
>     </persistence-unit>
> </persistence>`
> 
> Now JPA with Hibernate would be setup for a normal project.
> For a Minecraft plugin you just need to add a important piece of code more to make it work.
> Now JPA with Hibernate would be setup for a normal project.
> For a Minecraft plugin you just need to add a important piece of code more to make it work.
> 
> `Thread.currentThread().setContextClassLoader(getClass().getClassLoader());`
> 
> The code above need to be added to your java code before calling.
> 
> `Persistence.createEntityManagerFactory("persistence-unit");`
> 
> Else you may get a exception like this, and the persistence.xml may not be loaded.
> 
> `No Persistence provider for EntityManager`
> 
> Thanks to [@computerwizjared](https://www.spigotmc.org/members/computerwizjared.17705/) for investigating and finding a solution for
> Hibernate to utilize the classes and entities in the wrong classpath.
> you can find more information about the solution in the original post here:
> [https://www.spigotmc.org/threads/jpa-maven-shade-no-persistence-provider-for-entitymanager.374958/](https://www.spigotmc.org/threads/jpa-maven-shade-no-persistence-provider-for-entitymanager.374958/)
> 
> Now that JPA + Hibernate is setup succesfully an running without exceptions we can start using it like this.
> 
> `   
>     static {
>         Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
>     }`
> 
>     protected static void write() {
>         EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
>         EntityManager em = emf.createEntityManager();
>         EntityTransaction tx = em.getTransaction();
>         tx.begin();
> 
>         Entity data = new Entity();
>         data.setName("Lara");
>         em.persist(data);
> 
>         tx.commit();
>         em.close();
>         emf.close();
>     }
> 
>     protected static void read() {
>         EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
>         EntityManager em = emf.createEntityManager();
>         EntityTransaction tx = em.getTransaction();
>         tx.begin();
> 
>         Entity data = em.find(Entity.class, 1);
>         System.out.println("entity ="+ data);
> 
>         tx.commit();
>         em.close();
>         emf.close();
>     }
> 
