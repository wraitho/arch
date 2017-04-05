package com.wraitho.arch.feature.home;

import com.wraitho.arch.ActivityComponent;
import com.wraitho.arch.ActivityModule;
import com.wraitho.arch.ArchApplication;
import com.wraitho.arch.ArchComponent;
import com.wraitho.arch.BaseActivity;
import com.wraitho.arch.DaggerActivityComponent;
import com.wraitho.arch.R;

public class HomeActivity extends BaseActivity {

	private ActivityComponent activityComponent;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_home);
	}

	@Override
	protected void injectDependencies() {
		activityComponent = DaggerActivityComponent.builder()
				.archComponent(getArchComponent())
				.activityModule(new ActivityModule())
				.build();
		activityComponent.inject(this);
	}

	@Override
	protected void initialisePresenters() {
		// not yet
	}

	private ArchComponent getArchComponent() {
		return ArchApplication.get(this).getArchComponent();
	}

	public ActivityComponent getActivityComponent() {
		return activityComponent;
	}
}
