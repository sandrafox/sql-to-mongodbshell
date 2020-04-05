public class LexicalAnalyzer {
    private char curChar = 0;
    private String curString;
    private Token curToken;
    private String s;
    private int curPos;

    public void analyze(String is) {
        s = is;
        curPos = -1;
        nextChar();
    }

    private void nextChar() {
        curPos++;
        if (curPos >= s.length()) {
            curChar = '$';
        } else {
            curChar = s.charAt(curPos);
        }
    }

    public void nextToken() throws ParserException {
        curString = "" + curChar;
        switch (curChar) {
            case '>':
                nextChar();
                curToken = Token.GT;
                break;
            case '<':
                nextChar();
                if (curChar == '>') {
                    nextChar();
                    curToken = Token.NE;
                } else curToken = Token.LT;
                break;
            case '=':
                nextChar();
                curToken = Token.EQ;
                break;
            case ',':
                nextChar();
                curToken = Token.COMMA;
                break;
            case '*':
                nextChar();
                curToken = Token.STAR;
                break;
            case '$':
                nextChar();
                curToken = Token.EOL;
                break;
            case '"':
                nextChar();
                StringBuilder sb = new StringBuilder(curString);
                while (curChar != '"') {
                    sb.append(curChar);
                    nextChar();
                }
                curString = sb.toString();
                curToken = Token.STRING;
            default:
                if (Character.isDigit(curChar) || curChar == '-') {
                    nextChar();
                    sb = new StringBuilder(curString);
                    while (Character.isDigit(curChar)) {
                        sb.append(curChar);
                        nextChar();
                    }
                    if (curChar == '.') {
                        nextChar();
                        if (!Character.isDigit(curChar)) throw new SyntaxError();
                        while (Character.isDigit(curChar)) {
                            sb.append(curChar);
                            nextChar();
                        }
                    }
                    curString = sb.toString();
                    curToken = Token.NUMBER;
                } else if (Character.isLetter(curChar) || curChar == '_' ) {
                    switch (curChar) {
                        case 's':
                            curString = getString(6);
                            if (curString.equals("select")) {
                                curToken = Token.SELECT;
                                nextChar();
                                return;
                            }
                            break;
                        case 'f':
                            curString = getString(4);
                            if (curString.equals("from")) {
                                curToken = Token.FROM;
                                nextChar();
                                return;
                            }
                            if (curString.equals("fals")) {
                                nextChar();
                                if (curChar == 'e') {
                                    curString += curChar;
                                    curToken = Token.BOOL;
                                    nextChar();
                                    return;
                                }
                                if (!(Character.isLetter(curChar) || curChar == '_' || Character.isDigit(curChar))) {
                                    curToken = Token.ID;
                                    return;
                                }
                                curString += curChar;
                            }
                            break;
                        case 'w':
                            curString = getString(5);
                            if (curString.equals("where")) {
                                curToken = Token.WHERE;
                                nextChar();
                                return;
                            }
                            break;
                        case 'o':
                            curString = getString(6);
                            if (curString.equals("offset")) {
                                curToken = Token.OFFSET;
                                nextChar();
                                return;
                            }
                            break;
                        case 'l':
                            curString = getString(5);
                            if (curString.equals("limit")) {
                                curToken = Token.LIMIT;
                                nextChar();
                                return;
                            }
                            break;
                        //TODO add true, false, and
                        case 't':
                            curString = getString(4);
                            if (curString.equals("true")) {
                                curToken = Token.BOOL;
                                nextChar();
                                return;
                            }
                            break;
                        case 'a':
                            curString = getString(3);
                            if (curString.equals("and")) {
                                curToken = Token.AND;
                                nextChar();
                                return;
                            }
                            break;
                    }
                    nextChar();
                    sb = new StringBuilder(curString);
                    while (Character.isLetter(curChar) || curChar == '_' || Character.isDigit(curChar)) {
                        sb.append(curChar);
                        nextChar();
                    }
                    curString = sb.toString();
                    curToken = Token.ID;
                } else if (Character.isWhitespace(curChar)) {
                    nextChar();
                    while (Character.isWhitespace(curChar)) nextChar();
                    curToken = Token.SPACE;
                } else {
                    throw new SyntaxError();
                }
        }
    }

    private String getString(int length) {
        String res = s.substring(curPos, curPos + length);
        curPos += length - 1;
        return res;
    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }

    public String getCurString() {
        return curString;
    }
}
