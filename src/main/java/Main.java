import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;
import org.firmata4j.firmata.FirmataDevice;

public class Main {

	private static final int PIN_NUMBER = 13;
	private static final long DELAY_MILISECONDS = 1000;
	private static final long LOOP = 10;

	public static void main(String[] args) throws IOException, InterruptedException {
		IODevice device = new FirmataDevice("COM3"); // construct the Firmata device instance using the name of a port

		device.start(); // initiate communication to the device
		device.ensureInitializationIsDone(); // wait for initialization is done

		Pin pin = device.getPin(PIN_NUMBER);
		pin.setMode(Mode.PWM);

		for (int i = 0; i < LOOP; i++) {
			Thread.sleep(DELAY_MILISECONDS);
			pin.setValue(1); // on led

			Thread.sleep(DELAY_MILISECONDS);
			pin.setValue(0); // off led
		}

		device.stop(); // stop communication to the device
	}

}
