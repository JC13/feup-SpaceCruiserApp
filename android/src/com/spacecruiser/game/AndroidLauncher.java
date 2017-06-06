package com.spacecruiser.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.view.WindowManager;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new SpaceCruiser(), config);
	}
}
