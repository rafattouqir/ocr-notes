package com.plug.voice;

import java.util.ArrayList;

import keendy.projects.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SpeechToTextActivity extends Activity implements OnClickListener{
	
	private static final int REQUEST_CODE = 1;
	private ListView wordsRecognized;
	private Button speakBttn;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.voice_record);
		
		wordsRecognized = (ListView) findViewById(R.id.wordsList);
		speakBttn = (Button) findViewById(R.id.speak_button);
		
		speakBttn.setOnClickListener(this);
		
	}
	
	 @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
       if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
       {
          //recognition results
           ArrayList<String> matches = data.getStringArrayListExtra(
                   RecognizerIntent.EXTRA_RESULTS);
           wordsRecognized.setAdapter(new ArrayAdapter<String>(this,
          		 android.R.layout.simple_list_item_1,
                   matches));
       }
       super.onActivityResult(requestCode, resultCode, data);
   }

	@Override
	public void onClick(View arg0) {
		//prompts a user for speech and sends it to a speech recognizer
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    startActivityForResult(intent, REQUEST_CODE);
		
	}

	
	
}
