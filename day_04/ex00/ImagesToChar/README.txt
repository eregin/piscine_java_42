
#1. Make target directory
rm -rf target
mkdir target

#2. Compile ImageToChar:
javac -d ./target src/java/edu/school21/printer/*/*.java

#3. Run
java -classpath target edu.school21.printer.app.Program o . /Users/ereginia/Downloads/it.bmp