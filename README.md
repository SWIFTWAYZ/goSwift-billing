# goSwift-Billing-system
billing and business logic sub-system 

This is where post-trip processing happens. Billing of trip based on trip data
received from dispatch through message broker is carried out by Billing system. Rate
information is configured in this module. All business rules relating to the billing of a trip
or fare estimates are done by this module. This module gives users ability to configure base rates, 
rates per kilometer, rates per minute, cancellation fee, time-windows, fare estimate for car-pooling, 
fare splitting for car-pools using a web application. Support user profiles for drivers, partners and administrators.
Passenger portal will be supported in a later release. 

The goSwift busines API is deployed in the same process as billing. The business API is responsible for:-
- business rules relating to fare calculation, fare estimates, account recharge, account debits,
payments, cash payments recons.
- Driver business layer is responsible for driver creation, driver updates, driver approval, driver/vehicle
linking, driver docs upload
- Vehicle business layer is responsible for vehicle creation, vehicle updates, vehicle approval, vehicle docs upload
- other logic related to discounts, compaign, invoicing, trip summaries, trip details, 
- Reporting provides driver trip statements. Calculates amount due to driver per vehicle per week, month etc.
- 


### How do I get setup?

After cloning the repository switch to developmet branch and install all the dependancies.

### Switch Branch
 * git checkout -b development
 
### Software
 * [Apache-maven-3.3.9](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
 * [Postgresql](https://www.postgresql.org/download/) [setup](http://stackoverflow.com/questions/1471571/how-to-configure-postgresql-for-the-first-time)
 * [Dbeaver](http://dbeaver.jkiss.org/download/)

### Database Config

 * Follow this link to setup postgress [setup](http://stackoverflow.com/questions/1471571/how-to-configure-postgresql-for-the-first-time)
 * dbname = swiftwayz
 * dbuser = swiftwayz
 
 ### How to build and run
 * mvn clean install -Dspring.profiles.active=dev
 * cd application
 * mvn spring-boot:run -Dspring.profiles.active=dev
