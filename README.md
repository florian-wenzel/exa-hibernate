# Exasol Hibernate Dialect

EXASOL is a high-performance, in-memory, MPP database specifically designed for analytics. This repository contains a custom dialect to support ORM via JPA and Hibernate 5 using the EXASOL database.

## Disclaimer

###### Please note that this is an open source project which is not officially supported by EXASOL. We will try to help you as much as possible, but can't guarantee anything since this is not an official EXASOL product.

## Supported Hibernate Versions
This dialect is designed for Hibernate 5 and has been tested with version 5.2.9 and JPA 2.1. For Hibernate 4, methods of ExasolIdentitySupport have to be directly implemented in the ExasolDialect dialect class. 

## Hibernate Integration

The repository provides two new classes: 
- com.exasol.dialect.ExasolDialect as extension of [org.hibernate.dialect.Dialect](http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/dialect/Dialect.html)
- com.exasol.dialect.ExasolIDentitySupport as extension of [org.hibernate.dialect.identity.IdentityColumnSupportImpl](http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/dialect/identity/IdentityColumnSupportImpl.html)




