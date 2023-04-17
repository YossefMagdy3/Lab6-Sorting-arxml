# Exception Handling Assignment
## Description
- Write a program that reads an ARXML file containing a list of containers, each with a unique ID.
- Reorder the containers alphabetically by their name sub- container "SHORT-NAME".
- The program should write the reordered containers to a new ARXML file.
## Requirements
- The name of the arxml file shall be an argument which needs to passed through the command 
line.
- If the file is not having .arxml extension then you should trigger a user defined handled 
exception “NotVaildAutosarFileException”.
- If the file is empty, then you should trigger user defined unhandled exception 
“EmptyAutosarFileException”
- The output file shall be named as the same of the input file concatenated with “_mod.arxml”
  e.g. if the input was named “Rte_Ecuc.arxml” then the output should be “Rte_Ecuc_mod.arxml”.
- Assume any missing requirement
