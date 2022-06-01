package ascii.stringshandler;

import org.junit.jupiter.api.Test;

import java.util.List;

import static ascii.stringshandler.FileHandler.readASCII;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASCIITest {

    @Test
    void stringWithSpace() {
        var input = "32";
        var strings = readASCII(input);
        assertEquals(List.of(" "), strings);
    }

    @Test
    void ordinalString() {
        var input = "72 101 108 108 111 44 32 119 111 114 108 100 33";
        var strings = readASCII(input);
        assertEquals(List.of("Hello, world!"), strings);
    }

    @Test
    void stringsList() {
        var input = "72  101    108  108   111 44 32 119 111 114  108 100 33 10 104 32 101 32 108 32 108 32 111 10 63";
        var strings = readASCII(input);
        assertEquals(List.of("Hello, world!", "h e l l o", "?"), strings);
    }

    @Test
    void stringsListWithExtraSpaces() {
        var input = "72 101 108 108 111 44 32 119 111 114 108 100 33 10 104 32 101 32 108 32 108 32 111 10 63";
        var strings = readASCII(input);
        assertEquals(List.of("Hello, world!", "h e l l o", "?"), strings);
    }

    @Test
    void blankInput() {
        var input = "  ";
        var strings = readASCII(input);
        assertEquals(List.of(), strings);
    }

    @Test
    void emptyInput() {
        var input = "";
        var strings = readASCII(input);
        assertEquals(List.of(), strings);
    }

    @Test
    void nullInput() {
        var strings = readASCII(null);
        assertEquals(List.of(), strings);
    }
}
