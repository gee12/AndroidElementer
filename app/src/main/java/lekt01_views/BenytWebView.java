package lekt01_views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class BenytWebView extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    WebView webView = new WebView(this);
    webView.loadUrl("https://javabog.dk");
    setContentView(webView);
  }
}
