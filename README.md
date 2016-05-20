# GeoCodeAPI

This repository contains the backend code for the APP4API application. The primary goal of the application is to build an easy to use application for accessing data-providing APIs. This application supports any API that can accessed through URL and returns the data as a json object with attribute-value pairs. This backend code handles all the necessary tasks and functions required. This is a maven project with java code. In this document, we provide the packages, the files, and the functions present in them for the user to enhance and understand the application. 

## Structure

The code is organized into packages. The application has 4 workflows. 3 workflows for 3 preset APIs (Geocode, InternetArchive, and Human Resource). These 3 workflows are initiated from the front-end in the Preset workflow. Each preset API has its own dedicated config file and config folder. These APIs have specific url patterns, information holders, fixed set of keys, and access methods. These functions are present in different files in different packages. All the java classes are named appropriately. The packages, classes and their details are 

- com.api.config: This package contains the config files. These are only for the preset APIs. The package contains an interface (*ConfigHolder.java*) and the 3 config holders implementing the interface (*ConfigHolderGeo.java*, *ConfigHolderHR.java*, and *ConfigHolderIA.java*).

- com.api.general: There are 2 classes in this package, *ProcessRequestGeneral.java* and *UrlType.java*. These two classes are used in the workflow for any new API that is not in the preset list of APIs. *ProcessRequestGeneral* file contains the methods to access the URL and to parse the results. *UrlType* contains the appropriate functions to hit the url and retrive the results. The same applies to all the *ProcessRequest* and *UrlType* files present in other packages. 

- com.api.geocode: This package contains 2 files, *ProcessRequestGeo.java* and *PrefixReplacements.java* related to Geocode. *ProcessRequestGeo* contains the functions necessary to access the Geocode url. Geocode provides 5 different ways of accessing the geocodes namely Address, Blockface, Intersection, Place, and Search. Each attribute takes different set of input values and returns different output values. Each of these functions are present in the associated class files under *com.api.types.geo*. 

- com.api.types.geo: This package contains the interface (*IType.java*) and the associated geocode types discussed in the *com.api.geocode* package. 

- com.api.humanresource: This package is similar to *com.api.general* package. It has 2 files, *ProcessRequestHR* and *UrlType.java* and the functions are similar to the ones explained in the general package and these 2 are for the Human Resource API.

- com.api.internetarchive: This package is similar to *com.api.general* package. It has 2 files, *ProcessRequestHR* and *UrlType.java* and the functions are similar to the ones explained in the general package and these 2 are for the Internet Archive API.

- com.api.infoholder: The classes in this package are data strucutures to hold information for each request. When each request are made, an instance of the appropriate data structure is initialized and is filled with the information that is already present with us. These classes contains the appropriate functions to set and return the information and also has the function to print the datastucture in the form of a csv row. 

- com.api.run: This package contains the files that initiate the backend workflow. Each file has the function that is referenced from the front end applet. The files are named appropriately denoting the workflow they initiate. 

- com.api.utils: This package contains all the utility files that are required for this project. It contains the readers, parsers, and other necessary functionalities implemented in several classes. This package also contains the *SendEmail.java* that has the function to send the results as an attachment. 

## Workflow

In this section, we describe the basic outline of one workflow which is simlar to both general and preset cases. As mentioned earlier, the APIs that this application supports are accessed through URL only, for example

**https://api.what3words.com/position?key=[KEY]&lang=en&position=51.521251,-0.203586** 

and the output is always a json object, for example

**{"words":["index","home","raft"],"position":[51.521251,-0.203586],"language":"en"}**.

Any API of this form can be accessed using this application. This is a general API (not one of the preset APIs). Hence, the classes that are accessed by the workflow for accessign this API are

1. RunForLargeFilesGeneral
2. ProcessRequestGeneral
3. FileUtils
4. InfoHolderGeneral

*RunForLargeFilesGeneral* contains the main class that has to be run. The user has to have the sample url (with appropriate fields filled), input csv file, and a location and name for the output csv file. It also required to input the url keys and their column numbers in the input file for constructing new requests. The main class also requires the user to give a list of output fields to facilitate the access. The sample main class has all these attribtues set to give a more clear picture for the user.

*ProcessRequestGeneral* contains the process in which the url will be accessed and the construction of the url. 

*FileUtils* contains the functions for opening and closing the files and other necessary functions. 

*InfoHolderGeneral* is the information holder data structure that is instantiated for every request. This object is being sent across the pipeline and the output attribtues are set in this object. 

## Presentation
A final presentation was made on this application at the Digital Centers Intern showcase. The slides of the same can be found at [slides] (https://docs.google.com/a/columbia.edu/presentation/d/1kKKO3qvJ4KAkEQh1gG5j42qJvrwbdD1_1AYfllNh8Wg/edit?usp=sharing)

## Contact

This documentation should help in exploring the project. In case of any questions, feel free to reach out to Digital Social Science Center at Columbia University. 
