
import lombok.Data;

/**
 * Created by Elior on 2016-08-03.
 */
public @Data class Key implements java.io.Serializable {
    public byte firstKey;
    public byte secondKey;

    public Key() {    }

    public Key(byte i1, byte i2) {
        firstKey = i1;
        secondKey = i2;
    }
}



//filesForTests\file.txt

//filesForTests\file.txt.encrypted

//filesForTests\key.bin