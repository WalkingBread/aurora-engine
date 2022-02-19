package skorupinski.aurora.events.mouse;

import java.util.HashMap;

public enum MouseButton {
    RIGHT,
    LEFT,
    SCROLL;

    private static HashMap<MouseButton, Integer> buttons = new HashMap<>() {{
        put(RIGHT, 3);
        put(LEFT, 1);
        put(SCROLL, 2);
    }}; 

    public static MouseButton getButton(int code) {
        MouseButton button = null;

        for (HashMap.Entry<MouseButton, Integer> entry : buttons.entrySet()) {
            if(entry.getValue() == code) {
                button = entry.getKey();
            }

        }
        return button;
    }

    public static int getCode(MouseButton button) {
        return buttons.get(button);
    }
}