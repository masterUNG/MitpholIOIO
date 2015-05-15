package appewtc.masterung.mitpholioio;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ToggleButton;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


public class MainActivity extends IOIOActivity{

    private ToggleButton myToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToggleButton = (ToggleButton) findViewById(R.id.toggleButton);

    }   // onCreate


    //Class Looper
    class Looper extends BaseIOIOLooper {

        private DigitalOutput myOutput;

        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
           // super.setup();

            myOutput = ioio_.openDigitalOutput(0, false);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Connected OK", Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
           // super.loop();

            myOutput.write(!myToggleButton.isChecked());

            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }


        }
    }   // Looper Class

    protected IOIOLooper createIOIOLooper() {

        return new Looper();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
