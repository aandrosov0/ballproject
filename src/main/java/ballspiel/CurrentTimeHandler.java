package ballspiel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeHandler implements Runnable {
    public interface OnTimeChangeListener {
        void change(String time);
    }

    private final OnTimeChangeListener onTimeChanged;
    private final String pattern;

    public CurrentTimeHandler(OnTimeChangeListener onTimeChanged, String pattern) {
        this.onTimeChanged = onTimeChanged;
        this.pattern = pattern;
    }

    @Override
    public synchronized void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        while (true) {
            LocalTime currentTime = LocalTime.now();
            String time = formatter.format(currentTime);
            onTimeChanged.change(time);
            try {
                wait(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
