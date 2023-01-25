package info.inpureprojects.core.Scripting.Objects;

public class JavaScriptCompressor {

    private final String script;
    private final StringBuffer outputBuffer;
    private int pos;
    private char ch;
    private char lastAppend;
    private boolean endReached;
    private boolean contentAppendedAfterLastIdentifier = true;

    private JavaScriptCompressor(String script) {
        this.script = script;
        this.outputBuffer = new StringBuffer(script.length());
        nextChar();

        while (!this.endReached) {
            if (Character.isJavaIdentifierStart(this.ch)) {
                renderIdentifier();
                continue;
            }
            if (this.ch == ' ') {
                skipWhiteSpace();
                continue;
            }
            if (isWhitespace()) {

                skipWhiteSpace();
                continue;
            }
            if (this.ch == '"' || this.ch == '\'') {

                renderString();
                continue;
            }
            if (this.ch == '/') {

                nextChar();
                if (this.ch == '/') {
                    nextChar();
                    skipLineComment();
                    continue;
                }
                if (this.ch == '*') {
                    nextChar();
                    skipBlockComment();
                    continue;
                }
                append('/');
                continue;
            }
            append(this.ch);
            nextChar();
        }
    }

    private void nextChar() {
        if (!this.endReached) {
            if (this.pos < this.script.length()) {
                this.ch = this.script.charAt(this.pos++);
            } else {
                this.endReached = true;
                this.ch = Character.MIN_VALUE;
            }
        }
    }

    private void renderIdentifier() {
        if (!this.contentAppendedAfterLastIdentifier) append(' ');
        append(this.ch);
        nextChar();
        while (Character.isJavaIdentifierPart(this.ch)) {
            append(this.ch);
            nextChar();
        }
        this.contentAppendedAfterLastIdentifier = false;
    }

    private void skipWhiteSpace() {
        if (this.ch == '\n' || this.ch == '\r') {
            renderNewLine();
        } else {
            append(this.ch);
        }
        nextChar();
        while (this.ch == '\n' || this.ch == '\r' || this.ch == ' ' || this.ch == '\t') {
            if (this.ch == '\n' || this.ch == '\r') {
                renderNewLine();
            }
            nextChar();
        }
    }

    private boolean isWhitespace() {
        return (this.ch == '\r' || this.ch == ' ' || this.ch == '\t' || this.ch == '\n');
    }

    private void renderString() {
        char startCh = this.ch;
        append(this.ch);
        nextChar();
        while (true) {
            if (this.ch == '\n' || this.ch == '\r' || this.endReached) {
                return;
            }

            if (this.ch == '\\') {
                append(this.ch);
                nextChar();
                if (this.ch == '\n' || this.ch == '\r' || this.endReached) {
                    return;
                }

                append(this.ch);
                nextChar();
                continue;
            }
            append(this.ch);
            if (this.ch == startCh) {
                nextChar();
                return;
            }
            nextChar();
        }
    }

    private void skipLineComment() {
        while (this.ch != '\r' && this.ch != '\n') {
            if (this.endReached) {
                return;
            }
            nextChar();
        }
    }

    private void skipBlockComment() {
        while (true) {
            if (this.endReached) {
                return;
            }
            if (this.ch == '*') {
                nextChar();
                if (this.ch == '/') {
                    nextChar();
                    return;
                }
                continue;
            }
            nextChar();
        }
    }

    private void append(char ch) {
        this.lastAppend = ch;
        this.outputBuffer.append(ch);
        this.contentAppendedAfterLastIdentifier = true;
    }

    private void renderNewLine() {
        if (this.lastAppend != '\n' && this.lastAppend != '\r') {
            append('\n');
        }
    }

    public static String compress(String script) {
        JavaScriptCompressor jsc = new JavaScriptCompressor(script);
        return jsc.outputBuffer.toString();
    }
}
