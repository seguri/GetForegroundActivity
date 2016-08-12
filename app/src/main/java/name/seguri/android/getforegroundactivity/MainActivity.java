package name.seguri.android.getforegroundactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private EditText mEditPackage;
    private EditText mEditClass;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditPackage = (EditText) findViewById(R.id.editPackageName);
        mEditClass = (EditText) findViewById(R.id.editClassName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem switchItem = menu.findItem(R.id.toggleservice);
        mSwitch = (Switch) switchItem.getActionView();
        // First time will initialize with default value
        boolean isSavedAsChecked = Utils.getSharedPref(this, R.string.sharedprefs_key1, false);
        mSwitch.setChecked(isSavedAsChecked);
        mSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            // Save new value
            Utils.setSharedPref(MainActivity.this, R.string.sharedprefs_key1, isChecked);
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View v) {
        Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    public void onNewIntent(View v) {
        String packageName = mEditPackage.getText().toString();
        String className = packageName + "." + mEditClass.getText().toString();
        startForeignActivity(packageName, className);
    }

    private void startForeignActivity(String packageName, String className) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName(packageName, className));
        startActivity(intent);
    }
}
