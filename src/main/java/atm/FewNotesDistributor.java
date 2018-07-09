package atm;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Delcho Delov on 03.07.18.
 *
 */
public class FewNotesDistributor implements Distributor {
    @Override
    public void/*Map<Note, Integer>*/ change(Map<Note, Integer> result, int sum, NavigableMap<Note, Integer>
            availableNotes) throws AtmException {
        if(sum==0){
            return;// result;
        }
        if(availableNotes.isEmpty()){
            throw new AtmException("Not enough cash");
        }
        Map.Entry<Note, Integer> maxEntry = availableNotes.lastEntry();
        Note maxNote = maxEntry.getKey();
        int x = sum / maxNote.getFaceValue();
        int rest = sum % maxNote.getFaceValue();
        if(x>maxEntry.getValue()){//not enough volume
            x=maxEntry.getValue();
            rest = sum - maxNote.getFaceValue()*x;
        }
        //if there is smaller value notes available, replace one of maxNotes for few of them
        //Example: 300=6*50 In this case 300=5(6-1)*50 + y*20+z*10
        //Example 2: 350=7*50 will not be affected
        if(x>1 && x<7 && rest==0 && availableNotes.size()>1 && availableNotes.firstEntry().getValue()>0){
            x-=1;
            rest = sum - maxNote.getFaceValue()*x;
        }
        if(x>0) {
            result.put(maxNote, x);
        }
        if(rest>0){
            NavigableMap<Note, Integer> remainingNotes = new TreeMap<>(availableNotes);
            remainingNotes.remove(maxNote);
            change(result, rest, remainingNotes);
        }
    }
}
