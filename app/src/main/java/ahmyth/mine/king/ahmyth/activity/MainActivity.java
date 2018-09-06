package ahmyth.mine.king.ahmyth.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ahmyth.mine.king.ahmyth.R;
import ahmyth.mine.king.ahmyth.service.MainService;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MainService.class));
        finish();
    }

}
