# Exasol Hibernate Dialect

EXASOL is a high-performance, in-memory, MPP database specifically designed for analytics. This repository contains a custom dialect to support ORM via JPA and Hibernate 5 using the EXASOL database.

## Disclaimer

###### Please note that this is an open source project which is not officially supported by EXASOL. We will try to help you as much as possible, but can't guarantee anything since this is not an official EXASOL product.

## Supported Versions & Dependencies
This dialect is designed for Hibernate 5 and has been tested with hibernate-entitymanager version 5.2.9 and JPA 2.1. In addition to these dependencies, an EXASOL JDBC driver is required which can be obtained from the [EXASOL Maven Repository](https://maven.exasol.com) or via download from the [EXASOL homepage](http://www.exasol.com). More information about the JDBC driver can be found in the EXASOL manual.

## Hibernate Integration

The repository provides two new classes: 
- com.exasol.dialect.ExasolDialect as extension of [org.hibernate.dialect.Dialect](http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/dialect/Dialect.html)
- com.exasol.dialect.ExasolIdentityColumnSupport as extension of [org.hibernate.dialect.identity.IdentityColumnSupportImpl](http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/dialect/identity/IdentityColumnSupportImpl.html)

To use the dialect, add the two new classes to the build path. In addition, reference the dialect as well as the corresponding JDBC driver in *persistence.xml* file.

```
...
        <properties>
            <property name="javax.persistence.jdbc.driver" value="com.exasol.jdbc.EXADriver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:exa:ip-range:port" />
            <property name="javax.persistence.jdbc.user" value="myuser" />
            <property name="javax.persistence.jdbc.password" value="mypwd" />
	    <property name="hibernate.dialect" value="com.exasol.dialect.ExasolDialect"/>
            <property name="hibernate.default_schema" value="myschema" />
          ...
        </properties>
...
```
In this snippet *ip-range*, *port*, *myuser*, and *mypwd* have to be changed to the actual connection parameters of the EXASOL database. Also make sure to define a default schema by exchanging *myschema* with your own choice!

If you do not use JPA then reference the dialect in your *hibernate.cfg.xml* file.
```
...
 	<property name="connection.driver_class">com.exasol.jdbc.EXADriver</property>
        <property name="connection.url">jdbc:exa:ip-range:port</property>
        <property name="connection.username">myuser</property>
        <property name="connection.password">mypwd</property>
	<property name="dialect">com.exasol.dialect.ExasolDialect</property>
	<property name="default_schema">myschema</property>
...
```

## Remarks
This project provides basic ORM functionality for Hibernate using JPA. Some functionality still needs to be implemented or will not be supported by the EXASOL database.
