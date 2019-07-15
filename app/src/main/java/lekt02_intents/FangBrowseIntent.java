package lekt02_intents;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class FangBrowseIntent extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Tjek om vi er blevet startet med et Intent med en URL, f.eks. som
    // new Intent(Intent.ACTION_VIEW, Uri.parse("https://javabog.dk/OOP/kapitel3.jsp"));

    Intent i = getIntent();
    String urlFraIntent = i.getDataString();

    if (urlFraIntent == null) {
      TextView tv = new TextView(this);
      tv.setText("Dette eksempel viser hvordan man fanger et browserintent.\n"
              + "Gå ind på https://javabog.dk og vælg et kapitel fra grundbogen, "
              + "f.eks https://javabog.dk/OOP/kapitel3.jsp ");
      Linkify.addLinks(tv, Linkify.ALL);
      setContentView(tv);
    } else {
      // Ok, der var en URL med i intentet
      Toast.makeText(this, "AndroidElementer viser\n" + urlFraIntent, Toast.LENGTH_LONG).show();
      Toast.makeText(this, "Intent var\n" + i, Toast.LENGTH_LONG).show();

      WebView webView = new WebView(this);
      webView.loadUrl(urlFraIntent);

      setContentView(webView);
    }
  }
}
