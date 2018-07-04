package atm;

import java.util.*;

/**
 * Created by Delcho Delov on 02.07.18.
 */
public class AtmWithdraw {
    // notes in increasing (by face value) order
    final NavigableMap<Note, Integer> availableNotes = new TreeMap<>((note, note2) -> note.getFaceValue()-note2.getFaceValue());
    final int maxAmount;

    public AtmWithdraw(int maxAmount) {
        this.maxAmount = maxAmount;
    }
    public AtmWithdraw() {
        this.maxAmount = 400;
    }
    public void loadNote(Note note, int volume){
        availableNotes.put(note, volume);
    }

    public Map<Note, Integer> withdraw(int sum, Distributor distributor) throws AtmException{

        validateInput(sum, distributor);

        if(sum==0){
            return new HashMap<>();
        }
        final Map<Note, Integer> res = new HashMap<>();
        distributor.change(res, sum, availableNotes);

        validateOutput(res, sum, availableNotes);
        return res;
    }

    private void validateInput(int sum, Distributor distributor) throws
            AtmException {
        // data validations
        if(distributor==null){
            throw new AtmException("Please specify distributor implementation");
        }
        checkNotes(availableNotes);
        if(sum<0){
            throw new AtmException("Sum should be positive value");
        }
        if(sum>maxAmount){
            throw new AtmException("Sum should be less than or equals to " + maxAmount);
        }
        //check if sum is distinguishable via available notes
        int minFaceValue = availableNotes.firstKey().getFaceValue();
        if(sum % minFaceValue!=0){
            throw new AtmException("The amount must be divisible at "+minFaceValue);
        }
    }
    private void validateOutput(Map<Note, Integer> result, int sum, NavigableMap<Note, Integer> availableNotes) throws
            AtmException {
        //check result
        int total = 0;
        for (Map.Entry<Note, Integer> entry : result.entrySet()) {
            final Note note = entry.getKey();
            final Integer cnt = entry.getValue();
            final Integer availCount = availableNotes.get(note);
            if(availCount==null|| availCount<cnt){
                throw new AtmException("Not enough cash");
            }
            total += cnt * note.getFaceValue();
        }
        if(total<sum){
            throw new AtmException("Not enough cash");
        }
        if(total>sum){
            throw new AtmException("malware distribution detected");
        }
    }
    private void checkNotes(final Map<Note, Integer> notes) throws AtmException {
        if(notes==null || notes.isEmpty()){
            throw new AtmException("ATM empty");
        }
        for (Integer volume : notes.values()) {
            if(volume==null || volume<0){
                throw new AtmException("Notes count should be positive value or 0");
            }
        }
    }

}
