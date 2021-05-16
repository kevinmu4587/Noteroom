package kevin.android.notes;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("INSERT INTO notes (content) VALUES ('New note')")
    void create();

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();

    // :id refers to the id passed in the parameters of the method
    @Query("UPDATE notes SET content = :content WHERE ID = :id")
    void save(String content, int id);

    @Query("DELETE from notes WHERE id = :id")
    void delete(int id);
}
