package atm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by Delcho Delov on 03.07.18.
 */
public class FewNotesDistributorTest {
    private FewNotesDistributor distributor = new FewNotesDistributor();

    private NavigableMap<Note, Integer> prepareAvailable(){
        final NavigableMap<Note, Integer> availableNotes = new TreeMap<>((note, note2) -> note.getFaceValue()-note2.getFaceValue());
        availableNotes.put(Note.Fifty, 100);
        availableNotes.put(Note.Twenty, 250);
        availableNotes.put(Note.Ten, 500);
        return availableNotes;
    }

    @Test
    public void changeZero() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        distributor.change(map, 0, prepareAvailable());
        assertThat(map==null, is(false));
        assertThat(map.size(), is(0));
    }

    @Test
    public void changeTen() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        distributor.change(map,10, prepareAvailable());
        assertThat(map==null, is(false));
        assertThat(map.size(), is(1));
        assertThat(map.containsKey(Note.Ten), is(true));
        assertThat(map.containsValue(1), is(true));
    }
    @Test
    public void change30() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        distributor.change(map, 30, prepareAvailable());
        assertThat(map==null, is(false));
        assertThat(map.size(), is(2));
        assertThat(map.containsKey(Note.Twenty), is(true));
        assertThat(map.get(Note.Twenty), is(1));
        assertThat(map.containsKey(Note.Ten), is(true));
        assertThat(map.get(Note.Ten ), is(1));
    }

    @Test
    public void change300() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        distributor.change(map, 300, prepareAvailable());
        assertThat(map==null, is(false));
        assertThat(map.size(), is(3));
        assertThat(map.containsKey(Note.Fifty), is(true));
        assertThat(map.get(Note.Fifty ), is(5));
        assertThat(map.containsKey(Note.Twenty), is(true));
        assertThat(map.get(Note.Twenty), is(2));
        assertThat(map.containsKey(Note.Ten), is(true));
        assertThat(map.get(Note.Ten ), is(1));
    }
    @Test
    public void change310() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        distributor.change(map, 310, prepareAvailable());
        assertThat(map==null, is(false));
        assertThat(map.size(), is(2));
        assertThat(map.containsKey(Note.Fifty), is(true));
        assertThat(map.get(Note.Fifty ), is(6));
        assertThat(map.containsKey(Note.Ten), is(true));
        assertThat(map.get(Note.Ten ), is(1));
    }
    @Test
    public void change310x10() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        final NavigableMap<Note, Integer> available = prepareAvailable();
        available.put(Note.Fifty, 0);
        available.put(Note.Twenty, 0);
        distributor.change(map, 310, available);
        assertThat(map==null, is(false));
        assertThat(map.size(), is(1));
        assertThat(map.containsKey(Note.Ten), is(true));
        assertThat(map.get(Note.Ten ), is(31));
    }

    @Test(expected = AtmException.class)
    public void changeNotDev() throws Exception {
        final Map<Note, Integer> map = new HashMap<>();
        final NavigableMap<Note, Integer> available = prepareAvailable();
        available.put(Note.Fifty, 0);
        available.put(Note.Twenty, 340);
        available.remove(Note.Ten);
        distributor.change(map, 310, available);
    }
}