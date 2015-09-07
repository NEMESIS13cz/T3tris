package com.redstoner.nemes.t3tris.menu;

import java.util.ArrayList;

import com.redstoner.nemes.t3tris.gfx.FontMap;
import com.redstoner.nemes.t3tris.util.EnumKeyBind;
import com.redstoner.nemes.t3tris.util.Options;
import com.redstoner.nemes.t3tris.util.Constants;

public class OptionsMenu implements IMenu {

	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public OptionsMenu() {
		buttons.add(new BackButton(0.25f, 0.8f, 0.75f, 0.95f, this));
		buttons.add(new FramerateSlider(0.2f, 0.2f, 0.65f, 0.35f, this));
		buttons.add(new ResetFramerateButton(0.2f, 0.36f, 0.65f, 0.40f, this));
		buttons.add(new BindField(0.2f, 0.41f, 0.49f, 0.47f, this, EnumKeyBind.W));
		buttons.add(new BindField(0.2f, 0.48f, 0.49f, 0.54f, this, EnumKeyBind.A));
		buttons.add(new BindField(0.2f, 0.55f, 0.49f, 0.61f, this, EnumKeyBind.S));
		buttons.add(new BindField(0.2f, 0.62f, 0.49f, 0.68f, this, EnumKeyBind.D));
		buttons.add(new BindField(0.2f, 0.69f, 0.49f, 0.75f, this, EnumKeyBind.SPACE));

		buttons.add(new BindField(0.51f, 0.41f, 0.8f, 0.47f, this, EnumKeyBind.UP));
		buttons.add(new BindField(0.51f, 0.48f, 0.8f, 0.54f, this, EnumKeyBind.LEFT));
		buttons.add(new BindField(0.51f, 0.55f, 0.8f, 0.61f, this, EnumKeyBind.DOWN));
		buttons.add(new BindField(0.51f, 0.62f, 0.8f, 0.68f, this, EnumKeyBind.RIGHT));
		buttons.add(new BindField(0.51f, 0.69f, 0.8f, 0.75f, this, EnumKeyBind.ESCAPE));
	}
	
	public void render(int w, int h) {
		for (Button b : buttons) {
			b.render(w, h);
		}
		int fps = Options.framerateLimit;
		FontMap.drawString(0.70 * w, 0.25 * h, h / 80.0f, fps == Integer.MAX_VALUE ? "Unlimited" : String.valueOf(fps), Constants.BUTTON_TEXT_COLOR, false);
	}
	
	public void update(int x, int y, int w, int h) {
		for (Button b : buttons) {
			b.update(x, y, w, h);
		}
	}

}
