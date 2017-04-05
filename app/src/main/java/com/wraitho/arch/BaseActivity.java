package com.wraitho.arch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wraitho.base.PresenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends PresenterActivity implements NavigationView.OnNavigationItemSelectedListener {

	@BindView(R.id.drawer_layout) DrawerLayout drawer;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.nav_view) NavigationView navigationView;

	/**
	 * Set Up {@link Toolbar} and {@link NavigationView}
	 * @param savedInstanceState -
	 */
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ButterKnife.bind(this);

		if (drawer == null) {
			throw new NullPointerException("Drawer layout is not set properly.");
		}

		if (toolbar == null) {
			throw new NullPointerException("Toolbar is not set properly");
		}

		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
}
