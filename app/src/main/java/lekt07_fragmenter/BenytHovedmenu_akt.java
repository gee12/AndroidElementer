package lekt07_fragmenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import dk.nordfalk.android.elementer.R;


public class BenytHovedmenu_akt extends AppCompatActivity {

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_ACTION_BAR);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lekt04_fragmenter_manuel_nav);
/*
  Hvis der i layoutet i stedet for <FrameLayout... havde stået:
    <fragment
        android:name="lekt07_fragmenter.Hovedmenu_frag"
  ...ville fragmentet blive tilføjet af systemet og nedenstående kunne udelades
*/
    if (savedInstanceState == null) {
      Hovedmenu_frag fragment = new Hovedmenu_frag();
      getSupportFragmentManager().beginTransaction()
              .add(R.id.fragmentindhold, fragment)  // tom container i layout
              .commit();
    }

    // Man kan trykke på app-ikonet i øverste venstre hjørne
    // (og det betyder at brugeren vil navigere op i hierakiet)
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      // brugeren vil navigere op i hierakiet - afslut aktiviteten
      Toast.makeText(this, "OP blev trykket, afslutter "
              + getSupportFragmentManager().getBackStackEntryCount()
              + " niveauer nede i hierakiet", Toast.LENGTH_SHORT).show();
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


}



