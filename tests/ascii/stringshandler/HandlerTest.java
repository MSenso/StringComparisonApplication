package ascii.stringshandler;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ascii.stringshandler.FileHandler.uniqueFirst;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlerTest {

    @Test
    void listsWithCommonElements() {
        var list1 = List.of("Hello, world!", "M.", "...");
        var list2 = List.of("?", "Hello, world!");
        var result1 = uniqueFirst(list1, list2);
        var result2 = uniqueFirst(list2, list1);
        assertEquals(List.of("M.", "..."), result1);
        assertEquals(List.of("?"), result2);
    }

    @Test
    void uniqueListsElements() {
        var list1 = List.of("Hello, world!", "M.", "...");
        var list2 = List.of("?", " hello ", " world ");
        var result1 = uniqueFirst(list1, list2);
        var result2 = uniqueFirst(list2, list1);
        assertEquals(list1, result1);
        assertEquals(list2, result2);
    }

    @Test
    void sameElements() {
        var list1 = List.of("Hello, world!", "M.", "...");
        var list2 = List.of("Hello, world!", "M.", "...");
        var result1 = uniqueFirst(list1, list2);
        var result2 = uniqueFirst(list2, list1);
        assertEquals(List.of(), result1);
        assertEquals(List.of(), result2);
    }

    @Test
    void emptyLists() {
        var list1 = new ArrayList<String>();
        var list2 = new ArrayList<String>();
        var result1 = uniqueFirst(list1, list2);
        var result2 = uniqueFirst(list2, list1);
        assertEquals(List.of(), result1);
        assertEquals(List.of(), result2);
    }

    @Test
    void oneNullList() {
        var list2 = List.of("Hello, world!", "M.", "...");
        var result1 = uniqueFirst(null, list2);
        var result2 = uniqueFirst(list2, null);
        assertEquals(List.of(), result1);
        assertEquals(List.of(), result2);
    }

    @Test
    void nullLists() {
        var result1 = uniqueFirst(null, null);
        var result2 = uniqueFirst(null, null);
        assertEquals(List.of(), result1);
        assertEquals(List.of(), result2);
    }
}
