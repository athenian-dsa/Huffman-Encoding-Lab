import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: make sure tests do not depend on eachother (the original had the same problem)
public class MyTests {
    private static final int[] counts1 = {0, 2, 0, 0, 9, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 2};
    private static final int[] counts2 = {2, 0, 0, 0, 7, 0, 0, 4, 0, 0, 0, 4, 0, 1, 2, 0, 0, 1, 8, 1, 0, 0, 0, 0, 0, 0, 7};

    @Test
    public void testCreateHuffmanTree() {
        Node root1 = Node.createHuffmanTree(counts1);
        int expectedHashCode1 = 745235532;
        assert root1 != null;
        assertEquals(expectedHashCode1, root1.hashCode());

        Node root2 = Node.createHuffmanTree(counts2);
        int expectedHashCode2 = 836291287;
        assert root2 != null;
        assertEquals(expectedHashCode2, root2.hashCode());
    }

    // assumes createHuffmanTree works
    @Test
    public void testCreateCode() {
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        String[] binaryArray = new String[27];

        binaryArray['B' - 'A'] = "101";
        binaryArray['E' - 'A'] = "0";
        binaryArray['K' - 'A'] = "1111";
        binaryArray['P' - 'A'] = "1110";
        binaryArray['R' - 'A'] = "1100";
        binaryArray['S' - 'A'] = "100";
        binaryArray[26] = "1101";
        assertArrayEquals(binaryArray, code1);

        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        String[] binaryArrayDos = new String[27];

        binaryArrayDos['A' - 'A'] = "0001";
        binaryArrayDos['E' - 'A'] = "111";
        binaryArrayDos['H' - 'A'] = "011";
        binaryArrayDos['L' - 'A'] = "010";
        binaryArrayDos['N' - 'A'] = "00100";
        binaryArrayDos['O' - 'A'] = "0011";
        binaryArrayDos['R' - 'A'] = "0000";
        binaryArrayDos['S' - 'A'] = "10";
        binaryArrayDos['T' - 'A'] = "00101";
        binaryArrayDos[26] = "110";
        assertArrayEquals(code2, binaryArrayDos);
    }

    // assumes createHuffmanTree and createCode works
    @Test
    public void testEncode() {
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        String bin1 = Node.encode("BEEKEEPERS KEEP BEES", code1);
        assertEquals(bin1, "101 0 0 1111 0 0 1110 0 1100 100 1101 1111 0 0 1110 1101 101 0 0 100");
        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        String bin2 = Node.encode("SHE SELLS SEA SHELLS ON THE SEA SHORE", code2);
        assertEquals(bin2, "10 011 111 110 10 111 010 010 10 110 10 111 0001 110 10 011 111 010 010 10 110 0011 00100 110 00101 011 111 110 10 111 0001 110 10 011 0011 0000 111");
    }

    // assumes createHuffmanTree, createCode, and encode work
    @Test
    public void testDecode() {
        Node root1 = Node.createHuffmanTree(counts1);
        String[] code1 = Node.createCode(root1);
        String bin1 = Node.encode("BEEKEEPERS KEEP BEES", code1);
        String decodedMessage1 = Node.decode(bin1, code1);
        assertEquals("BEEKEEPERS KEEP BEES", decodedMessage1);

        Node root2 = Node.createHuffmanTree(counts2);
        String[] code2 = Node.createCode(root2);
        String bin2 = Node.encode("SHE SELLS SEA SHELLS ON THE SEA SHORE", code2);
        String decodedMessage2 = Node.decode(bin2, code2);
        assertEquals("SHE SELLS SEA SHELLS ON THE SEA SHORE", decodedMessage2);
    }

    @Test
    public void testEverything() {
        String message3 = "TURKEY TROTS TO WATER THE WORLD WONDERS";
        int[] counts3 = Node.countChars(message3);
        Node root3 = Node.createHuffmanTree(counts3);
        String[] code3 = Node.createCode(root3);
        String bin3 = Node.encode(message3, code3);
        String decodedMessage3 = Node.decode(bin3, code3);
        assertEquals("TURKEY TROTS TO WATER THE WORLD WONDERS", decodedMessage3);
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

    @Test
    public void testCountChars() {
        String message1 = "BEEKEEPERS KEEP BEES";
        int[] acc1 = Node.countChars(message1);
        assertArrayEquals(acc1, counts1);

        String message2 = "SHE SELLS SEA SHELLS ON THE SEA SHORE";
        int[] acc2 = Node.countChars(message2);
        assertArrayEquals(acc2, counts2);
    }
}