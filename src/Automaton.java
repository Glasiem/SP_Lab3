public class Automaton {
    private int state = 0;

    public int nextState(char c){
        switch (state) {
            case 0 -> {
                if (Character.isWhitespace(c)) {
                    state = 0;
                    return 3;
                }
                if ((c >= '1') && (c <= '9')) {
                    state = 1;
                    return 0;
                }
                if (c == '0') {
                    state = 2;
                    return 0;
                }
                if ((c == '"')) {
                    state = 7;
                    return 0;
                }
                if ((c == '\'')) {
                    state = 9;
                    return 0;
                }
                if ((c == '/')) {
                    state = 12;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || (c == '_') || (c == '$')) {
                    state = 16;
                    return 0;
                }
                if ((c == ';') || (c == '{') || (c == ',') || (c == '}') || (c == '(') || (c == ')')) {
                    state = 17;
                    return 2;
                }
                if ((c == '<') || (c == '>') || (c == '!') || (c == '=') || (c == '*') || (c == '%')) {
                    state = 18;
                    return 0;
                }
                if ((c == '|')) {
                    state = 19;
                    return 0;
                }
                if ((c == '&')) {
                    state = 20;
                    return 0;
                }
                if ((c == '+')) {
                    state = 21;
                    return 0;
                }
                if ((c == '-')) {
                    state = 22;
                    return 0;
                }
                if ((c == '?') || (c == ':') || (c == '.')) {
                    state = 23;
                    return 2;
                }
                return -1;
            }
            case 1 -> {
                if ((c >= '0') && (c <= '9')) {
                    return 0;
                }
                if (c == '.') {
                    state = 4;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                return 1;
            }
            case 2 -> {
                if (c == 'x') {
                    state = 3;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$') || ((c >= '0') && (c <= '9'))) {
                    state = 99;
                    return 0;
                }
                return 1;
            }
            case 3 -> {
                if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'F'))) {
                    state = 25;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 4 -> {
                if ((c >= '0') && (c <= '9')) {
                    state = 5;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 5 -> {
                if ((c >= '0') && (c <= '9')) {
                    return 0;
                }
                if (c == 'E') {
                    state = 6;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                return 1;
            }
            case 6 -> {
                if ((c >= '0') && (c <= '9')) {
                    state = 24;
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 7 -> {
                if (c == '"') {
                    state = 27;
                    return 2;
                }
                if (c == '\\') {
                    state = 8;
                    return 0;
                }
                if ((c >= 32) && (c <= 126)) {
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 8 -> {
                if ((c == '\\') || (c == 'n') || (c == 'r') || (c == 't') ||
                        (c == '"') || (c == 'f') || (c == 's') || (c == 'b') || (c == '\'')) {
                    state = 7;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 9 -> {
                if (c == '\'') {
                    return 2;
                }
                if (c == '\\') {
                    state = 10;
                    return 0;
                }
                if ((c >= 32) && (c <= 126)) {
                    state = 11;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 10 -> {
                if ((c == '\\') || (c == 'n') || (c == 'r') || (c == 't') ||
                        (c == '"') || (c == 'f') || (c == 'b') || (c == '\'')) {
                    state = 11;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 11 -> {
                if (c == '\'') {
                    return 2;
                }
                if (((c >= 32) && (c <= 126)) || (Character.isWhitespace(c))) {
                    state = 100;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 12 -> {
                if (c == '/') {
                    state = 13;
                    return 0;
                }
                if (c == '*') {
                    state = 14;
                    return 0;
                }
                state = 23;
                return 1;
            }
            case 13 -> {
                if ((c >= 32) && (c <= 126)) {
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 14 -> {
                if (c == '*') {
                    state = 15;
                    return 0;
                }
                if (((c >= 32) && (c <= 126)) || (Character.isWhitespace(c))) {
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 15 -> {
                if (c == '*') {
                    return 0;
                }
                if (c == '/') {
                    state = 26;
                    return 2;
                }
                if (((c >= 32) && (c <= 126)) || (Character.isWhitespace(c))) {
                    state = 14;
                    return 0;
                }
                state = 0;
                return -1;
            }
            case 16 -> {
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$') || ((c >= '0') && (c <= '9'))) {
                    return 0;
                }
                return 1;
            }
            case 18 -> {
                if (c == '=') {
                    state = 23;
                    return 2;
                }
                state = 23;
                return 1;
            }
            case 19 -> {
                if (c == '|') {
                    state = 23;
                    return 2;
                }
                state = 23;
                return 1;
            }
            case 20 -> {
                if (c == '&') {
                    state = 23;
                    return 2;
                }
                state = 23;
                return 1;
            }
            case 21 -> {
                if ((c == '=') || (c == '+')) {
                    state = 23;
                    return 2;
                }
                state = 23;
                return 1;
            }
            case 22 -> {
                if ((c == '=') || (c == '-')  || (c == '>')) {
                    state = 23;
                    return 2;
                }
                state = 23;
                return 1;
            }
            case 24 -> {
                if ((c >= '0') && (c <= '9')) {
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                return 1;
            }
            case 25 -> {
                if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'F'))) {
                    return 0;
                }
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$')) {
                    state = 99;
                    return 0;
                }
                return 1;
            }
            case 99 -> {
                if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) ||
                        (c == '_') || (c == '$') || ((c >= '0') && (c <= '9'))) {
                    return 0;
                }
                return 1;
            }
            case 100 -> {
                if ((c >= 33) && (c <= 126)) {
                    return 0;
                }
                return 1;
            }
        }
        return 47;
    }

    public int getState(){
        //System.out.println(state);
        if (state == 15){
            state = 14;
        }
        int temp = state;
        if ((state != 14) && (state != 7)){
            state = 0;
        }
        return temp;
    }
}
