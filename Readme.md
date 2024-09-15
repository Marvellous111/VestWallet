So basically this is a concept version the wallet, started late so there are a lot of things to iron out, but the overall concept still stands


Users can create an account and the wallet will provide the did and credentials for them

they can then convert the various amounts and get the pfis that was used to convert

I designed this in such a way that users dont necessarily have to click on the PFIs that they want

For user retention purposes and Ux I made it such that an algorithm selects the cheapest PFI for them.

I equally made it such that users can conveetr currencies ACRESS PFIs in an easy manner.

So say we have a user converting from KES to MXN

The algorithm can go through all pfis and return the conversion steps necessary to convert KES to MXN all while picking the cheapest options.


Profitability:
The app derives revenue from transaction fees, even if its deposit, withdrawal, convert

Optionality:
The app automatically selects the PFI offering that is the cheapest for the user.

Customer Management:
The app has fields for the user to report a transaction carried out by the PFIs, it may be buggy but it is the work i could do considering the amount of time.

Customer Satisfaction:
After a transaction the app has the option for the user to rate the transaction satisfaction from 1 to 5. This is not implemented completely but is working and may experience bugs, minor fixes in the future is necessary.


Key takeaway:
I joined this hackathon to build and test myself with the web5 TBDex SDK, and built a wallet that not only selects the cheapest options but can also convert virtually from any currency to currency of choice based on an algorithm i wrote myself on graph travesal

This algorithm enables users to convert from any currency in a pfi to another in another pfi and will automatically generate the conversion step for the users.

More work would need to be done to iron out certain things but i think i have taken care of all of the problems.