package com.pefoley.weedroid.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.pefoley.weedroid.message.Message;

import com.pefoley.weedroid.command.InitCommand;

import java.io.IOException;
import java.net.Socket;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void sendInit() {
        Socket s = null;
        try {
            //FIXME: get from settings.
            s = new Socket("pefoley.com", 8001);
            WeeChatRelay w = new WeeChatRelay(s);
            w.sendCommand(new InitCommand("bob"));
            List<Message> m = w.processPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
