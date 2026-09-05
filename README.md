# tabs ram tester
This program automatically opens the amount of tabs you ask it to. Because it is written in java, it should work across Mac OS, Windows, and Linux(though i have only tested it on Linux). Simply give it the websites you want to open, the amount of total websites you want, and watch your computer blow up!
This program opens the websites in your **default browser**, changeable via your system settings.
## installation and usage
- Download and install the [Java SDK for version 25(or newer) for your operating system](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html) if you don't already have it.
- Download the main.java file from the `src` folder/releases tab.
- In the folder of the .java file, open a terminal/command prompt
- In here, type `javac main.java && java main`
- The program should now launch!
### error codes
- status 1 means the conversion process has failed, you have given an invalid URL.
- status 2 means the program was not able to open tabs. Do you have a browser installed and set as default?
