all: parser Compiler C3aVM NasmVM SaVM

SaVM: sa/*.java SaVM.java
	javac sa/*.java
	javac -cp ".:../xerces-2_12_1/*" SaVM.java

NasmVM.jar : NasmVM
	jar cmf NasmVM.mf NasmVM.jar NasmVM.class nasm

NasmVM: nasm/*.java NasmVM.java
	javac nasm/*.java
	javac NasmVM.java

C3aVM: c3a/*.java C3aVM.java
	javac c3a/*.java
	javac C3aVM.java

Compiler.jar : Compiler
	jar cmf Compiler.mf Compiler.jar *.class sc sa ts nasm util c3a fg ig2

Compiler: Compiler.java sa/*.java c3a/*.java ts/*.java nasm/*.java fg/*.java util/intset/*.java  util/graph/*.java ig2/*.java
	javac sa/*.java
	javac c3a/*.java
	javac ts/*.java
	javac nasm/*.java
	javac fg/*.java
	javac util/intset/*.java
	javac util/graph/*.java
	javac ig2/*.java
	javac Compiler.java

parser: grammaireL.sablecc
	java -jar ../sablecc/sablecc.jar grammaireL.sablecc

clean:
	-rm *.class
	-rm sa/*.class
	-rm c3a/*.class
	-rm ts/*.class
	-rm nasm/*.class
	-rm fg/*.class
	-rm ig2/*.class
	-rm util/intset/*.class
	-rm util/graph/*.class

#java -cp ".:../xerces-2_12_1/*" SaVM -sa add1.sa
