# Bike Sharing System Modeling

This project is my attempt to model a bike sharing system using a JAVA framework.\
To have a full understanding of this project initial requirements please check the following [file](ProjectDescription.pdf)

To realize this project I decided to follow mainly a "waterfall" approach (Design > Implementation > Test > Debug).\
You can look into the UML model for this project [here](https://drive.google.com/file/d/1q-TxoB0rqAr499ppoq3I8OMpADKcnXM9/view?usp=sharing).
## Project Structure
 This Java project has the following structure:
 
```
├── ...
├── MyVelib-Project                  
│   ├── doc                      # this folder contains the javadoc generated documentation for the entire project
│   └── src                   
│      ├── core               
│      |   ├── CLUI              # this folder contains classes responsible for the commands described latter
│      |   ├── classes           # this folder contains the main classe
│      |   ├── enumerations      # this folder contains all the enumerations used in this project
│      |   ├── interfaces        # this folder contains the interfaces
│      |   └── test              # this folder contains unit tests for the main classes
│      ├── eval                 
│      |   ├── myVelib.ini.txt   # this file contains the command runned by default when running this project
│      |   └── testScenarioN.txt # this kind of file is used to run test scenarios (list of the commands)
|      └── model
├── ProjectDescription.pdf       # Project requirement and description
└── README.md               
```
## How to test this project
To test this project please run the java class file **MainCommand.java** that you can find [here](MyVelib-Project/src/core/CLUI/RUN/MainCommand.java).

Once you run it, in the console, you will receive a number of messages stating first that the system is populated with the network defined in myVelib.ini.text file and asking you to enter you command.

The file myVelib.ini.text contains the command : **setup initNetwork 15 15 1000
0.75**, so initially a network named initNetwork is created within a square of side 1000
with 15 stations containing 15 slots each and a population of bicycles of 75%.
### Main commands
After running the MainCommand class, you will be able to test some of the following commands via the console and in case there are some inconsistencies or typing errors you will get a message stating the error cause:
```
setup <velibnetworkName>
```
This command permits to create a myVelib **network** with given **name** and
consisting of **10** stations each of which has **10** parking slots and such that stations
are arranged on a square grid whose of side 4km and initially populated with a **75%**
bikes randomly distributed over the **10** stations
``` 
setup <name> <nstations> <nslots> <s> <nbikes>
```
This command helps to create a myVelib **network** with given **name** and consisting of **nstations** stations each of which has **nslots**
parking slots and such that stations are arranged in as uniform as possible manner
over a squared area of side **s**. Furthermore the network will
be initially populated with a **nbikes** bikes randomly distributed over the nstations
stations
```
addUser <userName,cardType, velibnetworkName>
``` 
This command can be used in order to add a user with **name**
userName and card **cardType** (i.e. ``none'' if the user has no card) to a myVelib network velibnetworkName

```
offline <velibnetworkName, stationID>
```
This is used to put **offline** the station **stationID**
of the myVelib network velibnetworkName
```
online <velibnetworkName, stationID>
```
This is used to put **online** the station **stationID** of
the myVelib network velibnetworkName
```
rentBike <userID, stationID>
```
This command allows the user **userID** to rent a bike from station
**stationID** (if no bikes are available it behaves accordingly)
```
returnBike <userID, stationID, duration>
```
This command enables the user userID to return a
bike to station **stationID** for a given **duration** (if no parking bay is available it
behaves accordingly).
```
displayStation<velibnetworkName, stationID>
```
This command is used to display the information of station **stationID** of a myVelib network **velibnetwork**.
```
displayUser<velibnetworkName, userID>
```
This command allows to display the information of a user **userID** of a myVelib network **velibnetwork**.
```
sortStation<velibnetworkName, sortpolicy>
```
This command enables to display the stations in increasing order w.r.t. to a sorting policy **sortpolicy**.
```
display <velibnetworkName>
```
This command is used to display the entire status (stations, parking bays,users) of an a myVelib network velibnetworkName.
## Project Amelioration

This project can be improved with some amelioration. Some of the possible improvements to this project include :

- [ ] Defining a configuration file with all the hyper-parameters (like the bonus from a
plus station, the cost per hour, etc) for this project to avoid having a parameter
inside the code.
- [ ] Creating a dictionary with exceptions, to avoid having repeated error messages in different places.
- [ ] Using singleton pattern to define the class App as we wish to have only one instance of it.
- [ ] Resolving some issues regarding the latency of the command lines.
- [ ] Refining the rentbike command to take into account the type of bicycle.

