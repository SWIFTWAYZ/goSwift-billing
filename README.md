# goSwift-Billing-system
billing and business logic sub-system 

This is where post-trip processing happens. Billing of trip based on trip data
received from dispatch through message broker is carried out by Billing system. Rate
information is configured in this module. All business relating to the billing of a trip
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
