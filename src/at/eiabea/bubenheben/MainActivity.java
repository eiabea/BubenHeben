package at.eiabea.bubenheben;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView cardView;
	private int currentCard = 0;
	private int bubenCount = 0;
	private List<Integer> cardList = new ArrayList<Integer>();
	private List<Integer> bubenList = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();

		setListener();

		initCardList();
		shuffleCards();
		play();

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
		// TODO Auto-generated method stub
		cardView = (ImageView) findViewById(R.id.cardView);

	}

	private void setListener() {
		// TODO Auto-generated method stub
		cardView.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
				builder.setPositiveButton("Fertig", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				switch (bubenCount) {
				case 0:
					builder.setTitle("1. Bube");
					builder.setMessage("Alkohol einschenken");
					System.out.println("1. Bube");
					
					break;
				case 1:
					builder.setTitle("2. Bube");
					builder.setMessage("Anti einschenken");
					System.out.println("2. Bube");
					
					break;
				case 2:
					builder.setTitle("3. Bube");
					builder.setMessage("Kosten");
					System.out.println("3. Bube");
					
					break;
				case 3:
					System.out.println("4. Bube");
					builder.setTitle("4. Bube");
					builder.setMessage("Austrinken");
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

}
