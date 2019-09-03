package lekt10_livscyklus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author Jacob Nordfalk
 */
public class LogAktivitet extends AppCompatActivity {

  /**
   * Denne klassevariabel vil tælle antallet af objekter (instanser af klassen)
   */
  static int instansNummer = 0;
  /**
   * I loggen skriver klassenavn#nummer foran hver log-sætniong.
   * F.eks LogAktivitet#1, LogAktivitet#2
   */
  String logNavn;

  public LogAktivitet() {
    instansNummer++;  // tæl nummeret op
    logNavn = this.getClass().getSimpleName() + "#" + instansNummer;
  }

  /**
   * Når aktiviteten skal oprettes
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(logNavn, "onCreate(" + savedInstanceState);
  }

  /**
   * Når aktiviteten er synlig
   */
  protected void onStart() {
    Log.d(logNavn, "onStart()");
    super.onStart();
  }

  /**
   * Når aktiviteten er i forgrunden
   */
  protected void onResume() {
    Log.d(logNavn, "onResume()");
    super.onResume();
  }

  /**
   * Når aktiviteten ikke mere er i forgrunden
   * Data der skal gemmes bør gemmes her - processen kan dræbes efter onPause()
   */
  protected void onPause() {
    Log.d(logNavn, "onPause()");
    super.onPause();
  }

  /**
   * Når aktiviteten ikke mere er synlig
   */
  protected void onStop() {
    Log.d(logNavn, "onStop()");
    super.onStop();
  }

  /**
   * Kaldes før onStart() hvis aktiviteten bliver genoptaget efter onStop()
   */
  protected void onRestart() {
    Log.d(logNavn, "onRestart()");
    super.onRestart();
  }

  /**
   * Når aktiviteten bliver nedlagt
   */
  protected void onDestroy() {
    Log.d(logNavn, "onDestroy()");
    super.onDestroy();
  }

  /**
   * Når aktiviteten skal gemme sin tilstand, fordi den måske bliver dræbt herefter.
   * Kaldes ikke hvis aktiviteten afslutter normalt (bruger trykker tilbage-knap eller
   * finish() kaldes
   */
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.d(logNavn, "onSaveInstanceState(outState=" + outState);
  }

  /**
   * Når aktiviteten skal genskabe sin tilstand sådan som den så ud på et tidligere
   * tidspunkt. Du kan bruge onCreate() til det samme,
   * og det er det samme Bundle-objekt der overføres
   */
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    Log.d(logNavn, "onRestoreInstanceState(" + savedInstanceState);
    // her genskabes indhold for alle views med id
    super.onRestoreInstanceState(savedInstanceState);
  }
}
