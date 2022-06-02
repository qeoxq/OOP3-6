package adapter;

import by.egorstrupinski.bsuir.CryptService;

import javax.management.RuntimeMBeanException;
import java.io.*;


public class Adapter implements Encryptor {

    private final CryptService cryptService;

    public Adapter() {
        this.cryptService = new CryptService();
    }

    @Override
    public byte[] encrypt(Object object) {
        return cryptService.encrypt(serialize(object));
    }

    @Override
    public Object decrypt(byte[] data)  {
        return deserialize(cryptService.decrypt(data));
    }

    private byte[] serialize(Object object) {
        try (final var out = new ByteArrayOutputStream(); final var objectOut = new ObjectOutputStream(out)) {
            objectOut.writeObject(object);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object deserialize(byte[] data) {
        try (final var input = new ByteArrayInputStream(data); final var objectInput = new ObjectInputStream(input)) {
            return objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
