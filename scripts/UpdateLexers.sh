SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
mkdir -p "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"



curl -o JavaLexer.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaLexer.g4
curl -o JavaParser.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaParser.g4

curl -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/JavaParserBase.java" https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/Java/JavaParserBase.java
echo "package Tools.CodeParsers.ANTLR;" | cat - "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"JavaParserBase.java > temp && mv temp "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"JavaParserBase.java


java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener   -visitor  -Dlanguage=Java JavaLexer.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 
java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener    -Dlanguage=Java JavaParser.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 

rm JavaLexer.g4
rm JavaParser.g4

