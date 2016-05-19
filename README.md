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
  
  Any API of this form can be accessed using this application. In the given example, the user might be interested in the 3 words returned by the api and user has a csv file with the position co-ordinates in one single column. The user should first form a sample url that can be used to call the API (in the given example, the user should generate his own key and then plug it in the url). Generally, the sample url is given in the documentation of the API. After forming the sample url, the user can use it to select the parameters that change for every request from among the input parameters. In general, for the example given the 3 input parameters are *key*, *lang*, and *position*, the only parameter that changes with every request is *position* and the other 2 parameters are constant for a particular user. The csv file that the user uploads should contain one column for each of the input parameters that he selects. Through the application, the user should enter the column numbers for each of the parameters and upload the csv file. The user should also select the output parameters that he is interested 
