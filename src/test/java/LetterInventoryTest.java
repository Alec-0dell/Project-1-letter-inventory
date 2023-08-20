import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LetterInventoryTest {

	@Test
	void testConstructor() {
		LetterInventory inventory = new LetterInventory("");
		assertTrue(inventory instanceof LetterInventory);
	}

	// Test get method throws needed exceptions
	@ParameterizedTest
	@MethodSource
	void testGetExcept(char letter, String string) {
		LetterInventory inventory = new LetterInventory(string);
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.get(letter);
		});
	}

	@ParameterizedTest
	@MethodSource
	void testGet(char letter, String string, int expected){
		LetterInventory inventory = new LetterInventory(string);
		int actual = inventory.get(letter);
		assertEquals(expected,actual);
	}

	// Check set rejects non-alphabetic characters
	@ParameterizedTest
	@MethodSource
	void testSetExceptLetter(char letter, int value, String string) {
		LetterInventory inventory = new LetterInventory(string);
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.set(letter, value);
		});
	}

	// Check set rejects negative values
	@Test
	void testSetExceptValue() {
		LetterInventory inventory = new LetterInventory("testing");
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.set('c', -2);
		});
	}

	@ParameterizedTest
	@MethodSource
	void testSet(String s, char c, int count) {
		LetterInventory inventory = new LetterInventory(s);
		inventory.set(c, count);
		int actual = inventory.get(c);
		assertEquals(count, actual);
	}

	@ParameterizedTest
	@MethodSource
	void testSize(String s, int expected) {
		LetterInventory inventory = new LetterInventory(s);
		int actual = inventory.size();
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource
	void testIsEmpty(String s, boolean expected) {
		LetterInventory inventory = new LetterInventory(s);
		boolean actual = inventory.isEmpty();
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource
	void testToString(String s, String expected) {
		LetterInventory inventory = new LetterInventory(s);
		String actual = inventory.toString();
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource
	void testAdd(String s1, String s2, String expected) {
		LetterInventory inventory1 = new LetterInventory(s1);
		LetterInventory inventory2 = new LetterInventory(s2);

		String actual = (inventory1.add(inventory2)).toString();
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource
	void testSubtract(String s1, String s2, String expected) {
		LetterInventory inventory1 = new LetterInventory(s1);
		LetterInventory inventory2 = new LetterInventory(s2);

		String actual = (inventory1.subtract(inventory2)).toString();
		assertEquals(expected, actual);
	}

	// Check that negative counts lead to a null result in subtract()
	@Test
	void testSubtractNull() {
		LetterInventory inventory1 = new LetterInventory("hello");
		LetterInventory inventory2 = new LetterInventory("hellomore");

		assertEquals(null, inventory1.subtract(inventory2));
	}

	private static Stream<Arguments> testGet() {
		return Stream.of(
				Arguments.of('b', "bob", 2),
				Arguments.of('Y', "flyBY", 2),
				Arguments.of('p', "hellothere", 0)
		);
	}

	private static Stream<Arguments> testSize() {
		return Stream.of(
				Arguments.of("bob",3),
				Arguments.of("OvErlAkE cAmpUs", 14),
				Arguments.of("2manyROBOTS",10)
		);
	}

	private static Stream<Arguments> testIsEmpty() {
		return Stream.of(
				Arguments.of("bob", false),
				Arguments.of("", true)
		);
	}

	private static Stream<Arguments> testToString() {
		return Stream.of(
				Arguments.of("ablmaaa", "[aaaablm]"),
				Arguments.of("rocketSHIPS", "[cehikoprsst]"),
				Arguments.of("abcdeffedcba", "[aabbccddeeff]")
				);
	}

	private static Stream<Arguments> testGetExcept() {
		return Stream.of(
				Arguments.of('[', "ablmaaa"),
				Arguments.of('#', "rocketSHIPS#"),
				Arguments.of('6', "abcdef6fedcba")
				);
	}

	private static Stream<Arguments> testSetExceptLetter() {
		return Stream.of(
				Arguments.of('[', 1, "hi there"),
				Arguments.of('#', 3, "does it work?"),
				Arguments.of('6', 4, "great")
				);
	}

	private static Stream<Arguments> testSet() {
		// String s, char c, int count
		return Stream.of(
				Arguments.of("hi there", 'b', 12),
				Arguments.of("does it work?", 'd', 0),
				Arguments.of("great", 't', 3)
				);
	}

	private static Stream<Arguments> testAdd() {
		return Stream.of(
				Arguments.of("George W. Bush", "Hillary Clinton", "[abceegghhiilllnnoorrstuwy]"),
				Arguments.of("babylon5", "centauri", "[aabbceilnnortuy]"),
				Arguments.of("whoshouldjoinrobotics", "you", "[bcdhhiijlnoooooorsstuuwy]")
				);
	}

	private static Stream<Arguments> testSubtract() {
		return Stream.of(
				Arguments.of("abceegghhiilllnnoorrstuwy", "beegghorsuw", "[achiilllnnorty]"),
				Arguments.of("[aabbceilnnortuy]", "centauri", "[abblnoy]"),
				Arguments.of("sparklyrat", "rat", "[aklprsy]")
				);
	}

}
