package se.mah.da401a_assignment_2;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class AuthFragment extends Fragment {

  private static final String TAG = "AuthFragment";

  EditText username, password;

  public static AuthFragment newInstance() {
    AuthFragment fragment = new AuthFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  public AuthFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_auth, container, false);

    username = (EditText) root.findViewById(R.id.fragment_auth_username);
    password = (EditText) root.findViewById(R.id.fragment_auth_password);

    Button btn = (Button) root.findViewById(R.id.fragment_auth_login_button);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Firebase ref = new Firebase("https://da401a.firebaseio.com");

        ref.authWithPassword(username.getText().toString(), password.getText().toString(),
            new Firebase.AuthResultHandler() {
              @Override
              public void onAuthenticated(AuthData authData) {
                getFragmentManager().beginTransaction().replace(R.id.container, GroupFragment.newInstance(), "group").commit();

                SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("uid", authData.getUid());
                edit.commit();
              }

              @Override
              public void onAuthenticationError(FirebaseError error) {
                // Handle errors
                Log.e(TAG, error.getMessage());
                Log.d(TAG, "Error logging in!");
              }
            });
      }
    });


    btn = (Button) root.findViewById(R.id.fragment_auth_register_button);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Firebase ref = new Firebase("https://da401a.firebaseio.com");
        if(username.getText().toString() != null)
        ref.createUser(username.getText().toString(), password.getText().toString(), new Firebase.ResultHandler() {
          @Override
          public void onSuccess() {
            Toast.makeText(getActivity(), "User created, you can now log in!", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onError(FirebaseError error) {
            // Handle errors
            Log.e(TAG, error.getMessage());

            Log.d(TAG, "Error registering!");
          }
        });
      }
    });

    return root;
  }


}
