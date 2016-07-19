package com.codedleaf.sylveryte.myemployeeattendanceregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FRAGMENT_CODE = "codedleafbro";
    private static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        com.getbase.floatingactionbutton.FloatingActionButton addSite=(com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.floating_add_site);
        com.getbase.floatingactionbutton.FloatingActionButton addDesignation=(com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.floating_add_designation);
        com.getbase.floatingactionbutton.FloatingActionButton addEmployee=(com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.floating_add_employee);


        if (addSite != null) {
            addSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(AdditionActivity.fetchIntent(HomeActivity.this,AdditionActivity.FRAGMENT_CODE_ADD_SITE),REQUEST_CODE);
                }
            });
        }

        if (addDesignation != null) {
            addDesignation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(AdditionActivity.fetchIntent(HomeActivity.this,AdditionActivity.FRAGMENT_CODE_ADD_DESIGNATION),REQUEST_CODE);
                }
            });
        }

        if (addEmployee != null) {
            addEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(AdditionActivity.fetchIntent(HomeActivity.this,AdditionActivity.FRAGMENT_CODE_ADD_EMPLOYEE),REQUEST_CODE);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }


        startFragment(SitesFragment.newInstance());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            Intent i=new Intent(this,DeleteMe.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();

        //clean up any fragments blah blah
        List<Fragment> frags=fm.getFragments();
        if(frags!=null)
        {

            for (Fragment frag:frags)
            {
                //did remove tostring after tag
                if(frag.getTag().equals(FRAGMENT_CODE))
                {
                    fm.beginTransaction().remove(frag).commit();
                }
            }
        }

        fm.beginTransaction()
                .add(R.id.home_fragment_container, fragment,FRAGMENT_CODE)
                .commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sites) {
            startFragment(SitesFragment.newInstance());
        } else if (id == R.id.nav_employees) {
            startFragment(EmployeeFragment.newInstance());
        } else if (id == R.id.nav_designation) {
            startFragment(DesignationFragment.newInstance());

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
