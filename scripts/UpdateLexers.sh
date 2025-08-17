curl -o JavaLexer.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaLexer.g4
curl -o JavaParser.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaParser.g4
curl -o JavaParserBase.java https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/Java/JavaParserBase.java

java -jar antlr-4.13.2-complete.jar -listener   -visitor  -Dlanguage=Java JavaLexer.g4
java -jar antlr-4.13.2-complete.jar -listener    -Dlanguage=Java JavaParser.g4


mkdir -p ../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/


for f in *.java; do
    echo "package Tools.CodeParsers.ANTLR;" | cat - "$f" > temp && mv temp "$f"
    mv "$f" ../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"$f"
done
