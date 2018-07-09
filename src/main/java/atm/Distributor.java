package atm;

import java.util.Map;
import java.util.NavigableMap; /**
 * Created by Delcho Delov on 03.07.18.
 */
public interface Distributor {
    /**
     * Note: input and output data are validated outside this method for simplicity
     *
     * @param sum positive amount of money
     * @param availableNotes cash register <Note, number of notes>
     * @return requested sum as <Note1, nb1><Note2, nb2>,etc. where Note1*nb1+Note2*nb2+..NoteN*nbN=sum
     * @throws AtmException
     */
    /*Map<Note,Integer>*/void change(Map<Note, Integer> result, int sum, NavigableMap<Note, Integer> availableNotes)
            throws AtmException;
}
