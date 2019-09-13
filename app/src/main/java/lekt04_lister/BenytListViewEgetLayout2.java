package lekt04_lister;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import dk.nordfalk.android.elementer.R;

public class BenytListViewEgetLayout2 extends AppCompatActivity implements OnItemClickListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String[] lande = {"Danmark", "Norge", "Sverige", "Finland", "Holland", "Italien", "Tyskland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand"};

    ArrayAdapter adapter = new ArrayAdapter(this, R.layout.lekt04_listeelement, R.id.listeelem_overskrift, lande) {
      @Override
      public View getView(int position, View cachedView, ViewGroup parent) {
        View view = super.getView(position, cachedView, parent);

        TextView beskrivelse = view.findViewById(R.id.listeelem_beskrivelse);
        beskrivelse.setText("Land nummer " + position);
        ImageView billede = view.findViewById(R.id.listeelem_billede);
        if (position % 3 == 2) {
          billede.setImageResource(android.R.drawable.sym_action_call);
        } else {
          billede.setImageResource(android.R.drawable.sym_action_email);
        }

        return view;
      }
    };

    ListView listView = new ListView(this);
    listView.setOnItemClickListener(this);
    listView.setAdapter(adapter);

    //listView.setDivider(getResources().getDrawable(android.R.drawable.divider_horizontal_dark));
    // Rød kasse omkring det valgte element
    listView.setSelector(android.R.drawable.ic_notification_overlay);

    setContentView(listView);
  }

  public void onItemClick(AdapterView<?> l, View v, int position, long id) {
    Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();
    //setResult(113, new Intent(position  eller noget andet der skan tilbage til kalderen));
    //finish();
  }
}
