package atm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Delcho Delov on 02.07.18.
 */
public class AtmWithdrawTest {
    private final DDistributor dd = new DDistributor();
    @Test
    public void withdraw() throws Exception {
        final Map<Note, Integer> withdraw = dd.withdraw(300, new FewNotesDistributor());
        printWithdraw(withdraw);
    }

    private void printWithdraw(Map<Note, Integer> withdraw) {
        for (Map.Entry<Note, Integer> entry : withdraw.entrySet()) {
            System.out.println(String.format("%d * %s -> %d",entry.getValue(), entry.getKey().name(), entry.getValue
                    ()*entry.getKey().getFaceValue() ));
        }
    }

    @Test
    public void pcs() throws Exception {
        Map<Note, Integer> accumulated = new HashMap<>();
//        boolean pcs = dd.pcs(accumulated, 10, 1, prepareAvailable());
//        assertThat(true, is(pcs));
//        assertThat(true, is(accumulated!=null));
//        assertThat(1, is(accumulated.size()));
//        assertThat(1, is(accumulated.get(Note.Ten).intValue()));

    }

    private NavigableMap<Note, Integer> prepareAvailable(){
        final NavigableMap<Note, Integer> availableNotes = new TreeMap<>((note, note2) -> note.getFaceValue()-note2.getFaceValue());
        availableNotes.put(Note.Fifty, 100);
        availableNotes.put(Note.Twenty, 250);
        availableNotes.put(Note.Ten, 500);
        return availableNotes;
    }
    private class DDistributor extends AtmWithdraw {
        public DDistributor() {
            super(400);
            loadNote(Note.Fifty, 100);
            loadNote(Note.Twenty, 250);
            loadNote(Note.Ten, 500);
        }
    }
}