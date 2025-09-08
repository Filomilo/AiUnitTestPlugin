

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"


mkdir -p "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"

############################### java

curl -o JavaLexer.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaLexer.g4
curl -o JavaParser.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaParser.g4

curl -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/JavaParserBase.java" https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/Java/JavaParserBase.java
echo "package Tools.CodeParsers.ANTLR;" | cat - "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"JavaParserBase.java > temp && mv temp "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"JavaParserBase.java


java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener   -visitor  -Dlanguage=Java JavaLexer.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 
java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener    -Dlanguage=Java JavaParser.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 

rm JavaLexer.g4
rm JavaParser.g4



############################### python

curl -o Python3Lexer.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/python/python3/Python3Lexer.g4
curl -o Python3Parser.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/python/python3/Python3Parser.g4

curl -o  "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/Python3ParserBase.java" https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/python/python3/Java/Python3ParserBase.java
curl -o  "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/Python3LexerBase.java" https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/python/python3/Java/Python3LexerBase.java
echo "package Tools.CodeParsers.ANTLR;" | cat -  "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"Python3ParserBase.java > temp && mv temp "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"Python3ParserBase.java
echo "package Tools.CodeParsers.ANTLR;" | cat - "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"Python3LexerBase.java > temp && mv temp "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"Python3LexerBase.java

java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener   -visitor  -Dlanguage=Java Python3Lexer.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 
java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener    -Dlanguage=Java Python3Parser.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 


rm Python3Lexer.g4
rm Python3Parser.g4
