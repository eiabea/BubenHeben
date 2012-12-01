package at.eiabea.bubenheben;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView cardView;
	private int currentCard = 0;
	private int bubenCount = 0;
	private List<Integer> cardList = new ArrayList<Integer>();
	private List<Integer> bubenList = new ArrayList<Integer>();
	private SharedPreferences settings = null;
	private Builder dlgTutorial = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();

		setListener();

		initCardList();
		
		shuffleCards();
		
		settings = this.getPreferences(Activity.MODE_PRIVATE);

	}

	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	        .setTitle(getStringFromRes(R.string.string_close_app))
	        .setMessage(getStringFromRes(R.string.string_do_you_really_want_to_quit))
	        .setNegativeButton(getStringFromRes(R.string.string_no), null)
	        .setPositiveButton(getStringFromRes(R.string.string_yes), new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface arg0, int arg1) {
	                MainActivity.super.onBackPressed();
	            }
	        }).create().show();
	}

	@Override
	protected void onResume() {
	     super.onResume();
	        if (settings.getBoolean("firstrun", true)) {
	        	dlgTutorial.create().show();
	        	settings.edit().putBoolean("firstrun", false).commit();
	        }
	    }

	private void initCardList() {
		cardList.add(R.drawable.c10);
		cardList.add(R.drawable.ca);
		cardList.add(R.drawable.cb);
		cardList.add(R.drawable.cd);
		cardList.add(R.drawable.ck);
		cardList.add(R.drawable.h10);
		cardList.add(R.drawable.ha);
		cardList.add(R.drawable.hb);
		cardList.add(R.drawable.hd);
		cardList.add(R.drawable.hk);
		cardList.add(R.drawable.k10);
		cardList.add(R.drawable.ka);
		cardList.add(R.drawable.kb);
		cardList.add(R.drawable.kd);
		cardList.add(R.drawable.kk);
		cardList.add(R.drawable.p10);
		cardList.add(R.drawable.pa);
		cardList.add(R.drawable.pb);
		cardList.add(R.drawable.pd);
		cardList.add(R.drawable.pk);
		
		bubenList.add(R.drawable.cb);
		bubenList.add(R.drawable.hb);
		bubenList.add(R.drawable.kb);
		bubenList.add(R.drawable.pb);
	}

	private void initUI() {
		cardView = (ImageView) findViewById(R.id.cardView);
		cardView.setImageDrawable(getResources().getDrawable(R.drawable.back));
    	dlgTutorial = new AlertDialog.Builder(this)
        .setTitle(getStringFromRes(R.string.menu_description))
        .setIcon(getResources().getDrawable(R.drawable.ic_launcher))
        .setMessage(getStringFromRes(R.string.string_description))
        .setPositiveButton(getStringFromRes(R.string.string_got_it), null);
	}

	private void setListener() {
		cardView.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_description:
	        dlgTutorial.show();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onClick(View v) {
		play();
	}
	
	public void play(){
		if(currentCard<20){
			if(bubenList.indexOf(cardList.get(currentCard)) != -1){
				
				Builder builder = new AlertDialog.Builder(this);
				builder.setCancelable(false);
				builder.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
				builder.setPositiveButton(getStringFromRes(R.string.string_done), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				switch (bubenCount) {
				case 0:
					builder.setTitle(getStringFromRes(R.string.string_first_boy));
					builder.setMessage(getStringFromRes(R.string.string_fill_alkohol));
					//System.out.println("1. Bube");
					
					break;
				case 1:
					builder.setTitle(getStringFromRes(R.string.string_second_boy));
					builder.setMessage(getStringFromRes(R.string.string_fill_anti));
					//System.out.println("2. Bube");
					
					break;
				case 2:
					builder.setTitle(getStringFromRes(R.string.string_third_boy));
					builder.setMessage(getStringFromRes(R.string.string_try));
					//System.out.println("3. Bube");
					
					break;
				case 3:
					//System.out.println("4. Bube");
					builder.setTitle(getStringFromRes(R.string.string_fourth_boy));
					builder.setMessage(getStringFromRes(R.string.string_drain));
					builder.setPositiveButton("Neu mischen?", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							shuffleCards();
							currentCard = 0;
							bubenCount = 0;
							play();
						}
					});
					
					break;
					
				default:
					break;
				}
				AlertDialog dialog = builder.create();
				dialog.show();
				bubenCount++;
			}
			cardView.setImageDrawable(getResources().getDrawable(cardList.get(currentCard)));
			currentCard++;
		}else{
			System.out.println("durch");
		}
		
	}
	
	public void shuffleCards(){
		Collections.shuffle( cardList );
	}
	
	public String getStringFromRes(int id){
		if(id != 0){
			return getResources().getString(id);
		}
		return null;
	}

}
