
#1. Make target directory
rm -rf target
mkdir target

#2. Compile ImageToChar:
javac -d ./target src/java/edu/school21/printer/*/*.java

#3. Copy resources
cp -a src/resources target

#4. Archive creation
jar cvfm target/image-to-chars-printer.jar src/manifest.txt -C target/  .

#5. Run
java -jar "target/image-to-chars-printer.jar" . o