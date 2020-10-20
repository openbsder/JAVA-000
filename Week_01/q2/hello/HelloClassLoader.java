package hello;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * HelloClassLoader
 *
 * @version V1.0
 */
public class HelloClassLoader extends ClassLoader {
    public static final String HELLO_CLASS_FILE_PATH = "hello/Hello.xlass";
    public static final String HELLO_CLASS_NAME = "Hello";
    public static final String HELLO_CLASS__HELLO_METHOD = "hello";

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass(HELLO_CLASS_NAME);

            Method helloMethod = helloClass.getMethod(HELLO_CLASS__HELLO_METHOD);
            helloMethod.invoke(helloClass.newInstance());
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Resolve Hello.xlass file and execute hello method failed: " + e.getMessage());
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes;

        try {
            classBytes = decode(readFile(HELLO_CLASS_FILE_PATH));
        } catch (IOException e) {
            throw new ClassNotFoundException("Class Hello not found, reason: " + e.getMessage());
        }

        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * readFile method is used to read file content to byte array
     *
     * @param fileName The file name
     * @return byte[]
     * @throws IllegalArgumentException if file name is null
     * @throws IOException              if an I/O error occurs reading from the stream
     */
    private byte[] readFile(String fileName) throws IOException {
        if (Objects.isNull(fileName)) {
            throw new IllegalArgumentException("File name must not be empty!");
        }

        return Files.readAllBytes(Paths.get(fileName));
    }

    /**
     * decode method is used to decode byte array.
     * <p>
     * NOTICE: decoded by '255 - source byte'
     *
     * @param bytes The source byte array
     * @return byte[]
     */
    private byte[] decode(byte[] bytes) {
        int len = bytes.length;
        byte[] decodeBytes = new byte[len];

        for (int i = 0; i < len; i++) {
            decodeBytes[i] = (byte) (255 - bytes[i]);
        }

        return decodeBytes;
    }
}