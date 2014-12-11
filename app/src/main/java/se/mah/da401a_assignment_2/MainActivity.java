package se.mah.da401a_assignment_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;


public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Firebase.setAndroidContext(this);

    Firebase ref = new Firebase("https://da401a.firebaseio.com");

      AuthData authData = ref.getAuth();
    if (authData != null) {
      getFragmentManager().beginTransaction().replace(R.id.container, GroupFragment.newInstance()).commit();

      SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
      SharedPreferences.Editor edit = prefs.edit();
      edit.putString("uid", authData.getUid());
      edit.commit();
    } else {
      getFragmentManager().beginTransaction().replace(R.id.container, AuthFragment.newInstance()).commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
