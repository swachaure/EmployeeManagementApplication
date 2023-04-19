1. I Think there is a conflict in requirement. In the first part is has 
"super rate (0% - 50% inclusive)", I understand the salary is package include super
However in the calculation details it has:
"super = gross income x super rate", I understand this calculation is for base salary plus super (exclusive supper).
Therefore I make assumption that the salary is exclusive supper

2. I've found that the requirement is never mention about partial monthly income for the first month employment start, or last month where employment finished. 
For the purpose of the test, I did not implement partial month income, therefore payment start date is not necessary. 
Moreover, the example is more to do with payment month instead of date: pay period = Month of March (01 March to 31 March).
Therefore, I change it to payment month in Employee, it accept int value 0 to 11 according to GregorianCalendar (for example GregorianCalendar.FEBRUARY)

3. I json format instead of csv format

4. Test is in PayrollTest.java

Step to run and test
1. Unzip the test.zip file
2. Install Eclipse IDE, Eclipse should connect to internet to download dependencies, firewall sometimes block Eclipse
2. Import the project as existing maven project into Eclipse workspace
3. Open PayrollTest.java, right click and Run as Junit test, the payslips.json file should be generated in target folder (refresh the folder if you can't see)
