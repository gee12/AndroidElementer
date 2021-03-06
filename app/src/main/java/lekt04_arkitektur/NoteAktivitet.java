package lekt04_arkitektur;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Simpel aktivitet til at tage noter
 * Benytter MinApp.data - som vi er SIKRE på er initialiseret,
 * da Application Singletons onCreate() metode bliver kaldt som
 * det første når en app (gen)startes
 *
 * @author Jacob Nordfalk
 */
public class NoteAktivitet extends AppCompatActivity implements OnClickListener {

  EditText noteEditText;
  private TextView alleNoterTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LinearLayout linearLayout = new LinearLayout(this);
    linearLayout.setOrientation(LinearLayout.VERTICAL);

    TextView textView = new TextView(this);
    textView.setText("Velkommen, " + MinApp.getData().navn + ", skriv dine noter herunder:");
    linearLayout.addView(textView);

    noteEditText = new EditText(this);
    linearLayout.addView(noteEditText);

    String seneste_note = MinApp.prefs.getString("seneste_note","");
    noteEditText.setText(seneste_note);

    Button okKnap = new Button(this);
    okKnap.setText("OK");
    okKnap.setOnClickListener(this);
    linearLayout.addView(okKnap);

    alleNoterTv = new TextView(this);
    alleNoterTv.setText("");
    linearLayout.addView(alleNoterTv);

    ScrollView scrollView = new ScrollView(this);
    scrollView.addView(linearLayout);
    setContentView(scrollView);

    opdaterSkærm();
  }

  @Override
  public void onClick(View v) {
    String note = noteEditText.getText().toString();
    MinApp.prefs.edit().putString("seneste_note", note).apply();
    MinApp.getData().noter.add(note);
    MinApp.gemData();
    noteEditText.setText("");
    opdaterSkærm();
  }

  private void opdaterSkærm() {
    String notetekst = MinApp.getData().noter.toString().replaceAll(", ", "\n");
    alleNoterTv.setText("Noter:\n" + notetekst);
  }
}
