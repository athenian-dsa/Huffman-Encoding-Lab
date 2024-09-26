import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTests {

    @Test
    public void testCountChars() {
        String message1 = "BEEKEEPERS KEEP BEES";
        int[] counts1 = Node.countChars(message1);
        assertArrayEquals(new int[]{0, 2, 0, 0, 9, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 2}, counts1);

        String message2 = "SHE SELLS SEA SHELLS ON THE SEA SHORE";
        int[] counts2 = Node.countChars(message2);
        assertArrayEquals(new int[]{2, 0, 0, 0, 7, 0, 0, 4, 0, 0, 0, 4, 0, 1, 2, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 7}, counts2);
    }

    @Test
    public void testCreateHuffmanTree() {
        int[] counts1 = {0, 2, 0, 0, 9, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 2};
        Node root1 = Node.createHuffmanTree(counts1);
        assertEquals("*: 20\n" +
                "├── E: 9\n" +
                "└── *: 11\n" +
                "    ├── *: 4\n" +
                "    │   ├── S: 2\n" +
                "    │   └── B: 2\n" +
                "    └── *: 7\n" +
                "        ├── *: 3\n" +
                "        │   ├── R: 1\n" +
                "        │   └──  : 2\n" +
                "        └── *: 4\n" +
                "            ├── P: 2\n" +
                "            └── K: 2\n", root1.toString());

        int[] counts2 = {2, 0, 0, 0, 7, 0, 0, 4, 0, 0, 0, 4, 0, 1, 2, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 7};
        Node root2 = Node.createHuffmanTree(counts2);
        assertEquals("*: 37\n" +
                "├── *: 15\n" +
                "│   ├── *: 7\n" +
                "│   │   ├── *: 3\n" +
                "│   │   │   ├── R: 1\n" +
                "│   │   │   └── A: 2\n" +
                "│   │   └── *: 4\n" +
                "│   │       ├── *: 2\n" +
                "│   │       │   ├── N: 1\n" +
                "│   │       │   └── T: 1\n" +
                "│   │       └── O: 2\n" +
                "│   └── *: 8\n" +
                "│       ├── L: 4\n" +
                "│       └── H: 4\n" +
                "└── *: 22\n" +
                "    ├── S: 8\n" +
                "    └── *: 14\n" +
                "        ├──  : 7\n" +
                "        └── E: 7\n", root2.toString());
    }

    @Test
    public void testCreateCode() {
        int[] counts1 = {0, 2, 0, 0, 9, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 2};
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        assertArrayEquals(new String[]{null, "101", null, null, "0", null, null, null, null, null, "1111", null, null, null, null, "1110", null, "1100", "100", null, null, null, null, null, null, null, "1101"}, code1);

        int[] counts2 = {2, 0, 0, 0, 7, 0, 0, 4, 0, 0, 0, 4, 0, 1, 2, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 7};
        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        assertArrayEquals(new String[]{"0001", null, null, null, "111", null, null, "011", null, null, null, "010", null, "00100", "0011", null, null, "0000", "10", "00101", null, null, null, null, null, null, "110"}, code2);
    }

    @Test
    public void testEncode() {
        String message1 = "BEEKEEPERS KEEP BEES";
        int[] counts1 = Node.countChars(message1);
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        String bin1 = Node.encode(message1, code1);
        assertEquals("101 0 0 1111 0 0 1110 0 1100 100 1101 1111 0 0 1110 1101 101 0 0 100", bin1.strip());

        String message2 = "SHE SELLS SEA SHELLS ON THE SEA SHORE";
        int[] counts2 = Node.countChars(message2);
        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        String bin2 = Node.encode(message2, code2);
        assertEquals("10 011 111 110 10 111 010 010 10 110 10 111 0001 110 10 011 111 010 010 10 110 0011 00100 110 00101 011 111 110 10 111 0001 110 10 011 0011 0000 111", bin2.strip());
    }

    @Test
    public void testDecode() {
        String message1 = "BEEKEEPERS KEEP BEES";
        int[] counts1 = Node.countChars(message1);
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        String bin1 = Node.encode(message1, code1);
        String decoded1 = Node.decode(bin1, code1);
        assertEquals(message1, decoded1);

        String message2 = "SHE SELLS SEA SHELLS ON THE SEA SHORE";
        int[] counts2 = Node.countChars(message2);
        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        String bin2 = Node.encode(message2, code2);
        String decoded2 = Node.decode(bin2, code2);
        assertEquals(message2, decoded2);
    }

    @Test
    public void testEverything() {
        String message = "TURKEY TROTS TO WATER THE WORLD WONDERS";
        int[] counts = Node.countChars(message);
        Node root = Node.createHuffmanTree(counts);
        String[] code = Node.createCode(root);
        String bin = Node.encode(message, code);
        String decoded = Node.decode(bin, code);
        assertEquals(message, decoded);
    }

    @Test
    public void testCalculateSavings() {
        String message1 = "BEEKEEPERS KEEP BEES";
        int savings1 = Node.calculateSavings(message1);
        assertEquals(111, savings1);

        String message2 = "SHE SELLS SEA SHELLS ON THE SEA SHORE";
        int savings2 = Node.calculateSavings(message2);
        assertEquals(184, savings2);

        String message3 = "TURKEY TROTS TO WATER THE WORLD WONDERS";
        int savings3 = Node.calculateSavings(message3);
        assertEquals(172, savings3);
    }
}