# Alignment of DNA

This project is a school assignment described in `PI421_biologie.pdf`. The goal
of this project it to compute the best alignment of two sequence of DNA
under the consideration the gap penality. My classmate and I have implemented
the Gotoh's Algorithm with Java. The source codes are in the folder
`PI_bio/src/workspace` and the test DNA sequence data are in
`PI_bio/SubSeq_Res{52,320,3275}.txt`. 


## Explanation the utility of each file in `PI_bio/src/workspace`
`ReadFile.java` is for read a file with DNA sequence data format like `SubSeq_Res{52,320,3275}.txt` 
`Common_sequence.java` is for calculate the longest common sequence of two strings.  
`Type_Result.java` is for customize the type of result of common_sequence.  
`Refline_alignement.java` is for calculate the alignement with score difined in `Blosum50.java`.  
`Keyword_tree.java` is for construct the type keyword tree.  
`Search_tool.java` is a tool to find the local alignement. 
