# SFDCDemo

Maven Project for test automation using selenium on SFDC.

Properties file has all SFDC information.

Here is the maven command that works:

mvn test -DSuite="testng" -Denvironment="QA" -Dbrowser="*firefox"
-DSuite:  refers to testng file under resources and mentions what classes to run.
-Dbrowser: which browser to run, currently Firefox/chrome works.
-Denvironment: Environment information can be configured, currently only one environment.


