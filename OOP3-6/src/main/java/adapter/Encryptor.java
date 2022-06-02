package adapter;

public interface Encryptor {
    byte[] encrypt(Object object);
    Object decrypt(byte[] data);

}
