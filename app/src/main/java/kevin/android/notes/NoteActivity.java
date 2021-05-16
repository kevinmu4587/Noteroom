package kevin.android.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    private EditText editText;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.editText);
        String content = getIntent().getStringExtra("content");
        // we can't trim here because that only updates the edittext, not the recyclerview note dao
        //content = content.trim();
        id = getIntent().getIntExtra("id", 0);
        editText.setText(content);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDao().save(editText.getText().toString().trim(), id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);

        MenuItem deleteItem = menu.findItem(R.id.deleteItem);
        MenuItem saveItem = menu.findItem(R.id.saveNote);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.deleteItem:
                deleteNote();
                return true;
            case R.id.saveNote:
                Toast.makeText(this, "Saved note", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteNote() {
        MainActivity.database.noteDao().delete(id);
        Toast.makeText(this, "Deleted note", Toast.LENGTH_LONG).show();
        finish();
    }
}