package atm;

/**
 * Created by Delcho Delov on 02.07.18.
 */
public enum Note {
    Ten(10),
    Twenty(20),
    Fifty(50);

    private int faceValue;

    Note(int value) {
        faceValue = value;
    }
    public int getFaceValue(){
        return faceValue;
    }
}
