/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2023
* Instructor: Professor Stough
* Section: 02 10AM
* Lab Section: 10AM
*
* Name: Chang Min Bark
* Date: 08/25/2023
*
* Lab / Assignment: lab01
*
* Description: A "Hello World" program in Java
*
* *****************************************/


package lab01; // Note: Quick Fix options for initial package statement

import java.util.Scanner;

public class Hello
{
    public static void main( String[] args ){
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is your name?");
        String userName = scnr.next();
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVer = System.getProperty("os.version");
        System.out.println("Here is your operating system, " + userName);
        System.out.println("OS Name: " + osName);
        System.out.println("OS Architecture: " + osArch);
        System.out.println("OS Version: " + osVer);
        System.out.println(userName + ", your name is " + userName.length() + " characters long." );
        System.exit(0);
    }
}