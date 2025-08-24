SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

curl -o JavaLexer.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaLexer.g4
curl -o JavaParser.g4 https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/JavaParser.g4
curl -o JavaParserBase.java https://raw.githubusercontent.com/antlr/grammars-v4/refs/heads/master/java/java/Java/JavaParserBase.java

mkdir -p "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/"


java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener   -visitor  -Dlanguage=Java JavaLexer.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 
java -jar "$SCRIPT_DIR"/antlr-4.13.2-complete.jar  -package Tools.CodeParsers.ANTLR -listener    -Dlanguage=Java JavaParser.g4 -o "$SCRIPT_DIR/../AiTestGenerator/src/main/java/Tools/CodeParsers/ANTLR/" 


