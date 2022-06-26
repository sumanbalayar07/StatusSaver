package com.balayar.statussaver;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private AlertDialog dialog;
ViewPager viewPager;
TabLayout tabLayout;
    private long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
// THIS BELOW CODE ADDS THE TAB IN THE TABLAYOUT
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.image)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.video)));
        //tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.save)));
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
public void CreateNewDialog()
{
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    final View popupview=getLayoutInflater().inflate(R.layout.custompopup,null);
    dialogBuilder.setView(popupview);
    dialog= dialogBuilder.create();
    dialog.show();
}
// THIS IS MY BACK BUTTON IN ANDROID
    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish();
            moveTaskToBack(true);
        } else {
            Snackbar.make(viewPager, "Press Again to Exit", Snackbar.LENGTH_LONG).show();
            back_pressed = System.currentTimeMillis();
        }
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        if (!(menu instanceof MenuBuilder)) {
            return true;
        }
        MenuBuilder m = (MenuBuilder) menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.contactUs:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailTo"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"orthohydrogen123@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Status Saver");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Write down your message here");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                return true;

            case R.id.menu_privacyPolicy:
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                return true;

            case R.id.menu_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;

            case R.id.menu_whatsapp:
                Intent send = new Intent();
                send.setAction(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_TEXT, "HELLO");
                send.setType("text/plain");
                send.setPackage("com.whatsapp");
                startActivity(send);
                return true;

            case R.id.menu_question:
                CreateNewDialog();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
