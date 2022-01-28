# nasaloganalyzer
Nasaloganalyzer produces a plain text report containing the following information:

1. Top 10 requested pages and the number of requests made for each
2. Percentage of successful requests (anything in the 200s and 300s range)
3. Percentage of unsuccessful requests (anything that is not in the 200s or 300s range)
4. Top 10 unsuccessful page requests
5. The top 10 hosts making the most requests, displaying the IP address and number of requests made.
6. Option parsing to produce only the report for one of the previous points (e.g. only the top 10 URLs, only the percentage of successful requests and so on)
7. For each of the top 10 hosts, show the top 5 pages requested and the number of requests for each page
8. The log file contains malformed entries; for each malformed line, display an error message and the line number.

# **Requirements**

 - Java 8 or newer
 - Maven 3.5.3

# **How to use**

**Build**

In order to build the application an **_mvn clean install_** is enough.
The executable file can be found under the target folder after the build.

**Parameters**

 - **{log file path}**:     _This parameter is **mandatory** and provides the path to the (uncompressed) log file in the local system;_
 - **topRequested**:        _This is the **optional** flag that has to be used for informing the parser to report only the top 10 requested pages and the number of requests made for each;_
 - **successPercent**:      _This is the **optional** flag that has to be used for informing the parser to report only the percentage of the successful requests;_
 - **unsuccessfulPercent**: _This is the **optional** flag that has to be used for informing the parser to report only the percentage of the unsuccessful requests;_ 
 - **topUnsuccessful**:     _This is the **optional** flag that has to be used for informing the parser to report only the top 10 unsuccessful page requests;_ 
 - **topHosts**:            _This is the **optional** flag that has to be used for informing the parser to report only the 10 hosts making the most request, with their IP address and number of requests made._ 

**Example usage**

  1. `java -jar NasaLogAnalyzer-1.0.jar "/home/rheluser/Desktop/access_log_Aug95" `
  2. `java -jar NasaLogAnalyzer-1.0.jar "/home/rheluser/Desktop/access_log_Aug95" successPercent`

# **Dependencies**

 - maven-jar-plugin v3.2.2
 - maven-surefire-plugin v2.22.0
 - junit v5.8.2

# **Assumptions**

 1) It is assumed that the log file is locally stored (uncompressed) and its path is specified by the user during the program execution.
 2) It is also assumed that the timestamp will always be in proper format and that the malformed entries are not caused due to a malformed timestamp.
 3) Log lines of the following format are not considered as malformed but as a normal case of a not found request:

    `    www-b2.proxy.aol.com - - [01/Aug/1995:01:48:29 -0400] "GET /pub/winvn/readme.txt HTTP/1.0" 404 -` 
 4) For features [5] & [7] both successful and unsuccessful requests have been considered in the counting. 
