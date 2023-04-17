@ECHO OFF
javac ArxmlSort.java
ECHO Compilation done.......
ECHO ------------------------------------------------------
ECHO Normal case test:.......
java ArxmlSort.java Normal.arxml
ECHO ------------------------------------------------------
ECHO Not valid file extension case test:.......
java ArxmlSort.java EZ.txt
ECHO ------------------------------------------------------
ECHO Empty file case test:.......
java ArxmlSort.java Empty.arxml
ECHO ------------------------------------------------------

pause


