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

**Usage with Examples**

The program can be executed using `java -jar NasaLogAnalyzer-1.0.jar` command.
It is noted that in order to load and parse the log file, the user has to provide the relevant path in a parameter.
NasaLogAnalyzer accepts **up to 2 parameters**, the log file path and one more option from the ones provided in the previous paragraph.
If no option is provided apart from the log file path, the program will produce all the implemented sub-reports.
Following are some example usages:

  1. `java -jar NasaLogAnalyzer-1.0.jar "/home/rheluser/Desktop/access_log_Aug95" `
  2. `java -jar NasaLogAnalyzer-1.0.jar "/home/rheluser/Desktop/access_log_Aug95" successPercent`

# **Dependencies**

 - maven-jar-plugin v3.2.2
 - maven-surefire-plugin v2.22.0
 - junit v5.8.2

# **Assumptions**

 1) It is assumed that the log file is locally stored (uncompressed) and its path is specified by the user during the program execution.
 2) It is also assumed that the timestamp will always be in proper format and that the malformed entries are not caused due to a malformed timestamp.
 3) Log lines of the following format are **_not considered as malformed_** but as a normal case of a not found request:

    `    www-b2.proxy.aol.com - - [01/Aug/1995:01:48:29 -0400] "GET /pub/winvn/readme.txt HTTP/1.0" 404 -` 
 4) Log lines where the request string is not exactly in the format "{request method}{single space}{request URL}{single space}{protocol}" **_are considered as malformed_**.
 5) For features [5] & [7] both successful and unsuccessful requests have been considered in the counting. 


# **Sample Output Report**

`********************
*** Final Report ***
********************
Percentage of successful requests: 99.3545%
Percentage of unsuccessful requests: 0.6455%
**********************************************************************
Top 10 Requested Pages************************************************
**********************************************************************
[URL] /images/NASA-logosmall.gif / [No of Requests] 97267
[URL] /images/KSC-logosmall.gif / [No of Requests] 75278
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 67349
[URL] /images/USA-logosmall.gif / [No of Requests] 66968
[URL] /images/WORLD-logosmall.gif / [No of Requests] 66344
[URL] /images/ksclogo-medium.gif / [No of Requests] 62663
[URL] /ksc.html / [No of Requests] 43615
[URL] /history/apollo/images/apollo-logo1.gif / [No of Requests] 37804
[URL] /images/launch-logo.gif / [No of Requests] 35116
[URL] / / [No of Requests] 30100
**********************************************************************
**********************************************************************
Top 10 Unsuccessful Page Requests*************************************
**********************************************************************
[URL] /pub/winvn/readme.txt
[URL] /pub/winvn/release.txt
[URL] /shuttle/missions/STS-69/mission-STS-69.html
[URL] /images/nasa-logo.gif
[URL] /shuttle/missions/sts-68/ksc-upclose.gif
[URL] /elv/DELTA/uncons.htm
[URL] /history/apollo/sa-1/sa-1-patch-small.gif
[URL] /://spacelink.msfc.nasa.gov
[URL] /images/crawlerway-logo.gif
[URL] /history/apollo/a-001/a-001-patch-small.gif
**********************************************************************
**********************************************************************
Top 10 Hosts**********************************************************
**********************************************************************
[Hostname] edams.ksc.nasa.gov
[URL] /ksc.html / [No of Requests] 1020
[URL] /images/WORLD-logosmall.gif / [No of Requests] 870
[URL] /images/NASA-logosmall.gif / [No of Requests] 869
[URL] /images/USA-logosmall.gif / [No of Requests] 867
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 867
[Hostname] piweba4y.prodigy.com
[URL] /images/KSC-logosmall.gif / [No of Requests] 329
[URL] /images/NASA-logosmall.gif / [No of Requests] 260
[URL] /history/apollo/images/apollo-logo1.gif / [No of Requests] 177
[URL] /images/launch-logo.gif / [No of Requests] 158
[URL] /images/ksclogo-medium.gif / [No of Requests] 150
[Hostname] 163.206.89.4
[URL] /images/NASA-logosmall.gif / [No of Requests] 568
[URL] /htbin/cdt_main.pl / [No of Requests] 360
[URL] /shuttle/countdown/images/countclock.gif / [No of Requests] 347
[URL] /ksc.html / [No of Requests] 251
[URL] /images/USA-logosmall.gif / [No of Requests] 237
[Hostname] piweba5y.prodigy.com
[URL] /images/KSC-logosmall.gif / [No of Requests] 267
[URL] /images/NASA-logosmall.gif / [No of Requests] 230
[URL] /images/ksclogo-medium.gif / [No of Requests] 152
[URL] /history/apollo/images/apollo-logo1.gif / [No of Requests] 143
[URL] /images/launch-logo.gif / [No of Requests] 141
[Hostname] piweba3y.prodigy.com
[URL] /images/KSC-logosmall.gif / [No of Requests] 287
[URL] /images/NASA-logosmall.gif / [No of Requests] 241
[URL] /history/apollo/images/apollo-logo1.gif / [No of Requests] 147
[URL] /images/launch-logo.gif / [No of Requests] 142
[URL] /images/ksclogo-medium.gif / [No of Requests] 137
[Hostname] www-d1.proxy.aol.com
[URL] /images/NASA-logosmall.gif / [No of Requests] 183
[URL] /images/KSC-logosmall.gif / [No of Requests] 166
[URL] /images/ksclogo-medium.gif / [No of Requests] 128
[URL] /images/WORLD-logosmall.gif / [No of Requests] 121
[URL] /images/USA-logosmall.gif / [No of Requests] 117
[Hostname] www-b2.proxy.aol.com
[URL] /images/NASA-logosmall.gif / [No of Requests] 199
[URL] /images/KSC-logosmall.gif / [No of Requests] 159
[URL] /images/ksclogo-medium.gif / [No of Requests] 134
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 133
[URL] /images/USA-logosmall.gif / [No of Requests] 131
[Hostname] www-b3.proxy.aol.com
[URL] /images/NASA-logosmall.gif / [No of Requests] 190
[URL] /images/KSC-logosmall.gif / [No of Requests] 149
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 134
[URL] /images/ksclogo-medium.gif / [No of Requests] 130
[URL] /images/USA-logosmall.gif / [No of Requests] 128
[Hostname] www-c5.proxy.aol.com
[URL] /images/NASA-logosmall.gif / [No of Requests] 161
[URL] /images/KSC-logosmall.gif / [No of Requests] 132
[URL] /images/USA-logosmall.gif / [No of Requests] 113
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 112
[URL] /images/ksclogo-medium.gif / [No of Requests] 108
[Hostname] www-b5.proxy.aol.com
[URL] /images/NASA-logosmall.gif / [No of Requests] 164
[URL] /images/KSC-logosmall.gif / [No of Requests] 147
[URL] /images/ksclogo-medium.gif / [No of Requests] 116
[URL] /images/USA-logosmall.gif / [No of Requests] 116
[URL] /images/MOSAIC-logosmall.gif / [No of Requests] 116`
**********************************************************************
