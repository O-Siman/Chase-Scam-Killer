import java.io.InputStream;
import java.util.Random;

public class B64InputStream extends InputStream {
    final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private Random random = new Random();
    final int size;
    final int totalCharacters;
    int characterCount = 0;

    public B64InputStream(int size) {
        this.size = size;
        this.totalCharacters = (size / 2) * 1024;
    }

    @Override
    public int read() {
        if (characterCount == totalCharacters) {
            return -1;
        }
        int index = random.nextInt(base64Chars.length());
        characterCount++;
        return base64Chars.charAt(index);
    }
}