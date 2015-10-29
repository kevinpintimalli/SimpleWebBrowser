package edu.temple.simplewebbrowser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private FrameLayout webFrame;
    private ArrayList<Fragment> fragmentArrayList;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webFrame = (FrameLayout) findViewById(R.id.webFrame);

        fragmentArrayList = new ArrayList<>();
        counter = 0;


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

        switch(id){
            case(R.id.newView):
                Fragment frag = WebViewFragment.newInstance();
                fragmentArrayList.add(frag);
                counter=fragmentArrayList.size();
                loadFragment(R.id.webFrame, frag, false);
                return true;
            case(R.id.prev):
                if(counter > 1){
                    counter--;
                    loadFragment(R.id.webFrame,fragmentArrayList.get(counter-1),false);
                }
                return true;
            case(R.id.next):
                if(counter < fragmentArrayList.size()){
                    loadFragment(R.id.webFrame,fragmentArrayList.get(counter),false);
                    counter++;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void loadFragment(int paneId,Fragment fragment,boolean placeOnBackStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(paneId, fragment);

        if(placeOnBackStack){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
