import java.awt.*;
import java.util.Scanner; // import dependencies
import java.net.URI;
import java.io.IOException;

void main() { // main class = the class the program will start at
    Desktop desktop = Desktop.getDesktop(); // get the desktop object
    boolean supported = desktop.isSupported(Desktop.Action.BROWSE);// get the browser integration for the operating system being used

    if (!supported) { // check if the computer supports opening links
        System.out.println("Your system/OS is not supported! Please refer to the documentation for more details.");
        return;
    }

    Scanner sc = new Scanner(System.in); // create scanner
    System.out.println("Hello, and welcome to the Browser tab RAM tester!"); // print welcome message
    System.out.print("How many tabs would you like to open initially? ");
    int amountOfTabs = sc.nextInt(); // get the amount of tabs the user wants to open

    System.out.print("Okay, what how many URLs would you like to use?(the program will loop through them): ");
    int amountOfURLs = sc.nextInt(); // get the amount of URLs the user wants to loop through

    if (amountOfURLs <= 0 || amountOfTabs <= 0) { // check if we don't have negative tabs/URLs
        System.out.println("You have entered a negative amount(or zero) of URLs or tabs. Please try again!");
        return; // incorrect URL/tab amount
    }

    String[] stringUrls = getURLs(amountOfURLs, sc /* were sending over the scanner so we don't have to create it again*/); // call the getURLs function(get the URLs we're opening)

    URI[] links = convertURLs(amountOfURLs,stringUrls); // convert the String URLs to the URI format, which the Desktop.browse uses

    openTabs(links,amountOfURLs,amountOfTabs); // call the tab opener
    int tabsOpened = amountOfTabs; // create the value that is the TOTAL amount fo tabs opened
    int input_int = amountOfTabs; // create the value for the int conversion of the input(doing this here as otherwise the interpreter doesn't detect it)
    while (true) {
        System.out.println("Successfully opened " + input_int + " tabs. Total amount of tabs opened: " + tabsOpened + ".");
        System.out.println("Please type the amount of tabs you wish to open next(using URLs given earlier), or type x to exit the program: ");
        String input = sc.next(); // get the input
        try {
            input_int = Integer.parseInt(input); // convert to int
        }
        catch (NumberFormatException ex) { // if it's a letter...
            if (input.equals("x")) { // ... check if its x...
                System.out.println("Exiting program..."); // ... and exit the program...
                return;
            }
            else { // otherwise throw an error
                System.out.println("Invalid input. Please try again.");
                continue;
            }
        }
        if (input_int <= 0) { // if it's a negative number we end it before it gets bad...
            System.out.println("Negative(or zero) number entered! Please try again.");
            continue;
        }
        tabsOpened += input_int; // adding the tabs opened this round to the total amount
        openTabs(links,amountOfURLs,input_int);
    }
}

String[] getURLs(int urlAmount, Scanner sc) { // define the input of urls
    String[] urls = new String[urlAmount]; // create the array for storing the urls

    for (int a=0; a<urlAmount; a++) { // get the input of the URLs
        System.out.println("Please type URL number " + (a + 1) + ":");
        urls[a] = sc.next();

        if (!(urls[a].startsWith("https://") || urls[a].startsWith("http://"))) { // check if the URL is valid
            System.out.println("invalid URL number " + (a + 1) + ". Please enter a correct URL below(must begin with either https:// or http://)");
            a--; // re-ask the user for a valid URL
        }
    }
    return urls;
}

URI[] convertURLs(int urlAmount,String[] oldURLs) {
    URI[] newURLs = new URI[urlAmount]; // create the new array for the converted URIs
    for (int c=0; c<urlAmount; c++) { // for every URL in the string format...
        try {
            newURLs[c] = URI.create(oldURLs[c]); // ... we convert it to a URI here...
        }
        catch(IllegalArgumentException uriConversion) { // ... and this catches any errors.
            System.out.println("Invalid URL " + (c + 1) + ", error in URI conversion process.");
            System.exit(1);
        }
    }
    return newURLs; // return the new array
}
void openTabs(URI[] URLs, int urlAmount, int tabAmount) {
    Desktop desktop = Desktop.getDesktop(); // get the desktop object
    int linkNumber;
    for(int d=0; d<tabAmount; d++) { // for every tab we have to open...
        linkNumber = d % urlAmount; // ... we cycle through the index of tabs...
        try {
            desktop.browse(URLs[linkNumber]); // ... and open them
        } catch (IOException ex) { // error catcher
            System.out.println("Opening tabs(desktop) error!");
            System.exit(2);
        }

    }
}